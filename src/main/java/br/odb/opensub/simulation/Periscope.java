package br.odb.opensub.simulation;


public class Periscope implements ShipPart {

	private boolean active;


	@Override
	public float getNoiseLevel() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void snapPicture() {
		
	}

	@Override
	public boolean isActive() {
		return active;
	}

	@Override
	public void setActive(boolean active) {
		this.active = active;

	}

	@Override
	public void update(long ms) {
	}
	
	
	@Override
	public String toString() {
		return "Periscope: periscope is " + ( active? "up" : "down" );
	}
}
