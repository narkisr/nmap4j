package parsers;

import org.junit.Test;
import org.nmap4j.data.NMapRun;
import org.nmap4j.data.nmaprun.Host;
import org.nmap4j.parser.OnePassParser;

import test.constants.IConstants;

public class OnePassParserTests implements IConstants {
	
	@Test
	public void testOnePass() {
		OnePassParser opp = new OnePassParser() ;
		NMapRun nmapRun = opp.parse( XML_FILE ) ;
		if( nmapRun != null ) {
			for( Host h: nmapRun.getHosts() ) {
				System.out.println( "host = " + h ) ;
 			}
			System.out.println( nmapRun ) ;
		} else {
			System.out.println( "nmapRun is null" ) ;
		}
	}
}
