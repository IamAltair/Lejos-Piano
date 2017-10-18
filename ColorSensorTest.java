package pack;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.PORT;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class ColorSensorTest {

  EV3ColorSensor colorSensor;
  SampleProvider colorProvider;
  float[] colorSample;

  public static void main(String[] args) {
    new ColorSensorTest();
  }

  public ColorSensorTest() {
    port s1 = localEV3.get().getPort("S1");
    colorSensor = new EV3ColorSensor(s1);
    colorprovider = colorSensor.getRGBMode();
    colorSample = new float[colorProvider.sampleSize()];

    while(Button.ESCSPE.isUp()) {
      colorProvider.fetchSample(colorSample, 0);
      System.out.println("R" + colorSample[0]);
      System.out.println("G" + colorSample[1]);
      System.out.println("B" + colorSample[2]);
      Delay.msDelay(250);
    }

    colorSensor.close();
  }
}
