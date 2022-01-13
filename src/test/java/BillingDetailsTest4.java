import java.util.List;

import com.java.BaseDao;
import com.java.strategy4.uinontableperclass.BankAccount;
import com.java.strategy4.uinontableperclass.BillingDetails;
import com.java.strategy4.uinontableperclass.CreditCard;
import org.junit.jupiter.api.Test;

public class BillingDetailsTest4 {

	@Test
	public void testCase1() {
		BillingDetails billingDetails =new BillingDetails();
		billingDetails.setId(5);
		billingDetails.setOwner("Majrul Ansari");
		billingDetails.setNumber("12345687");
		
		BankAccount bankAcc = new BankAccount();
		bankAcc.setId(1);
		bankAcc.setOwner("Majrul Ansari");
		bankAcc.setNumber("12345");
		bankAcc.setBankName("ICICI Bank");

		CreditCard creditCard = new CreditCard();
		bankAcc.setId(2);
		creditCard.setOwner("Majrul Ansari");
		creditCard.setNumber("412901234567890");
		creditCard.setType("VISA");
		creditCard.setExpiryMonth("12");
		creditCard.setExpiryYear("2099");
		
		BaseDao dao = new BaseDao();
		dao.merge(billingDetails);
		dao.merge(bankAcc);
		dao.merge(creditCard);
		
	}

	@Test
	public void testCase2() {
		BaseDao dao = new BaseDao();
		List<BillingDetails> list = dao.getAll("BillingDetails4");
		System.out.println(list);
	}
}
/*
Hibernate: select billingdet0_.id as id1_5_,
billingdet0_.number as number2_5_,
billingdet0_.owner as owner3_5_,
billingdet0_.bankName as bankname1_2_,
billingdet0_.expiryMonth as expirymo1_8_,
billingdet0_.expiryYear as expiryye2_8_,
billingdet0_.type as type3_8_,
billingdet0_.clazz_ as clazz_
from ( select id, number, owner,
cast(null as varchar(100)) as bankName,
cast(null as varchar(100)) as expiryMonth,
cast(null as varchar(100)) as expiryYear,
cast(null as varchar(100)) as type, 0 as clazz_
from BillingDetails4
union all
select id, number, owner, bankName, cast(null as varchar(100)) as expiryMonth, cast(null as varchar(100)) as expiryYear, cast(null as varchar(100)) as type, 1 as clazz_ from BankAccount4 union all select id, number, owner, cast(null as varchar(100)) as bankName, expiryMonth, expiryYear, type, 2 as clazz_ from CreditCard4 ) billingdet0_
[BillingDetails{id=5, owner='Majrul Ansari', number='12345687'}, BillingDetails{id=2, owner='Majrul Ansari', number='12345'}, BillingDetails{id=0, owner='Majrul Ansari', number='412901234567890'}]

 */
