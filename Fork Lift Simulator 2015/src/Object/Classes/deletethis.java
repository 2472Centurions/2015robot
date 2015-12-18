package Object.Classes;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class deletethis {
	Talontest t = new Talontest();
	switchytest s = new switchytest();
	liftytest l = new liftytest();
	long startTime,endTime;
	boolean endFactor;

	public deletethis(){
		setTimeOut();
	}
	public void startAction(){
		if(!t.done)
			t.startAction();
		else if(!s.done)
			s.startAction(); 
		else if(!l.done)
			l.startAction();
		periodic();
	}
	public void endAction(){
		
	}
	public void periodic(){
		while(!isFinished()){
			while(!t.done)
				t.periodic();
			while(!s.done)
				s.periodic();
			while(!l.done)
				l.periodic();
		}
	}
	private boolean timeout(){
		
		return endTime <= System.currentTimeMillis();
		
	}
	public void setTimeOut(){
		
		startTime = System.currentTimeMillis();
		
		endTime = startTime + 5000;
		
		SmartDashboard.putNumber("Self-Test Timeout Time", endTime);
		
	}
	public boolean isFinished(){
		
		if(timeout() || endFactor){
			
			endAction();
			
			return true;
			
		}else{
			
			return false;
		}
			
			
	}
}