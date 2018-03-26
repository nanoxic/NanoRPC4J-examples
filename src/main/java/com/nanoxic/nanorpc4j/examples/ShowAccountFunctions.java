package com.nanoxic.nanorpc4j.examples;

import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.nanoxic.nanorpc4j.Account;
import com.nanoxic.nanorpc4j.HistoryItem;
import com.nanoxic.nanorpc4j.Node;

public class ShowAccountFunctions {

	public static void main(String[] args) throws ConfigurationException {
		
		// Load config
		PropertiesConfiguration config = new PropertiesConfiguration();
		config.load("application.properties");

		String publicKey = config.getString("publicKey");
		String address = config.getString("address");
		String hostname = config.getString("hostname");
		int port = config.getInt("port");

		// initialize the client
		Node.init(hostname, port);

		// show functions using public key
		Account account = new Account(publicKey);
		System.out.println(account.getAddress());
		System.out.println(account.getPublicKey());
		System.out.println(account.getBalance());
		System.out.println(account.getPending());
		System.out.println(account.getBlockCount());
		System.out.println(account.getWeight());
		System.out.println(account.getRepresentative());
		System.out.println();

		// show functions using address
		account = new Account(address);
		System.out.println(account.getAddress());
		System.out.println(account.getPublicKey());
		System.out.println(account.getBalance());
		System.out.println(account.getPending());
		System.out.println(account.getBlockCount());
		System.out.println(account.getRepresentative());
		System.out.println(account.getFrontier());
		System.out.println(account.getLastModified());
		System.out.println(account.getOpenBlock());
		System.out.println();

		// Show some account history
		List<HistoryItem> history = account.getHistory();
		for (HistoryItem historyItem : history) {
			System.out.print(historyItem.getType() + " ");
			System.out.println(historyItem.getAccount().getAddress());
			System.out.println(historyItem.getAmount());
			System.out.println(historyItem.getHash());
			System.out.println();
		}
	}

}
