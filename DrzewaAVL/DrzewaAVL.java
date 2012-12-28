import java.awt.Container;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.io.PrintWriter;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

public class DrzewaAVL {

	public static void main(String[] args) throws IOException {

		menu();
	}

	public static void rysuj(AVL T) {
		List<Node> lista = new LinkedList<>();
		lista = T.list_Tree();
		if(lista == null)
			System.out.println("!!!");
		else{
		JFrame ramka = new SimpleTreeFrame(lista);
		ramka.setSize(400, 500);
		ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ramka.setVisible(true);
		}
	}

	public static Scanner in = new Scanner(System.in);

	public static boolean menu() throws IOException {
		System.out
				.println("Witam w Programie\n@author: P.Kruk\ni -insert\nd - delete\np - prezentacja\n"
						+ "t - plik tekstowy");
		AVL tree = new AVL();
		while (true) {
			String c = in.next();
			if (c.compareTo("q") == 0)
				return true;
			else if (c.compareTo("p") == 0) {
				System.out
						.println("1) Preorder\n2) Postorder\n3)Prezentacja Graficzna\n6 Inorder"
								+ "\n11 Prezentacja w Swingu\n-1 Koniec Prezentacji\n");
				int a = in.nextInt();
				while (a != -1) {
					if (a == 1)
						tree.preOrder();
					else if (a == 2)
						tree.postOrder();
					else if (a == 3)
						tree.levelOrder();
					else if (a == 6)
						tree.inOrder();
					else if (a == 7)
						sekcja();
					else if (a == 11)
						rysuj(tree);
					a = in.nextInt();
				}
			} else if (c.compareTo("i") == 0) {
				tree.insert(in.nextInt());
			} else if (c.compareTo("d") == 0) {
				tree.remove(in.nextInt());
			} else if (c.compareTo("t") == 0)
				plik();
            else if (c.compareTo("x") == 0)
                return true;
		}
	}

	public static void plik() throws IOException {
		AVL tree = new AVL();
        String nazwa = "//home//neptyd//ASD//AVL_TESTY//input1.txt";
        String nazwa1 = "//home//neptyd//ASD//AVL_TESTY//moje.txt";

        Scanner wejscie = null;
        try {
            wejscie = new Scanner(new BufferedReader(new FileReader(nazwa)));
            int ilosc = wejscie.nextInt();
            while (wejscie.hasNext()){
                if(wejscie.hasNextInt()){
                    int x = wejscie.nextInt();
                    if ( x != 0 ){
                        tree.insert(x);
                    } else if ( x == 0 ){
                        tree.RemoveAll(); 
                    }
                    tree.txtpreOrder();
                    dopiszDoPliku(tree.wyjscieP);
                    tree.wyjscieP = "";
                }
            }
        } finally {
            if(wejscie != null)
                wejscie.close();
        }
    }
    public static void dopiszDoPliku(String txt) throws IOException {
        PrintWriter dopis = null;
        String nazwa1 = "//home//neptyd//ASD//AVL_TESTY//moje.txt";
        try {
                        dopis = new PrintWriter(new FileWriter(nazwa1,true));
                        dopis.println(txt);
        } finally {
            if (dopis != null)
                dopis.close();
        }
    }
	public static void sekcja() {
		AVL t = new AVL();
		String q = "";
		while (true) {
			q = in.next();
			if (q.compareTo("q") == 0)
				return;
			if (q.compareTo("p") == 0) {
				t.postOrder();
				t.preOrder();
				t.levelOrder();
			} else {
				int c = Integer.parseInt(q);
				if (t.search(c) == false)
					t.insert(c);
				else
					t.remove(c);
			}
		}
	}
}

class AVL {
	public void levelOrder() {
		System.out.println();
		Queue<Node> kolejka = new LinkedList<Node>();
		kolejka.add(root);
		int ilDz = 1; // ilosc dzieci danym poziomie
		String s = "         ";
		while (!kolejka.isEmpty()) {
			for (int i = 0; i < ilDz; i++) {
				Node tmp = kolejka.poll();
				if (tmp.left != null)
					kolejka.add(tmp.left);
				if (tmp.right != null)
					kolejka.add(tmp.right);
				System.out.print(s + tmp.key);
				if (s.length() > 2)
					s = s.substring(0, s.length() - 1);
				/*
				 * if(tmp.key == 9){ System.err.println("Bezpiecznik!" +
				 * tmp.parent.key); }
				 */
			}
			System.out.println();
			ilDz = kolejka.size();
		}
	}

	public List<Node> list_Tree() {
		List<Node> lista = new LinkedList<Node>();
		Queue<Node> kolejka = new LinkedList<Node>();
		kolejka.add(root);
		int ilDz = 1;
		while (!kolejka.isEmpty()) {
			for (int i = 0; i < ilDz; i++) {
				Node tmp = kolejka.poll();
				if (tmp.left != null)
					kolejka.add(tmp.left);
				if (tmp.right != null)
					kolejka.add(tmp.right);
				lista.add(tmp);
			}
		}
		return lista;

	}

	public String wyjscieP = "";

	public void txtpreOrder() {
		_txtpreOrder(root);

	}

	public void RemoveAll() {
		while (root != null)
			remove(min(root).key);
		root = null;
	}

	private void _txtpreOrder(Node n) {
		if (n == null)
			return;
		wyjscieP += n.key + " ";
		_txtpreOrder(n.left);
		_txtpreOrder(n.right);
	}

	public void preOrder() {
		_preOrder(root);
		System.out.println();
	}

	private void _preOrder(Node t) {
		if (t == null)
			return;
		System.out.print(t.key + " ");
		_preOrder(t.left);
		_preOrder(t.right);
	}

	public void postOrder() {
		_postOrder(root);
		System.out.println();
	}

	public void inOrder() {
		_inOrder(root);
		System.out.println();
	}

	private void _inOrder(Node t) {
		if (t == null)
			return;
		_inOrder(t.left);
		System.out.print(t.key + " ");
		_inOrder(t.right);
	}

	private void _postOrder(Node t) {
		if (t == null)
			return;
		_postOrder(t.left);
		_postOrder(t.right);
		System.out.print(t.key + " ");
	}

	public void printTree() {
		System.out.print(root.key + " ");
		print(root);
	}

	private void print(Node x) {
		Node cur = root;
		Node pre = root;
		Stack<Node> s = new Stack<Node>();
		if (root != null)
			s.push(root);
		System.out.println("sysout" + s.isEmpty());
		while (!s.isEmpty()) {
			cur = s.peek();
			if (cur == pre || cur == pre.left || cur == pre.right) {// we are
																	// traversing
																	// down the
																	// tree
				if (cur.left != null) {
					s.push(cur.left);
				} else if (cur.right != null) {
					s.push(cur.right);
				}
				if (cur.left == null && cur.right == null) {
					s.pop().print();
					// System.out.println(s.pop().key);
				}
			} else if (pre == cur.left) {// we are traversing up the tree from
											// the left
				if (cur.right != null) {
					s.push(cur.right);
				} else if (cur.right == null) {
					s.pop().print();
					// System.out.println(s.pop().key);
				}
			} else if (pre == cur.right) {// we are traversing up the tree from
											// the right
				System.out.println("----");
				s.pop().print();
				// System.out.println(s.pop().key);
			}
			pre = cur;
		}
	}

	public void remove(int k) {
		Node t = __search(this.root, k);
		if (t != null)
			tree_delete(t);
		else {
			System.out.println("nie wystepuje w drzewie" + search(k) + "|" + k);
		}
	}

	private void tree_delete(Node z) {
		if (z.left == null)
			translant(z, z.right);
		else if (z.right == null)
			translant(z, z.left);
		else {
			Node y = min(z.right);
			if (y.parent != z) {
				translant(y, y.right);
				y.right = z.right;
				y.right.parent = y;
			}
			translant(z, y);
			y.left = z.left;
			y.left.parent = y;
			przywrocPorzadek(y);
		}
	}

	private void translant(Node u, Node v) {
		if (u.parent == null)
			root = v;
		else if (u == u.parent.left)
			u.parent.left = v;
		else
			u.parent.right = v;
		if (v != null) {
			v.parent = u.parent;
		}
	}

	public void insert(int k) {
		if (!search(k))
			_insert(root, new Node(k));
        else 
            remove(k);
	}

	public boolean search(int k) {
		return _search(root, k);
	}

	private boolean _insert(Node p, Node q) {
		if (p == null) {
			this.root = q;
		} else if (p != null) {
			if (q.key < p.key) {
				if (p.left == null) {
					p.left = q;
					q.parent = p;
					przywrocPorzadek(p);
				} else {
					_insert(p.left, q);
				}

			} else if (q.key > p.key) {
				if (p.right == null) {
					p.right = q;
					q.parent = p;
					przywrocPorzadek(p);
				} else {
					_insert(p.right, q);
				}
			} else {

			}
		}
		return false;
	}

	private Node rotateRightLeft(Node u) {
		u.right = rotateRight(u.right);
		return rotateLeft(u);
	}

	private Node rotateRight(Node x) {
		// Czysta symetria rotateleft!
		Node tmp = x.left;
		tmp.parent = x.parent;
		x.left = tmp.right;
		if (x.left != null) {
			x.left.parent = x;
		}
		tmp.right = x;
		x.parent = tmp;
		if (tmp.parent != null) {
			if (tmp.parent.right == x) {
				tmp.parent.right = tmp;
			} else if (tmp.parent.left == x) {
				tmp.parent.left = tmp;
			}
		}
		x.wsp = h(x.right) - h(x.left);
		tmp.wsp = h(tmp.right) - h(tmp.left);
		return tmp;
	}

	/*
	 * D B B E => A D A C C E
	 */
	/*
	 * Bierzemy wezel przed lisciem, jego prawe dziecko czyli
	 * successora(nastepnika) dziadek staje sie jego ojcem! zachowujemy,
	 * przesuwamy wszystko w gore,az do roota, na roocie obnizamy w prawo i na
	 * lewo dajemy Noda,ktorego wzielismy czyli succesora !
	 */
	private Node rotateLeft(Node x) {
		Node tmp = x.right;
		tmp.parent = x.parent;
		x.right = tmp.left;
		if (x.right != null)
			x.right.parent = x;
		tmp.left = x;
		x.parent = tmp;
		if (tmp.parent != null) {
			if (tmp.parent.right == x)
				tmp.parent.right = tmp;
			else if (tmp.parent.left == x)
				tmp.parent.left = tmp;
		}
		x.wsp = h(x.right) - h(x.left);
		tmp.wsp = h(x.right) - h(x.left);
		return tmp;
	}

	private Node rotateLeftRight(Node u) {
		u.left = rotateLeft(u.left);
		return rotateRight(u);
	}

	private boolean _search(Node p, int k) {
		if (p == null)
			return false;
		if (p.key > k)
			_search(p.left, k);
		else if (p.key < k)
			_search(p.right, k);
		else if (p.key == k)
			return true;
		return false;
	}

	private Node __search(Node p, int k) {
		if (p == null || p.key == k)
			return p;
		if (k < p.key)
			return __search(p.left, k);
		else
			return __search(p.right, k);

	}

	private void przywrocPorzadek(Node x) {

		wspRoznica(x);
		int balance = x.wsp;
		if (balance == -2) {
			// gdy prawe jest nizej od prawego
			// levelOrder();
			if (h(x.left.left) >= h(x.left.right)) {
				x = rotateRight(x);
			} else {
				x = rotateLeftRight(x);
			}
		} else if (balance == 2) {
			// gdy lewe poddrzewko!
			// levelOrder();
			if (h(x.right.right) >= h(x.right.left)) {
				x = rotateLeft(x);
			} else {
				x = rotateRightLeft(x);
			}
		}

		// we did not reach the root yet
		if (x.parent != null) {
			przywrocPorzadek(x.parent);
		} else {
			this.root = x;
		}
	}

	private void wspRoznica(Node x) {
		x.wsp = h(x.right) - h(x.left);
	}

	public Node min(Node x) {
		while (x.left != null)
			x = x.left;
		return x;
	}

	private int h(Node a) {
		if (a == null) {
			return -1; // nie ma go!
		}
		if (a.left == null && a.right == null) {
			return 0; // lisc!
		} else if (a.left == null) {
			return 1 + h(a.right);// Wysokosc prawego
		} else if (a.right == null) {
			return 1 + h(a.left); // wysokosc lewego
		} else {
			int t = h(a.left);
			int p = h(a.right);
			if (t >= p)
				return 1 + t;
			else
				return 1 + p;
		}
	}

	private Node successor(Node x) { // znalezienie nastepcy
		if (x.right != null) {
			Node r = x.right;
			while (r.left != null) {
				r = r.left;
			}
			return r;
		} else {
			Node p = x.parent;
			while (p != null && x == p.right) {
				x = p;
				p = x.parent;
			}
			return p;
		}
	}

	public void preorder(Node node) {
		if (node != null) {
			System.out.print(node.key);
			preorder(node.left);
			preorder(node.right);
		}
	}

	public AVL() {
		root = null;
	}

	public AVL(int k) {
		root = new Node(k);
	}

	public Node root;
}

class Node {
	public int key = 0;
	public Node left = null;
	public Node right;
	public int wsp = 0;
	public Node parent;

	List<Node> children = null;

	public void print() {
		children = new LinkedList<Node>();
		if (left != null)
			children.add(left);
		if (right != null)
			children.add(right);
		print("", true);
	}

	String y = "";

	private void print(String prefix, boolean isTail) {
		y = prefix + (isTail ? "└── " : "├── ") + key;
		System.out.println(y);
		if (children != null) {
			for (int i = 0; i < children.size() - 1; i++) {
				children.get(i).print(prefix + (isTail ? "    " : "│   "),
						false);
			}
			if (children.size() >= 1) {
				children.get(children.size() - 1).print(
						prefix + (isTail ? "    " : "│   "), true);
			}
		}
	}

	Node(int key, Node parent) {
		this.key = key;
		left = right = null;
		this.parent = parent;
		wsp = 0;
	}

	Node(int key) {
		this.key = key;
		left = right = null;
		parent = null;
		wsp = 0;
	}

	Node() {
		left = right = null;
		key = wsp = 0;
	}

}

class SimpleTreeFrame extends JFrame {

	public SimpleTreeFrame(List<Node> list) {
		setTitle("DRZEWO AVL");
		Node t = list.remove(0);
		for(int i = 0; i < list.size(); i++){
			System.out.print(list.get(i).key + " ");
		}
		System.out.println();
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(
				t.key);
		List<DefaultMutableTreeNode> lista_ojcow = new LinkedList<DefaultMutableTreeNode>();
		lista_ojcow.add(root);
		List<Node> list_o = new LinkedList<Node>();
		list_o.add(t);
		while(!lista_ojcow.isEmpty()){
			DefaultMutableTreeNode x = lista_ojcow.remove(0);
			Node y = list_o.remove(0);
			DefaultMutableTreeNode tmp = null;
			if(y.left != null){
				tmp = new DefaultMutableTreeNode(y.left.key);
				x.add(tmp);
				lista_ojcow.add(tmp);
				list_o.add(y.left);
			}
			if(y.right != null){
				tmp = new DefaultMutableTreeNode(y.right.key);
				x.add(tmp);
				lista_ojcow.add(tmp);
				list_o.add(y.right);
			}
		}
		
		JTree tree = new JTree(root);
		Container contentPane = getContentPane();
		contentPane.add(new JScrollPane(tree));
	}
	DefaultMutableTreeNode search(DefaultMutableTreeNode root,Node k){
		DefaultMutableTreeNode tmp = root;
		while(true){
			
			int r = (int) tmp.getUserObject();
			System.out.println("r" + r + tmp.getUserObject());
			if(tmp.isLeaf() && k.key != r)
				return null;
			if(k.key == r)
				return tmp;
			if( k.key < r)
				tmp = tmp.getFirstLeaf();
			if(k.key > r)
				tmp = tmp.getLastLeaf();
			
		}
	}
	public SimpleTreeFrame() {
		setTitle("Drzewko");
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("ROOT");
		DefaultMutableTreeNode lewy = new DefaultMutableTreeNode("LEWY");
		root.add(lewy);
		DefaultMutableTreeNode right = new DefaultMutableTreeNode("PRAWY");
		root.add(right);
		DefaultMutableTreeNode srodek = new DefaultMutableTreeNode("SRODEK");
		root.add(srodek);
		DefaultMutableTreeNode c = new DefaultMutableTreeNode("HAH!!!");
		srodek.add(c);
		// //////////////////
		JTree tree = new JTree(root);
		Container contentPane = getContentPane();
		contentPane.add(new JScrollPane(tree));
	}

	private static final int DEAFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
}
