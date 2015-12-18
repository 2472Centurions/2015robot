
package Object.Classes;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
//TODO New Spelling for Motor.... change the second o to an e
public class Elevator {
	Talon a,b;
	int ammountUp = 0;

	int dir;
	double startDist;
	public Encoder encoder = new Encoder(8,9);
	DigitalInput elevatorMax = new DigitalInput(0);
	DigitalInput elevatorMin = new DigitalInput(1); 
	
	public Elevator(int ElevatorMoter1, int ElevatorMoter2){

		a = new Talon(ElevatorMoter1);
		
		b = new Talon(ElevatorMoter2);
		
		
    	
    	encoder.startLiveWindowMode();
		//20 (.05^-1) rotations per 1 foot
    	encoder.setDistancePerPulse(.00015);
		
    	encoder.setMaxPeriod(2);
		
    	encoder.setReverseDirection(false);
    	
    }
	
	public void setDistancePerPulse(double d){
		
		encoder.setDistancePerPulse(d);
		
	}

	public void moveOneFoot(double feet){
		
		startDist = -encoder.getDistance() + feet;
		
	}
	
	public boolean hasGoneUpOneFoot(){
		
		return Math.abs(encoder.getDistance()) >= startDist;
		
		/*if(!elevatorMax.get()){
		
			return Math.abs(encoder.getDistance()) >= startDist;
		
		}else{
			
			return elevatorMin.get();
			
		}*/
		
	}
	
	public boolean hasGoneDownOneFoot(){
		
		if(!elevatorMax.get()){
		
			return Math.abs(encoder.getDistance()) <= (startDist - 1);
		
		}else{
			
			return elevatorMax.get();
			
		}
		
	}
	
	public void encoderUp(){
		
		up((int)(1 - encoder.getDistance()));
		
	}
	
	public void encoderDown(){
		
		
	}
	
	public void up(double i){
		
		if(!elevatorMax.get()){
			
			a.set(i);
			
			b.set(i);

		}else{
			
			stopMoters();
			
		}
	
	}
	
	public void down(double i){
		
		if(!elevatorMin.get()){
			
			a.set(-i);
			
			b.set(-i);
			
		}else{
			
			stopMoters();
			
		}
		
	}
	
	public boolean isAtTop(){
		
		return elevatorMax.get();
		
	}
	
	public boolean isAtBottom(){
		
		return false;
		
	}
	
	public void stopMoters(){
		
		a.set(0);
		
		b.set(0);
		
	}
	 
	public void zeroEncoder(){
		
		encoder.reset();
		
	}
}


