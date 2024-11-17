package wins.insomnia.sppcompiler.tree.literal;


public class LiteralInteger extends Literal<Integer> {
	public LiteralInteger() {
		super(0);
	}

	public LiteralInteger(Integer value) {
		super(value);
	}
}
