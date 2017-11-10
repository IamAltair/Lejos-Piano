import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.RotateMoveController;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.hardware.motor.*;
import lejos.hardware.lcd.*;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.NXTTouchSensor;
import lejos.hardware.sensor.NXTColorSensor;
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
import lejos.hardware.Button;
import lejos.hardware.motor.BaseRegulatedMotor;

class MegatronV1 {
  public static void main(String[] args) throws Exception {


	  	UltrasonicSonicSensor ultra = new UltrasonicSonicSensor(SensorPort.S4);
	  	range = ultra.getDistance();


    System.out.println("PROJECT MEGATRON: INITIATED");
    System.out.println("STATUS: FULLY OPERATIONAL");
    System.out.println("WAITING FOR COLORS...");
    thread.sleep(1000);




  }
}
