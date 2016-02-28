package org.usfirst.frc.team342.robot.commands.shootersystem.arm;

import org.usfirst.frc.team342.robot.subsystems.BoulderController;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary;

public class ArmDebug extends Command {

	BoulderController boulderCont;

	@Override
	protected void initialize() {
		boulderCont = BoulderController.getInstance();
	}

	@Override
	protected void execute() {
		String arm = "Arm Current" + boulderCont.getArmCurrent() + "\n";
		String pot = "Potientiometer" + boulderCont.getPotentiometer() + "\n";

		FRCNetworkCommunicationsLibrary.HALSetErrorData(arm);
		System.out.println(arm);

		FRCNetworkCommunicationsLibrary.HALSetErrorData(pot);
		System.out.println(pot);
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
