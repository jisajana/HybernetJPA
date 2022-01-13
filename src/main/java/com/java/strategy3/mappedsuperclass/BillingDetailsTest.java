package com.java.strategy3.mappedsuperclass;

import java.util.List;

import com.java.BaseDao;
import org.junit.jupiter.api.Test;


public class BillingDetailsTest {

	@Test
	public void testCase1() {
		
		BankAccount bankAcc = new BankAccount();
		bankAcc.setOwner("Majrul Ansari");
		bankAcc.setNumber("12345");
		bankAcc.setBankName("ICICI Bank");

		CreditCard creditCard = new CreditCard();
		creditCard.setOwner("Majrul Ansari");
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
		List<BillingDetails> list = dao.getAll("BillingDetails3");
		System.out.println(list);
	}
}
