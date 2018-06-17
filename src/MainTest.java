
import java.util.List;
/**
 * Created by saumyaagarwal on 6/17/18.
 *
 */
public class MainTest {

  public static void main(String[] args) throws Exception{

    MainTest test = new MainTest();

    if (test.mainTest() && test.primeNumbersBasicAlgo() && test.sieveOfEratosthenesAlgo()
        && test.isIntegerAndValid() && test.isPrimeTest() && test.AlgoComparison() )
      System.out.println(" All Tests passed");
    else
      System.out.println(" One of the tests failed ");

  }

  public boolean mainTest() throws Exception {

    String[] args = new String[1];
    args[0] = "0";
    // 0 size N and default algo
    Main.main(args);

    // defaults <---- check for invalid cases (verify no errors)
    Main.main(new String[0]);

    String[] args2 = new String[2];
    args2[0] = "10";
    args2[1] = "2";
    // defaults <---- check for valid case
    Main.main(args2);


    // invalid size <---- check for invalid cases (verify no errors)
    args[0] = "-1";
    Main.main(args);

    // invalid size <---- check for invalid cases (verify no errors)
    args[0] = "-1";
    Main.main(args);

    return true;

  }

  public boolean primeNumbersBasicAlgo() throws Exception {

    List<Integer> primes = Main.primeNumbersBasicAlgo(10);
    if(primes.size() == 10)
      return true;
    else return false;
  }

  public boolean sieveOfEratosthenesAlgo() throws Exception {

    List<Integer> primes = Main.improvedAlgo(10);
    if (primes.size() >= 10) // it will be atleast 10 (since the algo gives approximate value)
      return true;
    else
      return false;
  }

  public boolean AlgoComparison() throws Exception {

    long startTime = System.nanoTime();
    List<Integer> primesFromBasicAlgo = Main.primeNumbersBasicAlgo(200);
    long endTime = System.nanoTime();

    int runTime1 = (int) (endTime - startTime);

    List<Integer> primesFromImprovedAlgo = Main.improvedAlgo(200);
    long endTime2 = System.nanoTime();

    int runTime2 = (int) (endTime2 - endTime);

    System.out.println("runtime1 : " + runTime1);
    System.out.println("runtime2 : " + runTime2);
    if (runTime2 < runTime1)
      return true;
    else
      return false;
  }

  public boolean isIntegerAndValid() throws Exception {

    if (Main.isIntegerAndValid("6", 100) && !Main.isIntegerAndValid("sdf", 100))
      return true;
    else
      return false;

  }

  public boolean isPrimeTest() throws Exception {
    if (Main.isPrime(23) && !Main.isPrime(28 ))
      return true;
    else
      return false;
  }


}