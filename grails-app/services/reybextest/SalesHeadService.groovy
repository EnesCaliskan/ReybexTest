package reybextest

import grails.gorm.services.Service

@Service(SalesHead)
interface SalesHeadService {

    SalesHead get(Serializable id)

    List<SalesHead> list(Map args)

    Long count()

    void delete(Serializable id)

    SalesHead save(SalesHead salesHead)

}