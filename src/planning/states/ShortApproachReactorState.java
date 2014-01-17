package planning.states;

import planning.Movement;
import planning.PID;
import planning.State;
import planning.UntimedState;
import world.Reactor;
import world.World;

public class ShortApproachReactorState extends UntimedState {

	private final static double TARGET_Z = 0.05;
	private final static double TOLERANCE_Z = 0.01;
	private final static double TOLERANCE_X = 0.01;

	private final static double Z_P = 1.0;
	private final static double Z_I = 0.0;
	private final static double Z_D = 0.0;
	private final static double X_P = 1.0;
	private final static double X_I = 0.0;
	private final static double X_D = 0.0;

	private final Reactor reactor;
	private final PID zPID;
	private final PID xPID;

	public ShortApproachReactorState(Reactor reactor) {
		this.reactor = reactor;
		zPID = new PID(Z_P, Z_I, Z_D);
		xPID = new PID(X_P, X_I, X_D);
	}

	@Override
	public Movement step(World world) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public State transition(World world) {
		if (Math.abs(reactor.getZ()) < TOLERANCE_Z && Math.abs(reactor.getX()) < TOLERANCE_X) {
			return new ScoreAtReactorState();

		} else {
			return this;
		}
		// TODO: add cases for backing out of the scoring sequence if badly aligned
	}

}
