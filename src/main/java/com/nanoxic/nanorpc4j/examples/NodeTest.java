package com.nanoxic.nanorpc4j.examples;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.nanoxic.nanorpc4j.Node;

public class NodeTest {

	public static void main(String[] args) throws ConfigurationException {
		// Load config
		PropertiesConfiguration config = new PropertiesConfiguration();
		config.load("application.properties");

		String hostname = config.getString("hostname");
		int port = config.getInt("port");

		// initialize the client
		Node.init(hostname, port);

		// Get some info about the client
		System.out.println(Node.getStoreVersion());
		System.out.println(Node.getNodeVendor());
		System.out.println(Node.getRPCVersion());
		System.out.println(Node.SearchPending());
		System.out.println(Node.getReceiveMinimum());
		System.out.println(Node.getAvailableSupply());
		// Stops the running node
		// System.out.println(Node.stop());
	}
}
