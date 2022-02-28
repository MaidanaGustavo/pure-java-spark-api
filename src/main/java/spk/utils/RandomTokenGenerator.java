package spk.utils;

public class RandomTokenGenerator {
  
  private static RandomTokenGenerator randomTokenGenerator = null;
  
  private RandomTokenGenerator(){}

  public static RandomTokenGenerator getInstance(){
    if(randomTokenGenerator==null) 
      randomTokenGenerator = new RandomTokenGenerator();

      return randomTokenGenerator;
  }

  public String generateRandomNumber(){
      int min = 0;
      int max = 9;
      String randomToken = "";
      for (int i = 1; i <=6; i++) {
        int randomNumber = (int)Math.floor(Math.random()*(max-min+1)+min);
        randomToken += randomNumber;
      } 
      return randomToken;
  }
}
