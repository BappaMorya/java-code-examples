package in.co.shoonya.koda.stringmanipulation;

/**
 * Given two strings, write a function to check if one string is rotation of the other
 * Time     : O(n^2)
 * Space    : O(n)
 * 
 * @author Ketan_Khairnar
 * 
 */
public class CheckForRotation
{

    public static void main(String[] args)
    {
        System.out.println(isRotation("test", "estt"));
        System.out.println(isRotation("test", "estr"));
        System.out.println(isRotation("test", "estradad"));
        System.out.println(isRotation("CheckForRotation", "RotationCheckFor"));

    }

    private static boolean isRotation(String a, String b)
    {
        return(a.length()==b.length() && (a+a).indexOf(b)!=-1);
    }
}
