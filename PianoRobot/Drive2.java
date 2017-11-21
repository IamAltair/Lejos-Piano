import lejos.hardware.motor.*;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.hardware.*;
import lejos.hardware.port.Port;
import lejos.robotics.SampleProvider;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.Button;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.motor.*;
import lejos.hardware.lcd.*;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.NXTTouchSensor;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.hardware.sensor.NXTColorSensor;
import lejos.hardware.sensor.NXTSoundSensor;
import lejos.hardware.sensor.NXTUltrasonicSensor;
import lejos.hardware.port.Port;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.hardware.Keys;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.hardware.sensor.*;
import lejos.hardware.Sound;
import lejos.utility.Delay;
import lejos.hardware.ev3.LocalEV3;
import java.io.File;
import java.io.FileInputStream;
import java.io.Closeable;
import lejos.hardware.Device;
import lejos.hardware.*;
import lejos.*;
import lejos.hardware.motor.*;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.hardware.*;
import lejos.robotics.SampleProvider;
import lejos.robotics.SampleProvider;
import lejos.hardware.Sound;
import lejos.utility.Delay;
import lejos.hardware.ev3.LocalEV3;
import java.io.File;
import java.io.FileInputStream;
import lejos.hardware.sensor.*;
import lejos.hardware.port.*;
import lejos.robotics.SampleProvider;
import lejos.hardware.motor.*;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.hardware.*;
import lejos.hardware.port.Port;
import lejos.robotics.SampleProvider;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.Button;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.motor.*;
import lejos.hardware.lcd.*;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.NXTTouchSensor;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.hardware.sensor.NXTColorSensor;
import lejos.hardware.sensor.NXTSoundSensor;
import lejos.hardware.sensor.NXTUltrasonicSensor;
import lejos.hardware.port.Port;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.hardware.Keys;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.hardware.sensor.*;
import lejos.hardware.Sound;
import lejos.utility.Delay;
import lejos.hardware.ev3.LocalEV3;
import java.io.File;
import java.io.FileInputStream;
import lejos.hardware.motor.*;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.hardware.*;
import lejos.hardware.port.Port;
import lejos.robotics.SampleProvider;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.Button;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.motor.*;
import lejos.hardware.lcd.*;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.NXTTouchSensor;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.hardware.sensor.NXTColorSensor;
import lejos.hardware.sensor.NXTSoundSensor;
import lejos.hardware.sensor.NXTUltrasonicSensor;
import lejos.hardware.port.Port;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.hardware.Keys;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.hardware.sensor.*;
import lejos.hardware.Sound;
import lejos.utility.Delay;
import lejos.hardware.ev3.LocalEV3;
import java.io.File;
import java.io.FileInputStream;
import java.io.Closeable;
import lejos.hardware.Device;
import lejos.hardware.*;
import lejos.*;

 class Ultrasonic {
	private static SensorModes sensor;
	private float[] ultraSample;
	private static SampleProvider ultraLeser;


	public Ultrasonic(Port port) {

		sensor = new EV3UltrasonicSensor(port);
		ultraLeser = sensor.getMode("Distance");
		ultraSample = new float [ultraLeser.sampleSize()];

	}

	public float getUltraSample() {
		ultraLeser.fetchSample(ultraSample, 0);
		return ultraSample[0]*100;
	}
}



class Tangenter {
	private double bpm;
	private double mpb;
	private double standarNote;
	private double i = 0;
	private double lengdeCM;
	private double vei = 0;
	private double v = 0;
	private double h = 0;
	private long a = 0;
	private double c = 0;
	private double s = 0;
	private double b = 0;

	private Ultrasonic uss = new Ultrasonic(SensorPort.S4);

	public Tangenter(double lengde) {
		this.bpm = bpm;
		this.lengdeCM = lengdeCM;

		Motor.A.setSpeed(50);
		Motor.B.setSpeed(50);
		Motor.C.setSpeed(50);
		Motor.D.setSpeed(50);
	}

	public void spillNote(char note, int oktav, boolean skarp, double lengde) throws Exception {
		bevegTilAvstand(note, oktav, skarp);
		fingering(lengde);
	}


	public void fingering(double lengde) throws Exception {
		if (v<h) {
			fingeringV(lengde);
		} else {fingeringH(lengde);}
	}

	public void fingeringH(double lengde) throws Exception {
		Motor.C.rotate(45);
		c = lengde*6/9*1000*4;
		long a = (long) c;
		Thread.sleep(a);
		Motor.D.rotate(-45);
	}

	public void fingeringV(double lengde) throws Exception {
		Motor.D.rotate(45);
		c = lengde*6/9*1000*4;
		long a = (long) c;
		Thread.sleep(a);
		Motor.C.rotate(-45);
	}
// mkay

	public void bevegTilAvstand(char note, int oktav, boolean skarp) {

		v =  finnVei(note, oktav, skarp);
		h = finnVei(note, oktav, skarp)-17;
		float a = uss.getUltraSample();

		if(Math.abs(a - v) >= Math.abs(a - h)) {
			vei = h;
			} else {vei = v;}

		if(vei> a) {
			while(a<vei){
				a = uss.getUltraSample();
				Motor.B.backward();
				Motor.A.forward();
			}
		}

		else {
			while(a>vei){
				a = uss.getUltraSample();
				Motor.B.forward();
				Motor.A.backward();
			}
		}
		Motor.A.stop(true);
		Motor.B.stop(true);

	}

	public double finnVei(char note, int oktav, boolean skarp) { // Finner vei basert p Hre offset
		return (lengdeCM/22*noteTilVerdi(note, oktav, skarp))+5;
	}

	public double noteTilVerdi(char note, int oktav, boolean skarp) { // Gjr note om til verdi
		i = 0;
		switch (note) {
					case 'C':  i = 1;
							 break;
					case 'D':  i=2;
							 break;
					case 'E':  i=3;
							 break;
					case 'F':  i=4;
							 break;
					case 'G':  i = 5;
							 break;
					case 'A':  i = 6;
							 break;
					default: i=7;
							break;
		}
		i = i+(oktav-1);
		if (skarp = true && i < 22) {
			i += 0.5;
		}
		return i;
	}
}


public class Drive2
{
	public static void main(String[] args) throws Exception{



		System.out.println("hei paa dei");
		Tangenter jens = new Tangenter(43);
		System.out.println("Spill noter");
		jens.bevegTilAvstand('A',2,false);
		Thread.sleep(2000);
		jens.bevegTilAvstand('H',2,false);

	}

}
