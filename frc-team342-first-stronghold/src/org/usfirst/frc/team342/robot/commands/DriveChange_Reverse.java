package org.usfirst.frc.team342.robot.commands;

import org.usfirst.frc.team342.robot.commands.camera.ChangeCamera;
import org.usfirst.frc.team342.robot.commands.drive.ReverseRobotOrientation;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveChange_Reverse extends CommandGroup {
    
    public  DriveChange_Reverse() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addSequential(new ChangeCamera());
         addSequential(new ReverseRobotOrientation());
    }
    
}
