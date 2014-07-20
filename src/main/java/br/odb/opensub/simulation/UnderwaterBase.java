package br.odb.opensub.simulation;

import br.odb.utils.math.Vec3;

public class UnderwaterBase extends MaritimeTarget {

	public UnderwaterBase(Vec3 coordinates, TargetType type) {
		super(coordinates, type);
	}

	@Override
	public void update(long ms) {
	}
}
