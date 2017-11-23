


import lejos.robotics.RegulatedMotor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.Move;
import lejos.robotics.navigation.MoveListener;
import lejos.robotics.navigation.MovePilot;
import lejos.hardware.*;
import lejos.hardware.device.*;
import lejos.hardware.device.tetrix.*;
import lejos.hardware.ev3.*;
import lejos.hardware.gps.*;
import lejos.hardware.lcd.*;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.hardware.sensor.*;
import lejos.hardware.video.*;
import lejos.remote.ev3.*;
import lejos.remote.nxt.*;
import lejos.remote.rcx.*;
import lejos.robotics.*;
import lejos.robotics.chassis.*;
import lejos.robotics.filter.*;
import lejos.robotics.geometry.*;
import lejos.robotics.localization.*;
import lejos.robotics.mapping.*;
import lejos.robotics.navigation.*;
import lejos.robotics.objectdetection.*;
import lejos.robotics.pathfinding.*;
import lejos.robotics.subsumption.*;
import lejos.utility.*;
import java.util.*;
import lejos.hardware.motor.*;
import java.io.File;
import static javax.swing.JOptionPane.*;
import java.io.*;
import java.util.*;

public class ColorSensor1 {
  public static void main(String[] args) throws Exception {

    Port s2 = LocalEV3.get().getPort("S2");
    EV3ColorSensor colorSensor = new EV3ColorSensor(s2);
    SampleProvider colorProvider = colorSensor.getRedMode();
    float[] colorSample = new float[colorProvider.sampleSize()];

    int array[] = new int[26];

    System.out.println("Start");
    Motor.A.setSpeed(300);

    for(int i = 0; i < array.length; i++){

      colorProvider.fetchSample(colorSample, 0);
	  if(colorSample[0] > 0.3){array[i] = 1;}else
	  if(colorSample[0] <= 0.3f && colorSample[0] > 0.1f){array[i] = 2;}
	  else{
	  	array[i] = 3;
	  }

	  Motor.A.forward();
	  Thread.sleep(258);
	  Motor.A.stop();
	  Thread.sleep(200);

    }

    for(int i = 0; i < array.length; i++){
		System.out.println(array[i]);
		Thread.sleep(500);
	}
}
}
