package org.nmap4j.data.host.scripts;

import java.util.LinkedHashMap;

public class Script {
    public static final String TAG = "script";
    public static final String ELEMTAG = "elem";

    private String id;
    private String output;
    private LinkedHashMap<String, String> elems = new LinkedHashMap<>();

    public String getId() {
        return id;
    }

    public void setId(String scriptId) {
        this.id = scriptId;
    }

    public void setOutput(String outputValue) {
        this.output = outputValue;
    }

    public void addElem(String elemkey, String ch) {
        if (elems.containsKey(elemkey)) {
            elems.put(elemkey, elems.get(elemkey) + ch);
        }
        elems.put(elemkey, ch);
    }

    public LinkedHashMap<String, String> getElems() {
        return elems;
    }

    public String getElem(String key) {
        return elems.get(key);
    }

}
