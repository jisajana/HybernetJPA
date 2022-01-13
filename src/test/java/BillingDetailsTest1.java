import com.java.BaseDao;
import com.java.strategy1.tableperhierarchy.BankAccount;
import com.java.strategy1.tableperhierarchy.BillingDetails;
import com.java.strategy1.tableperhierarchy.CreditCard;
import org.junit.jupiter.api.Test;

import java.util.List;


public class BillingDetailsTest1 {

	@Test
	public void testCase1() {
		BankAccount bankAcc = new BankAccount();
		bankAcc.setOwner("Bishnu  Adhikari");
		bankAcc.setNumber("12345");
		bankAcc.setBankName("ICICI Bank");

		CreditCard creditCard = new CreditCard();
		creditCard.setOwner("Kiran Adhikari");
		creditCard.setNumber("412901234567890");
		creditCard.setType("VISA");
		creditCard.setExpiryMonth("12");
		creditCard.setExpiryYear("2099");

		BaseDao dao = new BaseDao();
		dao.merge(bankAcc);
		dao.merge(creditCard);
		
	}

	@Test
	public void testCase2() {
		BaseDao dao = new BaseDao();
		List<BillingDetails> list = dao.getAll("BillingDetails1");
		System.out.println(list);
	}
}

//[BankAccount{bankName='ICICI Bank'}
// BillingDetails {id=2, owner='Bishnu  Adhikari', number='12345'},
// CreditCard {type='VISA', expiryMonth='12', expiryYear='2099'}
// BillingDetails{id=3, owner='Kiran Adhikari', number='412901234567890'}]