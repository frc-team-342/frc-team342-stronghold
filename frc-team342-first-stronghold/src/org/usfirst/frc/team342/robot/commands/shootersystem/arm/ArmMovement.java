package org.usfirst.frc.team342.robot.commands.shootersystem.arm;

import org.usfirst.frc.team342.robot.subsystems.BoulderController;

import edu.wpi.first.wpilibj.command.Command;

public class ArmMovement extends Command {
	private static final int MINIMUM_STOP_ERROR = 70;
	private static final double TIME_DELAY = 0.2;

	private BoulderController arm;
	private boolean waitForArm;
	private ArmPosition position;

	private boolean hasRunOnce;

	public enum ArmPosition {
		FULL_IN(0.0), FULL_DOWN(-0.65); // .63 was used at first practice match
										// (March 31, 2016)

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

		hasRunOnce = false;
	}

	protected void initialize() {
		hasRunOnce = false;
	};

	protected void execute() {
		arm.moveArm(position.getPosition());
		hasRunOnce = true;
	}

	/**
	 * Depending on the mode the command was created in, either never finish
	 * such as in teleop, where the command would just be restarted again
	 * anyway, or finish when the arm is at it's limit which we use in
	 * autonomous to determine when we can start driving.
	 */
	protected boolean isFinished() {
		return hasRunOnce
				&& (((Math.abs(arm.getPositionError()) <= MINIMUM_STOP_ERROR && timeSinceInitialized() >= TIME_DELAY)
						|| !arm.armLimitBottom()) || !waitForArm);
	}

	protected void end() {
	}

	protected void interrupted() {
		end();
	}
}