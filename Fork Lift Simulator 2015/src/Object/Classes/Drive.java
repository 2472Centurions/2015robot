
package Object.Classes;

import edu.wpi.first.wpilibj.*;

public class Drive {
	Talon[] moter = new Talon[4];
	Talon a,b,c,e;  // fL fR bL bR
	
	double x;
	double y;
	double z;
	double X;
	double Y;
	double Z;
	private RobotDrive d;
	double range,powerCurve;
	public Drive(int a, int b, int c, int e){
		moter[0] = new Talon(a);
		moter[1] = new Talon(b);
		moter[2] = new Talon(c);
		moter[3] = new Talon(e);
		d = new RobotDrive(moter[0],moter[1],moter[2],moter[3]);
	}
	  public Drive(int a, int b){
		  moter[0] = new Talon(a);
	      moter[1] = new Talon(b);
	  }
	public void setPowerCurve(double PowerCurve){
    	
    	powerCurve = PowerCurve;
    }
	
	public void setDeadZone(double DeadZone){
		
		range = DeadZone;
	}
	
	public void mecanumDrive(double x, double y, double z, double throttle){
		
		double X,Y,Z;

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
            
            Z =  -Math.pow(z, powerCurve);
            
        }else if(z > range) {
            
            Z =  (Math.pow(z, powerCurve));
            
        }else{
            
            Z = 0;
            
        }
		
		
		d.mecanumDrive_Cartesian(X * throttle, Y * throttle, Z * throttle, 0);
		
     }
		

	
	

	public void runMotor(int i , double speed){
	
		moter[i].set(speed); 
	
	}
	public void arcadeDrive(double forward, double rotation, double throtle){

	
       
        y = forward;
      
        z = rotation;
      
        if(z < -range){
            
            X =  Math.pow(z , powerCurve);
           
        }else if(z > range) {
            
            X =  -(Math.pow(z, powerCurve));
            
        }else{
            
            X = 0;
            
        }
        
        if(y < range){
            
            Y =  -Math.pow(y , powerCurve);
            
        }else if(y > range) {
            
            Y =  (Math.pow(y , powerCurve));
            
        }else{
            
            Y = 0;
            
        }
        
        d.arcadeDrive(Y * throtle, X * throtle);
        
        
        
    }


	public void fieldDrive(double x, double y, double z, double throttle, float angle){
		
		double X,Y,Z;

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
            
            Y =  -Math.pow(y, powerCurve);
            
        }else if(y > range) {
            
            Y =  (Math.pow(y, powerCurve));
            
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
        
        double strafe = X;
        
        double fwd = Y;
        
        double pi = 3.1415926;
        
        double curr_gyro_angle_degrees = -angle;
        
        double curr_gyro_angle_radians = ((curr_gyro_angle_degrees * pi)/180);
        
        double temp = (fwd * Math.cos(curr_gyro_angle_radians)) + (strafe * Math.sin(curr_gyro_angle_radians));
        
        strafe = ((-fwd * Math.sin(curr_gyro_angle_radians)) + (strafe * Math.cos(curr_gyro_angle_radians)));
        
        fwd = temp;
        
        d.mecanumDrive_Cartesian(strafe * throttle, fwd * throttle, Z, 0);
		
	}
	
	
	public void tankDrive(double Y1, double Y2, double throttle){
   
		if(Y1 < -range){
			
			X =  Math.pow(Y1 , powerCurve);
       
		}else if(Y1 > range) {
        
			X =  -(Math.pow(Y1, powerCurve));
        
		}else{
        
			X = 0;
        
        }
    
		if(Y2 < range){
        
			Y =  Math.pow(Y2 , powerCurve);
        
		}else if(Y2 > range) {
        
			Y =  -(Math.pow(Y2 , powerCurve));
        
		}else{
        
			Y = 0;
        
    	}
    
		d.tankDrive(X, Y);
		
	}
}
