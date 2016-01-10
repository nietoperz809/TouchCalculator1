// Mathematical expressions.
// Copyright 1996 by Darius Bacon; see the file COPYING.

package expr;

/**
 * A mathematical expression, built out of literal numbers, variables,
 * arithmetic and relational operators, and elementary functions.  It
 * can be evaluated to get its value given its variables' current
 * values.  The operator names are from java.lang.Math where possible.
 */
public abstract class Expr
{
    /**
     * Calculate the expression's value.
     *
     * @return the value given the current variable values
     */
    public abstract Object value();

    /**
     * Binary operator: addition
     */
    public static final int ADD = 0;
    /**
     * Binary operator: subtraction
     */
    public static final int SUB = 1;
    /**
     * Binary operator: multiplication
     */
    public static final int MUL = 2;
    /**
     * Binary operator: division
     */
    public static final int DIV = 3;
    /**
     * Binary operator: exponentiation
     */
    public static final int POW = 4;
    /**
     * Binary operator: arctangent
     */
    public static final int ATAN2 = 5;
    /**
     * Binary operator: maximum
     */
    public static final int MAX = 6;
    /**
     * Binary operator: minimum
     */
    public static final int MIN = 7;
    /**
     * Binary operator: less than
     */
    public static final int LT = 8;
    /**
     * Binary operator: less or equal
     */
    public static final int LE = 9;
    /**
     * Binary operator: equality
     */
    public static final int EQ = 10;
    /**
     * Binary operator: inequality
     */
    public static final int NE = 11;
    /**
     * Binary operator: greater or equal
     */
    public static final int GE = 12;
    /**
     * Binary operator: greater than
     */
    public static final int GT = 13;
    /**
     * Binary operator: logical and
     */
    public static final int AND = 14;
    /**
     * Binary operator: logical or
     */
    public static final int OR = 15;
    /**
     * Binary operator: logical or
     */
    public static final int XOR = 16;

    /**
     * Unary operator: absolute value
     */
    public static final int ABS = 100;
    /**
     * Unary operator: arccosine
     */
    public static final int ACOS = 101;
    /**
     * Unary operator: arcsine
     */
    public static final int ASIN = 102;
    /**
     * Unary operator: arctangent
     */
    public static final int ATAN = 103;
    /**
     * Unary operator: ceiling
     */
    public static final int CEIL = 104;
    /**
     * Unary operator: cosine
     */
    public static final int COS = 105;
    /**
     * Unary operator: e to the x
     */
    public static final int EXP = 106;
    /**
     * Unary operator: floor
     */
    public static final int FLOOR = 107;
    /**
     * Unary operator: natural log
     */
    public static final int LOG = 108;
    /**
     * Unary operator: negation
     */
    public static final int NEG = 109;
    /**
     * Unary operator: rounding
     */
    public static final int ROUND = 110;
    /**
     * Unary operator: sine
     */
    public static final int SIN = 111;
    /**
     * Unary operator: square root
     */
    public static final int SQRT = 112;
    /**
     * Unary operator: tangent
     */
    public static final int TAN = 113;
    /**
     * Gamma function
     */
    public static final int GAMMA = 114;
    public static final int PRIMEFACTORS = 115;

    /**
     * Make a literal expression.
     *
     * @param v the constant value of the expression
     * @return an expression whose value is always v
     */
    public static Expr makeLiteral(Object v)
    {
        return new LiteralExpr((Double) v);
    }

    /**
     * Make an expression that applies a unary operator to an operand.
     *
     * @param rator a code for a unary operator
     * @param rand  operand
     * @return an expression meaning rator(rand)
     */
    public static Expr makeApp1(int rator, Expr rand)
    {
        Expr app = new UnaryExpr(rator, rand);
        if (app.value() instanceof Double)
            return rand instanceof LiteralExpr ? new LiteralExpr((Double) app.value()) : app;
        else
            return app;
    }

    /**
     * Make an expression that applies a binary operator to two operands.
     *
     * @param rator a code for a binary operator
     * @param rand0 left operand
     * @param rand1 right operand
     * @return an expression meaning rator(rand0, rand1)
     */
    public static Expr makeApp2(int rator, Expr rand0, Expr rand1)
    {
        Expr app = new BinaryExpr(rator, rand0, rand1);
        if (app.value() instanceof Double)
            return rand0 instanceof LiteralExpr && rand1 instanceof LiteralExpr ? new LiteralExpr(
                (Double) app.value()) : app;
        else
            return app;
    }

    /**
     * Make a conditional expression.
     *
     * @param test        `if' part
     * @param consequent  `then' part
     * @param alternative `else' part
     * @return an expression meaning `if test, then consequent, else
     * alternative'
     */
    public static Expr makeIfThenElse(Expr test, Expr consequent, Expr alternative)
    {
        Expr cond = new ConditionalExpr(test, consequent, alternative);
        if (test instanceof LiteralExpr)
            return (Double)test.value() != 0 ? consequent : alternative;
        else
            return cond;
    }
}

// These classes are all private to this module because we could
// plausibly want to do it in a completely different way, such as a
// stack machine.

