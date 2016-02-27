package org.usfirst.frc.team342.robot.commands.shootersystem.arm;

import org.usfirst.frc.team342.robot.subsystems.BoulderController;

import edu.wpi.first.wpilibj.command.Command;

public class ArmOut extends Command {
	private static final double SPEED = 0.8;
	private BoulderController arm;

	private boolean stopAtLimit;

	private boolean armAtLimit;

	/** Move the arm away from the robot. */
	public ArmOut(boolean stopWhenAtLimit) {
		arm = BoulderController.getInstance();

		stopAtLimit = stopWhenAtLimit;

		armAtLimit = false;
	}

	protected void initialize() {
		armAtLimit = false;
	}

	protected void execute() {
		armAtLimit = arm.setArmSpeed(SPEED);
	}

	// TODO Make armIn and ArmOut extend a common class to prevent
	// re-writing code.
	protected boolean isFinished() {
		if (stopAtLimit) {
			return armAtLimit;
		} else {
			return false;
		}
	}

	protected void end() {
		arm.stopArm();
	}

	protected void interrupted() {
		arm.stopAll();
	}
}
