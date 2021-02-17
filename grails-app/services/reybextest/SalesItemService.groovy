package reybextest

import grails.gorm.services.Service

@Service(SalesItem)
interface SalesItemService {

    SalesItem get(Serializable id)

    List<SalesItem> list(Map args)

    Long count()

    void delete(Serializable id)

    SalesItem save(SalesItem salesItem)

}