package main;
import planning.Movement;
import planning.State;
import planning.states.SeekBallState;
import world.World;

import comm.MapleComm;
import comm.MapleIO;

import devices.Camera;

public class Main {
	
	public static void main(String[] args) {
		new Main();
		System.exit(0);
	}
	
	public Main() {
		// MapleComm comm = new MapleComm(MapleIO.SerialPortType.SIMULATION);
		MapleComm comm = new MapleComm(MapleIO.SerialPortType.LINUX);
		DeviceSet devices = new DeviceSet(comm);
		
		Camera camera = new Camera();
		World world = new World();
		State state = new SeekBallState();

		devices.registerDevices();
		
		while (true) {
			devices.updateDeviceValues();
			world.update(camera);
			
			state = state.transition(world);
			
			if (state.isTimed()) {
				state.run(world, devices);
				continue;
			}
			
			Movement movement = state.step(world);
			devices.setMotors(movement);
			devices.sendDeviceCommands();
			
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) { }
		}
	}
	
}
