package org.usfirst.frc.team342.robot.commands.shootersystem.arm;

import org.usfirst.frc.team342.robot.subsystems.BoulderController;

import edu.wpi.first.wpilibj.command.Command;

public class ArmForceForward extends Command {
	private static final double SPEED = 0.2;
	private BoulderController arm;

	@Override
	protected void initialize() {
		arm = BoulderController.getInstance();
	}

	@Override
	protected void execute() {
		arm.moveBackwardVBUS(SPEED);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		arm.setMode(true);
	}

	@Override
	protected void interrupted() {
		end();
	}

}
