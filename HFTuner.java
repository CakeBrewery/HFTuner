public class HFTuner{
//The Hands Free Tuner

  public static void main(String[] args)
  {
    PitchDetector pitch_handler = new PitchDetector();
    pitch_handler.setup();
    pitch_handler.run(); 
  }
}
