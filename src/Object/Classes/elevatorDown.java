package Object.Classes;

public class elevatorDown extends Action{
	
	Elevator elevate;
	
	public elevatorDown(Elevator e ){
	
		elevate = e;
		
	}
	
	public void startAction(){
		
		setTimeOut();
		
		overRideFailSafe();
		
	}
	
	public void endAction(){
		
		elevate.stopMoters();
		
	}
	
	public void periodic(){
		
		endFactor = elevate.isAtTop();
		
		elevate.up(1);
		
		
	}

	
	//I want Indefinatly UP (to limit switch), and up/down one foot
	
}
