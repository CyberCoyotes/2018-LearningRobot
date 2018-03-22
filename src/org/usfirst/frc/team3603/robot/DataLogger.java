package org.usfirst.frc.team3603.robot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DataLogger {

	FileWriter fw;
	PrintWriter pw;
	boolean doPrint;
	
	public DataLogger(String path, boolean autoFlush) {
		try {
			File f = new File(path);
			f.createNewFile();
			fw = new FileWriter(f);
			pw = new PrintWriter(fw, autoFlush);
			doPrint = true;
			System.out.println("File creation successful");
		} catch(IOException ex) {
			System.out.println("File creation failed");
			ex.printStackTrace();
			doPrint = false;
		}
	}
	
	public void write(double data) {
		if(doPrint) {
			pw.print(data + ",");
		}
	}
	
	public void write(int data) {
		if(doPrint) {
			pw.print(data + ",");
		}
	}
	
	public void write(String data) {
		if(doPrint) {
			pw.print(data + ",");
		}
	}
	
	public void writeln(double data) {
		if(doPrint) {
			pw.print(data);
			pw.print("\r\n");
		}
	}
	
	public void writeln(int data) {
		if(doPrint) {
			pw.print(data);
			pw.print("\r\n");
		}
	}
	
	public void writeln(String data) {
		if(doPrint) {
			pw.print(data);
			pw.print("\r\n");
		}
	}
	
	public void flush() {
		try {
			pw.flush();
		} catch(NullPointerException ex) {
			
		}
	}
}
