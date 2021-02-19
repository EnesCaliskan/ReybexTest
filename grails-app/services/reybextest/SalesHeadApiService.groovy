package reybextest

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.HttpClient

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


    def orders(Map params) {

        // url objects --> kendi objem
        String baseUrl = "http://prestashop.p409543.webspaceconfig.de"
        def client = HttpClient.create(baseUrl.toURL()).toBlocking()

        HttpRequest request = HttpRequest.GET("/api/orders?output_format=JSON&display=full").basicAuth("LCR5YG55WIR86X4JNGS4CYRGKHBDWIBU","")

        HttpResponse<String> resp = client.exchange(request, String)
        client.close()

        String json = resp.body()
        def object = new JsonSlurper().parseText(json)
        //*----*

        //return [name:  object.orders.getAt(0).associations.order_rows.getAt(0)]

        object.orders.getAt(0).associations.order_rows.each {
            Orders orders = new Orders()
            orders.product_id = it.product_id
            orders.product_name = it.product_name
            orders.product_price = it.product_price
            orders.unit_price_tax_incl = it.unit_price_tax_incl
            orders.save(flush:true)
            println orders
        }

        def response =  Orders.list()
        return response

    }


}
