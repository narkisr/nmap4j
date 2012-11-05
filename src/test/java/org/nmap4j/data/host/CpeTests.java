package org.nmap4j.data.host;

import static org.junit.Assert.fail;

import org.junit.Test;

public class CpeTests {

	private String cpeSimpleLinux = "cpe:/o:linux:kernel:2.6" ;
	private String cpeSimpleWindows = "cpe:/o:microsoft:windows_xp::sp3" ;
	
	//<part>:<vendor>:<product>:<version>:<update>:<edition>:<language>
	
	@Test
	public void testCpeSimpleLinux() {
		Cpe cpe = new Cpe( cpeSimpleLinux ) ;
		testParsedData( cpe, "o", "linux", "kernel", "2.6", null, null,  null ) ;
	}
	
	@Test
	public void testCpeSimpleWindows() {
		Cpe cpe = new Cpe( cpeSimpleWindows ) ;
		testParsedData( cpe, "o", "microsoft", "windows_xp", "", "sp3", null, null ) ;
	}
	
	private void testParsedData( Cpe cpe, String part, String vendor, String product, 
			                      String version, String update, String edition, 
			                      String language ) {

		if( cpe.getPart() != null && !cpe.getPart().equals( part ) ) {
			fail( "Part - expected: " + part + " found: " + cpe.getPart() ) ;
		}
		if( cpe.getVendor() != null && !cpe.getVendor().equals( vendor ) ) {
			fail( "Vendor - expected: " + vendor + " found: " + cpe.getVendor() ) ;
		}
		if( cpe.getProduct() != null && !cpe.getProduct().equals( product ) ) {
			fail( "Product - expected: " + product + " found: " + cpe.getProduct() ) ;
		}
		if( cpe.getVersion() != null && !cpe.getVersion().equals( version ) ) {
			fail("Version - expected: " + version + " found: " + cpe.getVersion() ) ;
		}
		if( cpe.getUpdate() != null && !cpe.getUpdate().equals( update ) ) {
			fail( "Update - expected: " +  update + " found: " + cpe.getUpdate() ) ;
		}
		if( cpe.getEdition() != null && !cpe.getEdition().equals( edition ) ) {
			fail("Edition - expected: " + edition + " found: " + cpe.getEdition() ) ;
		}
		if( cpe.getLanguage() != null && !cpe.getLanguage().equals( language ) ) {
			fail( "Language - expected: " + language + " found: " + cpe.getLanguage() ) ;
		}
	}
}
