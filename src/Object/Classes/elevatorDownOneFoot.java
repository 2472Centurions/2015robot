package Object.Classes;

public class elevatorDownOneFoot extends Action{
	
	Elevator elevate;
	
	public elevatorDownOneFoot(Elevator e ){
		
		elevate = e;
		
	}
	
	public void startAction(){
		
		elevate.moveOneFoot(0);
		
	}
	
	public void endAction(){
		
		elevate.stopMoters();
		
	}
	
	public void periodic(){
		
		endFactor = elevate.hasGoneDownOneFoot();
		
		elevate.down(1);
		
		
		
	}

}
