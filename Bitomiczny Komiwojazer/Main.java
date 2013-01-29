import java.util.*;
import java.io.*;
import java.text.*;
public class Main{

	public static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		for (int i = 0; i < args.length; i++){
			if (args[i].equals("-f")){
				if(args[i+1] != null){
					System.setIn(new FileInputStream(args[i+1]));
				}
			}
		}
		txt();
	}

	public static void txt(){
		int len = in.nextInt();
		Node tab[] = new Node[len];
		for (int i = 0; i < len; i++){
			int a = in.nextInt();
			int b = in.nextInt();
			tab[i] = new Node(a,b,((char) ('A' + 1)));
		}
	}
}
