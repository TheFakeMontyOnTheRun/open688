package br.odb.opensub.app;

import br.odb.gameapp.ApplicationClient;
import br.odb.gameapp.ConsoleApplication;
import br.odb.gameapp.UserCommandLineAction;
import br.odb.opensub.simulation.Submarine;

public class PumpCommand extends UserCommandLineAction {

	@Override
	public void run(ConsoleApplication application, String operand)
			throws Exception {

		ApplicationClient client = application.getClient();
		OpenSubServer server = ( ( OpenSubServer )application );
		
		Submarine submarine = (Submarine) server.getMission().getPlayerCapitalShip();
		submarine.tanks.setLevel( Integer.parseInt( operand ) );
		client.printVerbose( "Ballast tanks working to reach level " + operand );
	}

	@Override
	public String toString() {
		return "pump";
	}

	@Override
	public int requiredOperands() {
		return 1;
	}

	@Override
	public String getHelp() {
		return "set the target ballast tank levels";
	}
}
