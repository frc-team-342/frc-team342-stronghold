package org.usfirst.frc.team342.robot.commands.drive;

import org.usfirst.frc.team342.robot.commands.shootersystem.arm.ArmMovement;
import org.usfirst.frc.team342.robot.commands.shootersystem.arm.ArmMovement.ArmPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveUnderLowBar extends CommandGroup {
	public DriveUnderLowBar() {
		addSequential(new ArmMovement(ArmPosition.FULL_DOWN, true));
		addSequential(new DriveStraight());
		addSequential(new DriveSlow());
	}
}
