package org.usfirst.frc.team342.robot.subsystems;

import org.usfirst.frc.team342.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterSystem extends Subsystem{
	private CANTalon shooterMotor;
	private CANTalon collectorMotor;
	
	
	public static final ShooterSystem INSTANCE = new ShooterSystem();
	
	
	
	
	public ShooterSystem() {
		// TODO Auto-generated constructor stub
		shooterMotor = new CANTalon(RobotMap.TALON_SHOOTER_MOTOR_CAN);
		collectorMotor = new CANTalon(RobotMap.TALON_COLLOECTOE_MOTOR_CAN);
	}
	
	public static ShooterSystem getInstance(){
		return INSTANCE;
		
	}
	
	public void collector(double eater){
		if(eater > 0){
			collectorMotor.set(eater);
			
		}
		else if (eater < 0){
			collectorMotor.set(eater);
		}
	}
		
	
	public void shooter(double speed){
		
		if(speed > 0){
			shooterMotor.set(speed);
		}
		else
			shooterMotor.set(0);
		}
	
	public void shooterStop(){
		shooterMotor.set(0);
	}
	
	public void collectorStop(){
		
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
