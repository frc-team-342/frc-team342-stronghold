package org.usfirst.frc.team342.robot.commands.shootersystem.arm;

import org.usfirst.frc.team342.robot.subsystems.BoulderController;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArmDashOutput extends Command {
	private static final String ARM_DATA = "ARM ENCODER";
	private BoulderController arm;

	@Override
	protected void initialize() {
		arm = BoulderController.getInstance();
	}

	@Override
	protected void execute() {
		SmartDashboard.putNumber(ARM_DATA, arm.getArmEncoder());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
