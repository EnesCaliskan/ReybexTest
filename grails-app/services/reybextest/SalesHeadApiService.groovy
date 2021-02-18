package reybextest

import grails.gorm.transactions.Transactional

@Transactional
class SalesHeadApiService {

    def show(Map params) {
        def response
        if(params.id){
            response = SalesHead.read(params.id?.toInteger())
            if(!response) {
                response = [message: "SalesHead not found"]
            }
        } else {
            response = SalesHead.list([readOnly:true])
        }
        return response
    }

    def save(Map params) {
        def response
        SalesHead salesHead
        if(params.id) {
            salesHead = SalesHead.get(params.id)
            if(!salesHead) {
                return [error: "SalesHead not found"]
            }
        } else {
            salesHead = new SalesHead()
        }

        salesHead.properties = params

        if (!salesHead.save(flush: true)) {
            String msg = ""
            salesHead.errors.each {
                msg += it
                println it
            }
            response = [error: msg]
        }
        println salesHead
        response = [id: salesHead.id]
        return response
    }

    Map delete(Map params) {
        println "SalesHead delete"
        Map response
        SalesHead salesHead = SalesHead.get(params.id)
        if(!salesHead) {
            response = [error: "saleshead not found"]
        }
        salesHead.delete(flush: true)
        response = [id: salesHead.id]
        return response
    }



}
