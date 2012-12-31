#include <iostream>
#include <algorithm>
#include <vector>
#include <cstring>
#include <string>
#include <fstream>
#define wyswietl(a) {  cout << "\033[1;31m" << a <<"	\033[0m\n"; };
#define FOREACH(i,v) for(typeof(v.end()) i = v.begin(); i != v.end(); i++)
using namespace std;


typedef vector <int> V;

//zmienne 
V v,pi; // lista czasow wejscia 
vector<V> adj; //macierz sasiadow bjaaacz 
int cnt; // timer 
int len; // dlugosc listy wierzcholkow
int nieskonczonosc = 100000000; //nieskonczonosc do dfs
V skladowe; // na skladowe
V mosty; // vector na wspolrzedne MOSTOW
bool flaga = true; // flaga do dfs gdy chcemy odczytac mosty a potem dwuspojne skladowe
// end zmienne 

//  funkcje : 
void wypiszADJ(); // wypisuje tablice sasiadow
void konsola(); //odczyt z konsoli
void init(int len); //inicjuje vektory
int dfsvisit(int u);//inicjuje i zaczyna dfsa
bool in(int gdzie,int co); //sprawdza czy w danym wektorze jest szukana wartosc
bool check(); // sprawdza czy jest skierowany czy nieskierowany
void wypisz_skladowe(); //wypisuje cale skladowe!
void dfs();
void odczyt(); //czyta z pliku txt
void answer(bool f); //odpowiedzi wytyczne do programu 
void wywal_mosty();
// end funkcje

void answer(bool f){
	if (f == true){
		cout << "Nieskierowany, czas zabawy" << endl;
		dfs();
		flaga = false;
		cout << "Skladowe\n";
		wypisz_skladowe();
		int mod = 0;
		cout << "\tMosty" << endl;
		FOREACH(it,mosty){
			if ( mod % 2 == 0)
				cout << endl;
			mod++;
			cout << *it << " ";	
		}
		cout << endl;
		wywal_mosty();
	}
	else
		cout << "n" << endl;

}

void usunkrawedz(int z, int jaka){ 
	cout << " z\t " << z <<" " << jaka << endl;	
	FOREACH(it,adj[z]) {
		cout << *it << " " ;
		if (*it == jaka) {
			cout << "Usuwam " << *it << endl;
			adj[z].erase(it);
		}
	}
}

void wywal_mosty(){
	
	vector<int>::iterator i = mosty.begin();
	cout << "---";
	vector<int>::iterator j = i++;
	while ( j != mosty.end() ){
		cout <<"ide" << *i << " " << *j << endl;
		usunkrawedz(*i,*j);
		usunkrawedz(*j,*i);	
	//	wypiszADJ();
		i++; 
		if ( i == mosty.end() )
			j = mosty.end();
		else
			j++;
	}
	cout << "WCHODZE" << endl;
//	usunkrawedz(*i,*j);
//	usunkrawedz(*j,*i);
	wypiszADJ();
}


void dfs(){
	init(len);
	for (int i = 0; i < len; i++)
		if (v[i] == nieskonczonosc){
			cout << "X : " << i << endl;
			if(flaga == true)
				skladowe.push_back(i);
			pi[i] = i;
			dfsvisit(i);
		}
}

int main(int argc, const char *argv[])
{
//	konsola();
	odczyt();
	return 0;
}


int dfsvisit(int u){
	int ans = u;
	v[u] = cnt++;
	for(int i = 0; i < adj[u].size(); i++){
		int tmp = adj[u][i];

		if (tmp == u) continue; //ignorujemy cykl
		if (tmp == pi[u]) continue; // ignorujemy poprzednika!
		if (v[tmp] == nieskonczonosc ) { //nieodwiedzony 
			pi[tmp] = u; //nowy poprzednik na drodze
			tmp = dfsvisit(tmp); //szukamy od niego drogi
		}
		if(v[ans] > v[tmp]){
			ans = tmp;
		}
	}
	if (ans == u && pi[u] != u && flaga == true){
	//	cout << "Most: "  << u +1 << " " << pi[u] +1 << endl;
		mosty.push_back(u+1); mosty.push_back(pi[u] +1);
	}
	return ans;
}

void odczyt(){
	string wejscie = ".//testy//input8.txt";
	char* w = new char [wejscie.size()];
	for (int  i = 0; i < wejscie.size(); i++)
		w[i] = wejscie[i];
//	while (*w != '\0')
//		cout << *w++;
//	cout << endl;
	ifstream czytaj;
	czytaj.open(w);
	czytaj >> len;
	adj.clear(); adj.resize(len);
	cout << "rozmiar : " << len << endl;
	for(int i = 0; i < len; i++){
		int l_s;
		int t;
		czytaj >> l_s;
		for(int j = 0; j < l_s; j++){
			czytaj >> t;
			--t;
			adj[i].push_back(t);
		}
	}	
	czytaj.close();
	for(int i = 0; i < len; i++){
		cout << "i : " << i + 1 << " | ";
		FOREACH(it,adj[i])
			cout << (*it +1)<< " ";
			cout << endl;
	}
	bool f = check();
	answer(f);
}


void konsola(){
	int x;
	cout << "WITAM W PROGRAMIE SZUKAJACYM MOSTOW " << endl;
	cout << "Podaj rozmiar";
		cin >> len;
	adj.clear(); adj.resize(len);
	for (int i = 0; i < len; i++) {
		int n_sasiadow = 0;
		cout <<"Podaj rozmiar listy : ";
		cin >> n_sasiadow;
		cout << endl;
		for (int j = 0 ; j < n_sasiadow; j++){
			cout << "Podaj sasiada : " << endl;
			cin >> x;
			--x;
			adj[i].push_back(x);
		}
	}
	cout << "Zakonczony zapis" << endl;
	bool f = check();
	answer(f);
}

void wypisz_skladowe(){
	vector<int>::iterator i = skladowe.begin();
	vector<int>::iterator j = i +1;
	while ( i != skladowe.end() ){
		if (j != skladowe.end())
			for(int k = *i; k < *j; k++)
				cout << k +1 << " ";
		if (j == skladowe.end())
			for(int w = *i; w < len; w++)
				cout << w + 1 << " ";
		cout << endl;
		i = j;
		j = j + 1;
	}	
}


bool check(){
	
	for (int i = 0; i < len; i++){
		V tmp = adj[i];
		for (vector <int>::iterator it = tmp.begin(); it != tmp.end(); it++){
			//bierzemy jego sasiada i sprawdzamy w sasiedzie czy wystepuje ON
			bool t = in(*it, i);
			if (t == false){
				cout << "SKIEROWANY\n";
				return false;
			}
		}
			
	} // ZLOZONOSC O(n^3);
	return true;
}

bool in(int gdzie,int co){
	V t = adj[gdzie];
	FOREACH(it,t)
		if(*it == co)
			return true;
	return false;
}

void init(int len){
	v.clear();
	v.resize(len,nieskonczonosc);
	pi.resize(len);
	cnt = 1;
 }// sprawdzamy :)!


void wypiszADJ(){
	for (int i = 0; i < len; i++){
		cout << "i : " << i +1 << " | ";
		FOREACH(it,adj[i]) {
			cout << *it +1<< " ";
		}
	cout << endl;
	}
}
