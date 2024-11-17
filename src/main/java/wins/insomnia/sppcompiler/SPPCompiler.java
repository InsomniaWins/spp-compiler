package wins.insomnia.sppcompiler;

import wins.insomnia.sppcompiler.parse.Parser;
import wins.insomnia.sppcompiler.runtime.Interpreter;
import wins.insomnia.sppcompiler.tree.statement.Program;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SPPCompiler {


	public static void main(String[] args) {

		if (args.length < 1) {

			System.out.println("Incorrect usage. Please use as follows: java -jar sppcompiler.jar <source_file_path>");

			System.exit(1);
		}


		String sourceFilePath = args[0];
		Program program = compileSourceFile(sourceFilePath);

        System.out.println("Compiled successfully!\nWould you like to run your program? (Y/N)");
		Scanner scanner = new Scanner(System.in);
		String answer = scanner.nextLine().toLowerCase();

		while (!answer.equals("y") && !answer.equals("n")) {

			System.out.println("Please enter only either 'y' (for yes) or 'n' (for no) without the single quotes.");
			answer = scanner.nextLine().toLowerCase();

		}


		if (answer.equals("y")) {

			Interpreter interpreter = new Interpreter();
			interpreter.runProgram(program);

		}


	}



	private static Program compileSourceFile(String sourceFilePath) {

		File sourceFile = new File(sourceFilePath);

		Scanner sourceScanner;

		try {
			sourceScanner = new Scanner(sourceFile);

		} catch (FileNotFoundException e) {

			System.out.println("Could not find source file: \"" + sourceFilePath + "\"");

			throw new RuntimeException(e);
		}



		// tokenize
		//System.out.println(" >> tokenizing " + sourceFilePath + " . . .");
		LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(sourceScanner);
		ArrayList<Token> tokens = lexicalAnalyzer.getTokens();

		/*
		for (Token token : tokens) {
			System.out.println(token.tokenType() + ":  \"" + token.tokenValue() + "\"");
		}*/

		sourceScanner.close();


		// parse tokens
		Parser parser = new Parser(tokens);
        return parser.parseProgram();

	}



}
