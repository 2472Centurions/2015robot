package Object.Classes;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class elevatorUp extends Action{
	
	Elevator elevate;
	
	private long startTime, endTime;
	
	private long t;
	
	public elevatorUp(Elevator e, double time){
		
		elevate = e;
		
		t = (long)time;
		
	}
	
	public void startAction(){
		
		startTime = System.currentTimeMillis();
				
		endTime = (startTime + (t * 1000));
		
		setTimeOut();
		
		overRideFailSafe();
		
		endFactor = false;
		
	}
	
	public void endAction(){
		
		elevate.stopMoters();
		
	}
	
	public void periodic(){
		
		endFactor = (endTime <= System.currentTimeMillis());
		
		SmartDashboard.putBoolean("Is Finished", done);
		
		elevate.down(1);
		
		
	}

}
