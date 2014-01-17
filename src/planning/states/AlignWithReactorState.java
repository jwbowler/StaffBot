package planning.states;

import planning.Movement;
import planning.PID;
import planning.State;
import planning.UntimedState;
import world.Reactor;
import world.World;

public class AlignWithReactorState extends UntimedState {
	
	private final static double TOLERANCE_X = 0.01;
	private final static double MAX_X = 1.0;
	
	private final static double X_P = 1.0;
	private final static double X_I = 0.0;
	private final static double X_D = 0.0;
	
	private final Reactor reactor;
	private final PID xPID;

	public AlignWithReactorState(Reactor reactor) {
		this.reactor = reactor;
		xPID = new PID(X_P, X_I, X_D);
	}

	@Override
	public Movement step(World world) {
		double omega = xPID.step(reactor.getX());
		return new Movement(0, omega);
	}

	@Override
	public State transition(World world) {
		if (Math.abs(reactor.getX()) < TOLERANCE_X) {
			return new ShortApproachReactorState(reactor);
			
		} else {
			return this;
		}
		// TODO: add cases for backing out of the scoring sequence if badly aligned
	}

}
