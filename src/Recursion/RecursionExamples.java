package Recursion;

public class RecursionExamples {
    /*
    Find the sum of squares S(n, m) of integers that fall
    within the range [n, m]
     */
    public static class SumOfSquares
    {
        public static int S(int M, int N)
        {
            if(N==M)
            {
                return 0;
            }
            else
            {
                return M*M+S(M-1,N);
            }
        }
    }
    /*
    Reverse a string
    */

    public static class StringReverse
    {
        public static String reverse(String s)
        {
            if(s.isEmpty())
            {
                return s;
            }
            else
            {
                return reverse(s.substring(1)) + s.charAt(0);
            }
        }

    }


}
