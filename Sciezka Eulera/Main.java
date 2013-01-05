/**
 * @author Piotr Kruk 
 * @version 2012/12/15
 */
import java.util.*;
import java.io.*;
public class Main{
	public static int zliczuj = 0;
    public static void main (String [] args)throws IOException{
        System.out.println("Hello");
		Scanner in = new Scanner(System.in);
		System.out.println("1) Example\n2) wprowadzaj dane recznie\n3) Pliki");
		int a = in.nextInt();
		if(a == 1){
			example();
		} else if (a == 3){
			 txt("./testy/input0.txt");
			 txt("./testy/input1.txt");
			 txt("./testy/input2.txt"); 
			 txt("./testy/input3.txt");
			 txt("./testy/input4.txt");
			 txt("./testy/input5.txt");
			txt("./testy/input6.txt");
			txt("./testy/input7.txt"); 
			txt("./testy/input8.txt");
			 txt("./testy/input9.txt");
			 txt("./testy/input10.txt");
			 txt("./testy/input11.txt");
			 txt("./testy/input12.txt"); 
		} else {
			int len = in.nextInt();
			int tab[][] = new int[len][len];
			for(int i = 0; i < len; i++)
				for(int j = 0; j < len; j++)
					tab[i][j] = in.nextInt();
			Graf g = new Graf(tab);
			g.DisplayAdjMat();
			g.Display();
			g.euler(0);
			String q = g.wyswietl();
		}
    }
	public static void txt(String a)throws IOException{
		BufferedReader wejscie = null;
		int tab[][];
		try {
			wejscie = new BufferedReader(new FileReader(a));
			String linia = wejscie.readLine();

			int rozmiar = Integer.parseInt(linia);
			tab = new int[rozmiar][rozmiar];

			linia = wejscie.readLine();
			int j = 0;
			while(linia != null){
				StringTokenizer tmp = new StringTokenizer(linia," ");
				int i = 0;
				while(tmp.hasMoreTokens()){
					int w = Integer.parseInt(tmp.nextToken());
					tab[j][i++] = w;
				}
				j++;
				linia = wejscie.readLine();
			}
		} finally {
			if (wejscie != null)
				wejscie.close();
		}		
		Graf g = new Graf(tab);
		/*for(int i = 0; i < tab.length; i++){
			for(int j = 0; j < tab[i].length; j++)
				System.out.print(tab[i][j] + " ");
			System.out.println();
		}*/
		g.DisplayAdjMat();
		g.Display();
		g.euler(0);
		
		String wyjscie = g.wyswietl();
		String traktor = "./testy/Moje/output"+zliczuj +".txt";
		++zliczuj;
		FileWriter out = null;
		try{
			out = new FileWriter(traktor);
			out.write(wyjscie);
		}finally {
			if (out != null)
				out.close();
		}
		System.out.println(wyjscie);

	}
	public static void example(){
		 int tab[][] = {
			{0,1,0,0,0,0},
			{0,0,1,1,0,0},
			{0,0,0,1,0,0},
			{0,0,0,0,1,1},
			{0,0,0,0,0,1},
			{1,1,0,0,0,0}
		};
        Graf E = new Graf(tab);
        E.DisplayAdjMat();
		E.Display();
        System.out.println();
		E.euler(0);
		E.wyswietl();
		E.displayWierzcholki();
	
	}

}




