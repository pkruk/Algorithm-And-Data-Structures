import java.awt.Container;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class Purpura {

	public static void main(String[] args) throws IOException {
		menu();
	}

	public static void rysuj(PurporoweDrzewo T) {
		List<Node> lista = new LinkedList<>();
		lista = T.list_Tree();
		if (lista == null)
			System.out.println("!!!");
		else {
			JFrame ramka = new SimpleTreeFrame(lista);
			ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ramka.setVisible(true);
		}
	}

	public static void menu() throws IOException {
		System.out.println("Witam w programie!");
		System.out.println("Author: Piotr Kruk");
		System.out
				.println("Podaj sciezke do pliku\nZnak X zostanie przy domyslnej\ny reczne wpisanie");
		String a = in.next();
		if (a.compareTo("y") == 0)
			wczytanie();
		else {
			if (a.compareTo("x") == 0)
				a = "//home//neptyd//ASD//RBT_TESTY//Input.txt";
			plikTxt(a);
		}
	}

	public static void plikTxt(String txt) throws IOException {
		BufferedReader odczyt = null;
		ArrayList<Integer> tmp = new ArrayList<Integer>();
		try {
			odczyt = new BufferedReader(new FileReader(txt));
			String linia = odczyt.readLine();
			linia = odczyt.readLine();
			while (linia != null) {
				for (int i = 0; i < 2; i++)
					if (linia.compareTo("") == 0 || linia.compareTo("\n") == 0)
						linia = odczyt.readLine();
				int a = Integer.parseInt(linia);
				if (a == 0) {
					tmp.add(0);
					obrob(tmp);
					tmp.removeAll(tmp);
				} else
					tmp.add(a);
				linia = odczyt.readLine();
			}
		} catch (FileNotFoundException e) {
			System.err.println("BLAD ODCZYTU NIE MA SCIEZKI");
		} catch (IOException e) {
			System.err.println("BLAD I/O");
		} finally {
			odczyt.close();
		}
	}

	public static void obrob(ArrayList<Integer> tmp) throws IOException {
		int i = tmp.remove(0);
		PurporoweDrzewo a = new PurporoweDrzewo();
		while (!tmp.isEmpty()) {
			if (i == 0)
				return;
			else {
				a.r = "";
				// a.supaInsert(i);
				a.txt_supaInsert(i);
				String tekst = a.r;
				// System.out.println(tekst);
				dopiszDoPliku(a.r);
			}
			i = tmp.remove(0);
		}
	}

	public static String wyjscie = "//home//neptyd//ASD//cos.txt";

	public static void dopiszDoPliku(String txt) throws IOException {
		PrintWriter dopis = null;
		try {
			dopis = new PrintWriter(new FileWriter(wyjscie, true));
			System.out.println("|" + txt);
			dopis.println(txt);
		} finally {
			if (dopis != null)
				dopis.close();
		}
	}

	public static void wczytanie() {
		int i = -1;
		PurporoweDrzewo a = new PurporoweDrzewo();
		while (i != 0) {
			i = in.nextInt();
			if (i == 0)
				continue;
			else {
				a.supaInsert(i);
			}
		}
		System.out.println("Narysowac? Wpisz 3");
		int y = in.nextInt();
		if(y == 3)
			rysuj(a);
	}

	public static Scanner in = new Scanner(System.in);
}

class PurporoweDrzewo {

	public void supaInsert(int i) {
		Node t = search(i);
		if (t == nil) {
			insert(i);
		} else
			Delete(t);
		preorder();
	}

	public String txt_supaInsert(int i) {
		Node t = search(i);
		if (t == nil) {
			insert(i);
		} else
			Delete(t);
		__preorder(root);
		return r;
	}

	String r = "";

	public void __preorder(Node x) {
		if (x == nil)
			return;
		if (x.c == BLACK)
			r = r + "" + x.key + " ";
		else
			r = r + "r" + x.key + " ";
		__preorder(x.left);
		__preorder(x.right);
	}

	public Node search(int k) {
		Node x = root;
		while (x != nil) {
			if (k < x.key)
				x = x.left;
			else if (k == x.key)
				return x;
			else
				x = x.right;
		}
		return x;
	}

	public void remove(int k) {
		Node t = search(k);
		if (t != nil)
			Delete(t);
		else
			return;
	}

	private void Delete(Node z) {
		Node y = z;
		Node x = nil;
		char oryginal_c = y.c;
		if (z.left == nil) {
			x = z.right;
			transplant(z, z.right);
		} else if (z.right == nil) {
			x = z.left;
			transplant(z, z.left);
		} else {
			y = treeminimum(z.right);
			// successor!
			oryginal_c = y.c;
			x = y.right;
			if (y.p == z) {
				x.p = y;
			} else {
				transplant(y, y.right);
				y.right = z.right;
				y.right.p = y;
			}
			transplant(z, y);
			y.left = z.left;
			y.left.p = y;
			y.c = z.c;
		}
		if (oryginal_c == BLACK)
			delete_fixup(x);
	}
	public Scanner in = new Scanner(System.in);
	private void delete_fixup(Node x) {
		while (x != root && x.c == BLACK) {
			if (root.p == null)
				root.p = nil;
			if (x == x.p.left) {
				Node w = x.p.right; // Stryj
				if (w.c == RED) {
					w.c = BLACK;
					x.p.c = RED;
					left_rotate(x.p);
					w = x.p.right;
				}
				if (w.left.c == BLACK && w.right.c == BLACK) {
					w.c = RED;
					x = x.p;
				} else {
					if (w.right.c == BLACK) {
						w.left.c = BLACK;
						w.c = RED;
						right_rotate(w);
						w = x.p.right;
					}
					w.c = x.p.c;
					x.p.c = BLACK;
					w.right.c = BLACK;
					left_rotate(x.p);
					x = root;
				}
			} else {
				// od x.p.right;
				Node w = x.p.left; // Stryj
				if (w.c == RED) {
					w.c = BLACK;
					x.p.c = RED;
					right_rotate(x.p);
					w = x.p.left;
				}
				if (w.left.c == BLACK && w.right.c == BLACK) {
					w.c = RED;
					x = x.p;
				} else {
					if (w.left.c == BLACK) {
						w.right.c = BLACK;
						w.c = RED;
						left_rotate(w);
						w = x.p.left;
					}
					w.c = x.p.c;
					x.p.c = BLACK;
					w.left.c = BLACK;
					right_rotate(x.p);
					x = root;
				}

			}
		}
		x.c = BLACK;
	}

	private Node treeminimum(Node x) {
		
		while (x.left != null && x.left != nil)
			x = x.left;
		return x;
	}

	private void transplant(Node u, Node v) {
		if (u.p == nil)
			root = v;
		else if (u == u.p.left)
			u.p.left = v;
		else
			u.p.right = v;
		v.p = u.p;
	}

	public void insert(int k) {
		Node z = new Node(k);
		z.left = nil;
		z.right = nil;
		z.p = nil;
		insert(new Node(k));
	}

	private void insert(Node z) {
		Node y = nil;
		Node x = root;
		while (x != nil) {
			y = x;
			if (z.key < x.key)
				x = x.left;
			else
				x = x.right;
		}
		z.p = y;
		if (y == nil)
			root = z;
		else if (z.key < y.key)
			y.left = z;
		else
			y.right = z;
		z.left = nil;
		z.right = nil;
		z.c = RED;
		// System.out.println("_____");
		// preorder();
		// System.out.println("_____");
		insert_fixup(z);
	}

	private void insert_fixup(Node z) {
		// System.out.println(z.p.c);
		while (z.p.c == RED) {
			if (z.p == z.p.p.left) {
				Node y = z.p.p.right;
				if (y.c == RED) {
					z.p.c = BLACK;
					y.c = BLACK;
					z.p.p.c = RED;
					z = z.p.p;
				} else {
					if (z == z.p.right) {
						z = z.p;
						left_rotate(z);
					}
					z.p.c = BLACK;
					z.p.p.c = RED;
					// System.out.println("LWWWW");
					// preorder();
					// System.out.println("LWWWW");
					right_rotate(z.p.p);
				}
			} else {
				Node y = z.p.p.left;
				if (y.c == RED) {
					z.p.c = BLACK;
					y.c = BLACK;
					z.p.p.c = RED;
					z = z.p.p;
				} else {
					if (z == z.p.left) {
						z = z.p;
						right_rotate(z);
					}
					z.p.c = BLACK;
					z.p.p.c = RED;
					// System.out.println("WWWW");
					// preorder();
					// System.out.println("WWWW");
					left_rotate(z.p.p);
				}
			}
		}
		root.c = BLACK;
	}

	private void right_rotate(Node x) {
		// Czysta symetria rotateleft!
		Node y = x.left;
		x.left = y.right;
		if (y.right != nil)
			y.right.p = x; // ?
		y.p = x.p;
		if (x.p == nil)
			root = y;
		else if (x == x.p.right)
			x.p.right = y;
		else
			x.p.left = y;
		y.right = x;
		x.p = y;
	}

	private void left_rotate(Node x) {
		Node y = x.right;
		x.right = y.left;
		if (y.left != nil)
			y.left.p = x;
		y.p = x.p;
		if (x.p == nil)
			root = y;
		else if (x == x.p.left)
			x.p.left = y;
		else
			x.p.right = y;
		y.left = x;
		x.p = y;

	}

	public void preorder() {
		_preorder(root);
		System.out.println();
	}

	private void _preorder(Node x) {
		if (x == nil)
			return;
		if (x.c == BLACK)
			System.out.print(x.key + " ");
		else
			System.out.print("r" + x.key + " ");
		_preorder(x.left);
		_preorder(x.right);
	}

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
			for (int i = 0; i < 1; i++) {
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

	PurporoweDrzewo() {
		nil = new Node();
		root = nil;
		root.p = nil;
		root.left = nil;
		root.right = nil;
	}

	public static char BLACK = 'B';
	public static char RED = 'R';
	Node nil;
	Node root;
}

class Node {

	Node(int k) {
		this.key = k;
		c = 'B';
		right = null;
		left = null;
		p = null;
	}

	Node() {
		c = 'B';
		right = left = p = null;
		key = -1;
	}

	public char c;
	public Node right;
	public Node left;
	public Node p;
	public int key;
}

class SimpleTreeFrame extends JFrame {

	public SimpleTreeFrame(List<Node> list) {
		setTitle("DRZEWO AVL");
		Node t = list.remove(0);
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i).key + " ");
		}
		System.out.println();
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(t.key);
		List<DefaultMutableTreeNode> lista_ojcow = new LinkedList<DefaultMutableTreeNode>();
		lista_ojcow.add(root);
		List<Node> list_o = new LinkedList<Node>();
		list_o.add(t);
		while (!lista_ojcow.isEmpty()) {
			DefaultMutableTreeNode x = lista_ojcow.remove(0);
			Node y = list_o.remove(0);
			DefaultMutableTreeNode tmp = null;
			if (y.left.key != -1) {
				tmp = new DefaultMutableTreeNode(y.left.key);
				x.add(tmp);
				lista_ojcow.add(tmp);
				list_o.add(y.left);
			}
			if (y.right.key != -1) {
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

	DefaultMutableTreeNode search(DefaultMutableTreeNode root, Node k) {
		DefaultMutableTreeNode tmp = root;
		while (true) {

			int r = (int) tmp.getUserObject();
			System.out.println("r" + r + tmp.getUserObject());
			if (tmp.isLeaf() && k.key != r)
				return null;
			if (k.key == r)
				return tmp;
			if (k.key < r)
				tmp = tmp.getFirstLeaf();
			if (k.key > r)
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
