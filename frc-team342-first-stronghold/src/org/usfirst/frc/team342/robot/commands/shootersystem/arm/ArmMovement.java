package org.usfirst.frc.team342.robot.commands.shootersystem.arm;

import org.usfirst.frc.team342.robot.subsystems.BoulderController;

import edu.wpi.first.wpilibj.command.Command;

public abstract class ArmMovement extends Command {
	private BoulderController arm;

	private double speed;

	private boolean stopAtLimit;

	private boolean isAtLimit;

	public ArmMovement(double speed, boolean stopAtLimit) {
		arm = BoulderController.getInstance();

		this.speed = speed;
		this.stopAtLimit = stopAtLimit;

		isAtLimit = false;
	}

	protected void initialize() {
		isAtLimit = false;
	}

	protected void execute() {
		isAtLimit = arm.setArmSpeed(speed);
	}

	/** Depending on the mode the command was created in, either never
	 * finish such as in teleop, where the command would just be restarted
	 * again anyway, or finish when the arm is at it's limit which we use in
	 * autonomous to determine when we can start driving. */
	protected boolean isFinished() {
		if (stopAtLimit) {
			return isAtLimit;
		} else {
			return false;
		}
	}

	protected void end() {
		arm.stopArm();
	}

	protected void interrupted() {
		arm.stopArm();
	}
}