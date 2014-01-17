package planning;

public class PID {
	private final double P;
	private final double I;
	private final double D;
	
	private double sum = 0;
	private double lastError = 0;
	private boolean firstStep = true;
	
	public PID(double p, double i, double d) {
		P = p;
		I = i;
		D = d;
	}
	
	public double step(double error) {
		double diff = firstStep ? 0 : error - lastError;
		firstStep = false;
		lastError = error;
		sum += error;
		return P*error + I*sum + D*diff;
	}

}
