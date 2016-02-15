package org.usfirst.frc.team342.robot.commands.shootersystem.collector;

import org.usfirst.frc.team342.robot.subsystems.BoulderController;

import edu.wpi.first.wpilibj.command.Command;

public class CollectorOut extends Command {
	private static final double ARM_SPEED = 0.6;

	private BoulderController shooter = BoulderController.getInstance();

	/** Set the collector motors to push away from the robot. */
	public CollectorOut() {
		shooter = BoulderController.getInstance();
		requires(shooter);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		shooter.setArmSpeed(ARM_SPEED);
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
		shooter.stopAll();
	}

}
