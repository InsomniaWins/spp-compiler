package wins.insomnia.sppcompiler.parse;

public class SyntaxTree {

	private SyntaxTreeNode root;

	public void setRoot(SyntaxTreeNode newRoot) {

		if (newRoot.getParent() != null) {
			System.out.println("Cannot set node with parents as the tree root!");
			return;
		}

		if (root != null) {
			root.setTree(null);
		}

		newRoot.setTree(this);
		root = newRoot;

	}


	public void printTree() {
		if (root == null) {
			System.out.println("Null syntax tree: no root node!");
			return;
		}

		System.out.println(root);

	}

}
