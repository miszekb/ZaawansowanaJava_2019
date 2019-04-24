
public class FuturePayment implements Payment{
	private int ID;
	private String name;
	private float price;
	private Categories type;
	private String description;
	
	FuturePayment(int ID, String name, float price, Categories type, String description)
	{
		this.ID = ID;
		this.name = name;
		this.price = price;
		this.type = type;
		this.description = description;
	}
	
	@Override
	public String toString() 
	{
		String info;
		info = Integer.toString(ID) + "," +
		name + "," +
		Float.toString(price) + "," +
		type.key + "," +
		description;
		
		return info;
	}
	
	@Override
	public String archive() 
	{
		// TODO archiving implementation
		return null;
	}
	
	@Override
	public int getPaymentID() 
	{
		return ID;
	}
	
	@Override
	public String getPaymentName() 
	{
		return name;
	}
	
	@Override
	public float getPaymentPrice() 
	{
		return price;
	}
	
	@Override
	public String getPaymentType()
	{
		return type.key;
	}
	
	@Override
	public String getPaymentDescription() 
	{
		return description;
	}
	
	//[SETTERS]


	@Override
	public void setPaymentID(int ID) 
	{
		this.ID = ID;
	}

	@Override
	public void setPaymentName(String name) 
	{
		this.name = name;
	}

	@Override
	public void setPaymentPrice(float price) 
	{
		this.price= price;
	}

	@Override
	public void setPaymentType(Categories type)
	{
		this.type = type;
		
	}

	@Override
	public void setPaymentDescription(String description) 
	{
		this.description = description;
	}
}
