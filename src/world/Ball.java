package world;

public class Ball extends WorldObject {
	
	public enum Type {RED, GREEN};
	private final Type type;

	public Ball(double[] location, Type type) {
		setLocation(location);
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}

}
