package org.usfirst.frc.team342.robot.commands.drive;

import org.usfirst.frc.team342.robot.commands.shootersystem.arm.ArmMovement;
import org.usfirst.frc.team342.robot.commands.shootersystem.arm.ResetArm;
import org.usfirst.frc.team342.robot.commands.shootersystem.arm.ArmMovement.ArmPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveUnderLowBar extends CommandGroup {
	public DriveUnderLowBar(double speed) {
		addSequential(new ResetArm());
		addSequential(new ArmMovement(ArmPosition.FULL_DOWN, true));
		addSequential(new DriveStraight(speed));
		addSequential(new DriveSlow(speed >= 0.0));
	}
}
