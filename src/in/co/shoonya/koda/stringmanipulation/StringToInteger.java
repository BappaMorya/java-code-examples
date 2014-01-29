package in.co.shoonya.koda.stringmanipulation;

/**
 * Given a string representation of the integer, return integer
 * @author Ketan_Khairnar
 *
 */
public class StringToInteger
{

    public static void main(String[] args)
    {
        // TODO Auto-generated method stub

    }
    
    private static int getInteger(String input)
    {
        int result =0;
        boolean isNegative=  false;
        int i=0;
        char[] inputStrArray = input.toCharArray();
        
        if(inputStrArray[0]=='-')
        {
            isNegative = true;
        }
        for(;i<inputStrArray.length;i++)
        {
            
        }
        return result;
    }

}
