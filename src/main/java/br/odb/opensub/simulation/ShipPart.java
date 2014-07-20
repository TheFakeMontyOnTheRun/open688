package br.odb.opensub.simulation;

public interface ShipPart {
	float getNoiseLevel();
	boolean isActive();
	void setActive( boolean active );
	void update( long ms );
}
