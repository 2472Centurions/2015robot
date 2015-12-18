package Object.Classes;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class switchytest extends blargleWargleDoodleBop {// err

	boolean works = false;

	public switchytest() {

	}

	public void startAction() {

	}

	public void endAction() {
		if (!works) {
			// turn switches offffffff
		}
	}

	public void periodic() {
		// err
		SmartDashboard.putString("Elevator min:", "" + elevatorMin.get());
		if (elevatorMin.get())
			works = true;
		else
			works = false;
		SmartDashboard.putString("Elevator max:", "" + elevatorMax.get());
		if (elevatorMax.get())
			works = true;
		else
			works = false;
	}
}
