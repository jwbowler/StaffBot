package planning;

import main.DeviceSet;
import world.World;

public abstract class TimedState extends State {
	
	@Override
	public boolean isTimed() {
		return true;
	}

	@Override
	public Movement step(World world) {
		return null;
	}

}
