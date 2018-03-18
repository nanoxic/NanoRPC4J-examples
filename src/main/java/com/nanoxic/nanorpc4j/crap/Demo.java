package com.nanoxic.nanorpc4j.crap;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.nanoxic.nanorpc4j.Balance;
import com.nanoxic.nanorpc4j.Node;
import com.nanoxic.nanorpc4j.Wallet;

public class Demo {

	public static void main(String[] args) throws ConfigurationException {
		
		// Load config
		PropertiesConfiguration config = new PropertiesConfiguration();
		config.load("application.properties");

		String address = config.getString("address");
		String walletId = config.getString("wallet");
		String hostname = config.getString("hostname");
		int port = config.getInt("port");

		// initialize the client
		Node.init(hostname, port);

		// initialize the Wallet
		Wallet wallet = new Wallet(walletId);
		
		//
		System.out.println();
		HashMap<String, Balance> j = wallet.getBalances(new BigInteger("1"));
		Iterator<?> it = j.entrySet().iterator();
		String mainWallet = ((Map.Entry<String, Balance>) it.next()).getKey();
		// generate unique ids for production use
		// (no guaranteed uniqueness in this example)
		int counter = ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE - 1000);
		while (it.hasNext()) {
			Map.Entry<String, Balance> pair = (Map.Entry<String, Balance>) it.next();
			System.out.println(pair.getKey() + " = " + pair.getValue());
			if (!pair.getKey().equals(mainWallet)) {
				wallet.send(pair.getKey().toString(), mainWallet,
						new BigInteger(((Balance) pair.getValue()).getBalance()), Integer.toString(counter));
				counter++;
			}

			it.remove(); // avoids a ConcurrentModificationException
		}
	}

}
