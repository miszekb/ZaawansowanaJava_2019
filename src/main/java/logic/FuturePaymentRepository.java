package logic;

import java.util.ArrayList;

public class FuturePaymentRepository {

	private ArrayList<FuturePayment> futurePayments = new ArrayList<FuturePayment>();

	public ArrayList<FuturePayment> getFuturePayments()
	{
		return futurePayments;
	}

	public String addToRepository(FuturePayment futurePayment)
	{	
		boolean result = futurePayments.add(futurePayment);
		String info;
		if (result == true) info = "Dodano wydatek do planu.";
		else info = "Dodanie wydatku nie powiodlo sie.";
			
		return info;
	}

	public String deletePayment(int ID)
	{
		boolean result = false;
		String info = "Nie odnaleziono wydatku o takim ID.";
		
		for (int i = 0; i < futurePayments.size(); i++)
		{
			if (futurePayments.get(i).getPaymentID() == ID)
			{
				if(futurePayments.get(i).getCyclic() == false)
					result = futurePayments.remove(futurePayments.get(i));
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
		String text = "";

		for(int i = 0; i< futurePayments.size(); i++)
		{
			text += futurePayments.get(i).toString() + "\n";
		}

		return text;
	}

	private void reloadID(){
		for(FuturePayment fp: futurePayments){
			fp.setID(futurePayments.indexOf(fp)+1);
		}
	}
}
