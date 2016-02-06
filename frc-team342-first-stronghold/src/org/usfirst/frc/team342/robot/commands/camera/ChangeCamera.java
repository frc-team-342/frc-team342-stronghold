package org.usfirst.frc.team342.robot.commands.camera;

import org.usfirst.frc.team342.robot.subsystems.CameraVisionRedux;

import edu.wpi.first.wpilibj.command.Command;

public class ChangeCamera extends Command {

	private CameraVisionRedux cam;

	public ChangeCamera() {
		cam = CameraVisionRedux.getInstance();
		requires(cam);

	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		cam.ChangeCamera();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
		//camera.StopSeeingIsBelieving();
	}

	@Override
	protected void interrupted() {

	}

}
