package reybextest

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SalesItemServiceSpec extends Specification {

    SalesItemService salesItemService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new SalesItem(...).save(flush: true, failOnError: true)
        //new SalesItem(...).save(flush: true, failOnError: true)
        //SalesItem salesItem = new SalesItem(...).save(flush: true, failOnError: true)
        //new SalesItem(...).save(flush: true, failOnError: true)
        //new SalesItem(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //salesItem.id
    }

    void "test get"() {
        setupData()

        expect:
        salesItemService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<SalesItem> salesItemList = salesItemService.list(max: 2, offset: 2)

        then:
        salesItemList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        salesItemService.count() == 5
    }

    void "test delete"() {
        Long salesItemId = setupData()

        expect:
        salesItemService.count() == 5

        when:
        salesItemService.delete(salesItemId)
        sessionFactory.currentSession.flush()

        then:
        salesItemService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        SalesItem salesItem = new SalesItem()
        salesItemService.save(salesItem)

        then:
        salesItem.id != null
    }
}
