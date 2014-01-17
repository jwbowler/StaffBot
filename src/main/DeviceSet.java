package main;
import planning.Movement;
import comm.MapleComm;

import devices.actuators.Cytron;
import devices.actuators.DigitalOutput;
import devices.actuators.PWMOutput;
import devices.sensors.Encoder;


public class DeviceSet {
	
	private final double HORIZ_STICK_DEFAULT = 0;
	private final double VERT_STICK_DEFAULT = 0;
	
	private final MapleComm comm;
	
	public final Encoder leftEncoder = new Encoder(17, 17);
	public final Encoder rightEncoder = new Encoder(17, 17);
	
	public final Cytron leftMotor = new Cytron(17, 17);
	public final Cytron rightMotor = new Cytron(17, 17);
	
	public final PWMOutput horizStickServo = new PWMOutput(17);
	public final PWMOutput vertStickServo = new PWMOutput(17);
	public final DigitalOutput ballShooter = new DigitalOutput(17);
	
	public DeviceSet(MapleComm comm) {
		this.comm = comm;
	}
	
	public void registerDevices() {
		comm.registerDevice(leftEncoder);
		comm.registerDevice(rightEncoder);
		comm.registerDevice(leftMotor);
		comm.registerDevice(rightMotor);
		comm.registerDevice(horizStickServo);
		comm.registerDevice(vertStickServo);
		comm.registerDevice(ballShooter);
	}
	
	public void updateDeviceValues() {
		comm.updateSensorData();
	}
	
	public void sendDeviceCommands() {
		comm.transmit();
	}

	public void sendDefaultValues() {
		leftMotor.setSpeed(0);
		rightMotor.setSpeed(0);
		horizStickServo.setValue(HORIZ_STICK_DEFAULT);
		vertStickServo.setValue(VERT_STICK_DEFAULT);
		ballShooter.setValue(false);
	}

	public void setMotors(Movement movement) {
		leftMotor.setSpeed(movement.getLeftMotorPWM());
		rightMotor.setSpeed(movement.getRightMotorPWM());
	}

}
