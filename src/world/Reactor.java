package world;

public class Reactor extends WorldObject {
	
	private double heading;
	private boolean topFull = false;
	
	public boolean isTopFull() {
		return topFull;
	}
	
	public void setTopFull() {
		topFull = true;
	}
	
	public void setApproachHeading(double heading) {
		this.heading = heading;
	}

	public double getApproachHeading() {
		return heading;
	}

}
