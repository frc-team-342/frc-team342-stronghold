package org.usfirst.frc.team342.robot.commands.shootersystem.collector;

import org.usfirst.frc.team342.robot.subsystems.BoulderController;

import edu.wpi.first.wpilibj.command.Command;

public class StopCollector extends Command {
	private BoulderController collector;

    public StopCollector() {
    	collector = BoulderController.getInstance();
    	requires(collector);
    }

    protected void initialize() {
    	collector.stopCollector();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    	collector.stopAll();
    }
}
