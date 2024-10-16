package wins.insomnia.sppcompiler.parse.operation;

import wins.insomnia.sppcompiler.parse.SyntaxTreeNode;
import wins.insomnia.sppcompiler.parse.literal.LiteralInteger;

public class OperationSubtract extends SyntaxTreeNode {

	public SyntaxTreeNode left;
	public SyntaxTreeNode right;

	public OperationSubtract() {}
	public OperationSubtract(SyntaxTreeNode left, SyntaxTreeNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public String toString() {
		return "(" + left + " - " + right + ")";
	}


	@Override
	public SyntaxTreeNode evaluate() {

		SyntaxTreeNode leftEvaluation = left.evaluate();
		SyntaxTreeNode rightEvaluation = right.evaluate();

		if (!(leftEvaluation instanceof LiteralInteger) || !(rightEvaluation instanceof LiteralInteger)) {
			throw new RuntimeException("Tried to subtract " + rightEvaluation + " from " + leftEvaluation + "!\nThese must be int literals!");
		}

		int newValue = ((LiteralInteger) leftEvaluation).value;
		newValue -= ((LiteralInteger) rightEvaluation).value;

		return new LiteralInteger(newValue);
	}

}
