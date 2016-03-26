package org.usfirst.frc.team342.robot.commands.drive;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousDrive extends CommandGroup {
	public AutonomousDrive() {
		addSequential(new DriveStraight());
		addSequential(new DriveSlow());
	}
}
