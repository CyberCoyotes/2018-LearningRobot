package org.usfirst.frc.team3603.robot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

/**
 * This currently is testing custom log files.
 * @author Connor
 *
 */
public class Robot extends IterativeRobot {
	Victor frontLeft = new Victor(0);
	Victor backLeft = new Victor(1);
	Victor frontRight = new Victor(2);
	Victor backRight = new Victor(3);
	MecanumDrive mainDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
	
	Joystick drive = new Joystick(0);
	
	FileWriter fw;
	PrintWriter pw;
	
	@Override
	public void robotInit() {
		try {
			File f = new File("/U/matchData/time.txt");
			if(!f.exists()) {
				f.createNewFile();
			}
			fw = new FileWriter(f);
			pw = new PrintWriter(fw, true);
		} catch(IOException ex) {
		}
	}
	@Override
	public void autonomousInit() {
	}

	@Override
	public void autonomousPeriodic() {
	}

	@Override
	public void teleopPeriodic() {
		double x = drive.getRawAxis(0);
		double y = drive.getRawAxis(1);
		double rot = drive.getRawAxis(4);
		if(Math.abs(x) >= 0.1 || Math.abs(y) >= 0.1 || Math.abs(rot) >= 0.1) {
			mainDrive.driveCartesian(y, x, rot);
		} else {
			mainDrive.driveCartesian(0, 0, 0);
		}
		pw.println(frontLeft.get());
	}

	@Override
	public void testPeriodic() {
	}
}

