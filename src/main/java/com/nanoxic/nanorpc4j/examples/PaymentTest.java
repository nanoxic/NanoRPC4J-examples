package com.nanoxic.nanorpc4j.examples;

import java.math.BigInteger;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.nanoxic.nanorpc4j.Node;
import com.nanoxic.nanorpc4j.Payment;

public class PaymentTest {

	public static void main(String[] args) throws ConfigurationException {
		// Load config
		PropertiesConfiguration config = new PropertiesConfiguration();
		config.load("application.properties");
		
		String walletId = config.getString("wallet");
		String hostname = config.getString("hostname");
		int port = config.getInt("port");
		
		// initialize the client
		Node.init(hostname, port);
		
		Payment payment = new Payment(walletId);
		
		System.out.println(payment.init());
		String paymentAddress = payment.begin() ;
		System.out.println(paymentAddress);
		System.out.println(payment.wait(paymentAddress, new BigInteger("2500"), 900000L));

	}

}
