package org.usfirst.frc.team342.robot.commands.shootersystem.arm;

import org.usfirst.frc.team342.robot.subsystems.BoulderController;

import edu.wpi.first.wpilibj.command.Command;

public class ArmIn extends Command {
	private static final double SPEED = -0.8;

	private BoulderController arm;

	/** Determines whether or not the command should stop running when a
	 * limit switch is hit. Used for changing functionality between
	 * autonomous and teleop. */
	private boolean stopAtLimit;

	private boolean isAtLimit;

	/** Moves the arm back into the robot. */
	// TODO Use java doc link.
	// TODO Rename variable
	public ArmIn(boolean stopWhenAtLimit) {
		arm = BoulderController.getInstance();

		stopAtLimit = stopWhenAtLimit;

		isAtLimit = false;
	}

	protected void initialize() {
		isAtLimit = false;
	}

	protected void execute() {
		isAtLimit = arm.setArmSpeed(SPEED);
	}

	/** Depending on the mode the command was created in, either never finish
	 * such as in teleop, where the command would just be restarted again
	 * anyway, or finish when the arm is at it's limit which we use in
	 * autonomous to determine when we can start driving. */
	protected boolean isFinished() {
		if (stopAtLimit) {
			return isAtLimit;
		} else {
			return false;
		}
	}

	protected void end() {
		arm.stopArm();
	}

	protected void interrupted() {
		arm.stopArm();
	}
}
