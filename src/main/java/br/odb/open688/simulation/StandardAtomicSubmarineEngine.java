package br.odb.open688.simulation;


public class StandardAtomicSubmarineEngine extends Engine {

	
	@Override
	public int getMaxNoiseLevel() {
		return 10;
	}

	@Override
	public float getTopSpeed() {
		return 150 * KNOTS; 
	}
}
