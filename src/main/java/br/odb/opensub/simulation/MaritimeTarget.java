package br.odb.opensub.simulation;

import br.odb.utils.math.Vec3;

public abstract class MaritimeTarget extends Target {

	public MaritimeTarget(Vec3 coordinates, TargetType type) {
		super(coordinates, type);
	}
}
