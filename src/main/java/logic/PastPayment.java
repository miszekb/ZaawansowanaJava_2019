package logic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "past_payment_table")
public class PastPayment implements Payment{

	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "price")
	private float price;
	@Column(name = "type")
	private Categories type;
	@Column(name = "description")
	private String description;
	@Column(name = "date")
	private Date date;

	private float priceInDifferentCurrency;

	public PastPayment(String name, float price, Categories type, String description, Date date)
	{
		this.id = Integer.parseInt((UUID.randomUUID().toString().replaceAll("[^0-9]", "")).substring(0,5));
		this.name = name;
		this.price = price;
		this.type = type;
		this.description = description;
		this.setPaymentDate(date);
	}

	public PastPayment()
	{
		this.id = Integer.parseInt((UUID.randomUUID().toString().replaceAll("[^0-9]", "")).substring(0,5));
		this.name = "";
		this.price = 0;
		this.type = Categories.Ubrania_Obuwie;
		this.description = "";
		this.setPaymentDate(new Date());
	}

	@Override
	public int getPaymentID() {
		return id;
	}
	
	@Override
	public String getPaymentName() {
		return name;
	}
	
	@Override
	public float getPaymentPrice() {
		return price;
	}
	
	@Override
	public String getPaymentType() {
		return type.key;
	}
	
	@Override
	public String getPaymentDescription() {
		return description;
	}

	public Date getPaymentDate() {
		return date;
	}

	public int getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public float getPrice() {
		return price;
	}

	public Categories getType() {
		return type;
	}

	public String getDescription() {
		return description;
	}

	public Date getDate() {
		return date;
	}

	public float getPriceInDifferentCurrency() {
		return priceInDifferentCurrency;
	}


	@Override
	public void setPaymentID(int ID) {
		this.id = ID;
	}

	@Override
	public void setPaymentName(String name) {
		this.name = name;
	}

	@Override
	public void setPaymentPrice(float price) {
		this.price= price;
	}

	@Override
	public void setPaymentType(Categories type) { this.type = type; }

	@Override
	public void setPaymentDescription(String description) {
		this.description = description;
	}

	public void setPriceInDifferentCurrency(float currency){
		priceInDifferentCurrency = price/currency;
	}

	public void setPaymentDate(Date date) {
		this.date = date;
	}

	public void setID(int ID) {
		this.id = ID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setType(Categories type) {
		this.type = type;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String archive() {
		// TODO archiving implementation
		return null;
	}

	@Override
	public String toString() {
		String info;

		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		String reportDate = df.format(date);

		info = (id) + "," +
				name + "," +
				(price) + "," +
				type.key + "," +
				description +"," + reportDate;

		return info;
	}
}
	
	

	
	

