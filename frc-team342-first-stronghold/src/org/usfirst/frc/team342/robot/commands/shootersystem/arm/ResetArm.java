package org.usfirst.frc.team342.robot.commands.shootersystem.arm;

import org.usfirst.frc.team342.robot.subsystems.BoulderController;

import edu.wpi.first.wpilibj.command.Command;

public class ResetArm extends Command {
	private BoulderController arm;

	@Override
	protected void initialize() {
		arm = BoulderController.getInstance();
	}

	@Override
	protected void execute() {
		arm.moveBackward();
	}

	@Override
	protected boolean isFinished() {
		// Stop at arm
		return arm.armLimit();
	}

	@Override
	protected void end() {
		arm.setMode(true);
		arm.stopArm(true);
	}

	@Override
	protected void interrupted() {
		end();
	}

}
