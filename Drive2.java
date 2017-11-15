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
		return ultraSample[0];
	}




}

class Tangenter {
	double bpm;
	double mpb;
	double standarNote;
	double i = 0;
	double lengdeCM;
	double vei = 0;
	Ultrasonic ultra = new Ultrasonic(SensorPort.S1);
	double v = 0;
	double h = 0;
	long a = 0;
	double c = 0;

	public Tangenter(double lengde, double bpm, double standarNote) {
		this.bpm = bpm;
		this.lengdeCM = lengdeCM;

	//	ultra.innitiate();

		Motor.A.setSpeed(900);
		Motor.B.setSpeed(900);
		Motor.C.setSpeed(900);
		Motor.D.setSpeed(900);

		mpb = 60/bpm*standarNote*4*1000;
	}

	public void spillNote(char note, int oktav, boolean skarp, double lengde){
		bevegTilAvstand(note, oktav, skarp);
		fingering(lengde);
	}

	public void spillNoteOpt(double lengde, double noteOpt){
		bevegTilAvstandOpt(noteOpt);
		fingering(lengde);
	}

	public void fingering(double lengde) {
		if (v<h) {
			fingeringH(lengde);
		} else {fingeringV(lengde);}
	}

	public void fingeringH(double lengde) {
		Motor.C.rotate(90);
		c = mpb*lengde;
		a = (long) c;
		//Thread.sleep(a);
		Motor.C.rotateTo(100);
	}

	public void fingeringV(double lengde){
		Motor.D.rotate(90);
		c = mpb*lengde;
		a = (long) c;
	//	Thread.sleep(a);
		Motor.D.rotateTo(100);
	}







	public void bevegTilAvstand(char note, int oktav, boolean skarp) {
		if(finnVeiV(note, oktav, skarp) >= finnVeiH(note, oktav, skarp)) {
			vei = finnVeiV(note, oktav, skarp);
			} else {vei = finnVeiH(note, oktav, skarp);}
			ultra.getUltraSample();

		if(vei>=ultra.getUltraSample()) {
			while(vei>=ultra.getUltraSample()){
				ultra.getUltraSample();
				Motor.A.forward();
				Motor.B.backward();
			}
		}

		else {
			while(vei<=ultra.getUltraSample()){
				ultra.getUltraSample();
				Motor.A.backward();
				Motor.B.forward();
			}
		}

	}

	public void bevegTilAvstandOpt(double noteOpt) {

				v = noteOpt+5;
				h = noteOpt-5;
				if(v>=h) {
					vei = v;
				} else {vei = h;}

				if(vei>=ultra.getUltraSample()) {
					while(vei>=ultra.getUltraSample()){
			//			ultra.runSample();
						Motor.A.forward();
						Motor.B.backward();
					}
				}else{
					while(vei<=ultra.getUltraSample()){
		//				ultra.runSample();
						Motor.A.backward();
						Motor.B.forward();
					}
				}

	}



	public double finnVeiV(char note, int oktav, boolean skarp) {
		return 36/lengdeCM*noteTilVerdi(note, oktav, skarp)+1+5;
	}

	public double finnVeiH(char note, int oktav, boolean skarp) {
		return 37/lengdeCM*noteTilVerdi(note, oktav, skarp)+18+5;
	}

	public double noteTilVerdi(char note, int oktav, boolean skarp) {
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
		System.out.println("hey mama");
		Thread.sleep(1000);

		Ultrasonic uss = new Ultrasonic(SensorPort.S4);
		System.out.println(uss.getUltraSample());

		System.out.println("hei");
		Thread.sleep(9000);
	}

}
