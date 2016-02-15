package org.usfirst.frc.team342.robot.commands.camera;

import org.usfirst.frc.team342.robot.subsystems.CameraVisionRedux;

import edu.wpi.first.wpilibj.command.Command;

public class ChangeCamera extends Command {

	private CameraVisionRedux cam;

	/** Alternate between the cameras being streamed. */
	public ChangeCamera() {
		cam = CameraVisionRedux.getInstance();
		requires(cam);
	}

	@Override
	protected void initialize() {
		cam.ChangeCamera();
	}

	@Override
	protected void execute() {
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
	}
}
