package Object.Classes;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;

public class intakeWheel {
	
	Relay moter,moterLever;
	
	DigitalInput max,min;
	
	public intakeWheel(int i, int i1, int i2, int i3){
		
		
		moter = new Relay(i);
		
		moterLever = new Relay(i1);
		
		max = new DigitalInput(i2);
		
		min = new DigitalInput(i3);
		
	}
	
	public void in(){
		
		moter.set(Relay.Value.kForward);
		
	}
	
	public boolean isIn(){
		
		return max.get();
		
	}
	
	public boolean isOut(){
		
		return min.get();
		
	}
	
	public void out(){
		
		moter.set(Relay.Value.kReverse);
		
	}
	
	@SuppressWarnings("unused")
	public void posIn(){
		
		if(!max.get()){
		
			moterLever.set(Relay.Value.kReverse);
			
		}else{
			
			moterLever.set(Relay.Value.kOff);
			
		}
		
	}
	
	@SuppressWarnings("unused")
	public void posOut(){
		
		if(!min.get()){
		
			moterLever.set(Relay.Value.kReverse);
		
		}else{
			
			moterLever.set(Relay.Value.kOff);
			
		}
		
	}
	
	public void off(){
		
		moter.set(Relay.Value.kOff);
		
	}

}
