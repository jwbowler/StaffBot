package planning.states;

import planning.Movement;
import planning.PID;
import planning.State;
import planning.UntimedState;
import world.Reactor;
import world.World;

public class LongApproachReactorState extends UntimedState {

	private final static double TARGET_DISTANCE_NORMAL_TO_REACTOR = 0.05;
	private final static double TOLERANCE_Z = 0.01;
	private final static double TOLERANCE_X = 0.01;
	private final static double MAX_Z = 1.0;
	private final static double MAX_X = 1.0;
	
	private final static double Z_P = 1.0;
	private final static double Z_I = 0.0;
	private final static double Z_D = 0.0;
	private final static double X_P = 1.0;
	private final static double X_I = 0.0;
	private final static double X_D = 0.0;
	
	private final Reactor reactor;
	private final PID zPID;
	private final PID xPID;
	
	public LongApproachReactorState(Reactor reactor) {
		this.reactor = reactor;
		zPID = new PID(Z_P, Z_I, Z_D);
		xPID = new PID(X_P, X_I, X_D);
	}

	@Override
	public Movement step(World world) {
		double speed = zPID.step(getTargetZ());
		double omega = xPID.step(getTargetX());
		return new Movement(speed, omega);
	}
	
	private double getTargetZ() {
		double theta = reactor.getApproachHeading();
		return reactor.getZ() - TARGET_DISTANCE_NORMAL_TO_REACTOR * Math.cos(theta);
	}
	
	private double getTargetX() {
		double theta = reactor.getApproachHeading();
		return reactor.getX() - TARGET_DISTANCE_NORMAL_TO_REACTOR * Math.sin(theta);
	}
	
	@Override
	public State transition(World world) {
		
		if (Math.abs(reactor.getZ() - getTargetZ()) < TOLERANCE_Z
				&& Math.abs(reactor.getX() - getTargetX()) < TOLERANCE_X) {
			return new AlignWithReactorState(reactor);
			
		} else if (Math.abs(reactor.getZ()) > MAX_Z
				|| Math.abs(reactor.getX()) > MAX_X) {
			return new SeekReactorState();
			
		} else {
			return this;
		}
	}

}
