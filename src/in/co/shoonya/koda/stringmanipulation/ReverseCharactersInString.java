package in.co.shoonya.koda.stringmanipulation;

/**
 * write a function to reverse characters in string.
 * 
 * Time    : O(n)
 * Space   : O(n)
 * 
 * @author Ketan_Khairnar
 *
 */
public class ReverseCharactersInString
{

    public static void main(String[] args)
    {
        System.out.println(reverseString("input"));
        System.out.println(reverseString("very very long string it is. isn't it? Yeh it is a long string"));
    }
    
    private static String reverseString(String input)
    {
        StringBuilder output = new StringBuilder();
        char [] inputStrArray = input.toCharArray();
        for (int count = inputStrArray.length - 1; count >= 0; count--)
        {
            output.append(inputStrArray[count]);
        }
        return output.toString();
    }

}
