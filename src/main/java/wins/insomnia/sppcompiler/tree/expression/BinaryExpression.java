package wins.insomnia.sppcompiler.tree.expression;

import wins.insomnia.sppcompiler.Token;
import wins.insomnia.sppcompiler.parse.literal.LiteralInteger;
import wins.insomnia.sppcompiler.parse.literal.LiteralNull;
import wins.insomnia.sppcompiler.runtime.Environment;

public class BinaryExpression extends Expression {

    private final Expression LEFT_EXPRESSION;
    private final Expression RIGHT_EXPRESSION;
    private final Token.TokenType OPERATOR;

    public BinaryExpression(Expression leftExpression, Expression rightExpression, Token.TokenType operator) {

        this.LEFT_EXPRESSION = leftExpression;
        this.RIGHT_EXPRESSION = rightExpression;
        this.OPERATOR = operator;

    }

    public Expression getLeftExpression() {
        return LEFT_EXPRESSION;
    }

    public Expression getRightExpression() {
        return RIGHT_EXPRESSION;
    }

    public Token.TokenType getOperator() {
        return OPERATOR;
    }

    @Override
    public String toString() {
        return "BinaryExpression : {" + getLeftExpression() + ' ' + getOperator() + ' ' + getRightExpression() + "}";
    }

    @Override
    public Expression evaluate(Environment environment) {

        Expression leftExpression = getLeftExpression().evaluate(environment);
        Expression rightExpression = getRightExpression().evaluate(environment);

        if (leftExpression instanceof LiteralInteger leftInt && rightExpression instanceof LiteralInteger rightInt) {

            switch (getOperator()) {
                case OPERATOR_ADD -> {
                    return new LiteralInteger(leftInt.getValue() + rightInt.getValue());
                }

                case OPERATOR_SUBTRACT -> {
                    return new LiteralInteger(leftInt.getValue() - rightInt.getValue());
                }

                case OPERATOR_MULTIPLY -> {
                    return new LiteralInteger(leftInt.getValue() * rightInt.getValue());
                }

                case OPERATOR_DIVIDE -> {
                    return new LiteralInteger(leftInt.getValue() / rightInt.getValue());
                }
            }

        }

        return new LiteralNull();

    }


}
