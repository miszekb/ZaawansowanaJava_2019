package logic;

import javax.persistence.*;
import java.util.UUID;
import static logic.PaymentsManager.getEntityManager;

@Entity
@Table(name = "future_payment_table")
public class FuturePayment implements Payment {

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

	private float priceInDifferentCurrency;
	private boolean isCyclic = false;

	public FuturePayment(String name, float price, Categories type, String description)
	{
		this.id = Integer.parseInt((UUID.randomUUID().toString().replaceAll("[^0-9]", "")).substring(0,5));
		this.name = name;
		this.price = price;
		this.type = type;
		this.description = description;
	}

	public FuturePayment()
	{
		this.id = Integer.parseInt((UUID.randomUUID().toString().replaceAll("[^0-9]", "")).substring(0,5));
		this.name = "";
		this.price = 0;
		this.type = Categories.Ubrania_Obuwie;
		this.description = "";
	}

	public void saveFuturePayment() {
		EntityManager entityManager = getEntityManager();

		entityManager.getTransaction().begin();

		entityManager.persist(this);
		entityManager.getTransaction().commit();
	}

	public FuturePayment getFuturePayment(int id) {
		EntityManager entityManager = getEntityManager();
		FuturePayment futurePayment = entityManager.find(FuturePayment.class,  id);
		entityManager.detach(futurePayment);
		return futurePayment;
	}

	@Override
	public int getPaymentID() { return id; }

	@Override
	public String getPaymentName() { return name; }

	@Override
	public float getPaymentPrice() { return price; }

	@Override
	public String getPaymentType() { return type.key; }

	@Override
	public String getPaymentDescription() { return description; }

	public float getPriceInDifferentCurrency() { return priceInDifferentCurrency; }

	public int getID() { return id; }

	public String getName() { return name; }

	public float getPrice() { return price; }

	public Categories getType() { return type; }

	public String getDescription() { return description; }


	@Override
	public void setPaymentID(int ID) { this.id = ID; }

	@Override
	public void setPaymentName(String name) { this.name = name; }

	@Override
	public void setPaymentPrice(float price) { this.price= price; }

	@Override
	public void setPaymentType(Categories type) { this.type = type; }

	@Override
	public void setPaymentDescription(String description) { this.description = description; }

	public void setPriceInDifferentCurrency(float currency){ priceInDifferentCurrency = price/currency; }

	public void setID(int ID) { this.id = ID; }

	public void setName(String name) { this.name = name; }

	public void setPrice(float price) { this.price = price; }

	public void setType(Categories type) { this.type = type; }

	public void setDescription(String description) { this.description = description; }

	public void setCyclic(boolean status){
		isCyclic = status;
	}

	public boolean getCyclic(){
		return isCyclic;
	}


	@Override
	public String toString()
	{
		String info;
		info = (id) + "," +
				name + "," +
				(price) + "," +
				type.key + "," +
				description;

		return info;
	}

	@Override
	public String archive() {
		// TODO archiving implementation
		return null;
	}
}
