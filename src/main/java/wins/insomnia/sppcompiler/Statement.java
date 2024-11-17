package wins.insomnia.sppcompiler;

import wins.insomnia.sppcompiler.parse.literal.Literal;
import wins.insomnia.sppcompiler.parse.literal.LiteralInteger;
import wins.insomnia.sppcompiler.parse.misc.BinaryExpression;

public class Statement {

    public static String serialize(Statement statement) {

        if (statement == null) return "";

        if (statement instanceof BinaryExpression binaryExpression) {
            return "BinaryExpression{" +
                    serialize(binaryExpression.getLeftExpression()) +
                    ", " +
                    binaryExpression.getOperator() +
                    ", " +
                    serialize(binaryExpression.getRightExpression()) +
                    "}";
        }
        else if (statement instanceof LiteralInteger integerLiteral) {
            return "LiteralInteger{" +
                    integerLiteral.getValue() +
                    "}";
        }

        throw new RuntimeException("Serialize method is empty for: " + statement);
    }


    public static Statement deserialize(String input) {



        throw new RuntimeException("Deserialize method is empty for: " + input);
    }

}
