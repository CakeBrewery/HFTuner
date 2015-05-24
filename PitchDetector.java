import be.tarsos.dsp.*;
import be.tarsos.dsp.io.jvm.*;
import be.tarsos.dsp.pitch.*;
import be.tarsos.dsp.pitch.PitchProcessor.PitchEstimationAlgorithm;


//Trying stuff from the manual of Tarsos

public class PitchDetector implements PitchDetectionHandler
{

  AudioDispatcher dispatcher;
  PitchEstimationAlgorithm algo;


  public AudioDispatcher setup(){

    NoiseLevel nl = new NoiseLevel();


    System.out.println("Setup started");

    try{
      System.out.println("debug: initializing try");
      dispatcher = AudioDispatcherFactory.fromDefaultMicrophone(1024, 0);
      //dispatcher.addAudioProcessor(nl);


      algo = PitchEstimationAlgorithm.YIN;
      dispatcher.addAudioProcessor(new PitchProcessor(algo, 44100, 1024, this));


      return dispatcher;

    } catch(Exception e){
      System.out.println("an error occured");
      return dispatcher;
    }
  }

  public void run(){
    dispatcher.run();
  }



  public void handlePitch(PitchDetectionResult pitchDetectionResult,AudioEvent audioEvent) {
		if(pitchDetectionResult.getPitch() != -1){
			float pitch = pitchDetectionResult.getPitch();
			float probability = pitchDetectionResult.getProbability();
			String message = String.format("Frequency: %.2fHz ( %.2f probability)", pitch,probability);
	    System.out.println(message);
	   }
	}
}
