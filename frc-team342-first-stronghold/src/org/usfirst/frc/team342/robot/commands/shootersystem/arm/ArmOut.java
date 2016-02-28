package org.usfirst.frc.team342.robot.commands.shootersystem.arm;

public class ArmOut extends ArmMovement {
	private static final double SPEED = 0.8;

	/** Move the arm away from the robot.
	 * 
	 * @param stopWhenAtLimit
	 *            True if if you want the command to stop when the arm limit
	 *            is reached. */ 
	public ArmOut(boolean stopWhenAtLimit) {
		super(SPEED, stopWhenAtLimit);
	}
}