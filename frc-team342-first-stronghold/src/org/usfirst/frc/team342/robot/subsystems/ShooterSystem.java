package org.usfirst.frc.team342.robot.subsystems;

import org.usfirst.frc.team342.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterSystem extends Subsystem {
	private CANTalon shooterMotor;
	private CANTalon collectorMotor;
	private CANTalon armMotor;

	boolean ball = false;

	public static final ShooterSystem instance = new ShooterSystem();

	public ShooterSystem() {
		shooterMotor = new CANTalon(RobotMap.TALON_SHOOTER_MOTOR_CAN);
		collectorMotor = new CANTalon(RobotMap.TALON_COLLOECTOR_MOTOR_CAN);
		armMotor = new CANTalon(RobotMap.TALON_ARM_CAN);
	}

	public static ShooterSystem getInstance() {
		return instance;
	}

	public boolean ball() {
		// TODO The system should only control motors. The collecting of the
		// ball should be done by a command.
		double collectorOutput = collectorMotor.getOutputCurrent();
		Timer.delay(0.5);

		if (collectorOutput < 0.8) {
			ball = false;
		} else if (collectorOutput >= 1.0) {
			ball = true;
		}
		if (ball == true) {
			return true;
		} else {
			return false;
		}

	}

	public void setArmSpeed(double speed) {
		armMotor.set(speed);
	}

	public void setCollectorSpeed(double speed) {
		collectorMotor.set(speed);
	}

	public void collector(double eater) {
		if (ball == false) {
			collectorMotor.set(eater);
		} else if (ball == true) {
			collectorMotor.set(0);
		}
	}

	public void shooter(double speed) {
		if (speed > 0 || speed < 0) {
			shooterMotor.set(speed);
		} else {
			shooterMotor.set(0);
		}
	}

	public void setShooterSpeed(double speed) {
		shooterMotor.set(speed);
	}

	public void shooterStop() {
		shooterMotor.set(0);
	}

	public void collectorStop() {
		collectorMotor.set(0);
	}

	@Override
	protected void initDefaultCommand() {
	}
}
