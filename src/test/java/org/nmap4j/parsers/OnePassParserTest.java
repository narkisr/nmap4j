package org.nmap4j.parsers;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.nmap4j.data.NMapRun;
import org.nmap4j.parser.OnePassParser;

import test.constants.IConstants;

public class OnePassParserTest implements IConstants {
	
	String fileName = "nmap-xml/ms-vscan.xml" ;
	int count = 0 ;
	
	@Test
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
}
