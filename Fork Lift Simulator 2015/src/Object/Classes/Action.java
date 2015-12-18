package Object.Classes;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Action {
	private long startTime,endTime;
	
	public boolean endFactor;
	
	public boolean done = false;
	
	public Action(){
		
		
		
	}
	
	
	public void startAction(){
		
		setTimeOut();
		
	}
	
	public void endAction(){
		
		
		
	}
	
	public boolean isFinished(){
		
		if(timeout() || endFactor){
			
			endAction();
			
			return true;
			
		}else{
			
			return false;
			
			
		}
		

		
	}
	
	private boolean timeout(){
		
		return endTime <= System.currentTimeMillis();
		
	}
	
	public void overRideFailSafe(){
		
		long timeAdded = 1000000000;
		
		endTime = endTime + timeAdded;
		
	}

	public void periodic(){
		
		
		
	}
	
	public void setTimeOut(){
		
		startTime = System.currentTimeMillis();
		
		endTime = startTime + 5000;
		
		SmartDashboard.putNumber("Action Timeout Time", endTime);
		
	}

}
