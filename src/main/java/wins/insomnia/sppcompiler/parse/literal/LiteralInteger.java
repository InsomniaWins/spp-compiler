package wins.insomnia.sppcompiler.parse.literal;

import wins.insomnia.sppcompiler.parse.SyntaxTreeNode;
import wins.insomnia.sppcompiler.parse.misc.Expression;

public class LiteralInteger extends Expression {

	public Integer value;

	public LiteralInteger(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}
}
