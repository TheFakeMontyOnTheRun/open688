package br.odb.opensub.app;

import br.odb.gameapp.ConsoleApplication;

public class PauseCommand extends UserMetaCommandLineAction {

	@Override
	public void run(ConsoleApplication application, String operand)
			throws Exception {

		OpenSubServer server = ( ( OpenSubServer )application );
		server.pause();
		server.getClient().printVerbose( "Simulation paused" );
	}

	@Override
	public String toString() {
		return "pause";
	}

	@Override
	public int requiredOperands() {
		return 0;
	}

	@Override
	public String getHelp() {
		return "pauses the simulation, so it can be verified in a step-by-step basis";
	}
}
