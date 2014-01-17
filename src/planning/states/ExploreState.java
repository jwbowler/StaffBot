package planning.states;

import planning.Movement;
import planning.State;
import planning.UntimedState;
import world.World;

public abstract class ExploreState extends UntimedState {

	@Override
	public Movement step(World world) {
		// spinny spin
		return new Movement(0, 0.5);
	}

}
