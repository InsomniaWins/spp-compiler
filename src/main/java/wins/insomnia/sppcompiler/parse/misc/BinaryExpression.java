package wins.insomnia.sppcompiler.parse.misc;

import wins.insomnia.sppcompiler.Token;

public class BinaryExpression extends Expression {

    private final Expression LEFT_EXPRESSION;
    private final Expression RIGHT_EXPRESSION;
    private final Token.TokenType OPERATOR;

    public BinaryExpression(Expression leftExpression, Expression rightExpression, Token.TokenType operator) {

        this.LEFT_EXPRESSION = leftExpression;
        this.RIGHT_EXPRESSION = rightExpression;
        this.OPERATOR = operator;

    }

    public Expression getLeft() {
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
        return "BinaryExpression : {" + getLeft() + ' ' + getOperator() + ' ' + getRightExpression() + "}";
    }

}
