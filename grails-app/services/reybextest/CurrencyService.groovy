package reybextest

import grails.gorm.services.Service

@Service(Currency)
interface CurrencyService {

    Currency get(Serializable id)

    List<Currency> list(Map args)

    Long count()

    void delete(Serializable id)

    Currency save(Currency currency)

}