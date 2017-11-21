
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

public class Drive2
{
	public static void main(String[] args) throws Exception{
		Ultrasonic uss = new Ultrasonic(SensorPort.S4);

		Motor.A.setSpeed(80);
		Motor.B.setSpeed(80);
		Motor.D.setSpeed(900);
		//KNAPP 0
		while(uss.getUltraSample() != 0.2055){

		if(uss.getUltraSample() > 0.2055){
			System.out.println("AKKURATT PASSEE!!!!");
			break;
		} else if(uss.getUltraSample() < 0.2055){
			Motor.A.forward();
			Motor.B.backward();
			System.out.println("Mindre enn 0.205");
		}
		}

		Motor.A.stop(true);
		Motor.B.stop();

		Motor.C.rotate(-45);
		Motor.C.rotate(45);

		//KNAPP 1
		while(uss.getUltraSample() !=0.235){
			if(uss.getUltraSample()< 0.235){
				Motor.A.forward();
				Motor.B.backward();
			} else if (uss.getUltraSample() > 0.235){
				System.out.println("Break");
				break;
			}
		}
			Motor.B.stop(true);
			Motor.A.stop();

		Motor.C.rotate(-45);
		Motor.C.rotate(45);
		//KNAPP 2
		while(uss.getUltraSample() !=0.2555){
			if(uss.getUltraSample()< 0.2555){
				Motor.A.forward();
				Motor.B.backward();
			} else if (uss.getUltraSample() > 0.2555){
				System.out.println("Break");
				break;
			}
		}
			Motor.B.stop(true);
			Motor.A.stop();

		Motor.C.rotate(-45);
		Motor.C.rotate(45);
		//KNAPP 3
		while(uss.getUltraSample() !=0.275){
			if(uss.getUltraSample()< 0.275){
				Motor.A.forward();
				Motor.B.backward();
			} else if (uss.getUltraSample() > 0.275){
				System.out.println("Break");
				break;
			}
		}
			Motor.B.stop(true);
			Motor.A.stop();

		Motor.C.rotate(-45);
		Motor.C.rotate(45);
		//KNAPP 4
		while(uss.getUltraSample() !=0.2955){
			if(uss.getUltraSample()< 0.2955){
				Motor.A.forward();
				Motor.B.backward();
			} else if (uss.getUltraSample() > 0.2955){
				System.out.println("Break");
				break;
			}
		}
			Motor.B.stop(true);
			Motor.A.stop();

		Motor.C.rotate(-45);
		Motor.C.rotate(45);
		Motor.C.rotate(-45);
		Motor.C.rotate(45);
		//KNAPP 5
		while(uss.getUltraSample() !=0.325){
			if(uss.getUltraSample()< 0.325){
				Motor.A.forward();
				Motor.B.backward();
			} else if (uss.getUltraSample() > 0.325){
				System.out.println("Break");
				break;
			}
		}
			Motor.B.stop(true);
			Motor.A.stop();

			for(int i = 0; i < 4; i++){
		Motor.C.rotate(-45);
		Motor.C.rotate(45);
			}
		//KNAPP 4
		while(uss.getUltraSample() !=0.2955){
			if(uss.getUltraSample() > 0.2955){
				Motor.A.backward();
				Motor.B.forward();
			} else if (uss.getUltraSample() < 0.2955){
				System.out.println("Break");
				break;
			}
		}
			Motor.B.stop(true);
			Motor.A.stop();

		Motor.C.rotate(-45);
		Thread.sleep(200);
		Motor.C.rotate(45);


		//KNAPP 3
		while(uss.getUltraSample() !=0.2755){
			if(uss.getUltraSample()> 0.2755){
				Motor.A.backward();
				Motor.B.forward();
			} else if (uss.getUltraSample() < 0.2755){
				System.out.println("Break");
				break;
			}
		}
		Motor.B.stop(true);
		Motor.A.stop();

		for(int i = 0; i < 4; i++){
				Motor.C.rotate(-45);
				Motor.C.rotate(45);
		}

		//KNAPP 2
		while(uss.getUltraSample() !=0.2555){
			if(uss.getUltraSample()> 0.2555){
				Motor.A.backward();
				Motor.B.forward();
			} else if (uss.getUltraSample() < 0.255){
				System.out.println("Break");
				break;
			}
		}
		Motor.B.stop(true);
		Motor.A.stop();

		for(int i = 0; i < 2; i++){
			int a = 0;
			Motor.C.rotate(-45);
			Motor.C.rotate(45);
			System.out.println(a);
			a++;
	}

		//KNAPP 1
		while(uss.getUltraSample() !=0.23){
			if(uss.getUltraSample()> 0.23){
				Motor.A.backward();
				Motor.B.forward();
			} else if (uss.getUltraSample() < 0.23){
				System.out.println("Break");
				break;
			}
		}
		Motor.B.stop(true);
		Motor.A.stop();

		for(int i = 0; i < 4; i++){
		Motor.C.rotate(-45);
		Motor.C.rotate(45);
		}
		//KNAPP 0
		while(uss.getUltraSample() !=0.205){
			if(uss.getUltraSample()>0.205){
				Motor.A.backward();
				Motor.B.forward();
			} else if (uss.getUltraSample() < 0.205){
				System.out.println("Break");
				break;
			}
		}
		Motor.B.stop(true);
		Motor.A.stop();

		Motor.C.rotate(-45);
		Thread.sleep(1000);
		Motor.C.rotate(45);


	}

}
