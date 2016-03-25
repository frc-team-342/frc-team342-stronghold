package org.usfirst.frc.team342.robot.commandgroups;

import org.usfirst.frc.team342.robot.commands.drive.DriveDebug;
import org.usfirst.frc.team342.robot.commands.shootersystem.arm.ArmDebug;
import org.usfirst.frc.team342.robot.commands.shootersystem.collector.CollectorDebug;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DebugInfo extends CommandGroup {
	public DebugInfo() {
		addParallel(new DriveDebug());
		addParallel(new ArmDebug());
		addParallel(new CollectorDebug());
		// addParallel(new ShooterDebug());
	}
}
