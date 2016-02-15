package org.usfirst.frc.team342.robot.commands.shootersystem.shoot;

import org.usfirst.frc.team342.robot.subsystems.BoulderController;

import edu.wpi.first.wpilibj.command.Command;

public class ShootLowPower extends Command {
	private static final double SPEED = 0.6;

	private BoulderController shooter;

	/** Shoot a ball a short distance from the robot. */
    public ShootLowPower() {
    	shooter = BoulderController.getInstance();
    	requires(shooter);
    }

    protected void initialize() {
    }

    protected void execute() {
    	shooter.setShooterSpeed(SPEED);
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
