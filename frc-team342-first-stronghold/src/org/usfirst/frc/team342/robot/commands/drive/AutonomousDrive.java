package org.usfirst.frc.team342.robot.commands.drive;

import org.usfirst.frc.team342.robot.commands.shootersystem.arm.ResetArm;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousDrive extends CommandGroup {
	public AutonomousDrive() {
		addSequential(new ResetArm());
		addSequential(new DriveStraight());
		addSequential(new DriveSlow());
	}
}
