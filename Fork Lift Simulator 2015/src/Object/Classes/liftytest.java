package Object.Classes;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class liftytest extends blargleWargleDoodleBop {

	public liftytest() {// err

	}

	public void startAction() {// err
		test.startAction();

		// periodic?

		// look at the autonomous mode to see how this works

	}

	public void endAction() {
		test.endAction();
		SmartDashboard.putString("Elevator gone up: ",
				"" + e.hasGoneUpOneFoot());

		othertest.endAction();
		SmartDashboard.putString("Elevator gone down: ",
				"" + e.hasGoneDownOneFoot());
		done = true;
		// disable elevationer
	}

	public void periodic() {
		super.test.periodic();
		if (test.isFinished()) {
			othertest.startAction();
		}
		othertest.periodic();
	}
}
