
package org.usfirst.frc.team2472.robot;

import java.util.ArrayList;

import com.kauailabs.nav6.frc.IMUAdvanced;

import debugging.drive;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import Object.Classes.*;
import Self.Test.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
@SuppressWarnings({"unused","deprecation"})//Got Annoyed by the Yellow Flags
public class Robot extends IterativeRobot {
	
	IMUAdvanced imu;
	
	SerialPort serial_port;
	
	int state = 0;
	
	double lastDistPerPulse;
	
	public String intakePos = "Out";
	
	Joystick superJoy = new Joystick(0);
	
	Joystick joypad = new Joystick(1);
	
	public double[] testNumbers = new double[2];
	
	Drive d = new Drive( 3,0,1,2 );
	
	Elevator elevate = new Elevator(5,4);
	
	intakeWheel[] in = new intakeWheel[2];
	
	ArrayList<Action> step = new ArrayList<Action>();
	
	ArrayList<Action> elevatorQ = new ArrayList<Action>();
	
	int currentAction = 0;
    
	String lastAction;
	
	public void robotInit() {
    	
    	Timer.delay(20);
    	try {
        	serial_port = new SerialPort(57600,SerialPort.Port.kUSB);
    		
    		// You can add a second parameter to modify the 
    		// update rate (in hz) from 4 to 100.  The default is 100.
    		// If you need to minimize CPU load, you can set it to a
    		// lower value, as shown here, depending upon your needs.
    		
    		// You can also use the IMUAdvanced class for advanced
    		// features.
    		
    		byte update_rate_hz = 50;
    		//imu = new IMU(serial_port,update_rate_hz);
    		imu = new IMUAdvanced(serial_port,update_rate_hz);
        	} catch( Exception ex ) {
        		
        	}
    	
    	SmartDashboard.putNumber("Elevator Distance Per Pulse", (5/1750));
    	
    	if ( imu != null ) {
            LiveWindow.addSensor("IMU", "Gyro", imu);
        }
    	
    	for(int i = 0; i < in.length; i++){
    		
    		in[i] = new intakeWheel(i, i+2,3+((i) * 2),4+((i) * 2));
    		
    	}
    	SmartDashboard.putNumber("Lift Drive Speed", .3);
    	
    	SmartDashboard.putNumber("Initial Drive Speed", .5);
    	
    	
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousInit(){
    	
    	//SmartDashboard.putBoolean("IMU Connected", imu.isConnected());
    	//TODO Put a (1) here to change to drive forward, nothing in () for normal code
    	//"" for middle container
    	//true for landfill clearing
    	//1 for drive forward
    	//
    	prepAuto(true);
    	
    	if(step.size() > 0){
    	
    		currentAction = 0;
    	
    		step.get(currentAction).startAction();
    	
    	}
    	
    	currentAction = 0;
    	
    	d.setPowerCurve(2);
    	
    	d.setDeadZone(.04);
    	
    	elevate.zeroEncoder();
    	
    	testNumbers[0] = SmartDashboard.getNumber("Initial Drive Speed");
    	
    	testNumbers[1] = SmartDashboard.getNumber("Lift Drive Speed");
    	
    }
    
    
    
	public void autonomousPeriodic() {
		
		//SmartDashboard.putNumber("IMU Yaw", imu.getYaw());
		
		SmartDashboard.putNumber("Current Time", System.currentTimeMillis());
		
		SmartDashboard.putInt("Autonomous Step", currentAction);
		
		
		if(step.size() > 0){
    	
    		if(step.get(currentAction) != null){
    	
    			if(step.get(currentAction).isFinished()){
    		
    				currentAction = currentAction + 1;
    				if(step.get(currentAction) != null){
    					
    					step.get(currentAction).startAction();
    		
    					
    					step.get(currentAction).periodic();
    				}else{
    					
    					in[1].posIn();
    					
    					in[0].posOut();
    					
    				}
    		
    			}else{
    				
    				step.get(currentAction).periodic();
    		
    			}
    	
    		}else{
    		
    			System.err.println("No Fun");
    		
    		}
    	
		}else{
			
			System.err.println("No Steps Found");
			
			
			
		}

    }

    /**
     * This function is called periodically during operator control
     */
    
    public void teleopInit(){
     	  	
    	d.setDeadZone(.04);
    	
    	d.setPowerCurve(2);
    	
    	state = 0;
    	
    	intakePos = "In";
    	
    	if(imu != null){
    		
    		SmartDashboard.putBoolean("Imu Connected", imu.isConnected());
    		
    	}
    		
    	
    }
    
    public void teleopPeriodic() {
    	
    	if(SmartDashboard.getNumber("Elevator Distance Per Pulse") != lastDistPerPulse){
    		
    		elevate.setDistancePerPulse(SmartDashboard.getNumber("Elevator Distance Per Pulse"));
    		
    	} 
        
    	/*if(superJoy.getRawButton(7)){
    		
    		imu.zeroYaw();
    		
    	}*/
    	
    	SmartDashboard.putDouble("IMU Yaw Angle", imu.getYaw());
    	
    	SmartDashboard.putNumber("X", superJoy.getX());
    	
    	SmartDashboard.putNumber("Y", superJoy.getY());
    	
    	SmartDashboard.putNumber("Z", superJoy.getZ());
    	
    	double strafe = superJoy.getX();
        
        double fwd = superJoy.getY();
        
        double pi = 3.1415926;
        
/*        double curr_gyro_angle_degrees = -imu.getYaw();
        
        double curr_gyro_angle_radians = ((curr_gyro_angle_degrees * pi)/180);
        
        double temp = (fwd * Math.cos(curr_gyro_angle_radians)) + (strafe * Math.sin(curr_gyro_angle_radians));
        
        strafe = ((-fwd * Math.sin(curr_gyro_angle_radians)) + (strafe * Math.cos(curr_gyro_angle_radians)));
        
        fwd = temp;
*/    	
    	double throttle = (-superJoy.getThrottle() + 1.0) /2.0; 
    	d.mecanumDrive(-superJoy.getX(), -superJoy.getZ(), -superJoy.getY(), 
    			throttle);
    	
    	if(elevate.isAtTop()){
    		
    		elevate.zeroEncoder();
    		
    	}
    	
    	if(joypad.getRawButton(4)){
    		
    		intakePos = "Out";
    		
    	}else if(joypad.getRawButton(1)){
    		
    		intakePos = "In";
    		
    		
    	}else if(joypad.getRawButton(3)){
    		
    		intakePos = "Spit";
    		
    	}
    	
    	if(joypad.getAxis(AxisType.kY) == -1){
    		
    		step.clear();
    		
    		step.add(new elevatorUpOneFoot(elevate,2.3));
    		
    		step.get(0).startAction();
    		
    	}
    	
    	if(joypad.getAxis(AxisType.kY) == 1){
    		
    		
    		
    	}
    	
    	if(step.size() > 0 && !step.get(0).isFinished()){
    		
    		step.get(0).periodic();
    		
    	}else{
    		
    		step.clear();
    		
    	}
    	
    	if(intakePos == "In"){
    		
    		for(int i = 0; i < in.length; i++){
    			
    			in[i].off();
    			
    		}
    		
    		in[1].posIn();
    		
    		in[0].posOut();
    		
    	}
    	
    	if(intakePos == "Out"){
    		
    		in[0].in();
    		
    		in[1].in();
    		
    		in[0].posIn();
    		
    		in[1].posOut();
    		
    	}
    	
    	if(intakePos == "Spit"){
    		
    		for(int i = 0; i < in.length; i++){
    			
    			in[i].out();
    			
    		}
    		
    		in[0].posIn();
    		
    		in[1].posOut();
    		
    	}
    	
    	if(joypad.getRawButton(7)){
    		
    		elevate.up(1);
    		
    		step.clear();
    		
    		//elevatorQ.removeAll(elevatorQ);
    		
    	}else if(joypad.getRawButton(5)){
    		
    		elevate.down(1);
    		
    		step.clear();
    		
    		//elevatorQ.removeAll(elevatorQ);
    		
    	}else if(step.size() == 0){
    		
    		elevate.stopMoters();
    		
    	}
    		/*else if(joypad.getRawButton(6)){
    	}
    		
    		if(lastAction == "Elevator Down"){
    			
    			elevatorQ.removeAll(elevatorQ);
    			
    		}
    		
    		lastAction = "Elevator Up";
    		
    		elevatorQ.add(new elevatorUpOneFoot(elevate));
    		
    	}else if(joypad.getRawButton(8)){
    		
    		if(lastAction == "Elevator Up"){
    			
    			elevatorQ.removeAll(elevatorQ);
    			
    		}
    		
    		lastAction = "Elevator Down";
    		
    		elevatorQ.add(new elevatorDownOneFoot(elevate));
    		
    	}else if(elevatorQ.size() == 0){
    		
    		elevate.stopMoters();
    		
    	}
    	
    	/*elevatorQ.get(0).periodic();
    	
    	if(elevatorQ.get(0).isFinished()){
    		
    		elevatorQ.remove(0);
    		
    		if(elevatorQ.size() > 0){
    			
    			elevatorQ.get(0).startAction();
    			
    			elevatorQ.get(0).periodic();
    			
    		}
    		
    	}*/
    	
    	//SmartDashboard.putNumber("Qued Actions", elevatorQ.size());
    	
    	SmartDashboard.putNumber("Encoder Value", -elevate.encoder.getDistance());
    	
    	lastDistPerPulse = SmartDashboard.getNumber("Elevator Distance Per Pulse");
    	
    }
    
    /**
     * This function is called periodically during test mode
     */
    
    public void testInit(){
    	
    	step.clear();
    	
    	step.add(new elevatorUp(elevate,2));
    	
    	step.get(0).startAction();
    	
    }
    public void testPeriodic() {
    	
    	if(step.get(0).isFinished()){
    		
    		
    	}else{
    	
    		step.get(0).periodic();
    	
    	}
    
    }
    
    
    
    private void prepAuto(){
    	
    	SmartDashboard.putBoolean("Auto Got Prepped", true);
    	
    	step.clear();
    	
    	step.add(new elevatorUpOneFoot(elevate,2.5));
    	
    	step.add(new driveForward(1.00,d,in));
    	
    	step.add(new elevatorDown(elevate));
    	
    	step.add(new elevatorUp(elevate,2));
    	
    	step.add(new Turn(90,d,imu));
    	
    	step.add(new driveForward(2.20,d));
    	
    	step.add(new elevatorDown(elevate));
    	
    	step.add(null);
    	
    }
    
    private void prepAuto(int i){
    	
    	SmartDashboard.putBoolean("Auto Got Prepped", true);
    	
    	step.clear();
    	
    	step.add(new driveForward(2.25,d));
    	
    	step.add(null);
    	
    }
    
private void prepAuto(String stringy){
    	
    	SmartDashboard.putBoolean("Auto Got Prepped", true);
    	
    	step.clear();
    	
    	step.add(new elevatorUpOneFoot(elevate,2.5));
    	
    	step.add(new driveBackward(.750, d));
    	
    	step.add(new Turn(90,d,imu));
    	
    	step.add(new driveForward(2.30,d));
    	
    	step.add(new elevatorDown(elevate));
    	
    	step.add(null);
    	
    }
    


	private void prepAuto(boolean bool){
	
		SmartDashboard.putBoolean("Auto Got Prepped", true);
	
		step.clear();
		
		step.add(new driveForward(2,d,.72));
	
		step.add(new elevatorUpOneFoot(elevate,1.5));
	
		step.add(new driveBackward(2, d));
		
	
	
		step.add(null);
	
	}


}
