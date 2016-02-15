package org.usfirst.frc.team342.robot.commands.shootersystem.shoot;

import org.usfirst.frc.team342.robot.subsystems.BoulderController;

import edu.wpi.first.wpilibj.command.Command;

public class StopShooter extends Command {
	private BoulderController shooter;

	/** Stops only the shoot motor. */
    public StopShooter() {
    	shooter = BoulderController.getInstance();
    	requires(shooter);
    }

    protected void initialize() {
    	shooter.stopShooter();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    	shooter.stopAll();
    }
}
