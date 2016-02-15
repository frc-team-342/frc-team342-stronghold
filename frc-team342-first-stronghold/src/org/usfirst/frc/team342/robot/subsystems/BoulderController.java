package org.usfirst.frc.team342.robot.subsystems;

import org.usfirst.frc.team342.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BoulderController extends Subsystem {
	private static final BoulderController instance = new BoulderController();

	/** Controls the launcher for long-range shooting. */
	private CANTalon shooterMotor;
	/** Controls the assembly that grabs and releases a ball for short range
	 * control. */
	private CANTalon collectorMotor;
	/** Controls the angle of the shooter and collector. */
	private CANTalon armMotor;

	/** The system for managing collecting and shooting boulders. It
	 * consists of three parts: an arm, a collector and a shooter. The arm
	 * controls the angle of the collector. The collector is a frame that
	 * has one spinning rod which can grab and release a ball. The ball is
	 * held by compression. The shooter shoots like a cannon using a ball
	 * obtained from the collector. */
	public BoulderController() {
		shooterMotor = new CANTalon(RobotMap.SHOOTER_MOTOR_CAN_TALON);
		collectorMotor = new CANTalon(RobotMap.COLLECTOR_MOTOR_CAN_TALON);
		armMotor = new CANTalon(RobotMap.ARM_CAN_TALON);
	}

	public static BoulderController getInstance() {
		return instance;
	}

	/** Set the shooter speed. */
	public void setShooterSpeed(double speed) {
		shooterMotor.set(speed);
	}

	/** Set the speed at which the arm is to rotate. */
	public void setArmSpeed(double speed) {
		armMotor.set(speed);
	}

	/** Set the speed for the collecor. Positive values will pull a ball in,
	 * negatives will relese a ball. */
	public void setCollectorSpeed(double speed) {
		collectorMotor.set(speed);
	}

	/** Get the current that is being drawn by the collector. */
	public double getCollectorCurrent() {
		return collectorMotor.getOutputCurrent();
	}

	public void stopShooter() {
		shooterMotor.set(0);
	}

	public void stopCollector() {
		collectorMotor.set(0);
	}

	public void stopArm() {
		armMotor.set(0);
	}

	/** Stops all the motors for the boulder control system. */
	public void stopAll() {
		shooterMotor.set(0);
		armMotor.set(0);
		collectorMotor.set(0);
	}

	@Override
	protected void initDefaultCommand() {
	}
}
