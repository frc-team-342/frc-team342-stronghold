package org.usfirst.frc.team342.robot.commands.shootersystem.arm;

import org.usfirst.frc.team342.robot.subsystems.BoulderController;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class ArmIn extends Command {
	private static final double SPEED = 0.8;

	private BoulderController arm;

	/** Moves the arm back into the robot. */
	public ArmIn() {
		arm = BoulderController.getInstance();
	}

	protected void initialize() {

	}

	protected void execute() {
		arm.setArmSpeed(SPEED);
		// System.out.println(arm.getPotentiometer());
		// TODO Remove
		// Timer.delay(0.05);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		arm.stopArm();
		
	}

	protected void interrupted() {
		arm.stopArm();
	}
}
