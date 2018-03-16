// Java program to create complete Binary Tree from its Linked List
// representation

// importing necessary classes
import java.util.*;
public class DAGGen {

	ArrayList<BinaryTreeNode> nodes = new ArrayList<BinaryTreeNode>();
	// A binary tree node
	static class BinaryTreeNode {
		String data;
		BinaryTreeNode left, right = null;

		BinaryTreeNode(String data) {
			this.data = data;
			left = right = null;
		}
	}

	class BinaryTree {
		Graph.Node head;
		BinaryTreeNode root;

		// Function to insert a node at the beginning of
		// the Linked List
		void push(String new_data) {
			// allocate node and assign data
			Graph.Node new_node = new Graph.Node(new_data);

			// link the old list off the new node
			new_node.next = head;

			// move the head to point to the new node
			head = new_node;
		}

		// converts a given linked list representing a
		// complete binary tree into the linked
		// representation of binary tree.
		BinaryTreeNode convertList2Binary(BinaryTreeNode node) {
			// queue to store the parent nodes
			Queue<BinaryTreeNode> q = new LinkedList<BinaryTreeNode>();

			// Base Case
			if (head == null) {
				node = null;
				return null;
			}

			// 1.) The first node is always the root node, and
			// add it to the queue
			node = new BinaryTreeNode(head.data);
			q.add(node);

			// advance the pointer to the next node
			head = head.next;

			// until the end of linked list is reached, do the
			// following steps
			while (head != null) {
				// 2.a) take the parent node from the q and
				// remove it from q
				BinaryTreeNode parent = q.peek();
				BinaryTreeNode pp = q.poll();

				// 2.c) take next two nodes from the linked list.
				// We will add them as children of the current
				// parent node in step 2.b. Push them into the
				// queue so that they will be parents to the
				// future nodes
				BinaryTreeNode leftChild = null, rightChild = null;
				leftChild = new BinaryTreeNode(head.data);
				q.add(leftChild);
				head = head.next;
				if (head != null) {
					rightChild = new BinaryTreeNode(head.data);
					q.add(rightChild);
					head = head.next;
				}

				// 2.b) assign the left and right children of
				// parent
				parent.left = leftChild;
				parent.right = rightChild;
			}

			return node;
		}

		// Utility function to traverse the binary tree
		// after conversion
		void inorderTraversal(BinaryTreeNode node) {
			if (node != null) {
				inorderTraversal(node.left);
				System.out.print(node.data + " ");
				nodes.add(node);
				inorderTraversal(node.right);
			}
		}

		// Driver program to test above functions
		public Graph generateDag(int numVertexs) {
			BinaryTree tree = new BinaryTree();
			for (int i = numVertexs; i > 0; i--) {
				tree.push(Integer.toString(i));
			}
			BinaryTreeNode node = tree.convertList2Binary(tree.root);

			System.out.println("Inorder Traversal of the" + " constructed Binary Tree is:");
			tree.inorderTraversal(node);
			String[][] graphin = new String[numVertexs][3];
			for (int i = 0; i < numVertexs; i++) {
				graphin[i][0] = nodes.get(i).data;
			}
			for (int i = 0; i < numVertexs; i++) {
				for (int j = 1; j <= 3; j++) {
					try {
					graphin[i][1] = nodes.get(i).left.data;
					}
					catch(Exception e) {
						graphin[i][1]="";
					}
					try {
					graphin[i][2] = nodes.get(i).right.data;
					}
					catch(Exception e) {
						graphin[i][2]="";
					}

				}
			}
			Graph DagGraph = new Graph(graphin);
			return DagGraph;
		}
	}

}