package planning.states;


import planning.State;
import world.Ball;
import world.Reactor;
import world.World;

public class SeekReactorState extends ExploreState {

	private static final double THRESHOLD_Z = 0.8;
	private static final double THRESHOLD_X = 0.8;

	@Override
	public State transition(World world) {
		Reactor nearestReactor = world.getClosestReactor();
		
		if (nearestReactor.getZ() < THRESHOLD_Z && nearestReactor.getX() < THRESHOLD_X) {
			return new LongApproachReactorState(nearestReactor);
			
		} else {
			return this;
		}
	}
}
