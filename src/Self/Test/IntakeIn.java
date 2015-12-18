package Self.Test;
import Object.Classes.*;

import Object.Classes.intakeWheel;

public class IntakeIn extends Action{

intakeWheel[] in;
	
	int motor;
	
	public IntakeIn(int PWM, intakeWheel[] intake){
		
		in = intake;
		
		motor = PWM;
		
	}
	
	public void startAction(){
		
		super.startAction();
		
		
		endFactor = false;
		
	}
	
	public void endAction(){
		
		in[motor].off();
		
	}
	
	public void periodic(){
		
		endFactor = (in[motor].isIn());
		
		in[motor].posIn();
		
	}
	
}
