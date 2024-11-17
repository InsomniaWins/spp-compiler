package wins.insomnia.sppcompiler;

public record Token(TokenType tokenType, Object tokenValue, int lineIndex) {

	public enum TokenType {
		// built-in functions
		FUNCTION_YAP,       // yap( . . . )


		// keywords
		KEYWORD_BET,        // bet


		// literals
		LITERAL_INT,
		LITERAL_STRING,
		LITERAL_DOUBLE,
		LITERAL_BOOLEAN,
		LITERAL_VOID,
		LITERAL_NULL,


		// operators
		OPERATOR_ADD,            // +
		OPERATOR_SUBTRACT,       // -
		OPERATOR_MULTIPLY,       // *
		OPERATOR_DIVIDE,         // /
		OPERATOR_COMPARE_EQUALS, // ==
		OPERATOR_SET_EQUALS,     // =


		// special symbols
		QUOTATION_MARK,
		OPENING_CURLY_BRACKET,  // {
		CLOSING_CURLY_BRACKET,  // }
		OPENING_ROUND_BRACKET,  // (
		CLOSING_ROUND_BRACKET,  // )
		OPENING_SQUARE_BRACKET, // [
		CLOSING_SQUARE_BRACKET, // ]
		COMMA,                  // ,
		COLON,                  // :

		// misc
		NEW_LINE,              // ;
		IDENTIFIER,

		END_OF_FILE
	}
}
