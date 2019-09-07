package br.com.mariojp.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



/**
 * Unit test for simple App.
 */
public class AppTest {
   
	Server server;

  
    @BeforeEach
	void init() {
		new Thread(new Runnable() {
			public void run() {
    			server = new Server();
				server.start(4444);
				server.stop();
			}
		}).start();
	}
	
	@AfterEach
	void tearDown() {
		server.stop();
	}
	

    @Test
    public void testClientAndServerRespondMessage(){
		Client client = new Client();
		client.startConnection("127.0.0.1", 4444);
		String response = client.sendMessage("Hello Server");
		client.stopConnection();
    	assertEquals("Hello Client", response);

    }
}
