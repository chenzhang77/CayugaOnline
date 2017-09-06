/**
* The Cayuga Dictionary project is to keep the language vibrant and alive 
* through immersion courses for adults and language daycare for children.
* 
* The project is Led by Dr. Carrie Dyck
* Faculty of Humanities and Social Sciences
* Memorial University of Newfoundland
*
* @author  Chen Zhang
* @version online 2.0
* @since   2016-12-01 
*/
package mun.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class NetworkConnection {

	public boolean networkConnection =  testInet("www.mochahost.com") || testInet("google.com") || testInet("amazon.com");	
	public boolean testInet(String site) {
	    Socket sock = new Socket();
	    InetSocketAddress addr = new InetSocketAddress(site,80);
	    try {
	        sock.connect(addr,3000);
	        return true;
	    } catch (IOException e) {
	        return false;
	    } finally {
	        try {sock.close();}
	        catch (IOException e) {}
	    }
	}	
}
