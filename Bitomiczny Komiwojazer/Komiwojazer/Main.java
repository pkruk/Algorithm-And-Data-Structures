import java.util.*;
import java.io.*;
import java.text.*;

public class Main{

	public static void main(String[] args)throws IOException {
		Scanner in = new Scanner(System.in);
		for (int i = 0; i < args.length; i++){
			if (args[i].equals("-f")){
				if(args[i+1] != null){
					System.setIn(new FileInputStream( new File(args[i+1])));
				}
			}
		}
		txt();
	}

	public static void txt(){
		Scanner in = new Scanner(System.in);
		int len = in.nextInt();
		Node tab[] = new Node[len];
		for (int i = 0; i < len; i++){
			int a = in.nextInt();
			int b = in.nextInt();
			tab[i] = new Node(a,b,((char) ('A' + i)));
		}
		Komiwojazer haron = new Komiwojazer(tab);
		haron.start();
		Dynamic a = new Dynamic(tab);
		a.start();
	}

}
