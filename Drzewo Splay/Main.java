import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args)throws IOException {
		Scanner in = new Scanner(System.in);
		for (int i = 0; i < args.length; ++i){
			if (args[i].equals("-f")){
				if (args[i+1] != null){
					System.setIn(new FileInputStream (new File(args[i+1])));
				}
			}
		}
		txt();
	}

	public static void  txt()throws IOException{
		_txt("./output1.txt");
		//test();
	}

	public static void _txt(String nazwa) throws IOException{
		Scanner in = null;
		in = new Scanner(System.in);
		String imie = in.nextLine();
		int smiec = in.nextInt();
		int ilosc_slow = in.nextInt();
		imie = in.nextLine();
		imie = in.nextLine();
		imie = in.nextLine();
		Prepare p = new Prepare();
		int licznik = 0;
		for(int i = 0; i < smiec*2 +1; i++){
			int ile = 0;
			String pr = "";
			String s = " ";
			if ( i %2 == 0){
				ile = in.nextInt();
				pr = in.next();
				double procenty =  (Double.parseDouble(pr.substring(0,pr.length()-1)))/100;
				p.q.add(new Node(ile,procenty));
			//	System.out.println("~" + ile + " " + procenty + " " + s);
			} else {
				ile = in.nextInt();
				pr = in.next();
				s = in.next();
				double procenty =  (Double.parseDouble(pr.substring(0,pr.length()-1)))/100;
				//System.out.println("~ " + ile + " " + procenty + " " + s);
				p.p.add(new Node(ile,procenty,s));
			}
			++licznik;
		}
		p.start_index();
		p.wyswietl();
		p.optimal_bst();
	}
}