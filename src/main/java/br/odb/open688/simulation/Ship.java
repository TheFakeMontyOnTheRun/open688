package br.odb.open688.simulation;

import java.util.HashMap;

import br.odb.utils.math.Vec3;

public abstract class Ship extends MaritimeTarget {

	float speed;

	@Override
	public String toString() {

		String toReturn = "Position: " + coordinates + "\n";

		for (ShipPart s : shipParts.values()) {

			toReturn += "* " + s + "\n";
		}

		return toReturn;
	}

	final HashMap<String, ShipPart> shipParts = new HashMap<String, ShipPart>();

	public Ship(Vec3 coordinates, TargetType type) {
		super(coordinates, type);

		addStation("Engine", makeEngine());
		addStation("Armory", makeArmory());
		addStation("Radar", makeRadar());
		addStation("Sonar", makeSonar());
		addStation("Crew", makeCrew());
		addStation("Comm", makeCommunications());
		addStation("Helm", new Helm()); // not much to surprise here...
	}

	protected void addStation(String name, ShipPart part) {
		shipParts.put(name, part);
	}

	abstract public Communications makeCommunications();

	abstract public Crew makeCrew();

	abstract public Sonar makeSonar();

	abstract public Radar makeRadar();

	abstract public Armory makeArmory();

	abstract public Engine makeEngine();

	@Override
	public void update(long ms) {

		for (ShipPart sp : shipParts.values()) {
			sp.update(ms);
		}

		Engine engine = ((Engine) getShipPart("Engine"));

		speed += engine.getSpeedGain() - getShipDrag();

		// TODO: reverse
		if (speed < 0) {
			speed = 0;
		} else {
			float angle = (float) (((Helm) getShipPart("Helm"))
					.getCurrentHeading() * (Math.PI / 180.0f));
			
			float cos = (float) Math.cos(angle);
			float sin = (float) Math.sin(angle);

			float x = coordinates.x;
			float z = coordinates.z;

			float px = cos; //( cos - sin);
			float pz = sin; //( sin + cos);

			coordinates.addTo(px, 0.0f, pz);
		}

	}

	public ShipPart getShipPart(String name) {
		return shipParts.get(name);
	}

	public abstract float getShipDrag();
}
