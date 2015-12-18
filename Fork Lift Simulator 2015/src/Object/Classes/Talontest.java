package Object.Classes;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Talontest extends blargleWargleDoodleBop // err
{
	public boolean done = false;

	public Talontest() {
		
	}

	public void startAction() {
		
		
		
	}

	public void endAction() {
		for (int i = 0; i <= 3; i++) {
			if (moter[i].isAlive())
				SmartDashboard.putString("Motor " + i, "working");
			else
			{
				SmartDashboard.putString("Motor " + i, "...Error!");
				//disable the crap out of the motors
			}
		}
		done = true;

	}

	// err
	public void periodic() {

	}

}