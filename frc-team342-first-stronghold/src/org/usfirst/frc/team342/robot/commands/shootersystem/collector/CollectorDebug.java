package org.usfirst.frc.team342.robot.commands.shootersystem.collector;

import org.usfirst.frc.team342.robot.subsystems.BoulderController;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary;

public class CollectorDebug extends Command {
	private BoulderController boulderCont;

	private double prevTime;

	@Override
	protected void initialize() {
		boulderCont = BoulderController.getInstance();

		prevTime = timeSinceInitialized();
	}

	@Override
	protected void execute() {
		// Only update at most 5 times a second
		if (timeSinceInitialized() > prevTime + 0.1) {
			String collector = "Collector Current " + boulderCont.getCollectorCurrent() + "\n";

			FRCNetworkCommunicationsLibrary.HALSetErrorData(collector);
			System.out.print(collector);
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
