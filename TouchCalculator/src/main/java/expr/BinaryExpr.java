package expr;

/**
 * Created by Administrator on 1/10/2016.
 */
class BinaryExpr extends Expr
{
    int rator;
    Expr rand0, rand1;

    BinaryExpr(int rator, Expr rand0, Expr rand1)
    {
        this.rator = rator;
        this.rand0 = rand0;
        this.rand1 = rand1;
    }

    public double value()
    {
        double arg0 = rand0.value();
        double arg1 = rand1.value();
        switch (rator)
        {
            case ADD:
                return arg0 + arg1;
            case SUB:
                return arg0 - arg1;
            case MUL:
                return arg0 * arg1;
            case DIV:
                return arg0 / arg1; // division by 0 has IEEE 754 behavior
            case POW:
                return Math.pow(arg0, arg1);
            case ATAN2:
                return Math.atan2(arg0, arg1);
            case MAX:
                return arg0 < arg1 ? arg1 : arg0;
            case MIN:
                return arg0 < arg1 ? arg0 : arg1;
            case LT:
                return arg0 < arg1 ? 1.0 : 0.0;
            case LE:
                return arg0 <= arg1 ? 1.0 : 0.0;
            case EQ:
                return arg0 == arg1 ? 1.0 : 0.0;
            case NE:
                return arg0 != arg1 ? 1.0 : 0.0;
            case GE:
                return arg0 >= arg1 ? 1.0 : 0.0;
            case GT:
                return arg0 > arg1 ? 1.0 : 0.0;
            case AND:
                return (long) arg0 & (long) arg1;
            case OR:
                return (long) arg0 | (long) arg1;
            case XOR:
                return (long) arg0 ^ (long) arg1;
            default:
                throw new RuntimeException("BUG: bad rator");
        }
    }
}
