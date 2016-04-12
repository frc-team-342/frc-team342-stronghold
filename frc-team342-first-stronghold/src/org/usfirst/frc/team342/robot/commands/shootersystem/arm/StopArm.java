package org.usfirst.frc.team342.robot.commands.shootersystem.arm;

import org.usfirst.frc.team342.robot.subsystems.BoulderController;

import edu.wpi.first.wpilibj.command.Command;

public class StopArm extends Command {
	private BoulderController arm;

	private boolean reset;

	public StopArm(boolean resetEncoder) {
		arm = BoulderController.getInstance();

		reset = resetEncoder;
	}

	protected void initialize() {
		// The method occasionally returned null (this may be due to how this
		// command is called from within the method. */
		// TODO This could probably be fixed by just removing the initalization
		// from the constructor.
		for (int i = 0; arm == null && i < 5; i++) {
			arm = BoulderController.getInstance();
		}
		arm.stopArm(reset);
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
