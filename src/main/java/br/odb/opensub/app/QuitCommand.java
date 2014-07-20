package br.odb.opensub.app;

import br.odb.gameapp.ApplicationClient;
import br.odb.gameapp.ConsoleApplication;

public class QuitCommand extends UserMetaCommandLineAction {

	@Override
	public void run( ConsoleApplication application, String operand ) {

		ApplicationClient client = application.getClient();

        client.sendQuit();

    }

    @Override
    public int requiredOperands() {
        return 0;
    }

    @Override
    public String toString() {
        return "quit";
    }
    
	@Override
	public String getHelp() {
		return "- Quit the game and (if applicable) send highscore to the online leaderboard.";
	}
    
}
