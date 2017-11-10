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

	public void ikkeFallAv(float min, float max){
	if(ultra.ikkeFall(min, max)){
				Motor.A.forward();
				Motor.B.backward();
	}else{
				Motor.A.backward();
				Motor.B.forward();
	}



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
		Tangenter jens = new Tangenter(43, 90, 1);
		Ultrasonic jens1 = new Ultrasonic(SensorPort.S1);
		float minimum = 0.312f;
		float maksimum = 0.543f;

		jens.ikkeFallAv(minimum,maksimum);

		System.out.println("hei");

		float a = jens1.getUltraSample();
		System.out.println(a);
		/*System.out.println("playing C2");
		Thread.sleep(1000);
		jens.spillNote('C',2,false, 0.25);
		System.out.println("playing D2");
		Thread.sleep(1000);
		jens.spillNote('D',2,false, 0.25);
		System.out.println("playing E2");
		Thread.sleep(1000);
		jens.spillNote('E', 2 ,false, 0.25);*/
		System.out.println("Sucses!!!");
		Thread.sleep(10000);
	}

}
