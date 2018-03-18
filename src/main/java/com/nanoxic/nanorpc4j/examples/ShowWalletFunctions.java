package com.nanoxic.nanorpc4j.examples;

import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.nanoxic.nanorpc4j.Account;
import com.nanoxic.nanorpc4j.Node;
import com.nanoxic.nanorpc4j.Wallet;

public class ShowWalletFunctions {

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

		// show functions
		Wallet wallet = new Wallet(walletId);
		System.out.println(wallet.getBalance());
		System.out.println(wallet.getPending());
		System.out.println(wallet.contains(address));
		System.out.println(wallet.isLocked());

		// Will generate a new account in the wallet
		// System.out.println(wallet.createAccount().getAccount());

		// List all Accounts in this Wallet
		System.out.println();
		List<Account> accounts = wallet.getAllAccounts();
		for (Account addressItem : accounts) {
			System.out.println(addressItem.getAddress() + " " + addressItem.getPublicKey());
		}

	}
}
