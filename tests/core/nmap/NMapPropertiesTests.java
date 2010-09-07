package core.nmap;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.nmap4j.core.nmap.NMapProperties;

public class NMapPropertiesTests {
	
	private NMapProperties fixture ;
	
	@Before
	public void setUp() {
		fixture = new NMapProperties() ;
	}
	
	@After
	public void tearDown() {
		fixture = null ;
	}
	
	@Test
	public void testSetPath() {
		String path = "/path/to/nmap" ;
		
		fixture.setPath( path ) ;
		
		if( fixture.getPath() == null ) {
			fail() ;
		}
		
		if( !fixture.getPath().equals( path ) ) {
			fail() ;
		}
	}

	@Test
	public void testGetShareDir() {
		String path = "/path/to/nmap" ;
		
		fixture.setPath( path ) ;
		
		if( fixture.getShareDir() == null ) {
			fail() ;
		}
		
		if( !fixture.getShareDir().startsWith( path ) ) {
			fail() ;
		}
	}
	
	@Test
	public void testGetBinDir() {
		String path = "/path/to/nmap" ;
		
		fixture.setPath( path ) ;
		
		if( fixture.getBinDir() == null ) {
			fail() ;
		}
		
		if( !fixture.getBinDir().startsWith( path ) ) {
			fail() ;
		}		
	}
	
	@Test
	public void testFullyFormattedCommand() {
		String path = "/path/to/nmap" ;
		
		fixture.setPath( path ) ;

		if( fixture.getFullyFormattedCommand() == null ) {
			fail() ;
		}
		
		///usr/local/bin/nmap --datadir /usr/local/share/nmap
		String verificationPath = path + File.separator + "bin" + File.separator + "nmap " + 
		   "--datadir " + path + File.separator + "share" + File.separator + "nmap";
		assertEquals( verificationPath, fixture.getFullyFormattedCommand() ) ;
	}
	
	@Test
	public void testStringConstructor() {
		String path = "/path/to/nmap" ;
		
		fixture = new NMapProperties( path ) ;
		
		assertNotNull( fixture.getPath() ) ;
		assertEquals( path, fixture.getPath() ) ;
	
	}
}
