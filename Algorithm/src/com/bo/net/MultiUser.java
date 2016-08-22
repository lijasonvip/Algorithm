package com.bo.net;

import java.net.*;

import java.io.*;

public class MultiUser extends Thread{

	private Socket client;

	public MultiUser(Socket c) {

		this.client = c;

	}

	public void run() {

		try {

			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

			PrintWriter out = new PrintWriter(client.getOutputStream());

			// Mutil User but can't parallel

			while (true) {

				String str = in.readLine();

				System.out.println(str);

				out.println("has receive.");

				out.flush();

				if (str.equals("end"))

					break;

			}

			client.close();

		} catch (IOException ex) {

		} finally {

		}

	}

	public static void main(String[] args) throws IOException {

		ServerSocket server = new ServerSocket(5678);

		while (true) {

			// transfer location change Single User or Multi User

			MultiUser mu = new MultiUser(server.accept());

			mu.start();

		}

	}
}
