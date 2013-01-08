import java.util.*;
import java.io.*;


public class Graf{

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_RED = "\u001B[31m";

	public void printSasiedzi(){
		System.out.println(ANSI_RED);
		for(int i = 0; i < V.size(); i++){
			System.out.print("V " + (i+1) + " :");
			for(int j = 0; j < V.get(i).sasiedzi.size(); j++)
				System.out.print((V.get(i).sasiedzi.get(j).index +1)+ " -- ");
			System.out.println();
		}
		System.out.println(ANSI_RESET);
	}	
	public String toString(){
		String a = "";
		for(int i = 0; i < len; i++)
			a += V.get(i).print() + "\n";
		return a;
	}
	public void dodajSasiadow(int index, int tab[]){
		for(int i = 0; i < tab.length; i++)
			V.get(index).sasiedzi.addLast(V.get(tab[i]-1));
	}
	public Node get(int index){
		return V.get(index);
	}
	
	public Node remove(int index){
		len--;
		return V.remove(index);
	}

	public void add(int i){
		V.add(new Node(i));
		len = V.size();
	}

	public Graf(){
		len = 0;
		time = 0;	
	}

	public int len;
	public static int time;
	public ArrayList <Node> V = new ArrayList<Node>(); // lista wierzcholkow	
}
