package org.nmap4j.core.valid;

import org.junit.Test;
import org.nmap4j.valid.HostsInputValidator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidationTest {

    @Test
    public void validHosts() {
        assertTrue(new HostsInputValidator().valid("8.8.4.3"));
        assertTrue(new HostsInputValidator().valid("8.8.4.3/32"));
        assertTrue(new HostsInputValidator().valid("google.com"));
    }

    @Test
    public void nonValidHosts() {
        boolean b;
        assertFalse(new HostsInputValidator().valid("8.8.4"));
        assertFalse(new HostsInputValidator().valid("8.8.4.3/322"));
        assertFalse(new HostsInputValidator().valid("google.com.8.8.4.3"));
    }
}
