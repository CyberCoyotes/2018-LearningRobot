package org.usfirst.frc.team3603.robot;

import java.io.FileWriter;
import java.io.PrintWriter;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.MatchType;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

/**
 * This currently is testing custom log files.
 * @author Connor
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
	
	DataLogger logger = new DataLogger("/U/matchData/log6.txt", false);
	
	@Override
	public void robotInit() {
		t.reset();
		t.start();
		String path;
		String name = DriverStation.getInstance().getEventName();
		MatchType match_type = DriverStation.getInstance().getMatchType();
		String matchType;
		switch(match_type) {
		case Elimination:
			matchType = "Elimination";
			break;
		case Qualification:
			matchType = "Qualification";
			break;
		case None:
			matchType = "";
			break;
		case Practice:
			matchType = "Practice";
			break;
		default:
			matchType = "";
			break;
		}
		String number = Integer.toString(DriverStation.getInstance().getMatchNumber());
		String time = Double.toString(DriverStation.getInstance().getMatchTime());
		String replay = (Integer.toString(DriverStation.getInstance().getReplayNumber()));
		if(name.equals(null)) {
			name = "";
		}
		if(matchType.equals(null)) {
			matchType = "";
		}
		if(number.equals(null)) {
			number = "";
		}
		if(time.equals(null)) {
			time = "";
		}
		if(replay.equals(null)) {
			replay = "";
		}
		path ="/U/matchData/" + matchType + "_" + number + "_" + replay + "_" + time + ".txt";
		if(path.equals("/U/matchData/.txt") || path.equals(null)) {
			
			path = "/U/matchData/log_" + Double.toString(t.get()) + ".txt";
		}
		logger = new DataLogger(path, false);
		logger.writeln("Time" + "\t" + "Front Left" + "\t" + "Back Left" + "\t" + "Front Right" + "\t" + "Back Right");
		logger.flush();
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
		logger.write(t.get());
		logger.write(frontLeft.get());
		logger.write(backLeft.get());
		logger.write(frontRight.get());
		logger.writeln(backRight.get());
		logger.flush();
	}

	@Override
	public void testPeriodic() {
	}
}

