package wins.insomnia.sppcompiler;

import java.util.ArrayList;

public class Program {

    private final ArrayList<Statement> STATEMENTS = new ArrayList<>();

    public ArrayList<Statement> getStatements() {
        return new ArrayList<>(STATEMENTS);
    }

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
