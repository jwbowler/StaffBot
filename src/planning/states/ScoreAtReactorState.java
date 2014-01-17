package planning.states;


import main.DeviceSet;
import planning.Movement;
import planning.State;
import planning.TimedState;
import world.Reactor;
import world.World;

public class ScoreAtReactorState extends TimedState {
	
	private static final int PRE_DELAY = 500;
	private static final int POST_DELAY = 500;

	@Override
	public void run(World world, DeviceSet devices) {
		try {
			Thread.sleep(PRE_DELAY);
		} catch (InterruptedException e) { }
		
		devices.ballShooter.setValue(true);
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
