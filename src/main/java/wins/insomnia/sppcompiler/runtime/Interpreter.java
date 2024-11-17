package wins.insomnia.sppcompiler.runtime;

import wins.insomnia.sppcompiler.tree.expression.AssignmentExpression;
import wins.insomnia.sppcompiler.tree.literal.Literal;
import wins.insomnia.sppcompiler.tree.statement.Program;
import wins.insomnia.sppcompiler.tree.statement.Statement;
import wins.insomnia.sppcompiler.tree.literal.LiteralNull;
import wins.insomnia.sppcompiler.tree.expression.Expression;
import wins.insomnia.sppcompiler.tree.statement.VariableDeclaration;
import wins.insomnia.sppcompiler.tree.statement.YapCall;

import java.util.ArrayList;

public class Interpreter {


    private Expression evaluateVariableDeclaration(VariableDeclaration variableDeclaration, Environment environment) {

        if (variableDeclaration.getValue() != null) {
            return environment.declareAndInitializeVariable(variableDeclaration.getIdentifier(), variableDeclaration.getValue());
        } else {
            return environment.declareVariable(variableDeclaration.getIdentifier());
        }


    }

    private Expression evaluateExpression(Expression expression, Environment environment) {

        if (expression == null) return new LiteralNull();

        return expression.evaluate(environment);

    }


    private void evaluateProgram(Program program, Environment environment) {

        ArrayList<Statement> statements = program.getStatements();
        for (Statement statement : statements) {
            Expression result = null;

            if (statement instanceof AssignmentExpression assignmentExpression) {
                result = assignmentExpression.evaluate(environment);
            }
            else if (statement instanceof YapCall yapCall) {

                Expression yapInput = yapCall.getValue().evaluate(environment);

                if (yapInput instanceof Literal<?> literal) {
                    System.out.println(literal.getValue());
                } else {
                    System.out.println(yapInput);
                }

            }
            else if (statement instanceof Expression expression) {

                result = evaluateExpression(expression, environment);
            }
            else if (statement instanceof VariableDeclaration variableDeclaration) {
                result = evaluateVariableDeclaration(variableDeclaration, environment).evaluate(environment);
            }

        }

    }


    public Environment runProgram(Program program) {

        Environment environment = new Environment();
        environment.initializeGlobalScope();
        evaluateProgram(program, environment);
        return environment;

    }

}
