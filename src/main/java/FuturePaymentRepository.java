package Logic;

import java.util.ArrayList;

public class FuturePaymentRepository {
	private ArrayList<FuturePayment> repo;

	@Override
	public String toString()
	{
		String temp = null;
		
		for(int i=0;i<repo.size();i++)
		{
			temp += repo.get(i).toString() + "\n";
		}
		
		return temp;
	}
	
	public String AddToRepo(FuturePayment payment)
	{	
		boolean result = repo.add(payment);
		String info;
		
		if (result == true) info = "Dodano wydatek do planu.";
		else info = "Dodanie wydatku nie powiodlo sie.";
			
		return info;
	}
	
	public String DeletePayment(int ID)
	{
		boolean result = false;
		String info = "Nie odnaleziono wydatku o takim ID.";
		
		for (int i=0;i<repo.size();i++)
		{
			if(repo.get(i).getPaymentID() == ID)
			{
				result = repo.remove(repo.get(i));
			}
		}
		
		if (result == true)
		{
			info = "Usunieto wydatek";
		}
		
		return info;
		
	}
	
	public ArrayList<FuturePayment> getRepo() 
	{
		return repo;
	}
}
