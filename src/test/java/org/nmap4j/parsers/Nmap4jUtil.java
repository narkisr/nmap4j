package org.nmap4j.parsers;

import org.junit.Test;
import org.nmap4j.core.flags.Flag;
import org.nmap4j.core.nmap.ExecutionResults;
import org.nmap4j.core.scans.BaseScan;
import org.nmap4j.core.scans.IScan.TimingFlag;
import org.nmap4j.data.NMapRun;
import org.nmap4j.parser.OnePassParser;

public class Nmap4jUtil {
	
	@Test
	public void testOpen() throws Exception {
		System.out.println( "Is Port Open? " + isPortOpen( 3307, "localhost" ) ) ;
	}
	
	public static boolean isPortOpen( int port, String address ) throws Exception {
		
		BaseScan baseScan = new BaseScan( "/usr/local") ;
		
		baseScan.includeHost( address ) ;
		baseScan.addPorts(new int[]{ port } ) ;
		baseScan.addFlag( Flag.OS_DETECTION ) ;
		baseScan.setTiming( TimingFlag.AGGRESSIVE ) ;
		
		boolean open = false ;
	
		ExecutionResults results = baseScan.executeScan() ;
		System.out.println( results.getExecutedCommand() ) ;
		System.out.println( results.getOutput() ) ;
		if( results.hasErrors() ) {
			return false ;
		}
		
		OnePassParser opp = new OnePassParser() ;
		NMapRun nmapRun = opp.parse(results.getOutput(), OnePassParser.STRING_INPUT ) ;
		
		String output = nmapRun.getHosts().get(0).getPorts().getPorts().get(0).getState().getState() ;
		
		if( output != null ) {
			if( output.toLowerCase().equals( "open" ) ) {
				open = true ;
			}
		}
		
		return open ;
	}
}
