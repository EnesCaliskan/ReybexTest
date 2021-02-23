package reybextest

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CurrencyServiceSpec extends Specification {

    CurrencyService currencyService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Currency(...).save(flush: true, failOnError: true)
        //new Currency(...).save(flush: true, failOnError: true)
        //Currency currency = new Currency(...).save(flush: true, failOnError: true)
        //new Currency(...).save(flush: true, failOnError: true)
        //new Currency(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //currency.id
    }

    void "test get"() {
        setupData()

        expect:
        currencyService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Currency> currencyList = currencyService.list(max: 2, offset: 2)

        then:
        currencyList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        currencyService.count() == 5
    }

    void "test delete"() {
        Long currencyId = setupData()

        expect:
        currencyService.count() == 5

        when:
        currencyService.delete(currencyId)
        sessionFactory.currentSession.flush()

        then:
        currencyService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Currency currency = new Currency()
        currencyService.save(currency)

        then:
        currency.id != null
    }
}
