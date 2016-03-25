package org.usfirst.frc.team342.robot.commands.shootersystem.arm;

import org.usfirst.frc.team342.robot.subsystems.BoulderController;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary;

public class StopArm extends Command {
	private BoulderController arm;

	private boolean reset;

	public StopArm(boolean resetEncoder) {
		arm = BoulderController.getInstance();

		reset = resetEncoder;
	}

	protected void initialize() {
		for (int i = 0; arm == null && i < 100; i++) {
			arm = BoulderController.getInstance();
			System.out.println("THE ARM IS NULL. FIX NOW!!!!");
			FRCNetworkCommunicationsLibrary.HALSetErrorData("ERROR: THE ARM IS NULL. FIX NOW!!!!");
		}
		arm.stopArm(reset);
		if (reset) {
			
		}
		System.out.println("STopping arm!");
		FRCNetworkCommunicationsLibrary.HALSetErrorData("STopping arm!");

	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
