package org.usfirst.frc.team342.robot.commands.shootersystem.arm;

import org.usfirst.frc.team342.robot.subsystems.BoulderController;

import com.sun.crypto.provider.ARCFOURCipher;

import edu.wpi.first.wpilibj.command.Command;

public class ArmMovement extends Command {
	private static final int MINIMUM_STOP_ERROR = 50;

	private BoulderController arm;

	private boolean waitForArm;
	private ArmPosition position;

	public enum ArmPosition {
		FULL_IN(0.0), FULL_DOWN(0.5);

		private double position;

		private ArmPosition(double position) {
			this.position = position;
		}

		public double getPosition() {
			return position;
		}
	}

	/**
	 * Move the arm.
	 * 
	 * @param direction
	 *            True if moving away from robot.
	 * @param stopAtLimit
	 *            True for autonomous. False for teleop.
	 */
	public ArmMovement(ArmPosition position, boolean stopAtLimit) {
		arm = BoulderController.getInstance();

		waitForArm = stopAtLimit;
		this.position = position;
	}

	protected void initialize() {
	};

	protected void execute() {
		arm.moveArm(position.getPosition());
	}

	/**
	 * Depending on the mode the command was created in, either never finish
	 * such as in teleop, where the command would just be restarted again
	 * anyway, or finish when the arm is at it's limit which we use in
	 * autonomous to determine when we can start driving.
	 */
	protected boolean isFinished() {
		return arm.getPositionError() < MINIMUM_STOP_ERROR || !waitForArm;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}