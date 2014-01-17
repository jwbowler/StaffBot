package planning.states;

import main.DeviceSet;
import planning.Movement;
import planning.State;
import planning.TimedState;
import world.World;

public class CaptureBallState extends TimedState {
	
	private static final int PRE_DELAY = 500;
	private static final double HORIZ_STICK_VALUE = 0.5;
	private static final int MID_DELAY = 500;
	private static final double VERT_STICK_VALUE = 0.5;
	private static final int POST_DELAY = 500;
	
	@Override
	public void run(World world, DeviceSet devices) {
		try {
			Thread.sleep(PRE_DELAY);
		} catch (InterruptedException e) { }
		
		devices.horizStickServo.setValue(HORIZ_STICK_VALUE);
		devices.sendDeviceCommands();
		
		try {
			Thread.sleep(MID_DELAY);
		} catch (InterruptedException e) { }
		
		devices.vertStickServo.setValue(VERT_STICK_VALUE);
		devices.sendDeviceCommands();
		
		try {
			Thread.sleep(POST_DELAY);
		} catch (InterruptedException e) { }
		
		devices.sendDefaultValues();
	}

	@Override
	public State transition(World world) {
		return new SeekBallState();
	}

}
