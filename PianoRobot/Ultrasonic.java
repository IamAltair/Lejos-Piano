package PianoRobot;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.port.Port;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.robotics.SampleProvider;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.ev3.EV3;
import lejos.hardware.sensor.SensorModes;

public class Ultrasonic {
String port;
float[] ultraSample;
SampleProvider ultraLeser;


	public Ultrasonic(String port) {
		this.port = port;
		Brick brick = BrickFinder.getDefault();
		EV3 ev3 = (EV3) BrickFinder.getLocal();
		Port s = brick.getPort(port);
		EV3UltrasonicSensor ultraSensor = new EV3UltrasonicSensor(s);
		SampleProvider ultraLeser = ultraSensor.getDistanceMode();
		float[] ultraSample = new float [ultraLeser.sampleSize()];
		ultraLeser.fetchSample(ultraSample, 0);
		}

	public float getUltraSample() {
		ultraLeser.fetchSample(ultraSample, 0);
		return ultraSample[0];
	}
}