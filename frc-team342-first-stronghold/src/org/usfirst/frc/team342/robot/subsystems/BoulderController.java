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
	private DigitalInput armLimitBottom;

	private class preventArmBreakLimitSwitch extends Trigger {
		@Override
		public boolean get() {
			return !armLimit.get();
		}
	}

	private class preventArmBreakCurrent extends Trigger {
		public boolean get() {
			return !armLimitBottom.get();
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
		armLimitBottom = new DigitalInput(RobotMap.ARM_LIMIT_DOWN);
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
		armMotor.setAllowableClosedLoopErr(5);
		/* set closed loop gains in slot0 */
		armMotor.setProfile(0);
		armMotor.setF(0.0);
		armMotor.setP(0.7);
		armMotor.setI(0.0);
		armMotor.setD(0.01);

		armMotor.changeControlMode(TalonControlMode.Position);

		/* End of arm code. */

		// armMotor.changeControlMode(CANTalon.TalonControlMode.Position);

		collectorMotor.setInverted(true);
		shooterMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);

		// potentiometer = new AnalogInput(RobotMap.POTENTIOMETER_ANALOG);
		// potentiometer.startLiveWindowMode();

		new preventArmBreakLimitSwitch().whenActive(new StopArm(true));
		new preventArmBreakCurrent().whenActive(new StopArm(false));
	}

	public static BoulderController getInstance() {
		return instance;
	}

	/** Get the velocity from the encoder on the shooter motors talon. */
	public int getShootEncVel() {
		return shooterMotor.getEncVelocity();
	}

	public boolean armLimit() {
		// Limit switch is inverted.
		return armLimit.get();
	}

	public boolean armLimitBottom() {
		return armLimitBottom.get();
	}

	public double getArmEncoder() {
		return (double) armMotor.getEncPosition() / ENCODER_CODES_PER_REVOLUTION;
	}

	/** Set the shooter speed. */
	public void setShooterSpeed(double speed) {
		shooterMotor.set(speed);
	}

	public void moveArm(double position) {
		armMotor.enable();
		armMotor.set(position);
	}

	public int getPositionError() {
		return armMotor.getClosedLoopError();
	}

	/** true is position, false is percentVbus. */
	public void setMode(boolean mode) {
		if (mode) {
			armMotor.changeControlMode(TalonControlMode.Position);
		} else {
			armMotor.changeControlMode(TalonControlMode.PercentVbus);
		}
	}

	public void moveBackwardVBUS(double speed) {
		armMotor.changeControlMode(TalonControlMode.PercentVbus);

		armMotor.enable();
		armMotor.set(speed);
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

	@Override
	protected void initDefaultCommand() {
	}
}
