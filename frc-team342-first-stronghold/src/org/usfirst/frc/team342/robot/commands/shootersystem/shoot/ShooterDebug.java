package org.usfirst.frc.team342.robot.commands.shootersystem.shoot;

import org.usfirst.frc.team342.robot.subsystems.BoulderController;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary;

public class ShooterDebug extends Command {
	BoulderController boulderCont;
	

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		boulderCont = BoulderController.getInstance();
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		FRCNetworkCommunicationsLibrary.HALSetErrorData("\nShooter Current" + boulderCont.getShooterCurrent());

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
