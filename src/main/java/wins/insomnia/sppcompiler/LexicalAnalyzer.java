package wins.insomnia.sppcompiler;

import java.util.ArrayList;
import java.util.Scanner;

public class LexicalAnalyzer {

	private final ArrayList<Token> TOKENS;
	private final Scanner FILE_SCANNER;

	private int currentLineIndex = 0;
	private String currentLine = "";
	private int currentCharIndex = 0;

	public LexicalAnalyzer(Scanner fileScanner) {

		TOKENS = new ArrayList<Token>();
		FILE_SCANNER = fileScanner;

		tokenize();

	}

	private boolean hasNextChar() {
		return currentLine.length() - 1 > currentCharIndex;
	}

	private char nextChar() {
		currentCharIndex += 1;

		return currentLine.charAt(currentCharIndex);
	}

	private char peekChar() {
		return peekChar(1);
	}

	private char peekChar(int offset) {

		if (currentCharIndex + offset > currentLine.length() - 1) {
			return '\0';
		}

		return currentLine.charAt(currentCharIndex + offset);
	}


	private void tokenize() {
		currentLineIndex = -1;

		lineLoop: while (FILE_SCANNER.hasNextLine()) {
			currentLine = FILE_SCANNER.nextLine();
			currentLineIndex += 1;

			if (currentLine.isEmpty()) {
				continue;
			}

			boolean isLineComment = false;
			String tokenValue = "";
			StringBuilder tokenValueBuilder = new StringBuilder();

			currentCharIndex = -1;


			charLoop: while (hasNextChar()) {
				tokenValueBuilder.setLength(0);
				char currentChar = nextChar();


				// check for line comments
				if (currentChar == '#' || (currentChar == '/' && peekChar() == '/')) {
					isLineComment = true;
					break charLoop;
				}

				// white space
				if (currentChar == ' ' || currentChar == '\t') {

					continue;

				}

				// check for keywords and identifiers
				if (Character.isAlphabetic(currentChar)) {

					tokenValueBuilder.append(currentChar);

					while (hasNextChar() && (Character.isAlphabetic(peekChar()) || Character.isDigit(peekChar()))) {

						currentChar = nextChar();
						tokenValueBuilder.append(currentChar);

					}

					tokenValue = tokenValueBuilder.toString();

					switch (tokenValue) {

						case "void" -> TOKENS.add(new Token(Token.TokenType.LITERAL_VOID, null, currentLineIndex));
						case "bet" -> TOKENS.add(new Token(Token.TokenType.KEYWORD_BET, null, currentLineIndex));
						case "yap" -> TOKENS.add(new Token(Token.TokenType.FUNCTION_YAP, null, currentLineIndex));
						case "null" -> TOKENS.add(new Token(Token.TokenType.LITERAL_NULL, null, currentLineIndex));
						default -> TOKENS.add(new Token(Token.TokenType.IDENTIFIER, tokenValue, currentLineIndex));

					}
					tokenValueBuilder.setLength(0);
				}


				// int literal
				else if (Character.isDigit(currentChar)) {

					tokenValueBuilder.append(currentChar);

					while (Character.isDigit(peekChar())) {

						currentChar = nextChar();
						tokenValueBuilder.append(currentChar);

					}

					tokenValue = tokenValueBuilder.toString();
					tokenValueBuilder.setLength(0);

					TOKENS.add(new Token(Token.TokenType.LITERAL_INT, Integer.valueOf(tokenValue), currentLineIndex));

				}


				// string literal
				else if (currentChar == '"') {

					int literalStartLine = currentLineIndex;

					currentChar = nextChar();

					tokenValueBuilder.append(currentChar);

					while (hasNextChar() && peekChar() != '"') {

						currentChar = nextChar();
						tokenValueBuilder.append(currentChar);

					}

					if (peekChar() != '"') {
						throw new RuntimeException("Failed to find closing quotation mark for string literal starting on line: " + currentLineIndex + 1 + "!");
					}

					tokenValue = tokenValueBuilder.toString();

					currentChar = nextChar();

					TOKENS.add(new Token(Token.TokenType.LITERAL_STRING, tokenValue, currentLineIndex));

					tokenValueBuilder.setLength(0);
				}

				// special characters
				else {

					switch (currentChar) {
						case '+' -> TOKENS.add(new Token(Token.TokenType.OPERATOR_ADD, null, currentLineIndex));
						case '-' -> TOKENS.add(new Token(Token.TokenType.OPERATOR_SUBTRACT, null, currentLineIndex));
						case '*' -> TOKENS.add(new Token(Token.TokenType.OPERATOR_MULTIPLY, null, currentLineIndex));
						case '/' -> TOKENS.add(new Token(Token.TokenType.OPERATOR_DIVIDE, null, currentLineIndex));
						case ';' -> TOKENS.add(new Token(Token.TokenType.NEW_LINE, null, currentLineIndex));
						case '=' -> {
							if (peekChar() == '=') {
								currentChar = nextChar();
								TOKENS.add(new Token(Token.TokenType.OPERATOR_COMPARE_EQUALS, null, currentLineIndex));
							} else {
								TOKENS.add(new Token(Token.TokenType.OPERATOR_SET_EQUALS, null, currentLineIndex));
							}
						}
						case ',' -> TOKENS.add(new Token(Token.TokenType.COMMA, null, currentLineIndex));
						case ':' -> TOKENS.add(new Token(Token.TokenType.COLON, null, currentLineIndex));
						case '{' -> TOKENS.add(new Token(Token.TokenType.OPENING_CURLY_BRACKET, null, currentLineIndex));
						case '}' -> TOKENS.add(new Token(Token.TokenType.CLOSING_CURLY_BRACKET, null, currentLineIndex));
						case '(' -> TOKENS.add(new Token(Token.TokenType.OPENING_ROUND_BRACKET, null, currentLineIndex));
						case ')' -> TOKENS.add(new Token(Token.TokenType.CLOSING_ROUND_BRACKET, null, currentLineIndex));
						case '[' -> TOKENS.add(new Token(Token.TokenType.OPENING_SQUARE_BRACKET, null, currentLineIndex));
						case ']' -> TOKENS.add(new Token(Token.TokenType.CLOSING_SQUARE_BRACKET, null, currentLineIndex));
						default -> {

							System.out.println("Failed to compile!");
							System.out.println("Failed to find valid code-path for \"" + currentChar + "\" at line " + currentLineIndex + ":" + currentCharIndex);
							System.exit(1);

						}
					}

				}
			}


		}

		TOKENS.add(new Token(Token.TokenType.END_OF_FILE, null, currentLineIndex));
		FILE_SCANNER.reset();
	}

	public ArrayList<Token> getTokens() {
		return new ArrayList<Token>(TOKENS);
	}

}
