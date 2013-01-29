import java.util.*;
import java.io.*;

public class Komiwojazer{


	public void wyswietl(){
		for (Node i : tab){
			System.out.println(i.toString());
		}
	}

	public Komiwojazer(Node tab[]){
		this.tab = tab;
		len = tab.length;
	}
	int len;
	Node tab[];
}