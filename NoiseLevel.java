import be.tarsos.dsp.*;
import be.tarsos.dsp.io.jvm.*;
import be.tarsos.dsp.pitch.*;

public class NoiseLevel implements AudioProcessor
{

  float threshold = -35;

  public boolean process(AudioEvent audioEvent){

    float[] buffer = audioEvent.getFloatBuffer();

    double level = soundPressureLevel(buffer);
    if(level > threshold){
      System.out.println("Sound detected.");
    }
    return true;
  }

  public void processingFinished() {}
  private double soundPressureLevel(final float[] buffer){
    double power = 0.0D;
    for(float element : buffer){
      power += element * element;
    }
    double value = Math.pow(power, 0.5)/buffer.length;
    return 20.0 * Math.log10(value);
  }
}
