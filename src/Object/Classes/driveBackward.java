package Object.Classes;

import com.kauailabs.nav6.frc.IMUAdvanced;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class driveBackward extends Action{
	
	private long startTime,endTime;
	
	private double t;
	
	private double powerCurve = 2;
	
	private Drive d;
	
	IMUAdvanced imu;
	
	private intakeWheel[] in;
	
	public driveBackward(double time, Drive RobotDrive){
		
		t = time;
		
		d = RobotDrive;
		
		d.setPowerCurve(2);
		
		d.setDeadZone(.04);
		
		//in = new intakeWheel[0];
		
	}
	
	public driveBackward(double time, Drive RobotDrive,intakeWheel[] IntakeWheels){
		
		t = time;
		
		d = RobotDrive;
		
		d.setPowerCurve(2);
		
		d.setDeadZone(.04);
		
		in = IntakeWheels;
		
	}
	
	public driveBackward(double time, Drive RobotDrive, IMUAdvanced IMU){
		
		t = time;
		
		d = RobotDrive;
		
		imu = IMU;
		
		//in = new intakeWheel[0];
		
	}
	
	public driveBackward(double time, Drive RobotDrive, IMUAdvanced IMU, double PowerCurve){
		
		t = time;
		
		d = RobotDrive;
		
		//imu = IMU;
		
		powerCurve = PowerCurve;
		
		//in = new intakeWheel[0];
		
	}
	
	
	public driveBackward(double time, Drive RobotDrive, IMUAdvanced IMU, intakeWheel[] IntakeWheels){
		
		t = time;
		
		d = RobotDrive;
		
		imu = IMU;
		
		in = IntakeWheels;
		
	}
	
	public void startAction(){
		
		super.startAction();
		//runs code in the Start Action void in Action
		startTime = System.currentTimeMillis();
		
		endTime = startTime + (long)(t * 1000);
		
		endFactor = endTime <= System.currentTimeMillis();
		
		d.setPowerCurve(powerCurve);
		
		SmartDashboard.putNumber("Drive Forward Timeout", endTime);
		
		setTimeOut();
		
		if(t > 5){
			
			overRideFailSafe();
			
		}
		
		if(imu != null){
			
			imu.zeroYaw();
			
		}
		
	}
	
	public void periodic(){
		
		endFactor = (endTime <= System.currentTimeMillis());
		
		SmartDashboard.putNumber("End Time", endTime);
		
		if(imu != null){
		
			d.arcadeDrive(-.75, (-imu.getYaw() * .06 + .2), 1);
		
		}else{
			
			d.arcadeDrive(-.75, 0, 1);
			
		}
		
		if(in != null){
		
			in[0].in();
    		
    		in[1].in();
    		
    		in[0].posIn();
    		
    		in[1].posOut();
			
		}
		
		
	}
	
	public void endAction(){
		
		d.arcadeDrive( 0 , 0 , 1 );
		if(in != null){
			for(int i = 0; i < in.length; i++){
			
				in[i].out();
			
			}
		}
		
		SmartDashboard.putBoolean("Ended", true);
		
	}

}
