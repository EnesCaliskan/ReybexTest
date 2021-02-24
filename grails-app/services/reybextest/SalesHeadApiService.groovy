package reybextest

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import groovy.json.JsonBuilder
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

    def products(Map params) {

        // url objects --> kendi objem
        String baseUrl = "http://prestashop.p409543.webspaceconfig.de"
        def client = HttpClient.create(baseUrl.toURL()).toBlocking()

        HttpRequest request = HttpRequest.GET("/api/orders?output_format=JSON&display=full").basicAuth("LCR5YG55WIR86X4JNGS4CYRGKHBDWIBU","")

        HttpResponse<String> resp = client.exchange(request, String)
        client.close()

        String json = resp.body()
        def object = new JsonSlurper().parseText(json)
        //*----*

        Products oProducts
        object.orders.getAt(0).associations.order_rows.each {
            if(!(Products.findAllByProduct_id(it.product_id))) {
                oProducts = new Products()
                    oProducts.product_id = it.product_id
                        oProducts.product_name = it.product_name
                        oProducts.product_price = it.product_price
                    oProducts.unit_price_tax_incl = it.unit_price_tax_incl
                oProducts.save(flush:true)
            }
            else {
                println "Product already exists"
            }
        }

        def response =  Products.list()
        return response

    }

    def uOrders(Map params) {

        // url objects --> kendi objem
        String baseUrl = "http://prestashop.p409543.webspaceconfig.de"
        def client = HttpClient.create(baseUrl.toURL()).toBlocking()

        HttpRequest request = HttpRequest.GET("/api/orders?output_format=JSON&display=full").basicAuth("LCR5YG55WIR86X4JNGS4CYRGKHBDWIBU","")

        HttpResponse<String> resp = client.exchange(request, String)
        client.close()

        String json = resp.body()
        def object = new JsonSlurper().parseText(json)

        //*----*

        Orders pOrders
        object.orders.each {
            if (!(Orders.findAllById_cart(it.id_cart))) {
                pOrders = new Orders()
                pOrders.id_cart = it.id_cart
                    pOrders.id_address_delivery = it.id_address_delivery
                        pOrders.id_customer = it.id_customer
                    pOrders.id_currency = it.id_currency
                pOrders.save(flush:true)
            }
            else {
                println "This order already exists"
            }
        }

        def response = Orders.list()
        return response

    }

    def currency(Map params) {

        def slurper = new XmlSlurper().parse("C:\\asd\\new.xml")

        Currency currency
        slurper.items.each{
             if(!(Currency.findAllByDate(it.'Tarih'))) {

                 currency = new Currency()
                    currency.date = it.'Tarih'
                        currency.usd = it.'TP_DK_USD_A'
                        currency.euro = it.'TP_DK_EUR_A'
                        currency.chf = it.'TP_DK_CHF_A'
                    currency.gbp = it.'TP_DK_GBP_A'
                 currency.jpy = it.'TP_DK_JPY_A'

                 currency.save(flush: true)
             }
            else {println 'this currency already exists'}
        }

        def response = Currency.list()
        return response

    }

}



