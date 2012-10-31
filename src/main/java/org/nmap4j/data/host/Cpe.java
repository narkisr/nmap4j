package org.nmap4j.data.host;

/**
 * A representation of the Common Platform Enumeration (CPE) in Nmap output.
 * <p>
 * The CPE data is documented here:
 * <p>
 * http://nmap.org/book/output-formats-cpe.html
 * <p>
 * But the important bits are that it is a colon delimited string. For example:
 * <p>
 * &ltcpe&gt;cpe:/o:apple:mac_os_x:10.7&lt;/cpe&gt;
 * <p>
 * This data is variable in length. This data can be a child of service or 
 * osclass.
 * 
 * @author jon.svede
 *
 */
public class Cpe {

	String cpeData ;

	public String getCpeData() {
		return cpeData;
	}

	public void setCpeData(String cpeData) {
		this.cpeData = cpeData;
	}
	
}
