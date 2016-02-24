package org.usfirst.frc.team342.robot.subsystems;

import org.usfirst.frc.team342.robot.commands.camera.SeeWithCamera;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.VisionException;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary;

/**
 * !!!!! ABANDON HOPE ALL YE' WHO ENTER HERE !!!!! <br>
 * Nobody understood the camera vision code when it was first written, so this
 * code is two years old and continuously decreasing in quality. <br>
 * <br>
 * Instead of bothering with this section of the code, you should stare at this
 * ascii art of a camera and be reminded of a simpler time when you didn't have
 * to fix code someone else clumsily copied because nobody understood the a
 * 31,000 line, auto-generated java file it utilized which was based off a c
 * program whose only trace on the Internet is a 5,000 line header file which
 * does next to nothing as far as explaining how it all works. <code> 
 * <pre>
       .---.
       |[X]|
_.==._.""""".___n__
d __ ___.-''-. _____b
|[__]  /."""".\ _   |
|     // /""\ \\_)  |
|     \\ \__/ //    |
|      \`.__.'/     |
\=======`-..-'======/
 `------------------   
 * </pre>
</code><br>
 * art source:
 * <a href="http://www.chris.com/ascii/index.php?art=objects/cameras"> chris
 * .com </a>
 */
public class CameraVisionRedux extends Subsystem {
	// There is a problem with camera names changing. If a change is
	// suspected, find the correct name using the roborio web interface.
	private static final String CAMERA_1 = "cam0";
	private static final String CAMERA_2 = "cam1";

	/*
	 * The quality of the compressed image sent to the smartDashboard. Used by
	 * the camera servers setQuality() method.
	 */
	private static final int CAMERA_QUALITY = 80;

	private static final CameraVisionRedux INSTANCE = new CameraVisionRedux();

	private CameraServer camServer;

	Image frame;

	int frontCam;
	int backCam;

	int curCam;

	/**
	 * True if the camera failed. This will prevent any code from trying to
	 * capture an image, which may through an exception causing the robot to
	 * crash.
	 */
	boolean failure;

	private CameraVisionRedux() {
		camServer = CameraServer.getInstance();

		failure = false;
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

		try {
			frontCam = NIVision.IMAQdxOpenCamera(CAMERA_1, NIVision.IMAQdxCameraControlMode.CameraControlModeListener);
		} catch (Exception e) {
			// Print error and disable both cameras
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("First camera failed");
			FRCNetworkCommunicationsLibrary.HALSetErrorData("First Camera Failed");
			failure = true;
		}

		try {
			backCam = NIVision.IMAQdxOpenCamera(CAMERA_2, NIVision.IMAQdxCameraControlMode.CameraControlModeListener);
			
			//If the first cam fails then set it equal to back cam and only use the back camera.
			if(failure == true){
				frontCam = backCam;
				failure = false;
			}
		} catch (Exception e) {
			// Print error and disable both cameras
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("Second camera failed");
			FRCNetworkCommunicationsLibrary.HALSetErrorData("Second Camera Failed");
			failure = true;
		}

		// Default camera is Front Camera
		curCam = frontCam;
		if (failure == false) {
			NIVision.IMAQdxConfigureGrab(curCam);
			NIVision.IMAQdxStartAcquisition(curCam);
		}

		camServer.setQuality(CAMERA_QUALITY);
	}

	public static CameraVisionRedux getInstance() {
		return INSTANCE;
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new SeeWithCamera());
	}

	public void grabImage() {
		if(failure == false){
			NIVision.IMAQdxGrab(curCam, frame, 0);
			camServer.setImage(frame);
		}
	}

	/*
	 * This Switches the camera by stopping the capture then changing the
	 * current camera id then restarting on a new camera.
	 */
	public void ChangeCamera() {
		FRCNetworkCommunicationsLibrary.HALSetErrorData("Change Camera Got To Change Camera");
		if (failure == false) {
			NIVision.IMAQdxStopAcquisition(curCam);
			NIVision.IMAQdxUnconfigureAcquisition(curCam);
			System.out.println("Switching Camera ID From" + curCam);
			FRCNetworkCommunicationsLibrary.HALSetErrorData("Change Camera Test");
			// Swap cameras using ternary operator.
			// curCam = (curCam == frontCam) ? backCam : frontCam;
			try {
				if (curCam == frontCam) {

					curCam = backCam;
				} else {
					curCam = frontCam;
				}
			} catch (VisionException error) {

				FRCNetworkCommunicationsLibrary.HALSetErrorData("" + error);
			}
			System.out.println("New Camera ID" + curCam);
			try {
				NIVision.IMAQdxConfigureGrab(curCam);
				NIVision.IMAQdxStartAcquisition(curCam);
			} catch (Exception e) {
				System.out.println("INoWork,5 yin ");
			}
		}
	}
}
