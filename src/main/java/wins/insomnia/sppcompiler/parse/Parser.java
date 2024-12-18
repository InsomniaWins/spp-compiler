package wins.insomnia.sppcompiler.parse;

import wins.insomnia.sppcompiler.tree.expression.AssignmentExpression;
import wins.insomnia.sppcompiler.tree.literal.LiteralString;
import wins.insomnia.sppcompiler.tree.statement.*;
import wins.insomnia.sppcompiler.Token;
import wins.insomnia.sppcompiler.tree.literal.LiteralInteger;
import wins.insomnia.sppcompiler.tree.literal.LiteralNull;
import wins.insomnia.sppcompiler.tree.expression.BinaryExpression;
import wins.insomnia.sppcompiler.tree.expression.Expression;
import wins.insomnia.sppcompiler.tree.expression.Identifier;

import java.util.ArrayList;

public class Parser {

	private ArrayList<Token> tokens;

	// preview next token to be popped from the stack
	private Token peekNext() {
		return peekNext(0);
	}

	// preview the next + offset token to be popped from stack
	private Token peekNext(int offset) {

		if (tokens.size() - 1 < offset) {
			return null;
		}

		return tokens.get(offset);
	}

	// pop the next token from the stack
	// if the token is not equal to {tokenType}, throw an exception
	private Token popNextExpected(Token.TokenType tokenType) {
		Token token = popNext();
		if (token == null || token.tokenType() != tokenType) {
			throw new RuntimeException("Unexpected token! Expecting: " + tokenType + " but got: " + token);
		}
		return token;
	}

	// pop next token from the stack
	private Token popNext() {
		Token returnToken = tokens.getFirst(); 
		tokens.removeFirst();

		return returnToken;
	}


	// creates an returns a program from the parsed statements
	public Program parseProgram() {

		Program program = new Program();

		while (!tokens.isEmpty()) {

			Statement parsedStatement = parseStatement();

			if (parsedStatement == null) continue;

			program.pushStatement(parsedStatement);

		}

		return program;

	}

	// parses expression (should be used for root node)
	private Expression parseExpression() {
		return parseAssignmentExpression();
	}

	// parses an assignment expression
	private Expression parseAssignmentExpression() {
		Expression left = parseAdditionExpression();

		if (peekNext() != null && peekNext().tokenType() == Token.TokenType.OPERATOR_SET_EQUALS) {
			popNext();

			if (!(left instanceof Identifier)) {
				throw new RuntimeException("Attempted assigning a value to an expression not of type \"identifier\": " + left);
			}

			Expression value = parseAssignmentExpression();
			return new AssignmentExpression((Identifier) left, value);
		}

		return left;
	}

	// parses an addition expression
	private Expression parseAdditionExpression() {

		Expression leftExpression = parseMultiplicationExpression();

		while (peekNext() != null && (peekNext().tokenType() == Token.TokenType.OPERATOR_ADD || peekNext().tokenType() == Token.TokenType.OPERATOR_SUBTRACT)) {

			Token.TokenType operator = popNext().tokenType();
			Expression rightExpression = parseMultiplicationExpression();

			leftExpression = new BinaryExpression(leftExpression, rightExpression, operator);

		}

		return leftExpression;
	}

	// parses a multiplication expression
	private Expression parseMultiplicationExpression() {

		Expression leftExpression = parsePrimaryExpression();

		while (peekNext() != null && (peekNext().tokenType() == Token.TokenType.OPERATOR_MULTIPLY || peekNext().tokenType() == Token.TokenType.OPERATOR_DIVIDE)) {

			Token.TokenType operator = popNext().tokenType();
			Expression rightExpression = parsePrimaryExpression();

			leftExpression = new BinaryExpression(leftExpression, rightExpression, operator);

		}

		return leftExpression;
	}

	// parses the primary expression into tokens
	private Expression parsePrimaryExpression() {

		Token nextToken = peekNext();
		Token.TokenType tokenType = nextToken.tokenType();

		// handle next/first token
		switch (tokenType) {

			case Token.TokenType.FUNCTION_YAP -> {

				popNext();
				popNextExpected(Token.TokenType.OPENING_ROUND_BRACKET);
				Expression valueWithinBrackets = parseExpression();
				popNextExpected(Token.TokenType.CLOSING_ROUND_BRACKET);

				return valueWithinBrackets;
			}

			case Token.TokenType.NEW_LINE -> {
				popNext();
				return null;
			}

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

			case Token.TokenType.LITERAL_STRING -> {
				return new LiteralString((String) popNext().tokenValue());
			}



			case Token.TokenType.OPENING_ROUND_BRACKET -> {
				Token openBracket = popNext();
				Expression valueWithinBrackets = parseExpression();
				Token closeBracket = popNextExpected(Token.TokenType.CLOSING_ROUND_BRACKET);
				return valueWithinBrackets;
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

	// parse the program_eepy function
	private Statement parseProgramEepyCall() {

		popNext();
		popNextExpected(Token.TokenType.OPENING_ROUND_BRACKET);

		Expression eepyExpression = parseExpression();

		popNextExpected(Token.TokenType.CLOSING_ROUND_BRACKET);

		return new ProgramEepyCall(eepyExpression);
	}

	// parse the yap function
	private Statement parseYapCall() {

		popNext();
		popNextExpected(Token.TokenType.OPENING_ROUND_BRACKET);

		Expression yapExpression = parseExpression();

		popNextExpected(Token.TokenType.CLOSING_ROUND_BRACKET);

		return new YapCall(yapExpression);
	}

	// parse a variable declaration
	private Statement parseVariableDeclaration() {

		Token token = popNext();
		Token identifierNameToken = popNextExpected(Token.TokenType.IDENTIFIER);

		String identifierName = (String) identifierNameToken.tokenValue();

		if (peekNext().tokenType() == Token.TokenType.NEW_LINE) {

			popNext();
			return new VariableDeclaration(identifierName);

		}

		popNextExpected(Token.TokenType.OPERATOR_SET_EQUALS);

		VariableDeclaration variableDeclaration = new VariableDeclaration(identifierName, parseExpression());

		if (peekNext().tokenType() == Token.TokenType.NEW_LINE) popNext();

		return variableDeclaration;
	}

	// generic, parse statement
	private Statement parseStatement() {
		switch (peekNext().tokenType()) {
			case Token.TokenType.KEYWORD_BET -> {
				return parseVariableDeclaration();
			}
			case Token.TokenType.FUNCTION_YAP -> {
				return parseYapCall();
			}
			case Token.TokenType.FUNCTION_PROGRAM_EEPY -> {
				return parseProgramEepyCall();
			}
            default -> {
                return parseExpression();
            }
        }
	}

	// constructor for parser
	public Parser(ArrayList<Token> tokens) {

		this.tokens = tokens;

	}

}
