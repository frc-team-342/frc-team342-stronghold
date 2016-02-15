package org.usfirst.frc.team342.robot.commands.shootersystem.collector;

import org.usfirst.frc.team342.robot.subsystems.BoulderController;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class PushBall extends Command {
	private static final double ArmSpeed = 0.7;
	private static final double ArmDelay = 1;

	private static final double CollectorSpeed = -0.9;
	private static final double CollectorDelay = 2;

	private BoulderController boulderSystem;

	/** Push the ball into the goal. */
	public PushBall() {
		boulderSystem = BoulderController.getInstance();
	}

	@Override
	protected void initialize() {
		boulderSystem.setArmSpeed(ArmSpeed);
		Timer.delay(ArmDelay);
		boulderSystem.stopAll();
	}

	@Override
	protected void execute() {
		boulderSystem.setCollectorSpeed(CollectorSpeed);
	}

	@Override
	protected boolean isFinished() {
		return timeSinceInitialized() > CollectorDelay;
	}

	@Override
	protected void end() {
		boulderSystem.stopAll();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
