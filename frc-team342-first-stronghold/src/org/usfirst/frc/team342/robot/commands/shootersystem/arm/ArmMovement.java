package org.usfirst.frc.team342.robot.commands.shootersystem.arm;

import org.usfirst.frc.team342.robot.subsystems.BoulderController;

import edu.wpi.first.wpilibj.command.Command;

public class ArmMovement extends Command {
	private BoulderController arm;

	private int position;

	/**
	 * Move the arm.
	 * 
	 * @param direction
	 *            True if moving away from robot.
	 * @param stopAtLimit
	 *            True for autonomous. False for teleop.
	 */
	public ArmMovement(int position, boolean stopAtLimit) {
		arm = BoulderController.getInstance();

		this.position = position;
	}

	protected void initialize() {
	};

	protected void execute() {
		arm.moveArm(position);
	}

	/**
	 * Depending on the mode the command was created in, either never finish
	 * such as in teleop, where the command would just be restarted again
	 * anyway, or finish when the arm is at it's limit which we use in
	 * autonomous to determine when we can start driving.
	 */
	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}