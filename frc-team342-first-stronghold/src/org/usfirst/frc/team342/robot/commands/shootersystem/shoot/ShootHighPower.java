package org.usfirst.frc.team342.robot.commands.shootersystem.shoot;

import org.usfirst.frc.team342.robot.subsystems.BoulderController;

import edu.wpi.first.wpilibj.command.Command;

public class ShootHighPower extends Command {
	private BoulderController shooter;

	private static final double SPEED = 1.0;

	/** Shoot the ball long range. */
	public ShootHighPower() {
		shooter = BoulderController.getInstance();
	}

	protected void initialize() {
	}

	protected void execute() {
		shooter.setShooterSpeed(SPEED);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		shooter.stopShooter();
	}

	protected void interrupted() {
		shooter.stopShooter();
	}
}
