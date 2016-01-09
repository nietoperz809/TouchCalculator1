package factorial;

/**
 * Created by Administrator on 1/9/2016.
 */
public class FactorialPoorMans
{
    private static long N;


    public static String Factorial (int n)
    {
        if (n < 0)
        {
            return "ERR";
        }

        if (n < 2)
        {
            return "1";
        }

        DecInteger p = new DecInteger(1);
        DecInteger r = new DecInteger(1);

        N = 1;

        int h = 0, shift = 0, high = 1;
        int log2n = (int) Math.floor(Math.log(n) * 1.4426950408889634);

        while (h != n)
        {
            shift += h;
            h = n >> log2n--;
            int len = high;
            high = (h - 1) | 1;
            len = (high - len) / 2;

            if (len > 0)
            {
                p = DecInteger.mult(p, Product(len));
                r = DecInteger.mult(r, p);
            }
        }

        r = r.mult(r, r.Pow2(shift));
        return r.toString();
    }

    public static DecInteger Product (int n)
    {
        int m = n / 2;
        if (m == 0)
        {
            return new DecInteger(N += 2);
        }
        if (n == 2)
        {
            return new DecInteger((N += 2) * (N += 2));
        }
        return DecInteger.mult(Product(n - m), Product(m));
    }
} // endOfFactorialPoorMans
