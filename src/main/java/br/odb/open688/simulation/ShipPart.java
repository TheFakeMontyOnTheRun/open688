package br.odb.open688.simulation;

public interface ShipPart {
	float getNoiseLevel();
	boolean isActive();
	void setActive( boolean active );
	void update( long ms );
}
