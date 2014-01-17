package planning;

import main.DeviceSet;
import world.World;

public abstract class State {
	
	public abstract boolean isTimed();
	public abstract Movement step(World world);
	public abstract void run(World world, DeviceSet devices);
	
	public abstract State transition(World world);

}
