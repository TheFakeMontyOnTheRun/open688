package br.odb.opensub.app;

import br.odb.gameapp.ConsoleApplication;

public class PlayCommand extends UserMetaCommandLineAction {

	@Override
	public void run(ConsoleApplication application, String operand)
			throws Exception {

		OpenSubServer server = ( ( OpenSubServer )application );
		server.play();
		server.getClient().printVerbose( "Simulation resumed." );
	}

	@Override
	public String toString() {
		return "play";
	}

	@Override
	public int requiredOperands() {
		return 0;
	}

	@Override
	public String getHelp() {
		return "puts the simulation in a continuos running state";
	}
}
