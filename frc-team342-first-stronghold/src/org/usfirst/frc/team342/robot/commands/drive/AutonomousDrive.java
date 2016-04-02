package org.usfirst.frc.team342.robot.commands.drive;

import org.usfirst.frc.team342.robot.commands.shootersystem.arm.ResetArm;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousDrive extends CommandGroup {
	public AutonomousDrive(double speed) {
		addSequential(new ResetArm());
		addSequential(new DriveStraight(speed));
		addSequential(new DriveSlow(speed > 0.0));
	}
}
