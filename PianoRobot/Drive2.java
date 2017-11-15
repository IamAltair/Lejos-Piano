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


	public boolean ikkeFall(float min, float max){
		ultraLeser.fetchSample(ultraSample, 0);
		float distance = ultraSample[0];

		if(distance <= min || max >= distance){
			return true;
		}else {
			return false;
		}
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

		Motor.A.setSpeed(900);
		Motor.B.setSpeed(900);
		Motor.C.setSpeed(900);
		Motor.D.setSpeed(900);
		mpb = 60/bpm*standarNote*4*1000;
		/*Port port = LocalEV3.get().getPort("S1");
		SensorModes sensor = new EV3UltrasonicSensor(port);
		SampleProvider distance = sensor.getMode("Distance");
		float[] sample = new float[distance.sampleSize()];*/
	}
		/*public void gayShit() {Brick brick = BrickFinder.getDefault();
			EV3 ev3 = (EV3) BrickFinder.getLocal();
			Port s1 = brick.getPort("S1");
			EV3UltrasonicSensor ultraSensor1 = new EV3UltrasonicSensor(s1);
			SampleProvider ultraLeser1 = ultraSensor1.getDistanceMode();
			float[] ultraSample1 = new float [ultraLeser1.sampleSize()];
			ultraLeser1.fetchSample(ultraSample, 0);
			System.out.println(ultraSample1[0]);

	}*/

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
			fingeringH(lengde);
		} else {fingeringV(lengde);}
	}

	public void fingeringH(double lengde) throws Exception {
		Motor.C.rotate(90);
		c = mpb*lengde;
		long a = (long) c;
		Thread.sleep(a);
		Motor.C.rotateTo(100);
	}

	public void fingeringV(double lengde) throws Exception {
		Motor.D.rotate(90);
		c = mpb*lengde;
		long a = (long) c;
		Thread.sleep(a);
		Motor.D.rotateTo(100);
	}

	public void bevegTilAvstand(char note, int oktav, boolean skarp) {
		if(finnVeiV(note, oktav, skarp) >= finnVeiH(note, oktav, skarp)) {
			vei = finnVeiV(note, oktav, skarp);
			} else {vei = finnVeiH(note, oktav, skarp);}
			uss.getUltraSample();

		if(vei>= uss.getUltraSample()) {
			while(vei>=uss.getUltraSample()){
				uss.getUltraSample();
				Motor.A.forward();
				Motor.B.backward();
			}
		}

		else {
			while(vei<=uss.getUltraSample()){
				uss.getUltraSample();
				Motor.A.backward();
				Motor.B.forward();
			}
		}

	}

	public void bevegTilAvstandOpt(double noteOpt) {
				uss.getUltraSample();
				v = noteOpt+5;
				h = noteOpt-5;
				if(v>=h) {
					vei = v;
				} else {vei = h;}

				if(vei>=uss.getUltraSample()) {
					while(vei>=uss.getUltraSample()){
						uss.getUltraSample();
						Motor.A.forward();
						Motor.B.backward();
					}
				}

				else {
					while(vei<=uss.getUltraSample()){
						uss.getUltraSample();
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



		System.out.println("hei paa dei");

/*		Port port = LocalEV3.get().getPort("S1");
		SensorModes sensor = new EV3UltrasonicSensor(port);
		SampleProvider distance = sensor.getMode("Distance");
		float[] sample = new float[distance.sampleSize()];
		distance.fetchSample(sample, 0);
		System.out.println(sample[0] + "hei");*/

		System.out.println("Getting some ultrasounds");
		Thread.sleep(1000);
		Tangenter jens = new Tangenter(43, 90, 1);
		System.out.println("Getting some more sounds");
		Thread.sleep(1000);
		jens.spillNote('A',2,false,1/4);
	}

}
