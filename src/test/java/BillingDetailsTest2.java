import com.java.BaseDao;
import com.java.strategy2.joinedtableperclass.BankAccount;
import com.java.strategy2.joinedtableperclass.BillingDetails;
import com.java.strategy2.joinedtableperclass.CreditCard;
import org.junit.jupiter.api.Test;
import java.util.List;

public class BillingDetailsTest2 {

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
		List<BillingDetails> list = dao.getAll("BillingDetails2");
		System.out.println(list);
	}
}
