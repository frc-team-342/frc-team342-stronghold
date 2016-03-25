package org.usfirst.frc.team342.robot.subsystems;

import org.usfirst.frc.team342.robot.RobotMap;
import org.usfirst.frc.team342.robot.commands.shootersystem.arm.StopArm;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BoulderController extends Subsystem {
	private static final BoulderController instance = new BoulderController();

	private static final int ENCODER_CODES_PER_REVOLUTION = 1000;

	private static final double[] ARM_OUT_ENCODER = { 0.0, -0.59, -0.3, 0.5 };

	/** Controls the launcher for long-range shooting. */
	private CANTalon shooterMotor;
	/**
	 * Controls the assembly that grabs and releases a ball for short range
	 * control.
	 */
	private CANTalon collectorMotor;
	/** Controls the angle of the shooter and collector. */
	private CANTalon armMotor;

	private DigitalInput armLimit;

	private class preventArmBreak extends Trigger {
		private static final double MAX_CURRENT = 7.0;

		@Override
		public boolean get() {
			return armMotor.getOutputCurrent() > MAX_CURRENT || !armLimit.get();
		}
	}

	/**
	 * The system for managing collecting and shooting boulders. It consists of
	 * three parts: an arm, a collector and a shooter. The arm controls the
	 * angle of the collector. The collector is a frame that has one spinning
	 * rod which can grab and release a ball. The ball is held by compression.
	 * The shooter shoots like a cannon using a ball obtained from the
	 * collector.
	 */
	public BoulderController() {
		shooterMotor = new CANTalon(RobotMap.SHOOTER_MOTOR_CAN_TALON);
		collectorMotor = new CANTalon(RobotMap.COLLECTOR_MOTOR_CAN_TALON);
		armLimit = new DigitalInput(RobotMap.ARM_LIMIT_DIO);

		armMotor = new CANTalon(RobotMap.ARM_CAN_TALON);

		/* Start of lots of arm motor code. */
		armMotor.setInverted(true);
		armMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);

		armMotor.reverseSensor(true);

		armMotor.configEncoderCodesPerRev(ENCODER_CODES_PER_REVOLUTION);

		armMotor.setEncPosition(0);

		// armMotor.enableLimitSwitch(true, true);

		/* set the peak and nominal outputs, 12V means full */
		armMotor.configNominalOutputVoltage(+0f, -0f);
		armMotor.configPeakOutputVoltage(+12f, -12f);
		/*
		 * set the allowable closed-loop error, Closed-Loop output will be
		 * neutral within this range. See Table in Section 17.2.1 for native
		 * units per rotation.
		 */
		armMotor.setAllowableClosedLoopErr(0); /* always servo */
		/* set closed loop gains in slot0 */
		armMotor.setProfile(0);
		armMotor.setF(0.0);
		armMotor.setP(0.5);
		armMotor.setI(0.000);
		armMotor.setD(0.1);

		armMotor.changeControlMode(TalonControlMode.Position);

		/* End of arm code. */

		// armMotor.changeControlMode(CANTalon.TalonControlMode.Position);

		collectorMotor.setInverted(true);
		shooterMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);

		// potentiometer = new AnalogInput(RobotMap.POTENTIOMETER_ANALOG);
		// potentiometer.startLiveWindowMode();

		new preventArmBreak().whenActive(new StopArm(true));
	}

	public static BoulderController getInstance() {
		return instance;
	}

	/** Get the velocity from the encoder on the shooter motors talon. */
	public int getShootEncVel() {
		return shooterMotor.getEncVelocity();
	}

	public double getArmEncoder() {
		return (double) armMotor.getEncPosition() / ENCODER_CODES_PER_REVOLUTION;
	}

	/** Set the shooter speed. */
	public void setShooterSpeed(double speed) {
		shooterMotor.set(speed);
	}

	/**
	 * TODO
	 */
	public void moveArm(int position) {
		armMotor.enable();
		armMotor.set(ARM_OUT_ENCODER[position]);
	}

	/** true is position, false is voltage. */
	public void setMode(boolean mode) {
		if (mode) {
			armMotor.changeControlMode(TalonControlMode.Position);
		} else {
			armMotor.changeControlMode(TalonControlMode.PercentVbus);
		}
	}

	public void moveBackward() {
		armMotor.changeControlMode(TalonControlMode.PercentVbus);

		armMotor.enable();
		armMotor.set(-0.2);
	}

	/**
	 * Set the speed for the collecor. Positive values will pull a ball in,
	 * negatives will relese a ball.
	 */
	public void setCollectorSpeed(double speed) {
		collectorMotor.set(speed);
	}

	/** Get the current that is being drawn by the collector. */
	public double getCollectorCurrent() {
		return collectorMotor.getOutputCurrent();
	}

	public double getShooterCurrent() {
		return shooterMotor.getOutputCurrent();
	}

	public double getArmCurrent() {
		return armMotor.getOutputCurrent();
	}

	public void stopShooter() {
		shooterMotor.set(0);
	}

	public void stopCollector() {
		collectorMotor.set(0);
	}

	public void stopArm(boolean reset) {
		armMotor.disable();
		if (reset) {
			armMotor.setEncPosition(0);
			System.out.println("Reseting encoder " + armMotor.getEncPosition());
		}
	}

	/** Stops all the motors for the boulder control system. */
	public void stopAll() {
		shooterMotor.set(0);
		armMotor.disable();
		collectorMotor.set(0);
	}

	// public boolean nicksFix() {
	// boolean limit = armLimit.get();
	// if (limit == false) {
	// armMotor.set(1.0);
	// return false;
	// } else if (limit == true) {
	// armMotor.set(0.0);
	// armMotor.setEncPosition(0);
	// return true;
	// }
	//
	// return false;
	// }

	@Override
	protected void initDefaultCommand() {
	}
}
