/*
 * Copyright (c) 2010, nmap4j.org
 *
 * All rights reserved.
 *
 * This license covers only the Nmap4j library.  To use this library with
 * Nmap, you must also comply with Nmap's license.  Including Nmap within
 * commercial applications or appliances generally requires the purchase
 * of a commercial Nmap license (see http://nmap.org/book/man-legal.html).
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 *
 *    * Redistributions of source code must retain the above copyright notice, 
 *      this list of conditions and the following disclaimer.
 *    * Redistributions in binary form must reproduce the above copyright 
 *      notice, this list of conditions and the following disclaimer in the 
 *      documentation and/or other materials provided with the distribution.
 *    * Neither the name of the nmap4j.org nor the names of its contributors 
 *      may be used to endorse or promote products derived from this software 
 *      without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 */
package org.nmap4j.data.host.ports;

import org.nmap4j.data.nmaprun.host.ports.port.Service;
import org.nmap4j.data.nmaprun.host.ports.port.State;

public class Port {
	
	public final static String PORT_TAG = "port" ;
	
	public final static String PROTOCOL_ATTR = "protocol" ;
	public final static String PORTID_ATTR = "portid" ;
	
	
	private String protocol ;
	private long portId ;
	
	private State state ;
	private Service service ;

	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public long getPortId() {
		return portId;
	}
	public void setPortId(long portId) {
		this.portId = portId;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	@Override
	public String toString() {
		return "Port [portId=" + portId + ", protocol=" + protocol + ",service=" + service + "]";
	}
	
}
