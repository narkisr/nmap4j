package org.nmap4j.core.flags;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedHashMap;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.nmap4j.core.flags.ArgumentProperties;
import org.nmap4j.core.flags.Flag;

public class ArgumentPropertiesTest {
	
	private ArgumentProperties fixture ;
	
	@Before
	public void setUp() {
		fixture = new ArgumentProperties() ;
	}
	
	@After
	public void tearDown() {
		fixture = null ;
	}
	
	@Test
	public void testAddFlagOneFlagArg() {
		fixture.addFlag( Flag.VERSION_ALL ) ;
		
		LinkedHashMap<String, String> flags = fixture.getFlagMap() ;
		
		assertTrue( flags != null ) ;
		assertTrue( flags.size() == 1 ) ;
		assertTrue( flags.containsKey(  Flag.VERSION_ALL.toString()) ) ;
		
	}
	
	@Test
	public void testAddFlagFlagString() {
		fixture.addFlag( Flag.PORT_SPEC, "80,443" ) ;

		LinkedHashMap<String, String> flags = fixture.getFlagMap() ;
		
		assertTrue( flags != null ) ;
		assertTrue( flags.size() == 1 ) ;
		assertTrue( flags.containsKey( Flag.PORT_SPEC.toString() ) ) ;
		assertTrue( flags.get(Flag.PORT_SPEC.toString()).equals( "80,443" ) ) ; 	
	}

	@Test
	public void testAddFlagTwoStrings() {
		fixture.addFlag( Flag.PORT_SPEC.toString(), "80,443" ) ;
		fixture.addFlag( Flag.PORT_SPEC, "7001,8001" ) ;

		LinkedHashMap<String, String> flags = fixture.getFlagMap() ;
		
		assertTrue( flags != null ) ;
		assertTrue( flags.size() == 1 ) ;

		assertTrue( flags.containsKey( Flag.PORT_SPEC.toString() ) ) ;
		assertTrue( flags.get(Flag.PORT_SPEC.toString()).equals( "80,443,7001,8001" ) ) ; 	
		
	}
	
	@Test
	public void testGetFlags() {
		fixture.addFlag( Flag.PORT_SPEC, "80,443" ) ;
		fixture.addFlag( Flag.VERBOSE ) ;
		fixture.addFlag( Flag.INSANE_TIMING ) ;
		
		fixture.addIncludedHost( "192.168.1.1" ) ;
		fixture.addExcludedHost( "192.168.1.2" ) ;
		
		String flags = fixture.getFlags() ;
		
		assertTrue( flags.contains( "-p 80,443 -v -T5" ) ) ;
		assertTrue( flags.contains( "--exclude 192.168.1.2" ) ) ;
		assertTrue( flags.contains( "192.168.1.1"  ) ) ;
	}
	
	@Test
	public void testIncludeHosts() {
		fixture.addIncludedHost( "localhost" ) ;
		
		Set<String> hosts = fixture.getIncludedHosts() ;
		
		assertNotNull( hosts ) ;
		assertTrue( hosts.contains( "localhost" ) ) ;
	}
	
	@Test
	public void testRemovingIncludeHost() {
		fixture.addIncludedHost( "localhost" ) ;
		fixture.addIncludedHost( "127.0.0.1" ) ;
		fixture.addIncludedHost( "192.168.1.1" ) ;
		
		Set<String> hosts = fixture.getIncludedHosts() ;
		
		assertNotNull( hosts ) ;
		assertTrue( hosts.size() == 3 ) ;
		
		fixture.removeIncludedHost("localhost" ) ;
		
		Set<String> hostsMinusOne = fixture.getIncludedHosts() ;
		assertNotNull( hostsMinusOne ) ;
		assertTrue( hostsMinusOne.size() == 2 ) ;
		assertTrue( hostsMinusOne.contains( "127.0.0.1") && hostsMinusOne.contains( "192.168.1.1" ) ) ;
		
	}
	
	@Test
	public void testReplaceFlag() {
		fixture.addFlag( Flag.PORT_SPEC, "80,443" ) ;
		
		LinkedHashMap<String, String> flagMap = fixture.getFlagMap() ;
		
		assertNotNull( flagMap ) ;
		assertTrue( flagMap.size() == 1 ) ;
		assertEquals( "80,443", flagMap.get( Flag.PORT_SPEC.toString() )  ) ;
		
		fixture.replaceFlag(Flag.PORT_SPEC, "22,23,25" ) ;
		
		LinkedHashMap<String, String> flagMapReplaced = fixture.getFlagMap() ;

		assertNotNull( flagMapReplaced ) ;
		assertTrue( flagMapReplaced.size() == 1 ) ;
		assertEquals( flagMapReplaced.get( Flag.PORT_SPEC.toString()), "22,23,25" )  ;
	}
	
	@Test
	public void testGetIncludedHostsAsString() {
		fixture.addIncludedHost( "localhost" ) ;
		fixture.addIncludedHost( "127.0.0.1" ) ;
		fixture.addIncludedHost( "192.168.1.1" ) ;
		
		String hostsAsString = fixture.getIncludedHostsAsString() ;
		
		assertNotNull( hostsAsString ) ;
		assertTrue( hostsAsString.contains( "localhost" ) ) ;
		assertTrue( hostsAsString.contains( "127.0.0.1" ) ) ;
		assertTrue( hostsAsString.contains( "192.168.1.1" ) ) ;
		
	}
}
