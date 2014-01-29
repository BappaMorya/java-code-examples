package in.co.shoonya.koda.stringmanipulation;

import java.util.HashSet;

/**
 * Check whether string contains all unique characters
 * 
 * Time     :O(n)
 * Space    :O(n)
 * 
 * One can use the same approach to return a string with all duplicate characters removed
 * @author Ketan_Khairnar
 *
 */
public class AllUniqueChars
{
    public static void main(String[] args)
    {
        System.out.println(isUnique("input"));
        System.out.println(isUnique("abracadabra"));
    }
    
    private static boolean isUnique(String input)
    {
        if(input==null || input.isEmpty())
            return false;
                    
        HashSet<Character> uniqueChars = new HashSet<Character>();
        char[] inputStrArray = input.toCharArray();
        
        for(char c: inputStrArray)
        {
            if(uniqueChars.contains(c))
            {
               return false; 
            }
            else
            {
                uniqueChars.add(c);
            }
        }
        
        return true;
    }
    
}
