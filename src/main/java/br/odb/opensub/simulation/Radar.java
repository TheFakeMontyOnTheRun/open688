package br.odb.opensub.simulation;

public class Radar implements ShipPart {

	@Override
	public float getNoiseLevel() {
		return 0;
	}

	@Override
	public boolean isActive() {
		return false;
	}

	@Override
	public void setActive(boolean active) {
	}

	@Override
	public void update(long ms) {
	}
	
	@Override
	public String toString() {
		return "Radar: inactive";
	}
}
