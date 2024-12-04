package wins.insomnia.sppcompiler.tree.statement;


import java.util.ArrayList;

/*


This class acts as a wrapper for the "program" coded by the user.
It holds the statements written by the user and extends Statement.
It acts as the ROOT node in the AST



 */



public class Program extends Statement {

    // statements array to hold all statements of program
    private final ArrayList<Statement> STATEMENTS = new ArrayList<>();

    // get statements array
    public ArrayList<Statement> getStatements() {
        return new ArrayList<>(STATEMENTS);
    }

    // push statement to statements array
    public void pushStatement(Statement statement) {
        STATEMENTS.add(statement);
    }


    @Override
    public String toString() {

        StringBuilder outString = new StringBuilder("- Start Program -\n");

        for (Statement statement : STATEMENTS) {

            if (statement == null) continue;

            outString.append(statement).append('\n');
        }

        outString.append("- End Program -");

        return outString.toString();
    }
}
