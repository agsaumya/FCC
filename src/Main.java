import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by saumyaagarwal on 6/16/18.
 *
 * Funding Circle Coding Challenge
 *
 * Write a program that prints out a multiplication table of the first 10 prime number.
 * The code should run from the command line and print the table on the command line (console) .
 *
 * Additionally #1 - is 1 a prime number? No. It is not as prime numbers are numbers that has exactly two positive divisors.
 * Additionally #2 - Instead of 10, let's scale the code for N
 * Additionally #3 - Assuming that max Int is 32 bit (2 ^ 31 -1, i.e value can be about 2billion) for our VM, if we need bigger numbers, would need to use Long
 *
 * Output should look like below:
 *
 *    |  2 |  3 |   5 |   7 |  11 |  13 |  17 |  19 |  23 |  29 |
 |----+----+----+-----+-----+-----+-----+-----+-----+-----+-----|
 |  2 |  4 |  6 |  10 |  14 |  22 |  26 |  34 |  38 |  46 |  58 |
 |  3 |  6 |  9 |  15 |  21 |  33 |  39 |  51 |  57 |  69 |  87 |
 |  5 | 10 | 15 |  25 |  35 |  55 |  65 |  85 |  95 | 115 | 145 |
 |  7 | 14 | 21 |  35 |  49 |  77 |  91 | 119 | 133 | 161 | 203 |
 | 11 | 22 | 33 |  55 |  77 | 121 | 143 | 187 | 209 | 253 | 319 |
 | 13 | 26 | 39 |  65 |  91 | 143 | 169 | 221 | 247 | 299 | 377 |
 | 17 | 34 | 51 |  85 | 119 | 187 | 221 | 289 | 323 | 391 | 493 |
 | 19 | 38 | 57 |  95 | 133 | 209 | 247 | 323 | 361 | 437 | 551 |
 | 23 | 46 | 69 | 115 | 161 | 253 | 299 | 391 | 437 | 529 | 667 |
 | 29 | 58 | 87 | 145 | 203 | 319 | 377 | 493 | 551 | 667 | 841 |
 *
 *
 * Some details of algorithm:
 *  #1 - To check if a number is prime, we only need to test for factors lower than or equal to the square root of the number being checked.
 *     - reason being, if a and b are two factors of a non-prime number, then if (a> sqrt(n) & b>sqrt(n)) then a*b > n
 *  #2 - Prime numbers cannot be an even number (even numbers are divisible by 2).
 *  #3 - There are two algorithms here : Brute force ( Time - O(n2) and space - O(1)) and then prime number Sieve of Eratosthenes (Time - O (nlogn) and space - O(n))
 *
 */
public class Main {

  public static final int DEFAULT_SIZE = 10;

  /**
   *
   * @param args
   */
  public static void main(String[] args) {

    if (args.length > 2) {
      System.out.println("The passed in values exceed the number of params. Rerun the code with the correct params.");
      return;
    }

    int n = DEFAULT_SIZE;

    if (args.length > 0 && !args[0].isEmpty() && !isIntegerAndValid(args[0], 1000)) {
      System.out.println("The passed in value of N is not valid. Rerun the code with the correct value.");
      return;
    }

    if (args.length > 0 && !args[0].isEmpty())
      n = Integer.parseInt(args[0]);


//    System.out.println("Enter which algorithm to use."
//        + " 1 - Brute force ( Time - O(n2) and space - O(1)) or 2 - prime number sieves (Time - O (nlogn) and space - O(n)). Default is #2. : ");
//
//    Scanner scan = new Scanner(System.in);
//    String input1 = scan.next();

    if (args.length > 0 &&  !args[1].isEmpty() && !isIntegerAndValid(args[1], 2)) {
      System.out.println("The passed in value of Algorithm choice not valid. Rerun the code with the correct value.");
      return;
    }
    int algoChoice = 1;
    if (args.length == 2 )
        algoChoice = Integer.parseInt(args[1]);
//    System.out.println("Enter value of N (number of primes). Default is 10 : ");
//    String input2 = scan.next();

    /*
    1. Get all prime numbers in an arraylist
    2. Store them in a 2d array - first row and first column
    3. Traverse through the 2d array and multiple with the prime[x][0] and prime[0][y] and insert the result into the current cell
    4. Print the table on the console the table (i.e 2d array)
    */

    System.out.println("Running the code with algoChoice# " + algoChoice + " and number of primes (n) = " + n);

    long startTime = System.currentTimeMillis();

    // Get List of N primes
    List<Integer> primes = null;
    switch (algoChoice) {
      case 2:
        primes = improvedAlgo(n);
        break;
      case 1:
      default:
        primes = primeNumbersBasicAlgo(n);
    }

    long endTime = System.currentTimeMillis();

    System.out.println(" Running Time of Algorithm to find primes : " + (endTime - startTime) );

    int[][] result = new int[n+1][n+1];
    int index = 0;
    result[index][index] = 1;
    index = index + 1;

    for (int prime : primes) {
      if (index > n) break;
      result [index][0] = prime;
      result [0][index++] = prime;
    }

    System.out.println(" Output (printing the table below) : ");
    System.out.println();

    for (int i=0; i<=n ; i++) {
      for (int j =0; j<= n; j++) {
        result[i][j] = result[i][0] * result[0][j];

        System.out.print("   " + String.format("|%8d|", result[i][j])); // formatting the output cell
      }
      System.out.println(); // next line
    }

  }

  /**
   *  Go by each odd number unless you get N prime numbers
   *  Time complexity - quadratic O (n^2)
   *  Space complexity - constant O(1)
   * @param n
   * @return
   */
  public static List<Integer> primeNumbersBasicAlgo(int n) {

    List<Integer> primeNumbers = new ArrayList<>(); // initializing to avoid NPE
    if (n > 0) {
      primeNumbers.add(2);
    }
    int count = 1;
    for (int i = 3; count < n; i += 2) { // check for only odd numbers

      if (isPrime(i)) {
        // add it to the list
        primeNumbers.add(i); count++;
      }
    }
    return primeNumbers;
  }

  /**
   * Check if number is prime or not by checking if there is any divisor of this number
   * @param number
   * @return
   */
  public static boolean isPrime(int number) {

    for (int i = 2; i * i <= number; i++) {
      if ( (number % i) == 0) { // is it divisble?
        return false;
      }
    } // end for
    return true;
  }



  /**
   * Algorithm used here : sieve Of Eratosthenes
   * Steps:
   *  1. Create a boolean list from 2 and  till the approximate number till which you find total n prime numbers x = ( n / (ln(10) n - 1)) <--approximate
   *  2. Iterate on all numbers 2...x
   *  3. For all i, mark multipliers of i  (i, 2i, 3i, 4i  ... < x) as non primes.
   *  4. Keep on doing it till you mark multipliers of every number till x. All unmarked numbers or marked just once when we incremented i, in the list are prime numbers.
   *
   * Assumptions: This algorithm is fast but will give approximate number of primes.
   *  Approx Formula : value of Nth prime : n * ln (n) + n * ln ( ln(n) )  where ln is the natural log (i.e of base 10)
   * @param n
   * @return
   */
  public static List<Integer> improvedAlgo(int n) {

    int approx = (int) (n * Math.log(n) + n * Math.log(Math.log(n)));

    boolean prime[] = new boolean[approx + 1];
    Arrays.fill(prime, true); // initiliazing all numbers with true

    for (int p = 2; p * p <= approx; p++) {

      if (prime[p]) {

        for (int i = p * 2; i <= approx; i += p) {
          prime[i] = false; // mark it as non-prime
        }
      }
    }
    List<Integer> primeNumbers = new ArrayList<>();
    for (int i = 2; i <= approx; i++) {
      if (prime[i]) {
        primeNumbers.add(i);
      }
    }
    return primeNumbers;
  }

  /**
   * Check if the param is a valid value
   * @param param
   * @return
   */
  public static boolean isIntegerAndValid(String param, int limit) {

    try {
      int n = Integer.parseInt(param);
      if (n <= 0 || n > limit)
        return false;

    } catch(NumberFormatException | NullPointerException e) {
      // if the input param is not of valid type
      return false;
    }
    // only go here if we didn't return false
    return true;
  }
}
