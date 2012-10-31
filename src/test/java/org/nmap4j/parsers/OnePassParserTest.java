package org.nmap4j.parsers;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.nmap4j.core.flags.Flag;
import org.nmap4j.core.nmap.ExecutionResults;
import org.nmap4j.core.nmap.NMapExecutionException;
import org.nmap4j.core.nmap.NMapInitializationException;
import org.nmap4j.core.scans.BaseScan;
import org.nmap4j.core.scans.IScan.TimingFlag;
import org.nmap4j.core.scans.ParameterValidationFailureException;
import org.nmap4j.data.NMapRun;
import org.nmap4j.data.host.Cpe;
import org.nmap4j.data.host.os.OsClass;
import org.nmap4j.data.nmaprun.Host;
import org.nmap4j.parser.OnePassParser;

import test.constants.IConstants;

public class OnePassParserTest implements IConstants {
	
	String fileName = "nmap-xml/ms-vscan.xml" ;
	int count = 0 ;
	
	
	public void testOnePass() {
		OnePassParser opp = new OnePassParser() ;
		
		NMapRun nmapRun = null ;
		try {
			InputStream is = getClass().getClassLoader().getResourceAsStream( fileName ) ;
			
			String fileAsString = IOUtils.toString( is ) ;
			
			nmapRun = opp.parse( fileAsString, OnePassParser.STRING_INPUT );
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if( nmapRun != null ) {
			System.out.println( "hosts count: " + nmapRun.getHosts().size() ) ;
		} else {
			System.out.println( "nmapRun is null" ) ;
		}
	}

	@Test
	public void testOnePassWithSMBOutput() {
		
		String smbFileName = "nmap-xml/SMB-os-discovery_CPE.xml" ;
		
		OnePassParser opp = new OnePassParser() ;
		
		NMapRun nmapRun = null ;
		try {
			InputStream is = getClass().getClassLoader().getResourceAsStream( smbFileName ) ;
			
			String fileAsString = IOUtils.toString( is ) ;
			
			nmapRun = opp.parse( fileAsString, OnePassParser.STRING_INPUT );
			
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if( nmapRun != null ) {
			System.out.println( "hosts count: " + nmapRun.getHosts().size() ) ;
		} else {
			System.out.println( "nmapRun is null" ) ;
		}
	}

	
	@Test
	public void testForPresenceOfCpeData() {
		
		System.out.println( "start") ;
		
		String smbFileName = "nmap-xml/SMB-os-discovery_CPE.xml" ;
		
		OnePassParser opp = new OnePassParser() ;
		
		NMapRun nmapRun = null ;
		try {
			InputStream is = getClass().getClassLoader().getResourceAsStream( smbFileName ) ;
			
			String fileAsString = IOUtils.toString( is ) ;
			
			nmapRun = opp.parse( fileAsString, OnePassParser.STRING_INPUT );
			
			ArrayList<Host> hosts = nmapRun.getHosts() ;
			
			boolean foundAtLeastOneNotNullCpeObj = false ;
			
			for( Host h : hosts ) {
				if( h.getOs() != null ) {
					if( h.getOs().getOsClasses() != null ) {
						ArrayList<OsClass> osClasses  = h.getOs().getOsClasses() ;
						for( OsClass osClass : osClasses ) {
							ArrayList<Cpe> cpeData = osClass.getCpe() ;
							for( Cpe cpe : cpeData ) {
								if( cpe != null ) {
									if( cpe.getCpeData() != null ) {
										System.out.println( cpe.getCpeData() ) ;
										foundAtLeastOneNotNullCpeObj = true ;
									}
								}
							}
						}
					}
				}
			}
			
			if( !foundAtLeastOneNotNullCpeObj ) {
				fail() ;
			}
			
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if( nmapRun != null ) {
			System.out.println( "hosts count: " + nmapRun.getHosts().size() ) ;
		} else {
			System.out.println( "nmapRun is null" ) ;
		}		
	}
	
	@Test
	public void testLocalHostScan() {
		BaseScan baseScan = new BaseScan( "/usr/local") ;
		
		baseScan.includeHost( "localhost" ) ;
		baseScan.addPorts(new int[]{ 3306} ) ;
		baseScan.addFlag( Flag.OS_DETECTION ) ;
		baseScan.setTiming( TimingFlag.AGGRESSIVE ) ;
		
		try {
			ExecutionResults results = baseScan.executeScan() ;
			System.out.println( results.getExecutedCommand() ) ;
			System.out.println( results.getOutput() ) ;
			if( results.hasErrors() ) {
				System.out.println( "Errors: " +  results.getErrors() ) ;
			} else {
				System.out.println( "Results: " +  results.getOutput() ) ;
			}
			
			OnePassParser opp = new OnePassParser() ;
			NMapRun nmapRun = opp.parse(results.getOutput(), OnePassParser.STRING_INPUT ) ;
			
			String output = nmapRun.getHosts().get(0).getPorts().getPorts().get(0).getState().getState() ;
			
			System.out.println( "Port state: " + output ) ;
			
		} catch (ParameterValidationFailureException e) {
			e.printStackTrace();
			fail() ;
		} catch (NMapExecutionException e) {
			e.printStackTrace();
			fail() ;
		} catch (NMapInitializationException e) {
			e.printStackTrace();
			fail() ;
		}
	}
}
