package org.usfirst.frc.team342.robot.commands.shootersystem.arm;

import org.usfirst.frc.team342.robot.subsystems.BoulderController;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class ArmInAuto extends Command {
	private static final double SPEED = 1.0;

	private BoulderController arm;
	
	private boolean isDone;

	/** Moves the arm back into the robot. */
	public ArmInAuto() {
		arm = BoulderController.getInstance();
	}

	protected void initialize() {

	}

	protected void execute() {
		isDone = arm.setArmSpeed(SPEED);
		// System.out.println(arm.getPotentiometer());
		// TODO Remove
		// Timer.delay(0.05);
	}

	protected boolean isFinished() {
		return isDone;
	}

	protected void end() {
		arm.stopArm();
		
	}

	protected void interrupted() {
		arm.stopArm();
	}
}
