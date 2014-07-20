package br.odb.opensub.app;

import br.odb.opensub.app.OpenSubServer.SimulationStatus;

public class StatusTickerRunnable implements Runnable {

	private OpenSubServer context;

	public StatusTickerRunnable(OpenSubServer context) {
		this.context = context;
	}


	@Override
	public void run() {

		while ( context.isConnected() ) {
			
			try {
				Thread.sleep( 1000 );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if ( context.simulationStatus == SimulationStatus.PLAYING ) {
				
				context.update( 1000 );
			}
		}
	}
}
