package wins.insomnia.sppcompiler.parse;

import java.util.ArrayList;

public class SyntaxTree {

	private final ArrayList<SyntaxTreeNode> NODES = new ArrayList<>();

	public void addNode(SyntaxTreeNode node) {
		NODES.add(node);
	}

	@Override
	public String toString() {
		StringBuilder outputString = new StringBuilder();

		for (SyntaxTreeNode node : NODES) {
			if (node == null) {
				outputString.append("null, ");
				continue;
			}

			outputString.append(node).append(", ");
		}

		return outputString.toString();
	}

}
