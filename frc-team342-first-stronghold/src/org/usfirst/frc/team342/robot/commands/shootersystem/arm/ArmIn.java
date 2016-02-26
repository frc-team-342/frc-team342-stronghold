package org.usfirst.frc.team342.robot.commands.shootersystem.arm;

import org.usfirst.frc.team342.robot.subsystems.BoulderController;

import edu.wpi.first.wpilibj.command.Command;

public class ArmIn extends Command {
	private static final double SPEED = 0.8;

	private BoulderController arm;

	boolean isAtStop;

	/** Moves the arm back into the robot. */
	public ArmIn() {
		arm = BoulderController.getInstance();
	}

	protected void initialize() {
		isAtStop = false;
	}

	protected void execute() {
		// The set arm speed method returns true if the arm is at its
		// stopping point.
		isAtStop = arm.setArmSpeed(SPEED);
	}

	protected boolean isFinished() {
		return isAtStop;
	}

	protected void end() {
		arm.stopArm();
	}

	protected void interrupted() {
		arm.stopArm();
	}
}
