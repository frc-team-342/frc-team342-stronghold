package org.usfirst.frc.team342.robot.commands.debug;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DebugInfo extends CommandGroup {
	public DebugInfo() {
		addParallel(new DriveDebug());
		addParallel(new ArmDebug());
	}
}
