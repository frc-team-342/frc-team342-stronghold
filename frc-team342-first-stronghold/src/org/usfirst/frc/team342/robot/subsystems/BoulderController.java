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

	/**
	 * This value was chosen arbitrarily and not improved upon later as it
	 * seemed quicker to adjust the positions fed to the motor rather than
	 * determine the correct value for this.
	 */
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

	private DigitalInput armLimitTop;
	private DigitalInput armLimitBottom;

	private class ArmLimit extends Trigger {
		private boolean innerLim;

		ArmLimit(boolean innerLimit) {
			innerLim = innerLimit;
		}

		public boolean get() {
			// Uses the boolean from constructor to either return the inner
			// limit switch value or outer limit switch value. Sorry for the
			// complicated appearance, but it was too pretty to pass up. The
			// conditional works by returning the inverted value of the limit
			// switch determined by the boolean form the constructor. The value
			// is inverted because it is taken from a normally open switch.
			return innerLim ? !armLimitTop.get() : !armLimitBottom.get();
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
		shooterMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);

		collectorMotor = new CANTalon(RobotMap.COLLECTOR_MOTOR_CAN_TALON);
		collectorMotor.setInverted(true);

		initArm();
	}

	/**
	 * Helper method because arm is so much more complicated than the rest of
	 * the constructor.
	 */
	private void initArm() {
		armLimitTop = new DigitalInput(RobotMap.ARM_LIMIT_DIO);
		armLimitBottom = new DigitalInput(RobotMap.ARM_LIMIT_DOWN);
		armMotor = new CANTalon(RobotMap.ARM_CAN_TALON);

		// The motor is wired un-intuitively
		armMotor.setInverted(true);

		// Defaults to quad encoder anyway, so this is jsut for clarrification.
		armMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);

		// The quad encoder is backwards.
		armMotor.reverseSensor(true);

		// Makes accurate movement simpler if the codes per revolution is
		// accurate.
		armMotor.configEncoderCodesPerRev(ENCODER_CODES_PER_REVOLUTION);

		// The robot is assumed to be started with the arm at the 0 position
		armMotor.setEncPosition(0);

		// set the peak and nominal outputs, 12V means full
		armMotor.configNominalOutputVoltage(+0f, -0f);
		armMotor.configPeakOutputVoltage(+12f, -12f);

		// set the allowable closed-loop error, Closed-Loop output will be
		// neutral within this range.
		armMotor.setAllowableClosedLoopErr(5);

		// set closed loop gains in slot0
		armMotor.setProfile(0);
		// We do not use feed forward
		armMotor.setF(0.0);
		// Proportional controls how fast the arm moves
		armMotor.setP(0.7);
		// We do not use integral
		armMotor.setI(0.0);
		// Derivative prevents it from going to far
		armMotor.setD(0.01);

		// Typically uses position mode.
		armMotor.changeControlMode(TalonControlMode.Position);

		// Limit switches are optional, but it is highly recommended to use at
		// least one to be able to manualy set the arm position.
		new ArmLimit(true).whenActive(new StopArm(true));
		new ArmLimit(false).whenActive(new StopArm(false));
	}

	public static BoulderController getInstance() {
		return instance;
	}

	/** Get the velocity from the encoder on the shooter motors talon. */
	public int getShootEncVel() {
		return shooterMotor.getEncVelocity();
	}

	public boolean armLimit() {
		return armLimitTop.get();
	}

	public boolean armLimitBottom() {
		return armLimitBottom.get();
	}

	public int getArmEncoder() {
		return armMotor.getEncPosition();
	}

	/** Set the shooter speed. */
	public void setShooterSpeed(double speed) {
		shooterMotor.set(speed);
	}

	/** Starts position mode if not already and moves to the given position */
	public void moveArm(double position) {
		armMotor.enable();
		armMotor.set(position);
	}

	/**
	 * Get the current error from the requested position. Returns values pased
	 * on encoder codes.
	 */
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

	/** Changes to vbus and sets the motor to the given value. */
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
		shooterMotor.set(0.0);
		collectorMotor.set(0.0);
		// Arm requires a special invitation when in position mode. */
		if (armMotor.getControlMode() == TalonControlMode.Position) {
			armMotor.disable();
		} else {
			armMotor.set(0.0);
		}
	}

	public double getShooterCurrent() {
		return shooterMotor.getOutputCurrent();
	}

	public double getArmCurrent() {
		return armMotor.getOutputCurrent();
	}

	@Override
	protected void initDefaultCommand() {
	}
}
