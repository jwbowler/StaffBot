package planning;

import main.DeviceSet;
import world.World;

public abstract class UntimedState extends State {

	@Override
	public boolean isTimed() {
		return false;
	}
	
	@Override
	public void run(World world, DeviceSet devices) { }
	
}
