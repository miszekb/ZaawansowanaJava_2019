
enum Categories {               //number equivalent
	Ubrania_Obuwie("Ubrania_Obuwie"),             //1
	Rachunki("Rachunki"),                   //2
	Zywnosc("Zywnosc"),                    //3
	Uzywki("Uzywki"),                     //4
	Chemia_SrodkiCzystosci("Chemia_SrodkiCzystosci"),     //5
	Transport("Transport"),                  //6
	Rozrywka("Rozrywka"),                   //7
	Sprzet("Sprzet"),                     //8
	Kosmetyki("Kosmetyki");             //9

	String key;
	Categories(String key) { this.key = key; }

}
public interface Payment {
	
	public int getPaymentID();
	public String getPaymentName();
	public float getPaymentPrice();
	public String getPaymentType();
	public String getPaymentDescription();
	
	public void setPaymentID(int ID);
	public void setPaymentName(String name);
	public void setPaymentPrice(float price);
	public void setPaymentType(Categories type);
	public void setPaymentDescription(String description);
	
	public String archive();
	public String toString();
	
}
