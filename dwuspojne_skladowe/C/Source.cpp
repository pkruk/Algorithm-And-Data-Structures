#include <iostream>
#include <algorithm>
#include <vector>
#include <cstring>
#include <string>
#include <fstream>
#define wyswietl(a) {  cout << "\033[1;31m" << a <<"	\033[0m\n"; };

using namespace std;


typedef vector <int> V;
V v,pi; // lista wierzcholkow i poprzednikow
vector<V> adj; //macierz sasiadow bjaaacz 
int cnt;
int len;
int nieskonczonosc = 100000000;
V skladowe;
V mosty;
bool flaga = true;

// Mniej wazne funkcje : 
void konsola();
void init(int len);
int dfsvisit(int u);
bool in(int gdzie,int co);
bool check(); // sprawdza czy jest skierowany czy nieskierowany
void wypisz_skladowe();
void dfs();


void answer(bool f){
	if (f == true){
		cout << "Nieskierowany, czas zabawy" << endl;
		dfs();
		flaga = false;
		cout << "Skladowe\n";
		wypisz_skladowe();
		int mod = 0;
		cout << "\tMosty" << endl;
		for (vector<int>::iterator it = mosty.begin(); it != mosty.end(); it++){
			if ( mod % 2 == 0)
				cout << endl;
			mod++;
			cout << *it << " ";	
		}
		cout << endl;
	}
	else
		cout << "n" << endl;

}




void dfs(){
	init(len);
	for (int i = 0; i < len; i++)
		if (v[i] == nieskonczonosc){
			if(flaga == true)
				skladowe.push_back(i);
			pi[i] = i;
			dfsvisit(i);
		}
}

int main(int argc, const char *argv[])
{
	konsola();
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
	for (vector<int>::iterator it = t.begin(); it != t.end(); it++)
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
