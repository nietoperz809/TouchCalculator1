package factorial;

/**
        * Created by Administrator on 1/9/2016.
        */
class DecInteger
{
    private static final long mod = 100000000L;
    private int[] digits;
    private int digitsLength;

    public DecInteger (long value)
    {
        digits = new int[]{(int) value, (int) (value >> 32)};
        digitsLength = 2;
    }

    public DecInteger (int[] digits, int length)
    {
        this.digits = digits;
        digitsLength = length;
    }

    public DecInteger Pow2 (int e)
    {
        if (e < 31)
        {
            return new DecInteger((int) Math.pow(2, e));
        }
        return mult(Pow2(e / 2), Pow2(e - e / 2));
    }

    public static DecInteger mult (DecInteger a, DecInteger b)
    {
        int alen = a.digitsLength, blen = b.digitsLength;
        int clen = alen + blen + 1;
        int[] digits = new int[clen];

        for (int i = 0; i < alen; i++)
        {
            long temp = 0;
            for (int j = 0; j < blen; j++)
            {
                temp = temp + ((long) a.digits[i] * (long) b.digits[j]) + digits[i + j];
                digits[i + j] = (int) (temp % mod);
                temp = temp / mod;
            }
            digits[i + blen] = (int) temp;
        }

        int k = clen - 1;
        while (digits[k] == 0)
        {
            k--;
        }

        return new DecInteger(digits, k + 1);
    }

    @Override
    public String toString ()
    {
        StringBuilder sb = new StringBuilder(digitsLength * 10);
        sb = sb.append(digits[digitsLength - 1]);
        for (int j = digitsLength - 2; j >= 0; j--)
        {
            sb = sb.append(("" + (digits[j] + (int) mod)).substring(1));
        }
        return sb.toString();
    }
}
