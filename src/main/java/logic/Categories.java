package logic;

public enum Categories {               						//number equivalent
	Ubrania_Obuwie("Ubrania_Obuwie"),						//1
	Rachunki("Rachunki"),                   			  	//2
	Zywnosc("Zywnosc"),                     			 	//3
	Uzywki("Uzywki"),                     				  	//4
	Chemia_SrodkiCzystosci("Chemia_SrodkiCzystosci"),  	//5
	Transport("Transport"),                  			  	//6
	Rozrywka("Rozrywka"),                   			  	//7
	Sprzet("Sprzet"),                     				  	//8
	Kosmetyki("Kosmetyki");           					 	//9

	String key;
	Categories(String key) { this.key = key; }
}
