package org.usfirst.frc.team342.robot.commands.drive;

import org.usfirst.frc.team342.robot.commands.shootersystem.arm.ArmIn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveUnderLowBar extends CommandGroup {
	public DriveUnderLowBar() {
		
		addSequential(new ArmIn());
		addSequential(new DriveStraight());
	}
}
