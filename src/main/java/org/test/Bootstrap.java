package org.test;

import eu.vaadinonkotlin.VaadinOnKotlin;
import eu.vaadinonkotlin.vaadin8.jpa.DBKt;
import eu.vaadinonkotlin.vaadin8.jpa.JPAVOKPlugin;
import org.flywaydb.core.Flyway;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Bootstraps the <a href="https://github.com/mvysny/vok-orm">Vok-ORM</a> which is used by the <code>SQLDataProvider</code> to access the database.
 * @author mavi
 */
@WebListener
public class Bootstrap implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        new JPAVOKPlugin().init();

        // create the 'Person' table
        final Flyway flyway = new Flyway();
        flyway.setDataSource(DBKt.getDataSource(VaadinOnKotlin.INSTANCE));
        flyway.migrate();

        // create some testing data
        DBKt.db(ctx -> {
            for (int i = 0; i < 100; i++) {
                final Person person = new Person();
                person.setName("Person " + i);
                person.setAge(i + 10);
                ctx.getEm().persist(person);
            }
            return null;
        });
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        new JPAVOKPlugin().destroy();
    }
}
