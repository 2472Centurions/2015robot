package Self.Test;
import Object.Classes.*;

public class WheelMove extends Action{
	
	Drive d;
	
	int motor;
	
	private long startTime, endTime;

	public WheelMove(int PWM, Drive MainDrive){
		
		d = MainDrive;
		
		motor = PWM;
		
	}
	
	public void startAction(){
		
		super.startAction();
		
		startTime = System.currentTimeMillis();
		
		endTime = startTime + 5000;
		
		endFactor = false;
		
	}
	
	public void endAction(){
		
		d.arcadeDrive(0, 0, 1);
		
	}
	
	public void periodic(){
		
		endFactor = (endTime < System.currentTimeMillis());
		
		d.runMotor(motor, .75);
		
	}
	
}
