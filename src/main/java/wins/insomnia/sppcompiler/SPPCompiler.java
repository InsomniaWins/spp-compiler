package wins.insomnia.sppcompiler;

import wins.insomnia.sppcompiler.parse.Parser;
import wins.insomnia.sppcompiler.parse.SyntaxAnalyzer;
import wins.insomnia.sppcompiler.parse.SyntaxTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class SPPCompiler {


	public static void main(String[] args) {

		if (args.length < 1) {

			System.out.println("Incorrect usage. Please use as follows: java -jar sppcompiler.jar <source_file_path>");

			System.exit(1);
		}


		String sourceFilePath = args[0];
		compileSourceFile(sourceFilePath);


	}



	private static void compileSourceFile(String sourceFilePath) {

		File sourceFile = new File(sourceFilePath);

		Scanner sourceScanner;

		try {
			sourceScanner = new Scanner(sourceFile);

		} catch (FileNotFoundException e) {

			System.out.println("Could not find source file: \"" + sourceFilePath + "\"");

			throw new RuntimeException(e);
		}



		// tokenize
		System.out.println(" >> tokenizing " + sourceFilePath + " . . .");
		LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(sourceScanner);
		ArrayList<Token> tokens = lexicalAnalyzer.getTokens();

		for (Token token : tokens) {
			System.out.println(token.tokenType() + ":  \"" + token.tokenValue() + "\"");
		}

		sourceScanner.close();


		// parse tokens
		Parser parser = new Parser(tokens);


		// syntax analysis
		SyntaxAnalyzer syntaxAnalyzer = new SyntaxAnalyzer(parser);

	}



}
