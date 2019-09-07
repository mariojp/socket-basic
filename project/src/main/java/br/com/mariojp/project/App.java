package br.com.mariojp.project;

/**
 * Hello world!
 *
 */
public class App {
	
	public static void main(String args[]) {

		//Start Server
		new Thread(new Runnable() {
			public void run() {
				System.out.println("Start Server");
				Server server = new Server();
				server.start(4444);
				server.stop();
			}
		}).start();

		
		//Start Client
		Client client = new Client();
		client.startConnection("localhost", 4444);
		String response = client.sendMessage("Hello Server");
		System.out.println(response);
		client.stopConnection();
		
	}
	
}
