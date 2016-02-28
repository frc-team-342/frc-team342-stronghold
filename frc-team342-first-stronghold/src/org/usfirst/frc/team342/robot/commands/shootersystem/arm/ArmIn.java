package org.usfirst.frc.team342.robot.commands.shootersystem.arm;

public class ArmIn extends ArmMovement {
	private static final double SPEED = -0.8;

	/** Moves the arm back into the robot.
	 * 
	 * @param stopWhenAtLimit
	 *            True if the command should end when the limit of he motion
	 *            is reached. */
	public ArmIn(boolean stopWhenAtLimit) {
		super(SPEED, stopWhenAtLimit);
	}

}
