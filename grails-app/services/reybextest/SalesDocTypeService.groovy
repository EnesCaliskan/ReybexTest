package reybextest

import grails.gorm.services.Service

@Service(SalesDocType)
interface SalesDocTypeService {

    SalesDocType get(Serializable id)

    List<SalesDocType> list(Map args)

    Long count()

    void delete(Serializable id)

    SalesDocType save(SalesDocType salesDocType)

}