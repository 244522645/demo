package com.util;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;



public class TrustAnyVerifier implements HostnameVerifier{
	
		 
	    public boolean verify(String hostname, SSLSession session) {
	        System.out.println(">>> " + hostname + " " + session);
	        return true;
	    }
	 
	
}
