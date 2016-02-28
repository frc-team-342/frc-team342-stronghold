package org.usfirst.frc.team342.robot.commands.shootersystem.collector;

import org.usfirst.frc.team342.robot.subsystems.BoulderController;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary;

public class CollectorDebug extends Command {

	BoulderController boulderCont;

	@Override
	protected void initialize() {
		boulderCont = BoulderController.getInstance();
	}

	@Override
	protected void execute() {
		String collector = "Collector Current"
				+ boulderCont.getCollectorCurrent() + "/n";

		FRCNetworkCommunicationsLibrary.HALSetErrorData(collector);
		System.out.println(collector);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
