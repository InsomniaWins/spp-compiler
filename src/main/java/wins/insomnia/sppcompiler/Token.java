package wins.insomnia.sppcompiler;

import java.util.HashMap;

public record Token(TokenType tokenType, Object tokenValue, int lineIndex) {

	public enum TokenType {
		// built-in functions
		FUNCTION_LOCK_IN,


		// keywords
		KEYWORD_FLEX,
		KEYWORD_SIGMA,
		KEYWORD_IS_GIVING,
		KEYWORD_INT,


		// literals
		LITERAL_INT,
		LITERAL_STRING,
		LITERAL_DOUBLE,
		LITERAL_BOOLEAN,
		LITERAL_VOID,
		LITERAL_NULL,


		// operators
		OPERATOR_ADD,
		OPERATOR_SUBTRACT,
		OPERATOR_MULTIPLY,
		OPERATOR_DIVIDE,
		OPERATOR_COMPARE_EQUALS,
		OPERATOR_SET_EQUALS,


		// special symbols
		OPENING_CURLY_BRACE,
		CLOSING_CURLY_BRACE,
		OPENING_BRACE,
		CLOSING_BRACE,
		COMMA,


		// misc
		NEW_LINE,
		IDENTIFIER,

		END_OF_FILE
	}
}
