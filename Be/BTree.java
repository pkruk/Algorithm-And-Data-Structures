import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import javax.crypto.interfaces.PBEKey;
import javax.management.Query;

public class BTree {
	void testDelete() {
		test_Insert(11);
		levelOrderprint();
		System.out.println("USUWAM 7");
		Delete(7);
		System.out.println("USUWAM 3");
		Delete(3);
		Display();

	}

	void Delete(int key) {
		Pair szukany = search(key);
		if (szukany == null) {
			System.err.print("Nie ma tego klucza w Drzewie " + key);
			return;
		} else {
			_Delete(root, key);
		}
	}

	/******
	 * PAIR ZWRACA INDEX ! I WEZEL
	 * 
	 * *******************/

	boolean _Delete(Node x, int key) {
		Node tmp = x;
		int flaga = 3;
		int index_x = -1;
		if (IN(x, key)) {
			index_x = getIndex(x, key);
		}
		if (IN(x, key)) {
			if (tmp.leaf) {
				tmp.key.remove(index_x);
				tmp.n--;
				return true;
			} else if (tmp.leaf == false) {
				Node y = x.c.get(index_x); // y bedzie synem poprzedzajacym k w
											// x
				Node z = x.c.get(index_x + 1); // a co jestli bierzemy
												// ostatniego?
				if (y.key.size() == t) {
					// funkcja znajdujaca poprzednik key' klucza k
					/* Debug */
					Pair max_wY = searchMax(y);
					wyswietl("Pierwszy Przypadek\n Wyszukuje k'", flaga);
					int k_prim = max_wY.szukany.key.get(max_wY.index);
					wyswietl("Usuwam rekurencje k_prim", flaga);
					_Delete(max_wY.szukany, k_prim);
					x.key.remove(index_x);
					x.key.add(k_prim);
					wyswietl("Wstawiam do ", flaga);
					sortujKlucze(x.key);
				} else if (y.key.size() < t) {
					if (z.key.size() >= t) {
						Pair min_wz = searchMin(z);
						wyswietl("Drugi Przypadek\n Wyszukuje k'", flaga);
						int k_prim = min_wz.szukany.key.get(min_wz.index);
						wyswietl("Usuwam rekurencje k_prim", flaga);
						_Delete(z, k_prim);
						x.key.remove(index_x);
						x.key.add(k_prim);
						wyswietl("Wstawiam do ", flaga);
						sortujKlucze(x.key);
					}
				} else if (y.key.size() == t - 1 && z.key.size() == t - 1) {
					y.key.add(key);
					while (!z.key.isEmpty()) {
						y.key.add(z.key.remove(0));
						y.n++;
						z.n--;
					}
					while (!z.c.isEmpty()) {
						y.c.add(z.c.remove(0));
					}
					/*
					 * przenies wszystko z "Z" do "Y" Nie sortuje listy bo Z
					 * jest po y!! czyli zachowana zostanie kolejnosc
					 */
					wyswietl("Zawartosc Z -> Y", flaga);
					x.c.remove(index_x + 1); // zwolnij pamiec dla z!
					wyswietl("Zwolniona pamiec dla Z", flaga);
					_Delete(y, key);
				}
			}
		}/* Koniec ifa IN(x, key) */
		{
			wyswietl("Przypadek Jesli Klucz nie wystepuje w wewnetrznym wezle",
					flaga);
			Pair a = wyznacz(x, key);
			int index_xci = a.index;
			Node xci = a.szukany;
			//wyznaczony korzen x.ci w ktorym musi sie znajdowac k jesli jest w drzewie!
			Node lBro = null;
			Node rBro = null;
			if(index_xci - 1 >= 0 )
				lBro = x.c.get(index_xci-1);
			if(index_xci + 1 < x.c.size())
				rBro = x.c.get(index_xci+1);
			if(xci.key.size() == t-1){
				wyswietl("Przypadek 3a",1);
				//Przypadek 3a @@@
				if(lBro != null && lBro.key.size() == t){
					wyswietl("Lewy brat",flaga);
					xci.key.add(x.key.remove(index_xci-1));
					x.key.add(lBro.key.get(lBro.key.size()-1));
					Collections.sort(x.key);
					if(!xci.leaf){
						//Przesun z brata
						List <Node> nowa = new LinkedList<Node>();
						nowa.add(lBro.c.remove(lBro.c.size()-1));
						for(int i = 0; i < xci.c.size(); i = 0)
							nowa.add(xci.c.remove(i));
						xci.c = nowa;
					}
					return _Delete(xci, key);
				}else if(rBro != null && rBro.key.size() == t){
					wyswietl("Prawy brat", flaga);
					xci.key.add(x.key.remove(index_xci));
					x.key.add(rBro.key.get(0));
					Collections.sort(x.key);
					if(!xci.leaf){
						xci.c.add(rBro.c.remove(0));
					}
					return _Delete(xci, key);
					// @@@@@@@@@@ 3A
				}else if(lBro != null && rBro != null && lBro.key.size() == t -1 && rBro.key.size() == t -1){
					wyswietl("3b",1);
					//&&&&&&& 3 B
					//2 PRZYPADKI TRZEBA ROZPATRZYĆ!
				}
				
				return _Delete(xci, key);
			}
		}
		return false;
	}
	
	Pair wyznacz(Node x, int k) {
		int flaga = 1;
		wyswietl("wyznaczam korzen x.ci ", flaga);
		int i = 0;
		while (i < x.key.size() && k > x.key.get(i))
			i++;
		return new Pair(i, x.c.get(i));

	}

	private Pair _search(Node x, int key) {
		int i = 0;
		while (i < x.key.size() && key > x.key.get(i)) {
			i++;
		}
		if (i < x.key.size() && key == x.key.get(i)) {
			Pair a = new Pair(i, x);
			// Zwracam wezel z tym kluczem i indeks tego klucza w Nodzie
			return a;
		}
		if (x.leaf)
			return null;
		else {
			return _search(x.c.get(i), key);
		}
	}

	void wyswietl(String a, int flaga) {
		if (flaga == 1)
			System.out.println(a);
		else if (flaga == 2)
			System.err.println(a);
		else if (flaga == 3) {
			System.out.println(a + "\n Display drzewa \n");
			levelOrderprint();
		}
	}

	void sortujKlucze(ArrayList<Integer> tmp) {
		Collections.sort(tmp);
	}

	Pair searchMin(Node x) {
		Node tmp = x;
		while (tmp != null && !tmp.leaf)
			tmp = tmp.c.get(0);
		return new Pair(0, tmp);
	}

	Pair searchMax(Node x) {
		Node tmp = x;
		while (tmp != null && !tmp.leaf)
			tmp = tmp.c.get(tmp.c.size() - 1);
		return new Pair(tmp.key.size() - 1, tmp);
	}

	Node findParent(Node x) {
		if (root != x)
			return _findParent(root, x);
		else {
			System.err.println("ROOT NIE MA OJCA!");
			return null;
		}
	}

	int getIndex(Node x, int k) {
		for (int i = 0; i < x.key.size(); i++)
			if (x.key.get(i) == k)
				return i;
		return -1;
	}

	void levelOrderprint() {
		Queue<Node> kolejka = new LinkedList<Node>();
		kolejka.add(root);
		int ilDz = 1; // ilosc dzieci danym poziomie
		String s = "                 ";
		int j = root.c.size();
		while (!kolejka.isEmpty()) {
			int k = 0;
			for (int i = 0; i < ilDz; i++) {
				Node tmp = kolejka.poll();
				for (int t = 0; t < tmp.c.size(); t++)
					kolejka.add(tmp.c.get((t)));
				System.out.print(s + tmp.getKeys());
				if (s.length() > 2)
					s = s.substring(0, s.length() - 2);
			}
			System.out.println();
			ilDz = kolejka.size();
		}
	}

	Node getChild(Node x, int k) {
		for (int i = 0; i < x.c.size(); i++)
			if (IN(x.c.get(i), k))
				return x.c.get(i);
		return null;
	}

	boolean IN(Node x, int k) {// sprawdza czy w danym wezle jest ten klucz
		for (int i = 0; i < x.key.size(); i++)
			if (x.key.get(i) == k)
				return true;
		return false;
	}

	void test_search() {
		test_Insert(20);
		for (int i = 1; i < 22; i++) {
			Pair tmp = search(i);
			if (tmp != null && tmp.szukany != null)
				System.out.println(tmp.szukany.key.get(tmp.index));
		}
	}

	Pair search(int key) {
		return _search(root, key);
	}

	private Node _findParent(Node x, Node szukany) {
		// zakladamy ze nie szukamy dla pustego wezla rodzica
		int key = szukany.key.get(0);
		int i = 0;
		while (i < x.key.size() && key > x.key.get(i))
			i++;
		for (int j = 0; j < x.c.size(); j++)
			if (IN(x.c.get(j), key))
				return x;
		if (x.leaf) {
			System.err.println("Nie ma rodzica dla tego dziecka "
					+ szukany.key.get(0));
			return null;
		} else {
			return _findParent(x.c.get(i), szukany);
		}
	}

	void testParent() {
		test_Insert(10);
		Node a = search(5).szukany;
		Node f = findParent(a);
		if (f != null) {
			for (int i = 0; i < f.key.size(); i++)
				System.out.println(f.key.get(i));
			Node w = getChild(f, 5);
			System.out.println(w.getKeys());
		} else {
			System.err.println("Jest rootem!");
		}

	}

	void test_Insert(int u) {
		int ilosc = u;
		ArrayList<Integer> list = new ArrayList<Integer>(ilosc);
		for (int i = 1; i <= ilosc; i++)
			list.add(i);
		Random r = new Random();
		while (list.size() > 0) {
			int index = r.nextInt(list.size());
			insert(list.remove(index));
		}
		System.out.println("***********************");
		System.out.println("************* INSERT **********");
		Display();
		levelOrderprint();
	}

	public void Display() {
		_Display(root);
		System.out.println();
	}

	private void _Display(Node x) {
		for (int i = 0; i < x.key.size(); i++) {
			if (i < x.c.size())
				_Display(x.c.get(i));
			System.out.print(x.key.get(i) + ",");
		}
		if (x.c.size() != 0)
			_Display(x.c.get(x.c.size() - 1));
	}

	private void nonfull(Node x, int k) {
		int i = x.key.size() - 1;
		if (x.leaf == true) {
			// System.out.println("LOL?"+k);
			x.key.add(k);
			x.n++;
			i = x.key.size() - 1;
			while (i > 0 && x.key.get(i) < x.key.get(i - 1)) {
				Collections.swap(x.key, i, i - 1);
				i--;
				// System.out.println("M"+x.leaf + k);
			}
		} else {
			while (i >= 0 && k < x.key.get(i))
				i--;
			i++;
			// WYznaczone drzew rekurencyjne!
			/********* TEST ***/
			if (x.c.get(i).n == 2 * t - 1) {
				split(x, i);
				if (k > x.key.get(i))
					i++;
			}
			nonfull(x.c.get(i), k);
		}
	}

	void insert(int k) {
		Node r = root;
		if (r.n == 2 * t - 1) {
			Node s = new Node();
			s.leaf = false;
			s.n = 0; // not sure!
			s.c.add(r);
			root = s;
			split(s, 0);
			/*
			 * System.out.println("\nPo splicie w insert root");
			 * root.printKeys(); System.out.println("\n**********\n");
			 * System.out.println(root.leaf + " "+k);
			 */
			nonfull(root, k);
		} else
			nonfull(r, k);
	}

	void test_split() {
		Node x = new Node();
		x.n = 0;
		x.leaf = true;
		x.key.add(1);
		x.key.add(20);
		Node y = new Node();
		y.leaf = false;
		y.n = 0;
		for (int i = 4; i < 11; i++, y.n++)
			y.key.add(i);
		x.c.add(y);
		for (int i = 0; i < this.t * 2; i++) {
			y.c.add(new Node());
			y.c.get(i).key.add(-1);
			y.c.get(i).n++;
		}
		/*
		 * Node tmp = new Node(); tmp.key.add(100); tmp.n++; Node tyr = new
		 * Node(); tyr.key.add(200); tyr.n++; x.c.add(tmp); x.c.add(tyr);
		 */
		x.printKeys();
		y.printKeys();
		split(x, 0);
		for (int i = 0; i < y.key.size(); i++)
			System.out.println(y.key.get(i));
		System.out.println("\nSAM X \n");
		for (int i = 0; i < x.key.size(); i++)
			System.out.print(x.key.get(i) + " ");
		System.out.println();
		x.printKeys();
		System.out.println("\nWSZYSTKIE DZIECI X\n");
		for (int i = 0; i < x.c.size(); i++)
			if (x.c.get(i) != null)
				x.c.get(i).printKeys();
		System.out
				.println("**************\n Tu sprawdzam czy y ma dobra ilosc dzieci!");
		for (int i = 0; i < y.c.size(); i++)
			if (y.c.get(i) != null)
				y.c.get(i).printKeys();
	}

	private void split(Node x, int i) {
		Node z = new Node();
		Node y = x.c.get(i);
		int wyciete = y.key.get(t - 1);
		z.leaf = y.leaf;
		z.n = t - 1;
		for (int j = 0; j < t - 1; j++)
			z.key.add(y.key.get(j + t));
		while (y.key.size() > t - 1)
			y.key.remove(y.key.size() - 1);
		if (!y.leaf) {
			for (int j = 0; j < t; j++)
				z.c.add(y.c.get(j + t));
			while (y.c.size() > t)
				y.c.remove(y.c.size() - 1);
		}
		y.n = t - 1;
		// WSZYSTKO U GÓRY BEZBŁEDNIE!!!!!
		x.c.add(z);
		int g = x.c.size() - 1;
		while (g - 1 > i) {
			Collections.swap(x.c, g - 1, g);
			--g;
		}
		/*
		 * System.out.println("\n--------------"); for(int k = 0; k <
		 * x.c.size(); k++){ if(x.c.get(k)!=null) x.c.get(k).printKeys(); }
		 * System.out.println("\n________________");
		 */
		x.key.add(wyciete);
		x.n++;
		int p = x.key.size() - 1;
		while (p > i) {
			Collections.swap(x.key, p, p - 1);
			--p;
		}
		x.n = x.key.size();
	}

	public BTree(int t) {
		this.t = t;
		Node x = new Node();
		x.leaf = true;
		x.n = 0;
		root = x;
	}

	public BTree() {
		t = 2;
		Node x = new Node();
		x.leaf = true;
		x.n = 0;
		root = x;
	}

	// n dziala tak samo jak lenght KURWA MAC!

	int t;
	Node root;
}
