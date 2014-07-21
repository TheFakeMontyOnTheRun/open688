package br.odb.open688.app;

import br.odb.gameapp.ApplicationClient;
import br.odb.gameapp.ConsoleApplication;
import br.odb.open688.simulation.Submarine;

public class StatusCommand extends UserMetaCommandLineAction {

	@Override
	public void run( ConsoleApplication application, String operand ) {

		ApplicationClient client = application.getClient();
		Open688Server server = ( ( Open688Server )application );		
		client.printVerbose( server.getStatus() );		
	}

	@Override
	public String toString() {
		return "status";
	}

	@Override
	public String getHelp() {
		return "display submarine status";
	}

	@Override
	public int requiredOperands() {
		return 0;
	}
}
