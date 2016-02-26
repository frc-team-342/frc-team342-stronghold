package org.usfirst.frc.team342.robot.commands.shootersystem.arm;

import org.usfirst.frc.team342.robot.subsystems.BoulderController;

import edu.wpi.first.wpilibj.command.Command;

public class ArmOut extends Command {
	private static final double SPEED = -0.8;

	private BoulderController arm;

	private boolean armAtLimit;

	/** Move the arm away from the robot. */
	public ArmOut() {
		arm = BoulderController.getInstance();
	}

	protected void initialize() {
		armAtLimit = false;
	}

	protected void execute() {
		// The arm speed method returns true if the arm is at its limit.
		armAtLimit = arm.setArmSpeed(SPEED);
	}

	protected boolean isFinished() {
		return armAtLimit;
	}

	protected void end() {
		arm.stopArm();
	}

	protected void interrupted() {
		arm.stopArm();
	}
}
