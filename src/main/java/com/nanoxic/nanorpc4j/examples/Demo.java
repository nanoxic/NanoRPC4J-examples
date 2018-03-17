package com.nanoxic.nanorpc4j.examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.RandomStringUtils;

import com.nanoxic.nanorpc4j.Account;
import com.nanoxic.nanorpc4j.Convert;
import com.nanoxic.nanorpc4j.Node;
import com.nanoxic.nanorpc4j.Wallet;

public class Demo {

	private static String destinationAddress = "xrb_1do85i48hp98dwjp6qq6oubhbhrp43tdz18y68t76h9mygs75qxiw9apcrhi";

	public static void main(String[] args) throws ConfigurationException, IOException {

		// Use a good old CLI app for demonstration purposes
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// Load config
		PropertiesConfiguration config = new PropertiesConfiguration();
		config.load("application.properties");

		String walletId = config.getString("wallet");
		String hostname = config.getString("hostname");
		int port = config.getInt("port");

		// Initialize the connection to the node, always needed before using any
		// function in this library
		Node.init(hostname, port);

		// Create the accounts to use
		Account sourceAccount = new Account(config.getString("address"));
		Account destinationAccount = new Account(destinationAddress);

		// Create a new wallet object using the Wallet ID provided in
		// application.properties file
		Wallet wallet = new Wallet(walletId);

		// Check if the wallet is locked, ask for password if needed.
		if (wallet.isLocked()) {
			System.out.println("Please provide password to unlock this wallet:");
			String password = br.readLine();
			if (wallet.enterPassword(password)) {
				System.out.println("Wallet unlocked");
			} else {
				System.out.println("Wrong password");
				System.exit(0);
			}
		}

		// Check if source address is part of the current wallet
		if (!wallet.contains(sourceAccount)) {
			System.out.println("Address given in config file is not part of given wallet");
			System.exit(0);
		}

		// Check if destination address is a valid Nano address
		if (!Node.isValidAccount(destinationAccount)) {
			System.out.println("The destination account is not a valid Nano account");
			System.exit(0);
		}

		// Generate a random string to use as ID in our payment
		String id = RandomStringUtils.randomAlphanumeric(16);

		// Convert the rai/nano amount to raw
		BigInteger rawAmount = Convert.raiToRaw(new BigInteger("2500"));

		// Make sure there is enough raw available to make the transaction
		if (rawAmount.compareTo(sourceAccount.getBalance()) <= 0) {
			// Sending a test transaction
			String block = wallet.send(sourceAccount, destinationAccount, rawAmount, id);
			System.out.println("Donation send! " + block);
		} else {
			System.out.println("Not enough Nano available to make demo transaction");
		}
	}
}
