package in.co.shoonya.koda.warmups;

/**
 * Write a function that iterates over numbers 1 through 100. if divisible by 3 then print Fizz;if divisible by 5 then print Buzz
 * If divisible by both 3 and 5; print FizzBuzz
 * @author Ketan_Khairnar
 *
 */
public class FizzBuzz
{

    public static void main(String[] args)
    {
        for (int i = 1; i <= 100; i++)
        {
            if (i % 3 == 0 && i % 5 == 0)
            {
                System.out.println(i+"-"+"FizzBuzz");
            }
            else if (i % 3 == 0)
            {
                System.out.println(i+"-"+"Fizz");

            }
            else if (i % 5 == 0)
            {
                System.out.println(i+"-"+"Buzz");
            }
        }
    }
}
