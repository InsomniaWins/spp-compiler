package wins.insomnia.sppcompiler.runtime;

import wins.insomnia.sppcompiler.tree.statement.Program;
import wins.insomnia.sppcompiler.tree.statement.Statement;
import wins.insomnia.sppcompiler.parse.literal.LiteralBool;
import wins.insomnia.sppcompiler.parse.literal.LiteralNull;
import wins.insomnia.sppcompiler.tree.expression.Expression;
import java.util.ArrayList;

public class Interpreter {

    private Expression evaluateExpression(Expression expression, Environment environment) {

        if (expression == null) return new LiteralNull();

        return expression.evaluate(environment);

    }


    private void evaluateProgram(Program program, Environment environment) {

        ArrayList<Expression> expressions = program.getExpressions();
        for (Expression expression : expressions) {

            Expression result = evaluateExpression(expression, environment);
            System.out.println(result);

        }

    }


    public Environment runProgram(Program program) {

        Environment environment = new Environment();
        environment.declareAndInitializeVariable("false", new LiteralBool(false));
        environment.declareAndInitializeVariable("true", new LiteralBool(true));
        evaluateProgram(program, environment);
        return environment;

    }


    // used for testing/running the interpreter
    public static void main(String[] args) {



    }

}
