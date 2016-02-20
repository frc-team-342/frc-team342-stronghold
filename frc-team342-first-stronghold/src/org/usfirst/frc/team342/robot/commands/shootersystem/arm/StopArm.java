package org.usfirst.frc.team342.robot.commands.shootersystem.arm;

import org.usfirst.frc.team342.robot.subsystems.BoulderController;

import edu.wpi.first.wpilibj.command.Command;

public class StopArm extends Command {
	private BoulderController arm;

	public StopArm() {
		arm = BoulderController.getInstance();
		requires(arm);
	}

	protected void initialize() {
		arm.stopArm();
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
