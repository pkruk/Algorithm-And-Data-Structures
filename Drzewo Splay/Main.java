import java.util.*;
import java.io.*;

public class Main {


	public static void zapisz(String txt,String zapis )throws IOException{
		FileWriter wyjscie = null;
		try {
			wyjscie = new FileWriter(txt);
			wyjscie.write(zapis);
		} catch(IOException ex) {

		} finally {
			if (wyjscie != null)
				wyjscie.close();
		}
	}

	public static void main(String[] args)throws IOException {
		Scanner in = new Scanner(System.in);
		String wejscie = "";
		for (int i = 0; i < args.length; ++i){
			if (args[i].equals("-f")){
				if (args[i+1] != null){
					System.setIn(new FileInputStream (new File(args[i+1])));
					wejscie += args[i+1];
				}
			}
		}
		String x = txt();
		String wyjscie = "testy9/out_moje/out" + wejscie.substring(wejscie.length()-5);
		//System.out.println(wyjscie + " " + x);
		zapisz(wyjscie,x);
	}

	public static String txt() throws IOException{
		Scanner in = null;
		in = new Scanner(System.in);
		String imie = "";
		int smiec = in.nextInt();
		int ilosc_slow = in.nextInt();
		imie = in.nextLine();
		imie = in.nextLine();
		imie = in.nextLine();
		Prepare p = new Prepare();
		int licznik = 0;
		for(int i = 0; i < smiec*2 +1; i++){
			int ile = 0;
			float pr = 0;
			String s = " ";
			if ( i %2 == 0){
				ile = in.nextInt();
				float procenty = Float.parseFloat(in.next())/100;
				p.q.add(new Node(ile,procenty));
			//	System.out.println("~" + ile + " " + procenty + " " + s);
			} else {
				ile = in.nextInt();
				pr = Float.parseFloat(in.next())/100;
				s = in.next();
				float procenty = pr;
				//System.out.println("~ " + ile + " " + procenty + " " + s);
				p.p.add(new Node(ile,procenty,s));
			}
			++licznik;
		}
		p.start_index();
		//p.wyswietl();
		p.optimal_bst();
		return p.zwroc_wynik();
	}
}