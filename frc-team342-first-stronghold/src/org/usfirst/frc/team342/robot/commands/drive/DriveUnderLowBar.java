package org.usfirst.frc.team342.robot.commands.drive;

import org.usfirst.frc.team342.robot.commands.shootersystem.arm.ArmOut;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveUnderLowBar extends CommandGroup {
	public DriveUnderLowBar() {
		addSequential(new ArmOut(true));
		addSequential(new DriveStraight());
	}
}
