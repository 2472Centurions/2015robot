package Object.Classes;

import edu.wpi.first.wpilibj.DigitalInput;

public class LimitSwitch {
	
	private DigitalInput lim;
	
	private boolean isDisabled = false;
	
	public LimitSwitch(int i){
		
		lim = new DigitalInput(i);
		
	}
	
	public void disable(){
		
		isDisabled = true;
		
	}
	
	public boolean getReading(){
		boolean returnStatement = false;
		
		if(!isDisabled && lim.get()){
			
			returnStatement = true;
			
		}
		
		return returnStatement;
		
	}
	
	public void enable(){
		
		isDisabled = false;
		
	}

}
