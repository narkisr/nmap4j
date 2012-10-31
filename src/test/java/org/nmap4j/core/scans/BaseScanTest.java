package org.nmap4j.core.scans;

import static org.junit.Assert.*;

import org.junit.Test;
import org.nmap4j.core.flags.Flag;
import org.nmap4j.core.nmap.ExecutionResults;
import org.nmap4j.core.nmap.NMapExecutionException;
import org.nmap4j.core.nmap.NMapInitializationException;
import org.nmap4j.core.scans.BaseScan;
import org.nmap4j.core.scans.ParameterValidationFailureException;
import org.nmap4j.core.scans.IScan.OutputType;
import org.nmap4j.core.scans.IScan.TimingFlag;

public class BaseScanTest {

	@Test
	public void testSimpleScan() {
		
		BaseScan baseScan = new BaseScan( "/usr/local") ;
		
		baseScan.includeHost( "localhost" ) ;
		baseScan.addPorts(new int[]{ 22,80,443,3306} ) ;
		baseScan.addFlag( Flag.OS_DETECTION ) ;
		baseScan.setTiming( TimingFlag.AGGRESSIVE ) ;
		
		System.out.println( baseScan.getArgumentProperties().getFlags() ) ;
		System.out.println( baseScan.getNMapProperties().getFullyFormattedCommand() ) ;
		
		try {
			ExecutionResults results = baseScan.executeScan() ;
			System.out.println( results.getExecutedCommand() ) ;
			System.out.println( results.getOutput() ) ;
			if( results.hasErrors() ) {
				System.out.println( "Errors: " +  results.getErrors() ) ;
			} else {
				System.out.println( "Results: " +  results.getOutput() ) ;
			}
			
			
			
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
