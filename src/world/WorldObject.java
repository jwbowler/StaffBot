package world;

public abstract class WorldObject {
	
	private double z;
	private double x;
	
	public double getZ() {
		return z;
	}
	
	public double getX() {
		return x;
	}
	
	public double[] getLocation() {
		return new double[] {z, x};
	}
	
	public void setLocation(double location[]) {
		this.z = location[0];
		this.x = location[1];
	}

}
