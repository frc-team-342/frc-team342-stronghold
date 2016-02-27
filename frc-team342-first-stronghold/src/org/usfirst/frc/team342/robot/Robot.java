
package org.usfirst.frc.team342.robot;

import org.usfirst.frc.team342.robot.commands.drive.DriveStraight;
import org.usfirst.frc.team342.robot.commands.drive.DriveUnderLowBar;
import org.usfirst.frc.team342.robot.commands.drive.DriveWithJoystick;
import org.usfirst.frc.team342.robot.subsystems.BoulderController;
import org.usfirst.frc.team342.robot.subsystems.CameraVisionRedux;
import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the
 * resource directory. */
// TODO Determine a standard formatting style
public class Robot extends IterativeRobot {
	private static final String INITIALIZED_MESSAGE = "Burnie is alive!!!";

	private static final String AUTONOMOUSE_DRIVE_STRAIGHT_MESSAGE = "Burnie gonna rec some scrubs";
	private static final String AUTONOMOUS_UNUSED_MESSAGE = "WHY YOU NO AUTO, CHUM-BUCKET?!?";
	private static final String AUTONOMOUS_LOW_BAR_MESSAGE = "Going under the low bar is not done yet, so the programming team will be really happy to hear about it working, or hear how amusing it's faiilure was...";

	private static final String TELEOP_MESSAGE = "Teleop Initialized, Win... Or Else! (JK)";

	private static final String AUTONOMOUS_ENABLED = "auto message here";
	private static final String AUTONMOUS_DRIVE_UNDER_LOWBAR = "auto drive under low bar message";

	private DriveWithJoystick joydrive;

	/** This function is run when the robot is first started up and should
	 * be used for any initialization code. */
	public void robotInit() {
		// Initialize subsystems.
		DriveSystem.getInstance();
		CameraVisionRedux.getInstance();
		BoulderController.getInstance();

		OI.initInstance();
		FRCNetworkCommunicationsLibrary.HALSetErrorData(INITIALIZED_MESSAGE);
		SmartDashboard.putBoolean(AUTONOMOUS_ENABLED, true);
		SmartDashboard.putBoolean(AUTONMOUS_DRIVE_UNDER_LOWBAR, false);
	}

	/** This function is called once each time the robot enters Disabled
	 * mode. You can use it to reset any subsystem information you want to
	 * clear when the robot is disabled. */
	public void disabledInit() {

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();

	}

	public void autonomousInit() {
		if (SmartDashboard.getBoolean(AUTONOMOUS_ENABLED)) {
			if (SmartDashboard.getBoolean(AUTONMOUS_DRIVE_UNDER_LOWBAR)) {
				(new DriveUnderLowBar()).start();
				FRCNetworkCommunicationsLibrary
						.HALSetErrorData(AUTONOMOUS_LOW_BAR_MESSAGE);
			} else {
				(new DriveStraight()).start();
				FRCNetworkCommunicationsLibrary
						.HALSetErrorData(AUTONOMOUSE_DRIVE_STRAIGHT_MESSAGE);
			}
		} else {
			// DO NOTHING
			FRCNetworkCommunicationsLibrary
					.HALSetErrorData(AUTONOMOUS_UNUSED_MESSAGE);
		}
	}

	/** This function is called periodically during autonomous */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		FRCNetworkCommunicationsLibrary.HALSetErrorData(TELEOP_MESSAGE);
		joydrive = new DriveWithJoystick();
	}

	/** This function is called periodically during operator control */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		joydrive.start();
	}

	/** This function is called periodically during test mode */
	public void testPeriodic() {
	}
}
