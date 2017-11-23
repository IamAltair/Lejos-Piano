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
		float a = ultraSample[0]*100;
		a = Math.round(a*1000)/1000;
		return a;
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

	public Tangenter(double lengdeCM) {
		this.bpm = bpm;
		this.lengdeCM = lengdeCM;

		Motor.B.setSpeed(100);
		Motor.C.setSpeed(900);
		Motor.D.setSpeed(900);
	}

	public void spillNote(char note, int oktav, boolean skarp, double lengde) throws Exception {
		System.out.println("note = " + note + ", oktav = " +  oktav + " informasjon.");
		bevegTilAvstand(note, oktav, skarp);
		fingering(lengde);
		System.out.println("note = " + note + ", oktav = " +  oktav + " informasjon END. ");
		System.out.println();

	}

	public void spillNoteOPT(double note, double lengde) throws Exception {
			System.out.println(note);
			bevegTilAvstandOPT(note);
			fingering(lengde);
			System.out.println(note + " er ferdig");
			System.out.println();

	}


	public void fingering(double lengde) throws Exception {
		float a = uss.getUltraSample();
		if (Math.abs(a - v) > Math.abs(a - h)) {
			fingeringH(lengde);
		} else {fingeringV(lengde);}
	}

	public void fingeringH(double lengde) throws Exception {

		System.out.println("tapp dat H");
		Motor.D.rotate(-60);
		c = lengde*6/9*1000*4;
		long a = (long) c;
		Thread.sleep(a);
		Motor.D.rotate(60);
	}

	public void fingeringV(double lengde) throws Exception {

		System.out.println("tapp dat V");
		Motor.C.rotate(-60);
		c = lengde*6/9*1000*4;
		long a = (long) c;
		Thread.sleep(a);
		Motor.C.rotate(60);
	}
// mkay
		public void bevegTilAvstandOPT(double note) {
		float a = uss.getUltraSample();
		vei =  (lengdeCM/22*note)+4.5;
		/*h = (lengdeCM/22*note)+5.0-16.0;
		System.out.println("Mål er for v: " + v);
		System.out.println("Mål er for h: " + h);
		float a = uss.getUltraSample();

		if(Math.abs(a - v) >= Math.abs(a - h)) {
			vei = h;
			System.out.println("vei = H = " + h);
			} else {vei = v;
		System.out.println("vei = V = " + v);}*/

		System.out.println("Vei - a = " + (vei-a));

		if(((vei-a)<-0.5) || (0.5<(vei-a))) {
		if(vei-a>=0) {
			while(vei-a>0){
				a = uss.getUltraSample();
				Motor.B.backward();
			}
		}

		else {
			while(vei-a<0){
				a = uss.getUltraSample();
				Motor.B.forward();
			}
		}
		Motor.B.stop();
		} else {System.out.println("står stille");}

	}

	public void bevegTilAvstand(char note, int oktav, boolean skarp) {

		v =  finnVei(note, oktav, skarp);
		h = finnVei(note, oktav, skarp)-17;
		float a = uss.getUltraSample();

		if(Math.abs(a - v) >= Math.abs(a - h)) {
			vei = h;
			System.out.println("H");
			} else {vei = v;
		System.out.println("V");}

		System.out.println("Vei - a = " + (vei-a));

		if(vei-a>=0) {
			while(vei-a>=0){
				a = uss.getUltraSample();
				Motor.B.backward();
			}
		}

		else {
			while(vei-a<=0){
				a = uss.getUltraSample();
				Motor.B.forward();
			}
		}
		System.out.println("Vei - a = " + (vei-a) + "Slut");
		Motor.A.stop(true);
		Motor.B.stop(true);

	}

	public double finnVei(char note, int oktav, boolean skarp) { // Finner vei basert p Hre offset
		System.out.println(("finnVei = " + (lengdeCM/22*noteTilVerdi(note, oktav, skarp))+5.0));
		return (lengdeCM/22*noteTilVerdi(note, oktav, skarp))+5.0;
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
		i = i+(oktav-1)*7;
		if (skarp == true) {
			i += 0.5;
		}
		System.out.println("note til verdi = " + i);
		return i;
	}
}


public class Drive5
{
	public static void main(String[] args) throws Exception{

		Tangenter jens = new Tangenter(44);

		Port s2 = LocalEV3.get().getPort("S2");
		EV3ColorSensor colorSensor = new EV3ColorSensor(s2);
		SampleProvider colorProvider = colorSensor.getRedMode();
		float[] colorSample = new float[colorProvider.sampleSize()];
		int array[] = new int[27];
		System.out.println("Start");
		Motor.A.setSpeed(300);

		for(int i = 0; i < array.length; i++){

		 colorProvider.fetchSample(colorSample, 0);
		 if(colorSample[0] > 0.3){array[i] = 1;}else
		 if(colorSample[0] <= 0.3f && colorSample[0] > 0.1f){array[i] = 2;}
		 else { array[i] = 3; }
		 Motor.A.forward();
		 Thread.sleep(258);
	 	 Motor.A.stop();
	 	 Thread.sleep(200);

		}
		Motor.A.stop();

		for(int i = 0; i < array.length; i++){
			jens.spillNoteOPT(array[i],1/4);
			System.out.println(array[i]);
		}

		Thread.sleep(5000);
		System.out.println("Lise gikk til skolen");
		jens.spillNoteOPT(1,1/4);
		jens.spillNoteOPT(2,1/4);
		jens.spillNoteOPT(3,1/4);
		jens.spillNoteOPT(4,1/4);
		jens.spillNoteOPT(5,1/4);
		jens.spillNoteOPT(5,1/4);
		jens.spillNoteOPT(6,1/4);
		jens.spillNoteOPT(6,1/4);
		jens.spillNoteOPT(6,1/4);
		jens.spillNoteOPT(6,1/4);
		jens.spillNoteOPT(5,1/4);
		jens.spillNoteOPT(4,1/4);
		jens.spillNoteOPT(4,1/4);
		jens.spillNoteOPT(4,1/4);
		jens.spillNoteOPT(4,1/4);
		jens.spillNoteOPT(3,1/4);
		jens.spillNoteOPT(3,1/4);
		jens.spillNoteOPT(2,1/4);
		jens.spillNoteOPT(2,1/4);
		jens.spillNoteOPT(2,1/4);
		jens.spillNoteOPT(2,1/4);
		jens.spillNoteOPT(1,1/4);
		Thread.sleep(200);
		System.out.println("hei");

	}

}
