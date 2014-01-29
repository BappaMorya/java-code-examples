package in.co.shoonya.koda.stringmanipulation;

/**
 * write a function to reverse characters in string.
 * 
 * @author Ketan_Khairnar
 *
 */
public class ReverseCharactersInString
{

    public static void main(String[] args)
    {
        //  Time    : O(n)
        //  Space   : O(n)
        System.out.println("----------Additional Space-------------");
        System.out.println(reverseStringAdditionalSpace("input"));
        System.out.println(reverseStringAdditionalSpace("input bad"));
        System.out.println(reverseStringAdditionalSpace("very very long string it is. isn't it? Yeh it is a long string"));
        
        //  Time    : O(n)
        //  Space   : O(1)
        System.out.println("----------Without Additional Space-------------");
        System.out.println(reverseString("input"));
        System.out.println(reverseString("ketankhairnar1"));
        System.out.println(reverseString("input bad"));
        System.out.println(reverseString("very very long string it is. isn't it? Yeh it is a long string"));
        
    }
    
    private static String reverseStringAdditionalSpace(String input)
    {
        StringBuilder output = new StringBuilder();
        char [] inputStrArray = input.toCharArray();
        for (int count = inputStrArray.length - 1; count >= 0; count--)
        {
            output.append(inputStrArray[count]);
        }
        return output.toString();
    }
    
    private static String reverseString(String input)
    {
        char [] inputStrArray = input.toCharArray();
        char temp;
        int min =0;
        int max = inputStrArray.length;
        for (int count =0; count < (max-min)/2;count++)
        {
            temp = inputStrArray[min+count];
            inputStrArray[min+count] = inputStrArray[max-count -1];
            inputStrArray[max-count -1] = temp;
        }
        return new String(inputStrArray);
    }    

}
