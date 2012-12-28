import java.util.ArrayList;
import java.util.LinkedList;

public class Bdrzewa {

	public static void main(String[] args) {
		/*Drzewo a = new Drzewo(2);
		a.root.key[0] = 1;
		a.root.key[1] = 2;
		a.root.key[2] = 3;
		for(int i = 4; i < 8; i++)
			a.insert(i);
		a.Displa();*/
		testSplit();
	}
	
	public static void testSplit(){
		Wezel a = new Wezel(2);
		Wezel b = new Wezel(2);
		for(int i = 1; i < 3; i++)
			a.key[i-1] = i;
		a.c[0] = b;
		for(int i = 4; i < 7; i++)
			b.key[i-4] = i;
		System.out.println("*******************");
		a.printKey();
		System.out.println("\nB");
		b.printKey();
		System.out.println("\n*******************");
		Drzewo z = new Drzewo(2);
		z.split(a, 1);
	}
	
}

class Drzewo { 
	
	void Displa(){
		root.printKey();
		for(int i = 0; i < root.c.length; i++)
			if(root.c[i]!=null)
				root.c[i].printKey();
	}
	
	void insert(int k){
		Wezel r = root;
		if(r.n == 2*t -1){
			Wezel s = new Wezel(t);
			root = s;
			s.leaf = false;
			s.n = 0;
			s.setChild(1, r);
			split(s, 1);
			nonfull(s, k);
		}else
			nonfull(r, k);
	}
	
	void nonfull(Wezel x, int k){
		if(x == null){
			x = new Wezel(t,true);
			x.n = 0;
			x.leaf = true;
		}
		int i = x.n;
		if(x.leaf){
			while(i >= 1 && k < x.getKey(i)){
				x.setKey(i+1, x.getKey(i));
				--i;
			}
			x.setKey(i+1, k);
			x.n++;
		} else { 
			while( i >= 1 && k < x.key[i-1])
				i--;
			i+=1;
			if(x.c[i-1]!=null && x.c[i-1].n == 2*t -1){
				split(x, i);
				if(k > x.key[i-1])
					i++;
			}
			nonfull(x.c[i-1], k);
		}
	}
	
	void split(Wezel x, int i){
		Wezel z = new Wezel(t);
		Wezel y = x.getChild(i);
		z.n = t -1;
		for(int j = 1; j <= t -1; j++)
			z.setKey(j, y.getKey(j+t));
		if(!y.leaf){
			for(int j = 1; j <= t; j++)
				z.setChild(j, y.getChild(j+t));
		}
		y.n = t -1;
		for(int j = x.n + 1; j >= i+1; j--)
			x.setChild(j+1, x.getChild(j));
		x.setChild(i+1, z);
		for(int j = x.n; j >= i; j--)
			x.setKey(j+1, x.getKey(j));
		x.setKey(i, y.getKey(t));
		x.n++;
		System.out.println("Z");
		z.printKey();
		System.out.println("\nY");
		y.printKey();
		System.out.println("\nX");
		x.printKey();
	}
	
	Drzewo(int t){
		this.t = t;
		Wezel x = new Wezel(t);
		x.leaf = true;
		x.n = 0;
		root = x;
	}
	
	int t;
	Wezel root;
}



class Wezel { 
	
	void printChild(){
		for(int i = 0; i < c.length; i++)
			if(c[i] != null)
				System.out.print("Jest dziecko nr " + i);
			else 
				System.out.println("NULL "+i);
	}
	
	String getKeyTo_String(){
		String wyjscie = "[ ";
		for(int i = 0; i < mantysa*2 -1;i++)
			wyjscie += key[i] + ",";
		return wyjscie + " ]";
	}
	
	void printKey(){
		for(int i = 0; i < mantysa*2 -1; i++)
			System.out.print(key[i] + " ");
	}
	
	void initChild(int t){
		for(int i = 0; i < c.length; i++)
			c[i] = new Wezel(t,true);
	}
	
	void setChild(int i, Wezel k){
		c[i-1] = k;
	}
	
	void setKey(int i, int k){
		key[i-1] = k;
	}
	
	Wezel getChild(int i){
		return c[i-1];
	}
	
	int getKey(int i){
		return key[i-1];
	}
	Wezel(int m, boolean a){
		this.mantysa = m;
		key = new int[2*m -1];
		
	}
	Wezel(){
		
	}
	Wezel(int mantysa){
		this.mantysa = mantysa;
		key = new int[2*mantysa-1];
		c = new Wezel[2*mantysa];
		for(int i = 0; i < 2*mantysa; i++)
			c[i] = null;
	}
	boolean leaf;
	int mantysa;
	int n = 0; //indeks ostatnio przechowywanego elementu i jednoczesnie rozmiar!
	int key[];
	Wezel c[];
}