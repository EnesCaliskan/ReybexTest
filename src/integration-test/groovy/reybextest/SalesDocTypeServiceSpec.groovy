package reybextest

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SalesDocTypeServiceSpec extends Specification {

    SalesDocTypeService salesDocTypeService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new SalesDocType(...).save(flush: true, failOnError: true)
        //new SalesDocType(...).save(flush: true, failOnError: true)
        //SalesDocType salesDocType = new SalesDocType(...).save(flush: true, failOnError: true)
        //new SalesDocType(...).save(flush: true, failOnError: true)
        //new SalesDocType(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //salesDocType.id
    }

    void "test get"() {
        setupData()

        expect:
        salesDocTypeService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<SalesDocType> salesDocTypeList = salesDocTypeService.list(max: 2, offset: 2)

        then:
        salesDocTypeList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        salesDocTypeService.count() == 5
    }

    void "test delete"() {
        Long salesDocTypeId = setupData()

        expect:
        salesDocTypeService.count() == 5

        when:
        salesDocTypeService.delete(salesDocTypeId)
        sessionFactory.currentSession.flush()

        then:
        salesDocTypeService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        SalesDocType salesDocType = new SalesDocType()
        salesDocTypeService.save(salesDocType)

        then:
        salesDocType.id != null
    }
}
