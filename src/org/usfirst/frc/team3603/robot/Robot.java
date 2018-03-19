package org.usfirst.frc.team3603.robot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

/**
 * This currently is testing custom log files.
 * @author Connor
 *
 */
public class Robot extends IterativeRobot {
	WPI_TalonSRX frontLeft = new WPI_TalonSRX(1);
	WPI_TalonSRX backLeft = new WPI_TalonSRX(2);
	WPI_TalonSRX frontRight = new WPI_TalonSRX(3);
	WPI_TalonSRX backRight = new WPI_TalonSRX(4);
	MecanumDrive mainDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
	
	Joystick drive = new Joystick(0);
	
	FileWriter fw;
	PrintWriter pw;
	
	Timer t = new Timer();
	
	@Override
	public void robotInit() {
		try {
			Random r = new Random();
			File f = new File("/U/matchData/log6.txt");
			if(!f.exists()) {
				System.out.println("Creating new file...");
				f.createNewFile();
				System.out.println("File created");
			} else {
				System.out.println("File exists");
			}
			fw = new FileWriter(f);
			pw = new PrintWriter(fw, false);

		} catch(IOException ex) {
			System.out.println("File failure...");
		}
		t.reset();
		t.start();
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
		pw.print(t.get() + "\t");
		pw.print(frontLeft.get() + "\t");
		pw.print(backLeft.get());
		pw.print("\r\n");
		pw.flush();
	}

	@Override
	public void testPeriodic() {
	}
}

