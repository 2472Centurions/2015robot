package Object.Classes;

import com.kauailabs.nav6.frc.IMUAdvanced;

public class Turn extends Action{
	
	private int desired;
	
	private Drive d;
	
	private double speed;
	
	@SuppressWarnings("unused")
	private long startTime, endTime;
	
	private IMUAdvanced imu;
	
	public Turn(int AngleInDegrees, Drive Drive, IMUAdvanced IMU){
		
		desired = AngleInDegrees;
		
		d = Drive;
		
		imu = IMU;
		
	}

	public void startAction(){
		
		startTime = System.currentTimeMillis();
		
		endTime = startTime + (long)750;
		
		if(imu != null){
		
			imu.zeroYaw();
		
		}
		
		d.setPowerCurve(1);
		
		setTimeOut();
		
		overRideFailSafe();
		
	}
	
	public void endAction(){
		
		d.arcadeDrive(0,0,1);
		
		d.setPowerCurve(2);
		
	}
	public void periodic(){
		
		
		
		double currentAngle = (double)(-imu.getYaw());
		
		endFactor = (currentAngle > (desired - 20));
		
		speed = ((desired - currentAngle) / 100 + .4);
		
		if(Math.abs(speed) > .6){
			
			speed = .6;
			
		}
		
		d.arcadeDrive(0,speed,1);
		
		
		
	}

}
