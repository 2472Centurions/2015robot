package Self.Test;
import Object.Classes.*;

public class MoveIntakeWheel extends Action{

intakeWheel[] in;
	
	int motor;
	
	String dir;
	
	private long endTime;

	public MoveIntakeWheel(int PWM, intakeWheel[] intake, String direction){
		
		in = intake;
		
		motor = PWM;
		
		dir = direction;
		
	}
	
	public void startAction(){
		
		super.startAction();
		
		
		endTime = System.currentTimeMillis();
		
		endFactor = false;
		
	}
	
	public void endAction(){
		
		in[motor].off();
		
	}
	
	public void periodic(){
		
		endFactor = (System.currentTimeMillis() > endTime);
		
		if(dir == "in"){
		
			in[motor].in();
		
		}else if(dir == "out"){
			
			in[motor].out();
			
		}else{
			
			in[motor].off();
			
		}
		
	}
	
	
}
