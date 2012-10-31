package org.nmap4j;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.nmap4j.core.nmap.NMapExecutionException;
import org.nmap4j.core.nmap.NMapInitializationException;
import org.nmap4j.data.NMapRun;

public class Nmap4jTest {
	
	
	@Test
	public void basicNmap4jUsageTest() {
		
		try {
			String path = "/usr/local" ;
			
			Nmap4j nmap4j = new Nmap4j( path ) ;
			nmap4j.addFlags( "-sV -T5 -O -oX -" ) ;
			nmap4j.includeHosts( "localhost" ) ;
			nmap4j.execute() ;
			if( !nmap4j.hasError() ) {
				NMapRun nmapRun = nmap4j.getResult() ;
				String output = nmap4j.getOutput() ;
				if( output == null ) {
					fail() ;
				}
				String errors = nmap4j.getExecutionResults().getErrors() ;
				if (errors == null ) {
					fail() ;
				}
			}
		} catch (NMapInitializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail() ;
		} catch (NMapExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail() ;
		}
		
	}

}
