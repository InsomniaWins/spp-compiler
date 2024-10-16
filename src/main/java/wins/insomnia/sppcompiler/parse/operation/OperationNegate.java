package wins.insomnia.sppcompiler.parse.operation;

import wins.insomnia.sppcompiler.parse.SyntaxTreeNode;
import wins.insomnia.sppcompiler.parse.literal.LiteralInteger;

public class OperationNegate extends SyntaxTreeNode {

	public SyntaxTreeNode argument;

	public OperationNegate(SyntaxTreeNode argument) {
		this.argument = argument;
	}

	@Override
	public String toString() {
		return "-(" + argument + ")";
	}

	@Override
	public SyntaxTreeNode evaluate() {

		if (!(argument instanceof LiteralInteger)) {
			throw new RuntimeException("Tried to negate " + argument + "!\nThis must be int literal!");
		}

		return new LiteralInteger(-(((LiteralInteger) argument).value));
	}

}
