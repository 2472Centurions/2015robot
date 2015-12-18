package Object.Classes;

import com.kauailabs.nav6.frc.IMU;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class imutest extends blargleWargleDoodleBop {

	boolean works = false;

	public imutest() {

	}

	public void startAction(IMU thingy) {

	}

	public void endAction() {

		if (!works) {
			// turn thingy off
		}

	}

	public void periodic(IMU thingy) {
		if (thingy.isConnected()) {

			SmartDashboard.putString("Good", null);
			works = true;
		} else {

			works = false;

		}
	}

}
