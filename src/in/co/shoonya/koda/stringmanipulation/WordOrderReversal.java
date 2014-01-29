package in.co.shoonya.koda.stringmanipulation;

public class WordOrderReversal
{

    public static void main(String[] args)
    {
        // input is --> my name is shoonya
        // required o/p is --> shoonya is name my
        
        // other additional conditions 1. without using additional space 
        // first reverse the string and then reverse words
        
        reverseWords("word order reversal string manipulate tricky");
        reverseWords("my name is shoonya");
        
        //if additional space is allowed; split by space; insert on to arraylist and reverse it and print
    }
    
    
    private static void reverseCharArray(char [] inputStrArray, int min, int max)
    {
        char temp;
        for (int count =0; count < (max-min)/2;count++)
        {
            temp = inputStrArray[min+count];
            inputStrArray[min+count] = inputStrArray[max-count -1];
            inputStrArray[max-count -1] = temp;
        }
    }    
    private static void reverseWords(String input)
    {
        StringBuilder output = new StringBuilder();
        char[] inputStrArray = input.toCharArray();
        
        //first reverse the complete char array
        reverseCharArray(inputStrArray,0,inputStrArray.length);
        //now iterate over it for each word and then reverse it
        int count =0;
        int lastspace=0;
        for(char c : inputStrArray)
        {
            if(c==' ')
            {
                reverseCharArray(inputStrArray, lastspace, count);
                //next word's beginning
                lastspace = count+1;
            }
            count++;
        }
        //for the last word
        reverseCharArray(inputStrArray, lastspace, inputStrArray.length);
        System.out.println(inputStrArray);
    }

}
