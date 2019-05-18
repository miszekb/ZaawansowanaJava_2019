package logic;

import java.util.ArrayList;

public class PastPaymentRepository {
	
	private ArrayList<PastPayment> pastPayments = new ArrayList<PastPayment>();

	public ArrayList<PastPayment> getPastPayments()
	{
		return pastPayments;
	}
	
	public String addToRepository(PastPayment payment)
	{	
		boolean result = pastPayments.add(payment);
		String info;
		if (result == true) info = "Dodano wydatek do archiwum.";
		else info = "Dodanie wydatku nie powiodlo sie.";
			
		return info;
	}
	
	public String deletePayment(int ID)
	{
		boolean result = false;
		String info = "Nie odnaleziono wydatku o takim ID.";
		
		for (int i = 0; i < pastPayments.size() ; i++)
		{
			if (pastPayments.get(i).getPaymentID() == ID)
			{
				result = pastPayments.remove(pastPayments.get(i));
			}
		}
		
		if (result == true)
		{
			info = "Usunieto wydatek";
		}
		
		return info;
	}

	@Override
	public String toString()
	{
		String temp = "";

		for(int i=0;i<pastPayments.size();i++)
		{
			temp += pastPayments.get(i).toString() + "\n";
		}

		return temp;
	}
}
