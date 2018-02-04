package org.nmap4j.data.host.scripts;

import java.util.LinkedList;
import java.util.List;

public class HostScript {
    public static final String TAG = "hostscript";

    private LinkedList<Script> scripts = new LinkedList<>();

    public void addScript(Script script) {
        scripts.add(script);
    }

    public List<Script> getScripts() {
        return scripts;
    }

    public Script getScript(String s) {
        for (Script script : scripts) {
            if (script.getId().equals(s)) {
                return script;
            }

        }
        return null;
    }
}
