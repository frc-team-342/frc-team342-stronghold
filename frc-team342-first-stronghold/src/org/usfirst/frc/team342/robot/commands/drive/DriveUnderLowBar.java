package org.usfirst.frc.team342.robot.commands.drive;

import org.usfirst.frc.team342.robot.commands.shootersystem.arm.ArmMovement;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveUnderLowBar extends CommandGroup {
	public DriveUnderLowBar() {
		addSequential(new ArmMovement(0, true));
		addSequential(new DriveStraight());
	}
}
