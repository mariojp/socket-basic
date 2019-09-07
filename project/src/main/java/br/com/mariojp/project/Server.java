package br.com.mariojp.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;

	public void start(int port) {
		try {
			serverSocket = new ServerSocket(port);
			clientSocket = serverSocket.accept();
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//read a message
		read();
	}
	
	public void read() {
		String message;
		try {
			message = in.readLine();
			if ("Hello Server".equals(message)) {
				out.println("Hello Client");
			} else {
				out.println("unrecognised message");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void stop() {
		try {
			in.close();
			out.close();
			clientSocket.close();
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		Server server = new Server();
		server.start(4444);
		server.stop();
	}
}
