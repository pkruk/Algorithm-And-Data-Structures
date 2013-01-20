import java.util.*;


public class Graf{

	public int  getSasiad(int skad, int ktory){
		if (skad > sasiedzi.size()){
			System.out.println(ANSI_RED +"Spoza zakresu tablicy sasiadow" + ANSI_RESET);
			return -1;
		}
		if (ktory > sasiedzi.get(skad).size()){
			System.out.println(ANSI_RED + "Spoza zakresu listy " + skad  + ANSI_RESET);
			return -1;
		}
		return sasiedzi.get(skad).get(ktory) -1;
	}

	public void printSasiedzi(){
		System.out.println(ANSI_BLUE);
		for (int i = 0; i < len; i++){
			System.out.println(i + " = " + sasiedzi.get(i).toString());
		}
		System.out.println(ANSI_RESET);
	}

	public String VtoString(){
		return V.toString();
	}


	public void add(int i){
		V.add(new Wezel(i));
		sasiedzi.add(new ArrayList<Integer>());
		len++;
	}

	public Graf(){
		time = 0;
		len = 0;
	}

	public int time;
	public static int len;	
	public ArrayList <Wezel> V = new ArrayList <Wezel> ();
	public ArrayList<ArrayList<Integer>> sasiedzi = new ArrayList<ArrayList<Integer>>();
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_RED = "\u001B[31m";

}