package com.nanoxic.nanorpc4j.examples;

import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.nanoxic.nanorpc4j.Account;
import com.nanoxic.nanorpc4j.HistoryItem;
import com.nanoxic.nanorpc4j.Node;

public class AccountTest {

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
		System.out.println();
		System.out.println(account.getInfo().getBalance());
		System.out.println(account.getInfo().getBlock_count());
		System.out.println(account.getInfo().getFrontier());
		System.out.println(account.getInfo().getModified_timestamp());
		System.out.println(account.getInfo().getOpen_block());
		System.out.println(account.getInfo().getPending());
		System.out.println(account.getInfo().getRepresentative());
		System.out.println(account.getInfo().getRepresentative_block());
		System.out.println(account.getInfo().getWeight());
		System.out.println();
		System.out.println(account.getInfo(true, true, true).getPending());
		System.out.println(account.getInfo(true, true, true).getRepresentative());
		System.out.println(account.getInfo(true, true, true).getRepresentative_block());
		System.out.println(account.getInfo(true, true, true).getWeight());
		System.out.println();
		List<HistoryItem> history = account.getHistory();
		for (HistoryItem i : history) {
			System.out.println(i.getAccount());
			System.out.println(i.getAmount());
			System.out.println(i.getHash());
			System.out.println(i.getType());
		}
		System.out.println();
		history = account.getHistory(20);
		for (HistoryItem i : history) {
			System.out.println(i.getAccount());
			System.out.println(i.getAmount());
			System.out.println(i.getHash());
			System.out.println(i.getType());
		}
	}

}
