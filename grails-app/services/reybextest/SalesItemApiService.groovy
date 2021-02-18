package reybextest

import grails.gorm.transactions.Transactional

@Transactional
class SalesItemApiService {

    def show(Map params) {
        def response
        if(params.id){
            response = SalesItem.read(params.id?.toInteger())
            if(!response) {
                response = [message: "SalesItem not found"]
            }
        } else {
            response = SalesItem.list([readOnly:true])
        }
        return response
    }

    def save(Map params) {
        def response
        SalesItem salesItem
        if(params.id) {
            salesItem = SalesItem.get(params.id)
            if(!salesItem) {
                return [error: "SalesItem not found"]
            }
        } else {
            salesItem = new SalesItem()
        }

        salesItem.properties = params

        if (!salesItem.save(flush: true)) {
            String msg = ""
            salesItem.errors.each {
                msg += it
                println it
            }
            response = [error: msg]
        }
        println salesItem
        response = [id: salesItem.id]
        return response
    }

    Map delete(Map params) {
        println "SalesItem delete"
        Map response
        SalesItem salesItem = SalesItem.get(params.id)
        if(!salesItem) {
            response = [error: "salesItem not found"]
        }
        salesItem.delete(flush: true)
        response = [id: salesItem.id]
        return response
    }




}
