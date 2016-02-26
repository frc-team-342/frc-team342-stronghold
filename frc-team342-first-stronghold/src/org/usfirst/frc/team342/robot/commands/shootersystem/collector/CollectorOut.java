package org.usfirst.frc.team342.robot.commands.shootersystem.collector;

import org.usfirst.frc.team342.robot.subsystems.BoulderController;

import edu.wpi.first.wpilibj.command.Command;

public class CollectorOut extends Command {
	private static final double COLLECT_SPEED = -0.7;

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
		shooter.setCollectorSpeed(COLLECT_SPEED);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		shooter.stopCollector();
	}

	@Override
	protected void interrupted() {
		shooter.stopCollector();
	}

}
