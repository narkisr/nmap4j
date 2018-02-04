package org.nmap4j.data.host.scripts;

public class Script {
    public static final String TAG = "script";
    private String id;
    private String output;

    public void setId(String a1) {
        this.id = a1;
    }

    public void setOutput(String value) {
        this.output = value;
    }
}
