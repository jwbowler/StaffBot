package planning.states;


import planning.State;
import world.Ball;
import world.World;

public class SeekBallState extends ExploreState {
	
	private static final double THRESHOLD_Z = 0.8;
	private static final double THRESHOLD_X = 0.8;

	@Override
	public State transition(World world) {
		Ball nearestBall = world.getClosestBall();
		
		if (nearestBall.getZ() < THRESHOLD_Z && nearestBall.getX() < THRESHOLD_X) {
			return new ApproachBallState();
			
		} else {
			return this;
		}
	}
}
