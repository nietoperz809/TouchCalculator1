package expr;

/**
 * Created by Administrator on 1/10/2016.
 */
class LiteralExpr extends Expr
{
    Object v;

    LiteralExpr(double v)
    {
        this.v = v;
    }

    public Object value()
    {
        return v;
    }
}
