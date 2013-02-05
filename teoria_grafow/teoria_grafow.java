import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.Scanner;
public class teoria_grafow {

	public static void main(String[] args) throws IOException {
		System.out.println("HELLO");
		String wejscie = "//home//neptyd//Pobrane//Testy//input2.txt";
		String wyjscie = "//home//neptyd//test1_out.txt";
		Theory t = new Theory(wejscie, wyjscie);
		t.odczytajzPliku();
	}

}

class Theory {
	void odczytajzPliku() throws IOException {
		BufferedReader odczyt = null;
		try {
			odczyt = new BufferedReader(new FileReader(wejscie));
			String k = odczyt.readLine();
			ZapisDoPliku(k);
			// Dopisana pierwsza linia imie nazwisko i jezyk
			k = odczyt.readLine();
			int liczba_slow = Integer.parseInt(k);
			// liczba slow kluczowych!
			slowa = new Node[liczba_slow];
			k = odczyt.readLine();
			int j = 0;
			StringTokenizer tmp = new StringTokenizer(k, " ");
			while (j < liczba_slow) {
				while (tmp.hasMoreTokens()) {
					String y = tmp.nextToken();
                    y.trim();
					slowa[j] = new Node(y);
					j++;
				}
				if (j < liczba_slow)
					k = odczyt.readLine();
			}// Odczytana tablica slow KLUCZY!!!
			sortujSlowa();
			// posortowane !
			k = odczyt.readLine();
			//System.out.println(search(slowa[1].slowo));
            int liczba_niekluczowych = 0;
            tmp = null;
            int wystapily = 0;
			while (k != null) {
                String q = "";
                boolean flaga = true;
                boolean f1 = true;
                while(flaga == true){
                    //wyrzucimy wszystkie komentarze!
                    int h = k.length();
                    for(int i = 0; i < h; i++){
                        char c = k.charAt(i);
                        if(c == '/' && i + 1 < h && k.charAt(i+1) == '*')
                            f1 = false;
                        if(c == '*' && i + 1 < h && k.charAt(i+1) == '/')
                            f1 = true;
                        if(c != '*'  && c != '\t' && c != ' ' && f1 == true)
                            q += c;
                        else if( c != '*' && (c == '\t' || c == ' ') && f1 == true)
                            q += ' ';
                    }
                    if(f1 == true)
                        flaga = false;
                    else
                        k = odczyt.readLine();
                }
//                System.out.println("*" + q); //WARTOWNIK!
                flaga = true;
                f1 = true;
                //wyrzucimy wszystkie " !
                String q1 = "";
                while(flaga == true && q != null){
                    int h = q.length();
                    for(int i = 0; i < h; i++){
                        char c = q.charAt(i);
                        if(c == '"' && i+1 < h && q.charAt(i+1) != 39)
                            f1 = !f1;
                        if(c != '"' && f1 == true)
                            q1 += c;
                    }
                    if(f1 == true)
                        flaga = false;
                    else{
                        q = odczyt.readLine();
                        k = q;
                    }
                }

                q = q1;
                int h1 = q.length();
                q1 = "";
                //*******************************\\
                for(int i = 0; i < h1; i++){
                    char c = q.charAt(i);
                    if(c == '/' && i + 1 < h1 && q.charAt(i+1) == '/')
                        i = h1; //koniec
                    else
                        q1+=c;
                }
                q = q1;
                h1 = q.length();
                //********************************\\
                q1 = "";
                for(int i = 0; i < h1; i++){
                    char c = q.charAt(i);
                    if(c == '[' || c == ']' || c == '(' || c == ')' || c == ':' || c == '>' || c == '<' )
                        q1 += " " + c + " ";
                    else
                        q1 += c;
                }
                q = q1;
                q1 = "";
                h1 = q.length();
                for(int i = 0; i < h1; i++){
                    char c = q.charAt(i);
                    if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <='9')|| c == ' ')
                        q1 += c;
                }
                q = q1;
                tmp = new StringTokenizer(q," ");
//                System.out.println(q);
                while(tmp.hasMoreTokens()){
                    String as = tmp.nextToken();
                    as.trim();
                    if(as.length() > 1){
                        int i = search(as);
                        if(i == -1)
                            liczba_niekluczowych++;
                        else {
                           slowa[i].ilosc++;
                           wystapily++;
                        }
                    }
                }
				k = odczyt.readLine();
			}
//            System.out.println("Nie wystapily!" + liczba_niekluczowych + " " + wystapily);
//            System.out.println(iloscWystapienSlowKluczowych());
//            printSlowa();
            drukuj(liczba_niekluczowych,wystapily);
		} catch (FileNotFoundException e) {
			System.err.println("BLAD ODCZYTU NIE MA SCIEZKI");
		} catch (IOException e) {
			System.err.println("BLAD I/O");
		} finally {
			if (odczyt != null)
				odczyt.close();
		}

	}

    void drukuj(int niekluczowe,int wystapily) throws IOException {
        int suma = niekluczowe + wystapily;
        int len = slowa.length;
        double a = (((double) wystapily) / suma) * 100;
        a = Math.round(a*100)/100;
        double b = (((double) niekluczowe) / suma) * 100;
        b = Math.round(b*100)/100;
        System.out.println("" + wystapily + " " + a +"%");
        System.out.println(niekluczowe + " " + b+"%");
        dopiszDoPliku("\n" + suma);
        dopiszDoPliku(""+wystapily+" " + a + "%");
        dopiszDoPliku(""+niekluczowe + " " + b +"%");
        for(int i = 0; i < len; i++){
            double x = slowa[i].ilosc;
            double percent = (x/suma) * 100;
            percent *= 100;
            percent = Math.round(percent);
            percent /= 100;
            System.out.println(x + " " + percent + " " + slowa[i].slowo);
            dopiszDoPliku("" + x + " " + percent + "% " + slowa[i].slowo);
        }
    }

	private	int search(String a){
		int lewo = 0;
		int prawo = slowa.length-1;
		while(lewo <= prawo){
			int srodek = (lewo+prawo)/2;
            if(a.compareTo(slowa[srodek].slowo) > 0){
                lewo = srodek +1;
                //glupi blad +1 a nie srodek++ Sprytny glupi blad oO!
            }else if(a.compareTo(slowa[srodek].slowo) < 0)
                prawo = srodek -1;
            else
                return srodek;
		}
		return -1;
	}
    private int iloscWystapienSlowKluczowych(){
        int x = 0;
        for(int i = 0; i < slowa.length; i++)
            if(slowa[i].ilosc != 0 )
                x += slowa[i].ilosc;
        return x;

    }
	void dopiszDoPliku(String[] txt) throws IOException {
		int len = txt.length;
		for (int i = 0; i < len; i++)
			dopiszDoPliku(txt[i]);
	}

	void dopiszDoPliku(String txt) throws IOException {
		PrintWriter dopis = null;
		try {
            dopis = new PrintWriter(new FileWriter(wyjscie,true));
			dopis.println(txt);
		} finally {
			if (dopis != null)
				dopis.close();
		}
	}

	void ZapiszTabliceString(String[] txt) {
		zapis = null;
		int len = txt.length;
		for (int i = 0; i < len; i++) 

			try {
				ZapisDoPliku(txt[i]);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	void ZapisDoPliku(String txt) throws IOException {
		zapis = null;
		try {
			zapis = new FileWriter(wyjscie);
			zapis.write(txt);

		} catch (FileNotFoundException e) {
			System.err.println("ZLA SCIEZKA DO ZAPISU! NIE MA TAKIEGO PLIKU!");
		} catch (IOException e) {
			System.out.println("Blad I/O");
		} finally {
			if (zapis != null)
				zapis.close();
		}

	}

	void printSlowa() {
		int l = slowa.length;
		for (int i = 0; i < l; i++)
			System.out.print(slowa[i].slowo + " " + slowa[i].ilosc + "\n");
	}

	void sortujSlowa() {
		int j;
		String v = "";
		for (int i = 1; i < slowa.length; i++) {
			j = i;
			v = slowa[i].slowo;
			while ((j > 0) && v.compareTo(slowa[j - 1].slowo) < 0) {
				slowa[j].slowo = slowa[j - 1].slowo;
				j--;
			}
			slowa[j].slowo = v;
		}
	}

	void zmienNazweWyjscia(String txt) {
		this.wyjscie = txt;
	}

	void zmienNazweWejscia(String txt) {
		this.wejscie = txt;
	}

	public Theory(String wejscie, String wyjscie) {
		this.wejscie = wejscie;
		this.wyjscie = wyjscie;
	}

	public Theory() {
		wejscie = "";
		wyjscie = "";
	}

	public Node[] slowa;
	public FileWriter zapis;
	public String wejscie;
	public String wyjscie;
}

class Node {

	void add() {
		ilosc++;
	}

	Node(String slowo) {
		this.slowo = slowo;
		ilosc = 0;
	}

	Node() {
		ilosc = 0;
		slowo = "";
	}

	public int ilosc;
	public String slowo;
}
