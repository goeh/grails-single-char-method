package issue

import org.grails.datastore.mapping.core.DatastoreUtils
import org.grails.orm.hibernate.HibernateDatastore
import org.hibernate.Session
import org.hibernate.dialect.H2Dialect
import org.springframework.orm.hibernate5.SessionHolder
import org.springframework.transaction.support.TransactionSynchronizationManager
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

/**
 * Tenant test.
 */
class PersonTenantSpec extends Specification {

    @Shared
    @AutoCleanup
    HibernateDatastore datastore

    void setupSpec() {
        Map config = [
                'dataSource.url'                              : "jdbc:h2:mem:grailsDB;MVCC=TRUE;LOCK_TIMEOUT=10000",
                'dataSource.dbCreate'                         : 'update',
                'dataSource.dialect'                          : H2Dialect.name,
                'dataSource.formatSql'                        : 'true',
                'hibernate.flush.mode'                        : 'COMMIT',
                'hibernate.cache.queries'                     : 'true',
                'hibernate.hbm2ddl.auto'                      : 'create'
        ]

        datastore = new HibernateDatastore(DatastoreUtils.createPropertyResolver(config), Person, PersonLink)
    }

    Session session

    void setup() {
        def sessionFactory = datastore.sessionFactory
        session = sessionFactory.openSession()
        TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session))
    }

    void cleanup() {
        def sessionFactory = datastore.sessionFactory
        session.close()
        TransactionSynchronizationManager.unbindResource(sessionFactory)
    }

    void "save person"() {
        when:
        def a = new Person(firstName: "Fred", lastName: "Flintstone").save(flush: true)
        def b = new Person(firstName: "Wilma", lastName: "Flintstone").save(flush: true)
        def r = new PersonLink(a: a, b: b).save(flush: true)

        then:
        Person.list().size() == 2
        PersonLink.list().size() == 1
    }
}
