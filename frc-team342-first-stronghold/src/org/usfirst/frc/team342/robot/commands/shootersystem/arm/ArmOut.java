package org.usfirst.frc.team342.robot.commands.shootersystem.arm;

import org.usfirst.frc.team342.robot.subsystems.BoulderController;

import edu.wpi.first.wpilibj.command.Command;

public class ArmOut extends Command {
	private static final double SPEED = -0.6;
	private BoulderController arm;

	/** Move the arm away from the robot. */
	public ArmOut() {
		arm = BoulderController.getInstance();
		requires(arm);
	}

	protected void initialize() {
		arm.setArmSpeed(SPEED);
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
		arm.stopAll();
	}
}
