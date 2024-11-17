package wins.insomnia.sppcompiler.runtime;

import wins.insomnia.sppcompiler.Program;
import wins.insomnia.sppcompiler.Statement;
import wins.insomnia.sppcompiler.parse.literal.LiteralBool;
import wins.insomnia.sppcompiler.parse.literal.LiteralInteger;
import wins.insomnia.sppcompiler.parse.literal.LiteralNull;
import wins.insomnia.sppcompiler.parse.misc.Expression;
import java.util.ArrayList;

public class Interpreter {

    private Statement evaluateStatement(Statement statement, Environment environment) {

        if (statement instanceof Expression expression) {

            return expression.evaluate(environment);

        }

        return new LiteralNull();

    }


    private void evaluateProgram(Program program, Environment environment) {

        ArrayList<Statement> statements = program.getStatements();
        for (Statement statement : statements) {

            Statement result = evaluateStatement(statement, environment);
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
