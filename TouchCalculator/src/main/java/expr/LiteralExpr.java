package expr;

/**
 * Created by Administrator on 1/10/2016.
 */
class LiteralExpr extends Expr
{
    double v;

    LiteralExpr(double v)
    {
        this.v = v;
    }

    public double value()
    {
        return v;
    }
}
