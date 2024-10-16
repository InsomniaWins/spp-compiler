package wins.insomnia.sppcompiler.parse;

import wins.insomnia.sppcompiler.Token;
import wins.insomnia.sppcompiler.parse.literal.LiteralInteger;
import wins.insomnia.sppcompiler.parse.operation.*;

import java.util.ArrayList;

public class Parser {

	public Parser(ArrayList<Token> tokens) {

		SyntaxTree tree = new SyntaxTree();
		OperationAdd add1 = new OperationAdd(
				new OperationNegate(
						new LiteralInteger(5)
				),
				new OperationMultiply(
						new LiteralInteger(2),
						new OperationDivide(
								new LiteralInteger(20),
								new LiteralInteger(2)
						)
				));

		tree.setRoot(add1);
		tree.printTree();


		SyntaxTreeNode eval = add1.evaluate();
		System.out.println(eval);
	}


}
