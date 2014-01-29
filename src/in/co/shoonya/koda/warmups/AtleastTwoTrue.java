package in.co.shoonya.koda.warmups;

/**
 * Write a function that takes 3 booleans and returns true if atleast 2 of them are true
 * @author Ketan_Khairnar
 *
 */
public class AtleastTwoTrue
{

    public static void main(String[] args)
    {
       System.out.println(checkAtleastTwoAreTrue(true, false, false));
       System.out.println(checkAtleastTwoAreTrue(true, true, false));
       System.out.println(checkAtleastTwoAreTrue(true, false, true));
       System.out.println(checkAtleastTwoAreTrue(false, false, false));
       System.out.println(checkAtleastTwoAreTrue(true, true, true));

    }
    
    private static boolean checkAtleastTwoAreTrue(boolean a, boolean b, boolean c)
    {
        return a ? (b || c ) : ( b && c);
    }

}
