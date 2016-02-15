package org.usfirst.frc.team342.robot.util;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class JoystickMonitor extends Trigger {
	/** If the joystick is within the deadzone it won't be monitored. */
	private static final double DEADZONE = 0.3;

	/** The Joystick to monitor. */
	private Joystick joystick;

	public class JoystickEvent {
		private double leftStick;
		private double rightStick;

		private JoystickEvent() {
			updateValue();
		}

		public void updateValue() {
			leftStick = getLeftStick();
			rightStick = getRightStick();
		}

		public double left() {
			return leftStick;
		}

		public double right() {
			return rightStick;
		}
	}

	private final JoystickEvent lastEvent;

	/** This is an experimental command to handle responding to joystick
	 * events
	 *
	 * @param joy
	 *            The joystick to monitor
	 * @param active
	 *            Whether to start monitoring the joystick when created or
	 *            not. The joystick to monitor. */
	public JoystickMonitor(Joystick joy, boolean active) {
		joystick = joy;
		lastEvent = new JoystickEvent();
	}

	/** @return True if the joystick is outside of the deadzone and the
	 *         monitor is active. */
	@Override
	public boolean get() {
		boolean outsideDeadzone = false;

		double sum = Math.abs(getLeftStick()) + Math.abs(getRightStick());
		outsideDeadzone = sum > DEADZONE;

		lastEvent.updateValue();

		return outsideDeadzone;
	}

	public JoystickEvent getLastEvent() {
		return lastEvent;
	}

	/** The forwad value of the left joystick. This is used to reduce
	 * confusion when getting data from the joystick. It should also make it
	 * easier to adapt the code to other types of joysticks.
	 * 
	 * @return Double value between -1.0 and 1.0 */
	private double getLeftStick() {
		return joystick.getY();
	}

	/** The forwad value of the right joystick. This is used to reduce
	 * confusion when getting data from the joystick. It should also make it
	 * easier to adapt the code to other types of joysticks.
	 * 
	 * @return Double value between -1.0 and 1.0 */
	private double getRightStick() {
		return joystick.getTwist();
	}
}