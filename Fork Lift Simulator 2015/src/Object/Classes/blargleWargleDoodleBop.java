package Object.Classes;

import com.kauailabs.nav6.frc.IMU;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Talon;

public class blargleWargleDoodleBop {

	Talon[] moter = { new Talon(1), new Talon(2), new Talon(3), new Talon(4) };

	DigitalInput elevatorMin = new DigitalInput(5);

	DigitalInput elevatorMax = new DigitalInput(6);

	Talontest t = new Talontest();

	switchytest s = new switchytest();

	liftytest l = new liftytest();

	imutest i = new imutest();

	IMU shtuff = new IMU(null);

	public boolean done = false;

	public blargleWargleDoodleBop() {
		l.startAction();
		
		i.startAction(shtuff);
		
		s.startAction();
		
		t.startAction();
	}
}
