package reybextest

import grails.gorm.transactions.Transactional

@Transactional
class SalesDocTypeApiService {

    def show(Map params) {
        def response
        if(params.id){
            response = SalesDocType.read(params.id?.toInteger())
            if(!response) {
                response = [message: "SalesDocType not found"]
            }
        } else {
            response = SalesDocType.list([readOnly:true])
        }
        return response
    }

    def save(Map params) {
        def response
        SalesDocType salesDocType
        if(params.id) {
            salesDocType = SalesDocType.get(params.id)
            if(!salesDocType) {
                return [error: "SalesDocType not found"]
            }
        } else {
            salesDocType = new SalesDocType()
        }

        salesDocType.properties = params

        if (!salesDocType.save(flush: true)) {
            String msg = ""
            salesDocType.errors.each {
                msg += it
                println it
            }
            response = [error: msg]
        }
        println salesDocType
        response = [id: salesDocType.id]
        return response
    }

    Map delete(Map params) {
        println "salesDocType delete"
        Map response
        SalesDocType salesDocType = SalesDocType.get(params.id)
        if(!salesDocType) {
            response = [error: "salesDocType not found"]
        }
        salesDocType.delete(flush: true)
        response = [id: salesDocType.id]
        return response
    }




}
