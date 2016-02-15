package org.usfirst.frc.team342.robot.commands.camera;

import org.usfirst.frc.team342.robot.subsystems.CameraVisionRedux;

import edu.wpi.first.wpilibj.command.Command;

public class SeeWithCamera extends Command {

	private CameraVisionRedux camera;

	/** Continuously retrieve images from the camera. */
	public SeeWithCamera() {
		camera = CameraVisionRedux.getInstance();
		requires(camera);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		camera.grapImage();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
