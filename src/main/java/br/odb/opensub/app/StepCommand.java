package br.odb.opensub.app;

import br.odb.gameapp.ConsoleApplication;

public class StepCommand extends UserMetaCommandLineAction {

	@Override
	public void run(ConsoleApplication application, String operand)
			throws Exception {

		OpenSubServer server = ( ( OpenSubServer )application );
		server.update( Long.parseLong( operand ) );
		server.getClient().printVerbose( "ticking the clock in " + operand + "ms" );
	}

	@Override
	public String toString() {
		return "step";
	}

	@Override
	public int requiredOperands() {
		return 1;
	}

	@Override
	public String getHelp() {
		return "provides the simulation with a clock tick (acording to operand, in milisseconds)";
	}
}
