package org.nmap4j.data.host.scripts;

import java.util.LinkedList;

public class HostScript {
    public static final String TAG = "hostscript";
    private LinkedList<Script> scripts = new LinkedList<>();

    public void addScript(Script script) {
        scripts.add(script);
    }
}
