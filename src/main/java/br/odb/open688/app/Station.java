package br.odb.open688.app;

import java.io.IOException;

import br.odb.gameapp.ConsoleApplication;

public class Station extends TelnetClientServer implements Runnable {

	private Open688Server app;

	Station(int diff) {
		super(diff);
	}

	public void setMasterClient(ConsoleApplication app) {

		this.app = (Open688Server) app;

		new Thread(this).start();
	}

	@Override
	public String getInput(String msg) {
		return super.getInput(msg);
	}

	@Override
	public void run() {

		openServer();

		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				super.out.write( ( "\n\n" + app.getStatus() + "'\n\n").getBytes() );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			app.sendData(getInput("---"));
		}
	}
}
