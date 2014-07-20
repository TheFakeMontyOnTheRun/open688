package br.odb.opensub.simulation;

import br.odb.utils.math.Vec3;

public abstract class LandTarget extends Target {

	public LandTarget(Vec3 coordinates, TargetType type) {
		super(coordinates, type);
	}
}
