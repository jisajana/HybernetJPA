import com.java.BaseRepository;
import com.java.manytomany.Customer;
import com.java.manytomany.Subscription;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class ManyToManyTest {

    @Test
    public void addCustomer() {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("MyJPA");
        System.out.println("Entity Manager Factory : " + entityManagerFactory);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("Entity Manager  : " + entityManager);


        Customer cust1 = new Customer();
        cust1.setCustomerName("Bishnu");
        cust1.setCustomerEmailAddress("bishnu@gmail.com");

        Customer cust2 = new Customer();
        cust2.setCustomerName("Kiran");
        cust2.setCustomerEmailAddress("kiran@gmail.com");

        Customer cust3 = new Customer();
        cust3.setCustomerName("Sajana");
        cust3.setCustomerEmailAddress("Sajana@gmail.com");

        Customer cust4 = new Customer();
        cust4.setCustomerName("Subed");
        cust4.setCustomerEmailAddress("subed@gmail.com");

        EntityTransaction entityTransaction = entityManager.getTransaction();
        System.out.println("Entity transaction : " + entityTransaction);

        entityTransaction.begin();
        System.out.println("Transaction started....");

        entityManager.persist(cust1);
        entityManager.persist(cust2);
        entityManager.persist(cust3);
        entityManager.persist(cust4);

        entityTransaction.commit();


    }

    @Test
    public void addSubscriptions() {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("MyJPA");
        System.out.println("Entity Manager Factory : " + entityManagerFactory);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("Entity Manager  : " + entityManager);

        Subscription sub1 = new Subscription();
        sub1.setSubscriptionType("Books");
        sub1.setSubscriptionDuration("6 months");

        Subscription sub2 = new Subscription();
        sub2.setSubscriptionType("DVDs");
        sub2.setSubscriptionDuration("3 months");


        EntityTransaction entityTransaction = entityManager.getTransaction();
        System.out.println("Entity transaction : " + entityTransaction);

        entityTransaction.begin();
        System.out.println("Transaction started....");

        entityManager.persist(sub1);
        entityManager.persist(sub2);


        entityTransaction.commit();


    }

    @Test
    public void assignTheExistingSubscriptionToTheExistingCustomer() {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("MyJPA");
        System.out.println("Entity Manager Factory : " + entityManagerFactory);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("Entity Manager  : " + entityManager);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        System.out.println("Entity transaction : " + entityTransaction);

        entityTransaction.begin();
        System.out.println("Transaction started....");

        Customer theCust = entityManager.find(Customer.class, 12);
        Subscription theSub1 = entityManager.find(Subscription.class, 16);
        Subscription theSub2 = entityManager.find(Subscription.class, 17);

        theCust.getSubscriptions().add(theSub1);//set is returned on which add method is invoked
        theCust.getSubscriptions().add(theSub2);

        entityManager.merge(theCust);

        entityTransaction.commit();


    }


    @Test
    public void addCustomer2() {
        BaseRepository baseRepo = new BaseRepository();

        Customer cust = new Customer();
        cust.setCustomerEmailAddress("vishal@gmail.com");
        cust.setCustomerName("vishhal");

        //no need to create entitymanagerfactory
        //no need to create entitymanager
        //no need to craete entity transaction
        baseRepo.persist(cust);

        //no need of commit the transaction

    }
}

