package org.usfirst.frc.team342.robot.commands.shootersystem.collector;

import org.usfirst.frc.team342.robot.subsystems.BoulderController;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class CollectBall extends Command {
	// Arm
	private static final double ARM_SPEED = 0.7;
	private static final double ARM_DELAY = 3;

	// Collector
	private static final double TIMER_DELAY = 0.5;
	private static final double COLLECT_SPEED = 0.7;

	// Current value to detect ball
	private static final double BALL_VOLTAGE = 1.2;

	private BoulderController shooter;

	/** Collect a ball. */
	public CollectBall() {
		shooter = BoulderController.getInstance();
		requires(shooter);
	}

	@Override
	protected void initialize() {
		// Push the arm out to prepare for the ball.
		shooter.setArmSpeed(ARM_SPEED);
		Timer.delay(ARM_DELAY);
		shooter.stopArm();

		// Start the collector, but wait for the current spike to stop.
		shooter.setCollectorSpeed(COLLECT_SPEED);
		// TODO Test the effect of delay to make sure other commands still
		// run. Consider reading a timer value inside of the execute loop
		// instead. Timer is also used in PushBall command.
		Timer.delay(TIMER_DELAY);
	}

	@Override
	protected void execute() {
	}

	/** Finishes when a ball is goes in the collector, causing a current
	 * spike. */
	@Override
	protected boolean isFinished() {
		return shooter.getCollectorCurrent() > BALL_VOLTAGE;
	}

	@Override
	protected void end() {
		shooter.stopCollector();
	}

	@Override
	protected void interrupted() {
		shooter.stopAll();
	}
}
