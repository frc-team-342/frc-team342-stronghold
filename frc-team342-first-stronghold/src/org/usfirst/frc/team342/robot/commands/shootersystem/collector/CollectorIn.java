package org.usfirst.frc.team342.robot.commands.shootersystem.collector;

import org.usfirst.frc.team342.robot.subsystems.BoulderController;

import edu.wpi.first.wpilibj.command.Command;

public class CollectorIn extends Command {
	private static final double COLLECT_SPEED = 0.7;

	private BoulderController shooter;

	/** Set the collector motor to pull towards the robot. */
	public CollectorIn() {
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
		return true;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
		shooter.stopCollector();
	}
}
