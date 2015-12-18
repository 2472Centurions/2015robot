package Object.Classes;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class elevatorUpOneFoot extends Action{
	
	Elevator elevate;
	
	double dist;
	
	Drive d;
	
	public elevatorUpOneFoot(Elevator e, double feet){
		
		elevate = e;
		
		dist = feet;
		
	}
	
	public elevatorUpOneFoot(Elevator e, double feet, Drive RobotDrive){
		
		elevate = e;
		
		d = RobotDrive;
		
		dist = feet;
		
	}
	
	public void startAction(){
		
		elevate.moveOneFoot(dist);
		
		super.startAction();
		
	}
	
	public void endAction(){
		
		elevate.stopMoters();
		
	}
	
	public void periodic(){
		
		endFactor = elevate.hasGoneUpOneFoot();
		
		elevate.down(1);
		
		if(d != null){
			
			d.arcadeDrive(.72, 0, 1);
			
		}
		
	}

}
