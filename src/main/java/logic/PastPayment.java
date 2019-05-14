package logic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "past_payment_table")
public class PastPayment implements Payment{

	@Id
	@Column(name = "id")
	private int ID;
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

	public PastPayment(int ID, String name, float price, Categories type, String description, Date date)
	{
		this.ID = ID;
		this.name = name;
		this.price = price;
		this.type = type;
		this.description = description;
		this.setPaymentDate(date);
	}
	
	@Override
	public String toString() {
		String info;
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		String reportDate = df.format(date);
		
		info = Integer.toString(ID) + "," +
		name + "," +
		Float.toString(price) + "," +
		type.key + "," +
		description +"," + reportDate;
		
		return info;
	}
	
	@Override
	public String archive() {
		return null;
	}
	
	@Override
	public int getPaymentID() {
		return ID;
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
	
	//[SETTERS]


	@Override
	public void setPaymentID(int ID) {
		this.ID = ID;
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
	public void setPaymentType(Categories type) {
		this.type = type;
		
	}

	@Override
	public void setPaymentDescription(String description) {
		this.description = description;
	}

	public Date getPaymentDate() {
		return date;
	}

	public void setPaymentDate(Date date) {
		this.date = date;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Categories getType() {
		return type;
	}

	public void setType(Categories type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
	
	

	
	

