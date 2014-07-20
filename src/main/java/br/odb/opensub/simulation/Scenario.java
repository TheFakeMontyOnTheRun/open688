package br.odb.opensub.simulation;

import java.util.ArrayList;

import br.odb.opensub.simulation.Target.TargetType;

public abstract class Scenario {
	
	final private ArrayList< LandTarget > landTargets = new ArrayList<LandTarget>();
	final private ArrayList< MaritimeTarget > maritimeTarget = new ArrayList<MaritimeTarget>();

	void addLandTarget(LandTarget target) {
		landTargets.add( target );
	}
	
	void addMaritimeTarget(MaritimeTarget target) {
		maritimeTarget.add( target );
	}

	public Mission makeMission() {

		Mission mission = new Mission();
		mission.setDeparture( getFriendlyBase() );
		mission.addPlayerShip( getFriendlyBase().createSubmarine() );
		mission.addPrimaryObjective( new PhotographyObjective( getRandomLandTarget( Target.TargetType.Enemy ) ) );
		mission.addSecundaryObjective( new SinkObjective( getRandomMaritimeTarget( Target.TargetType.Enemy ) ) );
		mission.setDestination( getFriendlyBase() );

		return mission;
	}

	private MaritimeTarget getRandomMaritimeTarget(TargetType enemy) {
		return maritimeTarget.get( 0 );
	}

	private LandTarget getRandomLandTarget(TargetType enemy) {
		return landTargets.get( 0 );
	}

	abstract Target getFriendlyBase();
}
