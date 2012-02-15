package org.nmap4j.core.nmap;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.nmap4j.core.nmap.ExecutionResults;

public class ExecutionResultsTest {
	
	private ExecutionResults fixture ;
	
	@Before
	public void setUp() {
		fixture = new ExecutionResults() ;
	}

	@After
	public void tearDown() {
		fixture = null ;
	}
	
	@Test
	public void testSetErrors() {
		String errMsg = "A Test error string" ;
		fixture.setErrors( errMsg ) ;
		
		if( fixture.getErrors() == null ) {
			fail() ;
		} else {
			assertEquals( errMsg, fixture.getErrors() ) ;
		}
	}
	
	@Test
	public void testSetOutput() {
		String outputMsg = "A test output message" ;
		fixture.setOutput(outputMsg)  ;
		
		if( fixture.getOutput() == null ) {
			fail() ;
		} else {
			assertEquals( outputMsg, fixture.getOutput() ) ;
		}
		
	}
	
	@Test
	public void testTwoStringConstructor() {
		String errMsg = "A test err message" ;
		String outputMsg = "A test output message" ;
		
		fixture = new ExecutionResults(errMsg, outputMsg ) ;
		
		assertEquals( errMsg, fixture.getErrors())  ;
		assertEquals( outputMsg, fixture.getOutput() ) ;
		
	}
}
