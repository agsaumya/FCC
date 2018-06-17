# FCC



#### Funding Circle Coding Challenge (Java)
 
##### Problem Statement

 * Write a program that prints out a multiplication table of the first 10 prime number.
 * The code should run from the command line and print the table on the command line (console).
 * Additional case - Instead of 10, let's scale the code for N prime numbers.
 

###### Additional assumptions (my notes):
  - is 1 a prime number? No. It is not as prime numbers are numbers that has exactly two positive divisors.
  - Assuming that max Int is 32 bit (2 ^ 31 -1, i.e value can be about 2 billion) for our VM, if we need bigger numbers, would need to use Long
 

 
### Output 
   - based on my understanding of the full problem statement.

```
|    |  2 |  3 |   5 |   7 |  11 |  13 |  17 |  19 |  23 |  29 |
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
```
 
 
 #### How to Run:
 Note: Make sure you have Java 1.7+ and can run the java commands from console.
 
 1. Code has been checked in here on git. Git Clone the repo somewhere on your local.
      - `$ git clone https://github.com/agsaumya/FCC.git`  (lets say you have it in directory abc)
 2. Compile the code using standard `javac` command.  (inside abc directory)
      - `$ cd fcc/src`
      - `$ javac Main.java`
 3. Run the code using the standard `java` command. You can pass in either zero (which will configure the program to use defaults), one or two arguments.
    First Argument : Value of N (Default is 10)
    Second Argument : Which algorithm to use. 
     - First one is the standard Brute Force ( Time - O(n^2) and Space - O(1)). 
     - Second one is Sieve of Eratosthenes algorithm to find prime numbers (Time - O (nlogn) and space - O(n)). Approx Formula : value of Nth prime : n * ln (n) + n * ln ( ln(n) )  where ln is the natural log (i.e of base 10)
        This algo is fast for small value of N but will give approximate list upto N.
     
      - `$ java Main`  (use defaults)
      - `$ java Main 20`   (change value of N to 20)
      - `$ java Main 10 2`    (change value of N to 20 and use 2nd algorithm``)
             
 

 #### Some notes about the algorithms:
    - To check if a number is prime, we only need to test for factors lower than or equal to the square root of the number being checked.
    - reason being, if a and b are two factors of a non-prime number, then if (a> sqrt(n) & b>sqrt(n)) then a*b > n
    - Prime numbers cannot be an even number (even numbers are divisible by 2).
    - There are two algorithms here : Brute force which use only constant memory and then improved prime number at the expense of more memory (Sieve of Eratosthenes). So second algorithm is only useful when we have infinite memory (space) or value of N is not too large.
 
