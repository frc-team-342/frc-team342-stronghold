package org.usfirst.frc.team342.robot.commands.shootersystem.collector;

import org.usfirst.frc.team342.robot.subsystems.BoulderController;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class CollectBall extends Command {
	// Collector
	private static final double TIMER_DELAY = 0.5;
	private static final double COLLECT_SPEED = 0.7;

	// Current value to detect ball
	private static final double BALL_CURRENT = 1.2;

	private BoulderController shooter;

	private boolean timerOver;
	private boolean continueDetectingCurrent;

	private boolean firstIteration;

	/** Collect a ball. */
	public CollectBall() {
		shooter = BoulderController.getInstance();
		requires(shooter);

	
	}

	@Override
	protected void initialize() {
		timerOver = false;
		firstIteration = true;
		continueDetectingCurrent = true;
		shooter.setCollectorSpeed(COLLECT_SPEED);
	}

	@Override
	protected void execute() {
		// Only read the voltage after the current has had a chance to drop.
		timerOver = timerOver || timeSinceInitialized() > TIMER_DELAY;
		System.out.println(timeSinceInitialized());
		if (timerOver && firstIteration && (shooter.getCollectorCurrent() > BALL_CURRENT)) {
			continueDetectingCurrent = false;
		}
	}

	/**
	 * Finishes when a ball is goes in the collector, causing a current spike,
	 * but only if the ball wasn't already detecting a ball as being in the
	 * robot.
	 */
	@Override
	protected boolean isFinished() {
		return !continueDetectingCurrent && shooter.getCollectorCurrent() > BALL_CURRENT;
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
