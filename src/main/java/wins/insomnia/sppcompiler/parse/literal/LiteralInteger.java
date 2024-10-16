package wins.insomnia.sppcompiler.parse.literal;

import wins.insomnia.sppcompiler.parse.SyntaxTreeNode;

public class LiteralInteger extends SyntaxTreeNode {

	public Integer value;

	public LiteralInteger(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	@Override
	public SyntaxTreeNode evaluate() {
		return this;
	}
}
