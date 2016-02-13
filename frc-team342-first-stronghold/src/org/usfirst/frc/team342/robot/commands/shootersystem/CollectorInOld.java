package org.usfirst.frc.team342.robot.commands.shootersystem;

import org.usfirst.frc.team342.robot.OI;
import org.usfirst.frc.team342.robot.subsystems.ShooterSystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class CollectorInOld extends Command {
	
	private Joystick joypad;
	
	private ShooterSystem shooter = ShooterSystem.getInstance();
	
	private boolean ball;

	public CollectorInOld() {
		// TODO Auto-generated constructor stub
		
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		OI oi = OI.getInstance();
		joypad = OI.getJoypad();
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		double eater = 1.0;
		shooter.collector(eater);
		ball = shooter.ball();
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		if(ball == true){
			return true;
		}else{
		return false;
		}
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		shooter.collector(0);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		shooter.collector(0);
	}

}
