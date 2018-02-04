package org.nmap4j.parsers;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.nmap4j.core.flags.Flag;
import org.nmap4j.core.nmap.ExecutionResults;
import org.nmap4j.core.nmap.NMapExecutionException;
import org.nmap4j.core.nmap.NMapInitializationException;
import org.nmap4j.core.scans.BaseScan;
import org.nmap4j.core.scans.IScan.TimingFlag;
import org.nmap4j.core.scans.ParameterValidationFailureException;
import org.nmap4j.data.NMapRun;
import org.nmap4j.data.host.Cpe;
import org.nmap4j.data.host.os.OsClass;
import org.nmap4j.data.host.scripts.HostScript;
import org.nmap4j.data.host.scripts.Script;
import org.nmap4j.data.nmaprun.Host;
import org.nmap4j.parser.OnePassParser;
import org.nmap4j.parser.events.NMap4JParserEventListener;
import org.nmap4j.parser.events.ParserEvent;
import test.constants.IConstants;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.junit.Assert.fail;

public class OnePassParserTest implements IConstants {

    String fileName = "nmap-xml/ms-vscan.xml";
    String smbFileName = "nmap-xml/SMB-os-discovery_CPE.xml";
    int count = 0;


    public void testOnePass() {
        OnePassParser opp = new OnePassParser();

        NMapRun nmapRun = null;
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);

            String fileAsString = IOUtils.toString(is);

            nmapRun = opp.parse(fileAsString, OnePassParser.STRING_INPUT);

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (nmapRun != null) {
            System.out.println("hosts count: " + nmapRun.getHosts().size());
        } else {
            System.out.println("nmapRun is null");
        }
    }

    @Test
    public void testOnePassWithSMBOutput() {


        OnePassParser opp = new OnePassParser();

        NMapRun nmapRun = null;
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(smbFileName);

            String fileAsString = IOUtils.toString(is);

            nmapRun = opp.parse(fileAsString, OnePassParser.STRING_INPUT);


        } catch (IOException e) {
            e.printStackTrace();
        }

        if (nmapRun != null) {
            System.out.println("hosts count: " + nmapRun.getHosts().size());
        } else {
            System.out.println("nmapRun is null");
        }
    }


    @Test
    public void testForPresenceOfCpeData() {

        System.out.println("start");

        String smbFileName = "nmap-xml/SMB-os-discovery_CPE.xml";

        OnePassParser opp = new OnePassParser();

        NMapRun nmapRun = null;
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(smbFileName);

            String fileAsString = IOUtils.toString(is);

            nmapRun = opp.parse(fileAsString, OnePassParser.STRING_INPUT);

            ArrayList<Host> hosts = nmapRun.getHosts();

            boolean foundAtLeastOneNotNullCpeObj = false;

            for (Host h : hosts) {
                if (h.getOs() != null) {
                    if (h.getOs().getOsClasses() != null) {
                        ArrayList<OsClass> osClasses = h.getOs().getOsClasses();
                        for (OsClass osClass : osClasses) {
                            ArrayList<Cpe> cpeData = osClass.getCpe();
                            for (Cpe cpe : cpeData) {
                                if (cpe != null) {
                                    if (cpe.getCpeData() != null) {
                                        System.out.println(cpe.getCpeData());
                                        foundAtLeastOneNotNullCpeObj = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (!foundAtLeastOneNotNullCpeObj) {
                fail();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        if (nmapRun != null) {
            System.out.println("hosts count: " + nmapRun.getHosts().size());
        } else {
            System.out.println("nmapRun is null");
        }
    }

    @Test
    public void testLocalHostScan() {
        BaseScan baseScan = new BaseScan("/usr/local");

        baseScan.includeHost("localhost");
        baseScan.addPorts(new int[]{3306});
        baseScan.addFlag(Flag.OS_DETECTION);
        baseScan.setTiming(TimingFlag.AGGRESSIVE);

        try {
            ExecutionResults results = baseScan.executeScan();
            System.out.println(results.getExecutedCommand());
            System.out.println(results.getOutput());
            if (results.hasErrors()) {
                System.out.println("Errors: " + results.getErrors());
            } else {
                System.out.println("Results: " + results.getOutput());
            }

            OnePassParser opp = new OnePassParser();
            NMapRun nmapRun = opp.parse(results.getOutput(), OnePassParser.STRING_INPUT);

            String output = nmapRun.getHosts().get(0).getPorts().getPorts().get(0).getState().getState();

            System.out.println("Port state: " + output);

        } catch (ParameterValidationFailureException e) {
            e.printStackTrace();
            fail();
        } catch (NMapExecutionException e) {
            e.printStackTrace();
            fail();
        } catch (NMapInitializationException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testAddingListener() {
        System.out.println("start");

        String smbFileName = "nmap-xml/SMB-os-discovery_CPE.xml";

        OnePassParser opp = new OnePassParser();

        NMapRun nmapRun = null;

        HostListener simpleListener = new HostListener();

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(smbFileName);

            String fileAsString = IOUtils.toString(is);

            opp.addListener(simpleListener);
            nmapRun = opp.parse(fileAsString, OnePassParser.STRING_INPUT);

            ArrayList<Host> hosts = nmapRun.getHosts();

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (nmapRun != null) {
            if (nmapRun.getHosts().size() != simpleListener.getHostCount()) {
                fail();
            }
        } else {
            fail();
        }
    }

    private class HostListener implements NMap4JParserEventListener {

        private int hostCount = 0;

        @Override
        public void parseEventNotification(ParserEvent event) {
            if (event.getPayload() instanceof Host) {
                hostCount++;
            }
        }

        public int getHostCount() {
            return hostCount;
        }


    }

    @Test
    public void testHostScript() {
        OnePassParser onePassParser = new OnePassParser();
        URL testFileUrl = getClass().getClassLoader().getResource(smbFileName);
        String testFilePath = testFileUrl.getFile();
        NMapRun nmap = onePassParser.parse(testFilePath, onePassParser.FILE_NAME_INPUT);
        for (Host host : nmap.getHosts()) {
            HostScript hostScripts = host.getHostScripts();
            if (hostScripts == null) {
                continue;
            }
            Script script = hostScripts.getScript("smb-os-discovery");
            LinkedHashMap<String, String> elems = script.getElems();
            if (elems.size() == 0) {
                continue;
            }
            String server = script.getElem("os");
            if (!server.equals("Windows 7 Professional 7600")) {
                throw new RuntimeException();
            }
            System.out.println("");
        }
        System.out.println("");
    }
}
