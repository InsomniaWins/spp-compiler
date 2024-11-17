package wins.insomnia.sppcompiler.parse;

import wins.insomnia.sppcompiler.tree.statement.Program;
import wins.insomnia.sppcompiler.tree.statement.Statement;
import wins.insomnia.sppcompiler.Token;
import wins.insomnia.sppcompiler.parse.literal.LiteralInteger;
import wins.insomnia.sppcompiler.parse.literal.LiteralNull;
import wins.insomnia.sppcompiler.tree.expression.BinaryExpression;
import wins.insomnia.sppcompiler.tree.expression.Expression;
import wins.insomnia.sppcompiler.tree.expression.Identifier;
import java.util.ArrayList;

public class Parser {

	private ArrayList<Token> tokens;

	private Token peekNext() {
		return peekNext(0);
	}

	private Token peekNext(int offset) {

		if (tokens.size() - 1 < offset) {
			return null;
		}

		return tokens.get(offset);
	}

	private Token popNextExpected(Token.TokenType tokenType) {
		Token token = popNext();
		if (token == null || token.tokenType() != tokenType) {
			throw new RuntimeException("Unexpected token! Expecting: " + tokenType + " but got: " + token);
		}
		return token;
	}

	private Token popNext() {
		Token returnToken = tokens.getFirst(); 
		tokens.removeFirst();

		return returnToken;
	}


	public Program parseProgram() {

		Program program = new Program();

		while (!tokens.isEmpty()) {

			program.pushExpression(parseStatement());

		}

		return program;

	}

	private Expression parseExpression() {

		return parseAdditionExpression();

	}


	private Expression parseAdditionExpression() {

		Expression leftExpression = parseMultiplicationExpression();

		while (peekNext() != null && (peekNext().tokenType() == Token.TokenType.OPERATOR_ADD || peekNext().tokenType() == Token.TokenType.OPERATOR_SUBTRACT)) {

			Token.TokenType operator = popNext().tokenType();
			Expression rightExpression = parseMultiplicationExpression();

			leftExpression = new BinaryExpression(leftExpression, rightExpression, operator);

		}

		return leftExpression;
	}

	private Expression parseMultiplicationExpression() {

		Expression leftExpression = parsePrimaryExpression();

		while (peekNext() != null && (peekNext().tokenType() == Token.TokenType.OPERATOR_MULTIPLY || peekNext().tokenType() == Token.TokenType.OPERATOR_DIVIDE)) {

			Token.TokenType operator = popNext().tokenType();
			Expression rightExpression = parsePrimaryExpression();

			leftExpression = new BinaryExpression(leftExpression, rightExpression, operator);

		}

		return leftExpression;
	}

	private Expression parsePrimaryExpression() {

		Token nextToken = peekNext();
		Token.TokenType tokenType = nextToken.tokenType();

		switch (tokenType) {

			case Token.TokenType.IDENTIFIER -> {
				return new Identifier((String) popNext().tokenValue());
			}

			case Token.TokenType.LITERAL_NULL -> {

				popNext();
				return new LiteralNull();

			}

			case Token.TokenType.LITERAL_INT -> {
				return new LiteralInteger((Integer) popNext().tokenValue());
			}

			case Token.TokenType.OPENING_BRACE -> {
				Token openBrace = popNext();
				Expression valueWithinBraces = parseExpression();
				Token closeBrace = popNextExpected(Token.TokenType.CLOSING_BRACE);
				return valueWithinBraces;
			}

			case Token.TokenType.END_OF_FILE -> {
				// do nothing
				popNext();
				return null;
			}

			default -> {
				throw new RuntimeException("Encountered unexpected token! : " + nextToken);
			}
		}

	}

	private Expression parseStatement() {

		return parseExpression();

	}


	public Parser(ArrayList<Token> tokens) {

		this.tokens = tokens;

	}

}
