package wins.insomnia.sppcompiler.runtime;

import wins.insomnia.sppcompiler.tree.expression.AssignmentExpression;
import wins.insomnia.sppcompiler.tree.literal.Literal;
import wins.insomnia.sppcompiler.tree.literal.LiteralBool;
import wins.insomnia.sppcompiler.tree.literal.LiteralInteger;
import wins.insomnia.sppcompiler.tree.statement.*;
import wins.insomnia.sppcompiler.tree.literal.LiteralNull;
import wins.insomnia.sppcompiler.tree.expression.Expression;

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

                    if (yapInput instanceof LiteralBool bool) {
                        System.out.println(bool.getValue() ? LiteralBool.TRUE_SYNONYM : LiteralBool.FALSE_SYNONYM);
                    } else {
                        System.out.println(literal.getValue());
                    }

                } else {
                    System.out.println(yapInput);
                }

            }
            else if (statement instanceof ProgramEepyCall programEepyCall) {

                Expression eepyInput = programEepyCall.getValue().evaluate(environment);

                if (!(eepyInput instanceof LiteralInteger)) {
                    throw new RuntimeException("Attempted to call program_eepy() with non-integer-literal argument!");
                }

                long sleepValue = Long.valueOf(((LiteralInteger) eepyInput).getValue());
                try {
                    Thread.sleep(sleepValue);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
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
