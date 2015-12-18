/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package debugging;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Nate and Dane
 */
public class drive {
    
    public RobotDrive drive;

    double x;
    
    double X;
    
    double y;
    
    double Y;
    
    double z;
    
    double Z;
    
    double TargetXSpeed = 0;
	double TargetYSpeed = 0;
	double TargetZSpeed = 0;

	double CurrentXSpeed = 0;
	double CurrentYSpeed = 0;
	double CurrentZSpeed = 0;

	public double softStart = 1.0;
    
    double range,powerCurve;
    
    
    
    public drive(int rightFront, int rightBack, int leftFront, int leftBack){
        //4 motor drive
        drive = new RobotDrive(leftFront, leftBack, rightBack, rightFront);
        powerCurve = 2;
     
    }
    
    public drive(int rightFront, int rightBack, int leftFront, int leftBack,double PowerCurve){
        //4 motor drive
        drive = new RobotDrive(leftFront, leftBack, rightBack, rightFront);
        powerCurve = PowerCurve;
        
    }
    
	public void setDeadZone(double DeadZone){
		
		range = DeadZone;
	}
    
    
    public void set(int right, int left){
        //2 Motor drive
        drive = new RobotDrive(right,left);
        
        powerCurve = 2;
        
    }
    
    public void set(int right, int left,double PowerCurve){
        //2 Motor drive
        drive = new RobotDrive(right,left);
        
        powerCurve = PowerCurve;
        
    }
    
    public void setPowerCurve(double PowerCurve){
    	
    	powerCurve = PowerCurve;
    	
    }
    
    public void mechanumDrive(double forward, double straif, double rotation, double throttle){
        //straif = side to side
        //Dead Zone defines how much the joystick can be displaced before the robot responds
        
        y = forward;
        
        x = straif;
        
        z = rotation;
        SmartDashboard.putNumber("X-Axis", x);
        SmartDashboard.putNumber("Y-Axis", y);
        SmartDashboard.putNumber("Z-Axis", z);
        
        //.04
        //Square function put in for better control of the robot at different speeds, so drivers
        //have more control and makes a better all around driving Experience
        //inverting and squaring joysticks
        if(x < -range){
            //x^powerCurve
            X =  Math.pow(x, powerCurve);
            
        }else if(x > range) {
            //-(x^powerCurve)
            //or robot would only move 1 direction
            X =  -(Math.pow(x, powerCurve));
            
        }else{
            
            X = 0;
            
        }
        
        if(y < -range){
            
            Y =  Math.pow(y, powerCurve);
            
        }else if(y > range) {
            
            Y =  -(Math.pow(y, powerCurve));
            
        }else{
            
            Y = 0;
            
        }
        
        if(z < -range){
            
            Z =  Math.pow(z, powerCurve);
            
        }else if(z > range) {
            
            Z =  -(Math.pow(z, powerCurve));
            
        }else{
            
            Z = 0;
            
        }
        
        CurrentXSpeed = CurrentXSpeed + ((X - CurrentXSpeed) * softStart);
    	CurrentYSpeed = CurrentYSpeed + ((Y - CurrentYSpeed) * softStart);
    	CurrentZSpeed = CurrentZSpeed + ((Z - CurrentZSpeed) * softStart);
        
        drive.mecanumDrive_Cartesian(CurrentXSpeed * throttle, CurrentZSpeed, CurrentYSpeed * throttle, 0);
        
    }
    
    public void mechanumDrive(double forward, double straif, double rotation, double deadZone, double throttle, float angle){
        //straif = side to side
        //Dead Zone defines how much the joystick can be displaced before the robot responds
        
        y = forward;
        
        x = straif;
        
        z = rotation;
        SmartDashboard.putNumber("X-Axis", x);
        SmartDashboard.putNumber("Y-Axis", y);
        SmartDashboard.putNumber("Z-Axis", z);
        
        //.04
        range = deadZone;
        //Square function put in for better control of the robot at different speeds, so drivers
        //have more control and makes a better all around driving Experience
        //inverting and squaring joysticks
        if(x < -range){
            //x^powerCurve
            X =  -Math.pow(x, powerCurve);
            
        }else if(x > range) {
            //-(x^powerCurve)
            //or robot would only move 1 direction
            X =  (Math.pow(x, powerCurve));
            
        }else{
            
            X = 0;
            
        }
        
        if(y < -range){
            
            Y =  Math.pow(y, powerCurve);
            
        }else if(y > range) {
            
            Y =  -(Math.pow(y, powerCurve));
            
        }else{
            
            Y = 0;
            
        }
        
        if(z < -range){
            
            Z =  Math.pow(z, powerCurve);
            
        }else if(z > range) {
            
            Z =  -(Math.pow(z, powerCurve));
            
        }else{
            
            Z = 0;
            
        }
        
        drive.mecanumDrive_Cartesian(-X * throttle, Z, Y * throttle, angle);
        
    }
    
    
    public void arcadeDrive(double forward, double rotation, double deadZone, double throtle) {
        //Dead Zone defines how much the joystick can be displaced before the robot responds
        //Square function put in for better control of the robot at different speeds, so drivers
        //have more control and makes a better all around driving Experience
        //inverting and squaring joysticks
        y = forward;
      
        z = rotation;
        
        if(z < -deadZone){
            
            X =  Math.pow(z , powerCurve);
           
        }else if(z > deadZone) {
            
            X =  -(Math.pow(z, powerCurve));
            
        }else{
            
            X = 0;
            
        }
        
        if(y < deadZone){
            
            Y =  Math.pow(y , powerCurve);
            
        }else if(y > deadZone) {
            
            Y =  -(Math.pow(y , powerCurve));
            
        }else{
            
            Y = 0;
            
        }
        
        CurrentXSpeed = CurrentXSpeed + ((X - CurrentXSpeed) * softStart);
    	CurrentYSpeed = CurrentYSpeed + ((Y - CurrentYSpeed) * softStart);
    	CurrentZSpeed = CurrentZSpeed + ((Z - CurrentZSpeed) * softStart);
        
        drive.arcadeDrive(CurrentYSpeed * throtle, CurrentXSpeed * throtle);
        
        
        
    }
    
    public void tankDrive(double Y1, double Y2, double deadZone){
        //Dead Zone defines how much the joystick can be displaced before the robot responds
        //Square function put in for better control of the robot at different speeds, so drivers
        //have more control and makes a better all around driving Experience
        //inverting and squaring joysticks
        if(Y1 < -deadZone){
            
            X =  Math.pow(Y1 , powerCurve);
           
        }else if(Y1 > deadZone) {
            
            X =  -(Math.pow(Y1, powerCurve));
            
        }else{
            
            X = 0;
            
        }
        
        if(Y2 < deadZone){
            
            Y =  Math.pow(Y2 , powerCurve);
            
        }else if(Y2 > deadZone) {
            
            Y =  -(Math.pow(Y2 , powerCurve));
            
        }else{
            
            Y = 0;
            
        }
        
        drive.tankDrive(X, Y);
    }

    public void stopMotors() {

        drive.mecanumDrive_Cartesian(0, 0, 0, 0);
        
    }
    
    public void setSoftStart(double value){
    	value = Math.min(value, 1.0);		// softstart must be less <= 1
    	value = Math.max(0.01,  value);		// softstart must be > .01
    	softStart = value;    	
    }
    
}

