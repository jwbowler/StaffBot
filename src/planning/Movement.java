package planning;

public class Movement {
	private static final double MAX_SPEED_PWM = 0.3;
	private static final double MAX_OMEGA_PWM = 0.3;
	
	public final double speed;
	public final double omega;
	
	public Movement(double speed, double omega) {
		this.speed = speed;
		this.omega = omega;
	}
	
	public double getLeftMotorPWM() {
		return MAX_SPEED_PWM*speed + MAX_OMEGA_PWM*omega;
	}
	
	public double getRightMotorPWM() {
		return MAX_SPEED_PWM*speed - MAX_OMEGA_PWM*omega;
	}
}
