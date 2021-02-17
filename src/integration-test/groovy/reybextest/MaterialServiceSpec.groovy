package reybextest

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class MaterialServiceSpec extends Specification {

    MaterialService materialService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Material(...).save(flush: true, failOnError: true)
        //new Material(...).save(flush: true, failOnError: true)
        //Material material = new Material(...).save(flush: true, failOnError: true)
        //new Material(...).save(flush: true, failOnError: true)
        //new Material(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //material.id
    }

    void "test get"() {
        setupData()

        expect:
        materialService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Material> materialList = materialService.list(max: 2, offset: 2)

        then:
        materialList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        materialService.count() == 5
    }

    void "test delete"() {
        Long materialId = setupData()

        expect:
        materialService.count() == 5

        when:
        materialService.delete(materialId)
        sessionFactory.currentSession.flush()

        then:
        materialService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Material material = new Material()
        materialService.save(material)

        then:
        material.id != null
    }
}
