import java.util.*;
import java.io.*;
public class Main {

	public static void main(String[] args) throws IOException{
		//praca();
		txt();
	}

	public static String praca(Graf g){
		g.printSasiedzi();
		DFS t = new DFS(g);
		return t.poczynaj();
	}
	public static Graf konsola(){
			System.out.println("KONSOLA : ");
			Scanner in = new Scanner(System.in);
			int rozmiar = in.nextInt();
			Graf g = new Graf();
			for(int i = 0; i < rozmiar; i++)
				g.add(i);
			for(int i = 0; i < rozmiar; i++){
				int len = in.nextInt();
				for(int j = 0; j < len; j++){
					int odczyt = in.nextInt();
					g.sasiedzi.get(i).add(odczyt-1);
				}
			}
			System.out.println(".................................................................");
		praca(g);
		return g;
	}


	public static void txt() throws IOException{
		String a1 = "../testy/input0.txt";
		String a2 = "../testy/input1.txt";
		String a3 = "../testy/input2.txt";
		String a4 = "../testy/input3.txt";
		String a5 = "../testy/input4.txt";
		String a6 = "../testy/input5.txt";
		String a7 = "../testy/input6.txt";
		String a8 = "../testy/input7.txt";
		String a9 = "../testy/input8.txt";
		String a10 = "../testy/input9.txt";
		_txt(a1);
		_txt(a2);
	//	_txt(a3);
		_txt(a4);
		_txt(a5);
		_txt(a6);
		_txt(a7);
		_txt(a8);
		_txt(a9);
		_txt(a10);
	}

	public static void _txt(String txt)throws IOException {
		Scanner plikWej = null;
		Graf g = new Graf();
		try {
			plikWej = new Scanner(new BufferedReader(new FileReader(txt)));
			int licznik = 0;
			int rozmiar_grafu = plikWej.nextInt();
			for(int i = 0; i < rozmiar_grafu; i++)
				g.add(i);
			while(plikWej.hasNext()){
				if(plikWej.hasNextInt()){
					int rozmiar = plikWej.nextInt();
					//System.out.println("---" + rozmiar);
					for (int i = 0; i < rozmiar; i++){
						g.sasiedzi.get(licznik).add(plikWej.nextInt() -1);
						//System.out.print(g.sasiedzi.get(licznik).get(i) + " ");
					}	
					System.out.println();
					licznik++;
				} else if (plikWej.hasNext())
					plikWej.next();
			}
		} catch (IOException ex){
			System.err.println("Blad wejscia");
		} finally {
			if (plikWej != null)
				plikWej.close();
		}
		//g.printSasiedzi();
		if(sprawdzSkierowany(g) == true){
			String wynik = praca(g);
			FileWriter zapis = null;
			try { 
				String gdzie = "../testy/MOJE/";
				gdzie += "output" + txt.substring(txt.length()-5,txt.length());
				System.out.println("~" + wynik);
				zapis = new FileWriter(gdzie);
				zapis.write(wynik);
			} catch (IOException ex) {
				System.err.println("Blad wyjscia");
			} finally {
				if (zapis != null)
					zapis.close();
			}
		} else {
			FileWriter zapis = null;
			try {
				String gdzie = "../testy/MOJE/";
				gdzie += "output" + txt.substring(txt.length()-5,txt.length());
				zapis = new FileWriter(gdzie);
				zapis.write("n ");
			} finally {
				if (zapis != null)
					zapis.close();
			}
		}
	}

	public static  boolean sprawdzSkierowany(Graf g){
		for (int i = 0; i < g.len; i++){
			ArrayList<Integer> tmp = g.sasiedzi.get(i);
			for (int j = 0; j < tmp.size(); j++){
				int temp = tmp.get(j);
				if(IN(g.sasiedzi.get(temp),i) == false){
					System.out.println("SKIEROWANY!!!!");
					return false;
				}

			}
		}
		return true;
	}

	public static boolean IN(ArrayList<Integer> x , int k){
		for (int i = x.size() -1; i >= 0; i--)
			if ( x.get(i) == k)
				return true;
		return false;
	}
	public static void wyswietl(String a){
		System.out.println("~ " + a);
	}
}