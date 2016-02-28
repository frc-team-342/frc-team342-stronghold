package org.usfirst.frc.team342.robot.commands.shootersystem.shoot;

import org.usfirst.frc.team342.robot.subsystems.BoulderController;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary;

public class ShooterDebug extends Command {
	BoulderController boulderCont;

	@Override
	protected void initialize() {
		boulderCont = BoulderController.getInstance();
	}

	@Override
	protected void execute() {
		String current = "Shooter Current " + boulderCont.getShooterCurrent()
				+ "/n";
		FRCNetworkCommunicationsLibrary.HALSetErrorData(current);
		System.out.println(current);
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
