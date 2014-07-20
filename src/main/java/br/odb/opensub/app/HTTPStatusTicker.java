package br.odb.opensub.app;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class HTTPStatusTicker implements Runnable {

	static class RequestHandler implements HttpHandler {

		private OpenSubServer server;

		public RequestHandler(OpenSubServer openSubServer) {
			super();

			this.server = openSubServer;
		}

		public void handle(HttpExchange t) throws IOException {
			String response = server.getStatus();
			t.sendResponseHeaders(200, response.length());
			t.setAttribute("Access-Control-Allow-Origin", "*");
			OutputStream os = t.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}
	}

	volatile ServerSocket server;
	volatile Socket socket;
	volatile DataOutputStream out;
	private OpenSubServer openSubServer;

	public HTTPStatusTicker(OpenSubServer openSubServer) {
		this.openSubServer = openSubServer;
	}

	@Override
	public void run() {
		HttpServer server;
		try {
			server = HttpServer.create(new InetSocketAddress(8000), 0);
			server.createContext("/test", new RequestHandler(openSubServer));
			server.setExecutor(null); // creates a default executor
			server.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
