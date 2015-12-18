package Self.Test;
import Object.Classes.*;

public class IntakeOut extends Action{
	
	intakeWheel[] in;
	
	int motor;
	
	public IntakeOut(int PWM, intakeWheel[] intake){
		
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
		
		endFactor = (in[motor].isOut());
		
		in[motor].posOut();
		
	}

}
