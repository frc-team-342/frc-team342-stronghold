package org.usfirst.frc.team342.robot.commands.shootersystem.collector;

import org.usfirst.frc.team342.robot.subsystems.BoulderController;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary;

public class CollectorDebug extends Command{
	
	BoulderController boulderCont;
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		boulderCont = BoulderController.getInstance();
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		FRCNetworkCommunicationsLibrary.HALSetErrorData("\nCollector Current" + boulderCont.getCollectorCurrent());
		
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
