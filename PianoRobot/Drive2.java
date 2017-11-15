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

	private Ultrasonic uss = new Ultrasonic(SensorPort.S4);

	public Tangenter(double lengde, double bpm, double standarNote) {
		this.bpm = bpm;
		this.lengdeCM = lengdeCM;

		Motor.A.setSpeed(150);
		Motor.B.setSpeed(150);
		Motor.C.setSpeed(150);
		Motor.D.setSpeed(150);
		mpb = 60/bpm*standarNote*4*1000;
	}

	public void spillNote(char note, int oktav, boolean skarp, double lengde) throws Exception {
		bevegTilAvstand(note, oktav, skarp);
		fingering(lengde);
	}

	public void spillNoteOpt(double lengde, double noteOpt) throws Exception {
		bevegTilAvstandOpt(noteOpt);
		fingering(lengde);
	}

	public void fingering(double lengde) throws Exception {
		if (v<h) {
			fingeringV(lengde);
		} else {fingeringH(lengde);}
	}

	public void fingeringH(double lengde) throws Exception {
		Motor.C.rotate(-45);
		c = mpb*lengde;
		long a = (long) c;
		Thread.sleep(a);
		Motor.C.rotate(45);
	}

	public void fingeringV(double lengde) throws Exception {
		Motor.D.rotate(45);
		c = mpb*lengde;
		long a = (long) c;
		Thread.sleep(a);
		Motor.D.rotate(-45);
	}

	public void bevegTilAvstand(char note, int oktav, boolean skarp) {
		v =  finnVeiV(note, oktav, skarp);
		h = finnVeiH(note, oktav, skarp);

		if(v >= h) {
			vei = h;
			} else {vei = v;}

			float a = uss.getUltraSample();

		if(vei>= a) {
			while(vei>=a){
				a = uss.getUltraSample();
				Motor.A.backward();
				Motor.B.forward();
			}
		}

		else {
			while(vei<=a){
				a = uss.getUltraSample();
				Motor.A.forward();
				Motor.B.backward();
			}
		}

	}

	public void bevegTilAvstandOpt(double noteOpt) { // Hvilken vei som er minst g kjrer den
				uss.getUltraSample();
				v = noteOpt+5;
				h = noteOpt+18;
				float a = uss.getUltraSample();
				if(v>=h) {
					vei = h;
				} else {vei = v;}

				if(vei>=a) {
					while(vei>=a){
						a = uss.getUltraSample();
						Motor.A.forward();
						Motor.B.backward();
					}
				}

				else {
					while(vei<=a){
						a = uss.getUltraSample();
						Motor.A.backward();
						Motor.B.forward();
					}
				}

	}



	public double finnVeiV(char note, int oktav, boolean skarp) { // Finner vei basert p Venstre Offset
		return lengdeCM/22*noteTilVerdi(note, oktav, skarp)+1+5;
	}

	public double finnVeiH(char note, int oktav, boolean skarp) { // Finner vei basert p Hre offset
		return lengdeCM/22*noteTilVerdi(note, oktav, skarp)+18+5;
	}

	public double noteTilVerdi(char note, int oktav, boolean skarp) { // Gjr note om til verdi
		i = 0;
		switch (note) {
					case 'H':  i = 2;
							 break;
					case 'C':  i = 3;
							 break;
					case 'D':  i=4;
							 break;
					case 'E':  i=5;
							 break;
					case 'F':  i=6;
							 break;
					default: i=7;
							break;
		}
		i = i*oktav;
		if (skarp = true || i <= 37) {
			i += 0.5;
		}
		return i;
	}
}


public class Drive2
{
	public static void main(String[] args) throws Exception{



		System.out.println("hei paa dei");

		System.out.println("Getting some ultrasounds");
		Thread.sleep(1000);
		Tangenter jens = new Tangenter(43, 90, 1);
		System.out.println("Fingring");
		Thread.sleep(2000);
		jens.fingeringH(1/4);
		jens.fingeringV(1);
		System.out.println("Finnvei");
		Thread.sleep(2000);
		System.out.println(jens.finnVeiV('B',3,true) + "Venstre");
		Thread.sleep(1000);
		System.out.println(jens.finnVeiH('A',2,false) + "Hoyre");
		Thread.sleep(2000);
		System.out.println("Spill noter");
		Thread.sleep(2000);
		jens.spillNote('A',2,false,1/4);

	}

}
