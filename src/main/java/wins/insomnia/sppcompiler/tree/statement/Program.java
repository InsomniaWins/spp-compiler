package wins.insomnia.sppcompiler.tree.statement;

import wins.insomnia.sppcompiler.tree.expression.Expression;

import java.util.ArrayList;

public class Program extends Statement {

    private final ArrayList<Expression> EXPRESSIONS = new ArrayList<>();

    public ArrayList<Expression> getExpressions() {
        return new ArrayList<>(EXPRESSIONS);
    }

    public void pushExpression(Expression expression) {
        EXPRESSIONS.add(expression);
    }


    @Override
    public String toString() {

        StringBuilder outString = new StringBuilder("- Start Program -\n");

        for (Expression expression : EXPRESSIONS) {

            if (expression == null) continue;

            outString.append(expression).append('\n');
        }

        outString.append("- End Program -");

        return outString.toString();
    }
}
