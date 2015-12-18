package Object.Classes;

public class elevatorUpOneFoot extends Action{
	
	Elevator elevate;
	
	double dist;
	
	public elevatorUpOneFoot(Elevator e, double feet){
		
		elevate = e;
		
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
		
		
		
	}

}
