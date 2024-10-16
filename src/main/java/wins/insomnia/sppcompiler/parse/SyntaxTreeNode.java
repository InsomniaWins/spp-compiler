package wins.insomnia.sppcompiler.parse;

import java.util.ArrayList;

public class SyntaxTreeNode {
	private SyntaxTree syntaxTree = null;
	private SyntaxTreeNode parent = null;
	private final ArrayList<SyntaxTreeNode> CHILDREN = new ArrayList<>();


	// constructor for non-root node
	public SyntaxTreeNode(SyntaxTreeNode... children) {

		for (SyntaxTreeNode child : children) {
			addChild(child);
		}

	}

	public void addChild(SyntaxTreeNode node) {

		if (node.parent != null) {
			return;
		}

		CHILDREN.add(node);
		node.parent = this;

	}

	public void removeChild(SyntaxTreeNode node) {

		if (node.parent != this) {
			return;
		}

		CHILDREN.remove(node);
		node.parent = null;

	}


	public SyntaxTreeNode getParent() {
		return parent;
	}

	public ArrayList<SyntaxTreeNode> getChildren() {
		return CHILDREN;
	}

	public SyntaxTreeNode getChildAt(int childIndex) {
		if (CHILDREN.size() > childIndex) {
			return CHILDREN.get(childIndex);
		}

		return null;
	}

	public void removeChildAt(int childIndex) {

		if (getChildren().size() <= childIndex) {
			return;
		}

		SyntaxTreeNode child = getChildren().get(childIndex);
		removeChild(child);
	}

	public boolean isRoot() {
		return syntaxTree != null;
	}

	protected void setTree(SyntaxTree tree) {
		syntaxTree = tree;
	}


	public SyntaxTreeNode evaluate() {
		return null;
	}



}
