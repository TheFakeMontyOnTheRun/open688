package br.odb.open688.simulation;

import java.util.ArrayList;

import br.odb.utils.math.Vec3;

public class Docks extends LandTarget {

	ArrayList< Ship > dockedShips;
	int capacity ;
	
	public Docks(Vec3 coordinates, TargetType type, int capacity ) {
		super(coordinates, type);
		
		this.capacity = capacity;
	}
	
	public void dockShip( Ship newShipToDock ) throws InvalidDockingException {
	}

	@Override
	public void update(long ms) {
	}
}
