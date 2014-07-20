package br.odb.opensub.app;

import br.odb.gameapp.ApplicationClient;
import br.odb.gameapp.ConsoleApplication;
import br.odb.gameapp.UserCommandLineAction;
import br.odb.opensub.simulation.Engine;
import br.odb.opensub.simulation.Submarine;

public class ThrustCommand extends UserCommandLineAction {

	@Override
	public void run(ConsoleApplication application, String operand)
			throws Exception {

		ApplicationClient client = application.getClient();
		OpenSubServer server = ( ( OpenSubServer )application );
		
		Submarine submarine = (Submarine) server.getMission().getPlayerCapitalShip();
		Engine engine = ( (Engine )submarine.getShipPart( "Engine" ) );
		engine.setThrust( Float.parseFloat( operand ) );
		client.printNormal( "Changing engine thrust to " + operand + ", my captain." );
	}

	@Override
	public String toString() {
		return "thrust";
	}

	@Override
	public int requiredOperands() {
		return 1;
	}

	@Override
	public String getHelp() {
		return "Change the engine thrust.";
	}
}
