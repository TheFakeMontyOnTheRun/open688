package br.odb.opensub.app;

import java.util.ArrayList;

import br.odb.gameapp.ConsoleApplication;
import br.odb.gameapp.UserCommandLineAction;
import br.odb.opensub.simulation.FuturisticScenario;
import br.odb.opensub.simulation.Mission;
import br.odb.opensub.simulation.Scenario;
import br.odb.opensub.simulation.Submarine;
import br.odb.utils.Utils;

public class OpenSubServer extends ConsoleApplication {

	enum SimulationStatus {
		STOPPED, PLAYING, PAUSED
	}

	private final ArrayList<Station> stations = new ArrayList<Station>();
	private Mission mission;
	private Thread tickerThread;
	private Thread statusTickerThread;

	SimulationStatus simulationStatus = SimulationStatus.STOPPED;

	public static void main(String[] args) {

		// Eventually, this part will be created acording to graphical menus
		Scenario scenario = new FuturisticScenario();
		Mission mission = scenario.makeMission();

		// This will probably never change.
		OpenSubServer openSubServer = (OpenSubServer) new OpenSubServer()
				.addStation(new TelnetSonarStation(1))
				.addStation(new TelnetCartographyStation(2))
				.addStation(new TelnetHealmStation(3))
				.addStation(new TelnetPropulsionStation(4))
				.addStation(new TelnetArmoryStation(5)).setScenario(scenario)
				.setAppName("OpenSub")
				.setAuthorName("Daniel 'MontyOnTheRun' Monteiro")
				.setLicenseName("3-Clause BSD").setReleaseYear(2014);

		openSubServer.createDefaultClient(); // just for the server
		openSubServer.startMission(mission);
	}

	public void update(long ms) {
		mission.update(ms);
	}

	private void startMission(Mission mission) {

		this.mission = mission;

		start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		pause();
		startStatusTicker();

		for (Station s : stations) {
			s.setMasterClient(this);
		}
	}

	private void startStatusTicker() {
		this.tickerThread = new Thread(new StatusTickerRunnable(this));
		this.tickerThread.start();
		this.statusTickerThread = new Thread(new HTTPStatusTicker(this));
		this.statusTickerThread.start();
	}

	@Override
	public ConsoleApplication init() {

		registerCommand(new StatusCommand());
		registerCommand(new QuitCommand());
		registerCommand(new SteerCommand());
		registerCommand(new ThrustCommand());
		registerCommand(new ToggleCommand());
		registerCommand(new PlayCommand());
		registerCommand(new PauseCommand());
		registerCommand(new StepCommand());
		registerCommand(new PumpCommand());

		return super.init();
	}

	private OpenSubServer setScenario(Scenario scenario) {

		return this;
	}

	private OpenSubServer addStation(Station station) {

		this.stations.add(station);

		return this;
	}

	public String getStatus() {

		Submarine submarine = (Submarine) getMission().getPlayerCapitalShip();

		return "\ncurrent status\n==============\n" + submarine.toString()
				+ "\n* simulation time:"
				+ getMission().getSimulationEllapsedTime() + "s\n\t";
	}

	@Override
	public void onDataEntered(String entry) {

		if (entry == null || entry.length() == 0) {
			return;
		}

		super.onDataEntered(entry);

		String[] tokens = Utils.tokenize(entry.trim(), " ");
		String operator = tokens[0];
		String operand = entry.replace(operator, "").trim();

		
		UserCommandLineAction cmd = getCommand( tokens[ 0 ] );

		if (cmd != null) {
			try {
				cmd.run(this, operand);
			} catch ( InvalidShipPart isp ) {
				getClient().alert( "Invalid ship part!" );
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

	}

	@Override
	public void log(String tag, String string) {
	}

	@Override
	protected void doQuit() {
		continueRunning = false;
	}

	public boolean isConnected() {
		return this.continueRunning;
	}

	public Mission getMission() {
		return mission;
	}

	public void pause() {
		simulationStatus = SimulationStatus.PAUSED;
	}

	public void play() {
		simulationStatus = SimulationStatus.PLAYING;
	}
}
