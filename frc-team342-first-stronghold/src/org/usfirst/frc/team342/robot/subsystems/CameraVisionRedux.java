package org.usfirst.frc.team342.robot.subsystems;

import org.usfirst.frc.team342.robot.commands.camera.SeeWithCamera;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.Rect;
import com.ni.vision.NIVision.ShapeMode;
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
	private static final String CAMERA_1 = "cam1";
	// private static final String CAMERA_2 = "cam0";

	/*
	 * The quality of the compressed image sent to the smartDashboard. Used by
	 * the camera servers setQuality() method.
	 */
	private static final int CAMERA_QUALITY = 70;

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
			System.out.println("Cam0 ID: " + frontCam);
		} catch (VisionException e) {
			// Print error and disable both cameras
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("First camera failed");
			FRCNetworkCommunicationsLibrary.HALSetErrorData("First Camera Failed");
			failure = true;
		}

		// try {
		// backCam = NIVision.IMAQdxOpenCamera(CAMERA_2,
		// NIVision.IMAQdxCameraControlMode.CameraControlModeListener);
		// System.out.println("Cam1 ID: " + backCam);
		//
		// // If the first cam fails then set it equal to back cam and only use
		// // the back camera.
		// if (failure == true) {
		// frontCam = backCam;
		// failure = false;
		// }
		// } catch (VisionException e) {
		// // Print error and disable both cameras
		// e.printStackTrace();
		// System.out.println(e.getMessage());
		// System.out.println("Second camera failed");
		// FRCNetworkCommunicationsLibrary.HALSetErrorData("Second Camera
		// Failed");
		// failure = true;
		// }

		// Default camera is Front Camera
		curCam = frontCam;
		if (!failure) {
			NIVision.IMAQdxConfigureGrab(curCam);
			NIVision.IMAQdxStartAcquisition(curCam);
		}

		camServer.setQuality(CAMERA_QUALITY);
		camServer.setSize(2);
	}

	public static CameraVisionRedux getInstance() {
		return INSTANCE;
	}

	@Override
	protected void initDefaultCommand() {
		SeeWithCamera defaultCom = new SeeWithCamera();
		defaultCom.setRunWhenDisabled(true);
		setDefaultCommand(defaultCom);
	}

	public void grabImage() {
		if (!failure) {
			NIVision.IMAQdxGrab(curCam, frame, 0);

			// NIVision.imaqDrawLineOnImage(frame, frame, DrawMode.DRAW_INVERT,
			// new NIVision.Point(0, 50),
			// new NIVision.Point(100, 50), (float) 0x0);

			// NIVision.imaqDrawShapeOnImage(frame, frame, new Rect(0, 0, 100,
			// 100), DrawMode.PAINT_INVERT,
			// ShapeMode.SHAPE_RECT, 0x000000);

			camServer.setImage(frame);
		}
	}

	/*
	 * This Switches the camera by stopping the capture then changing the
	 * current camera id then restarting on a new camera.
	 */
	public void ChangeCamera() {
		if (failure == false) {
			NIVision.IMAQdxStopAcquisition(curCam);
			NIVision.IMAQdxUnconfigureAcquisition(curCam);
			System.out.println("Switching Camera ID From " + curCam);

			if (curCam == frontCam) {
				// curCam = backCam;
			} else {
				curCam = frontCam;
			}

			System.out.println("New Camera ID " + curCam);
			try {
				NIVision.IMAQdxConfigureGrab(curCam);
				NIVision.IMAQdxStartAcquisition(curCam);
			} catch (VisionException e) {
				e.printStackTrace();
				FRCNetworkCommunicationsLibrary.HALSetErrorData("Failed changing cameras.");
				failure = true;
			}
		}
	}
}
