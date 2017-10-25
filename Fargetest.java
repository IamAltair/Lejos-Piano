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
    colorprovider = colorSensor.getColorIDMode();
    colorSample = new float[colorProvider.sampleSize()];

    while(Button.ESCSPE.isUp()) {
      int currentColor = colorSensor.getColorID();
      switch(currentColor) {
        case Color.RED:
          colorSensor.setFloodlight(Color.RED);
          break;
        case Color.BLUE:
          colorSensor.setFloodlight(Color.BLUE);
          break;
        default:
          colorSensor.setFloodlight(Color.NONE);
          break;
      }
      Delay.msDelay(250);
    }

    colorSensor.close();
  }
}
