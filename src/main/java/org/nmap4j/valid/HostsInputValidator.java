package org.nmap4j.valid;

import com.google.common.net.InternetDomainName;

import java.util.regex.Pattern;

public class HostsValidator {
    private final static String IP_REGEX = "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])";

    private final static String IP_CIDR = "(\\/([0-9]|[1-2][0-9]|3[0-2]))";

    public boolean valid(String host) {
        return InternetDomainName.isValid(host) || ip(host) || subnet(host);
    }


    private boolean ip(final String ip) {
        final Pattern PATTERN = Pattern.compile(IP_REGEX + "$");
        return PATTERN.matcher(ip).matches();
    }

    private boolean subnet(final String subnet) {
        final Pattern PATTERN = Pattern.compile(IP_REGEX + IP_CIDR);
        return PATTERN.matcher(subnet).matches();
    }

}
