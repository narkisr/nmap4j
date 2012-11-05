package org.nmap4j.model;

/**
 * A class to flatten the port data structure that comes out of Nmap.
 * 
 * 
 * @author jon.svede
 *
 */
public class Port {

	private String protocol ;
	private int portNumber ;
	private String state ;
	private String reason ;
	private String reasonTtl ;
	private String serviceName ;
	private String method ;
	private String conf ;

	public Port( String prtcl, int portNum ) {
		protocol = prtcl ;
		portNumber = portNum ;
	}
	
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public int getPortNumber() {
		return portNumber;
	}
	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getReasonTtl() {
		return reasonTtl;
	}
	public void setReasonTtl(String reasonTtl) {
		this.reasonTtl = reasonTtl;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getConf() {
		return conf;
	}
	public void setConf(String conf) {
		this.conf = conf;
	}
}
