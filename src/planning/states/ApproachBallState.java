package planning.states;

import planning.Movement;
import planning.PID;
import planning.State;
import planning.UntimedState;
import world.Ball;
import world.World;

public class ApproachBallState extends UntimedState {

	private final static double TARGET_Z = 0.05;
	private final static double TARGET_X = 0.0;
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
	
	private final PID zPID;
	private final PID xPID;
	
	public ApproachBallState() {
		zPID = new PID(Z_P, Z_I, Z_D);
		xPID = new PID(X_P, X_I, X_D);
	}

	@Override
	public Movement step(World world) {
		Ball closestBall = world.getClosestBall();
		double speed = zPID.step(closestBall.getZ());
		double omega = xPID.step(closestBall.getX());
		return new Movement(speed, omega);
	}

	@Override
	public State transition(World world) {
		Ball closestBall = world.getClosestBall();
		
		if (Math.abs(closestBall.getZ() - TARGET_Z) < TOLERANCE_Z
				&& Math.abs(closestBall.getX() - TARGET_X) < TOLERANCE_X) {
			return new CaptureBallState();
			
		} else if (Math.abs(closestBall.getZ()) > MAX_Z || Math.abs(closestBall.getX()) > MAX_X) {
			return new SeekBallState();
			
		} else {
			return this;
		}
	}

}