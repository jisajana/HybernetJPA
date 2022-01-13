package com.java;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*class MyThread extends Thread
{
	EntityManagerFactory  ref;

	MyThread (	EntityManagerFactory  r)
	{
		ref = r;
	}
	public void run() {
		ref.close();
	}
}*/

public class JPAUtil {
    private static EntityManagerFactory entityManagerFactory;

    static
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("MyJPA");//META-INF/persistence.xml
        //	MyThread m = new MyThread(entityManagerFactory);
//		m.start();
        //	Runtime.getRuntime().addShutdownHook(m);

        Runtime.getRuntime().addShutdownHook(

                new Thread()
                {
                    public void run() {
                        entityManagerFactory.close();
                    }
                }
        );

    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
