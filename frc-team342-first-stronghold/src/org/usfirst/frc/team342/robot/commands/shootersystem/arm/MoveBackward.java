<<<<<<< HEAD
package org.usfirst.frc.team342.robot.commands.shootersystem.arm;

import org.usfirst.frc.team342.robot.subsystems.BoulderController;

import edu.wpi.first.wpilibj.command.Command;

public class MoveBackward extends Command {
	private static final double SPEED = -0.3;

	private BoulderController arm;

	public MoveBackward() {
		arm = BoulderController.getInstance();
	}

	@Override
	protected void initialize() {
		arm.setMode(false);
	}

	@Override
	protected void execute() {
		arm.moveBackwardVBUS(SPEED);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		arm.setMode(true);
	}

	@Override
	protected void interrupted() {
		end();
	}

}
=======
package org.usfirst.frc.team342.robot.commands.shootersystem.arm;

import org.usfirst.frc.team342.robot.subsystems.BoulderController;

import edu.wpi.first.wpilibj.command.Command;

public class MoveBackward extends Command {
	private static final double SPEED = -0.3;

	private BoulderController arm;

	public MoveBackward() {
		arm = BoulderController.getInstance();
	}

	@Override
	protected void initialize() {
		arm.setMode(false);
	}

	@Override
	protected void execute() {
		arm.moveBackwardVBUS(SPEED);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		arm.setMode(true);
	}

	@Override
	protected void interrupted() {
		end();
	}

}
>>>>>>> origin/master
