package org.usfirst.frc.team342.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveStriaght  extends Command {
	DriveSystem driveS;
	
	public DriveStriaght() {
		// TODO Auto-generated constructor stub
	driveS = DriveSystem.getInstance();
	
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		driveS.resetGyro();
	}
	
	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		double angle = driveS.GetGyro(); // get current heading
		driveS.driv3straight(-1.0, angle); // drive towards heading 0
		Timer.delay(0.004);	
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		driveS.Stop();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
