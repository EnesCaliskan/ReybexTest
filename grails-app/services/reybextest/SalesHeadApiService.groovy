package reybextest

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import groovy.util.slurpersupport.GPathResult
import groovy.xml.DOMBuilder
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.HttpClient

import javax.xml.parsers.DocumentBuilderFactory

@Transactional
class SalesHeadApiService {

    def show(Map params) {
        def response
        if (params.id) {
            response = SalesHead.read(params.id?.toInteger())
            if (!response) {
                response = [message: "SalesHead not found"]
            }
        } else {
            response = SalesHead.list([readOnly: true])
        }
        return response
    }

    def save(Map params) {
        def response
        SalesHead salesHead
        if (params.id) {
            salesHead = SalesHead.get(params.id)
            if (!salesHead) {
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
        if (!salesHead) {
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

        HttpRequest request = HttpRequest.GET("/api/orders?output_format=JSON&display=full").basicAuth("LCR5YG55WIR86X4JNGS4CYRGKHBDWIBU", "")

        HttpResponse<String> resp = client.exchange(request, String)
        client.close()

        String json = resp.body()
        def object = new JsonSlurper().parseText(json)
        //*----*

        Products oProducts
        object.orders.getAt(0).associations.order_rows.each {
            if (!(Products.findAllByProduct_id(it.product_id))) {
                oProducts = new Products()
                oProducts.product_id = it.product_id
                oProducts.product_name = it.product_name
                oProducts.product_price = it.product_price
                oProducts.unit_price_tax_incl = it.unit_price_tax_incl
                oProducts.save(flush: true)
            } else {
                println "Product already exists"
            }
        }

        def response = Products.list()
        return response

    }

    def uOrders(Map params) {

        // url objects --> kendi objem
        String baseUrl = "http://prestashop.p409543.webspaceconfig.de"
        def client = HttpClient.create(baseUrl.toURL()).toBlocking()

        HttpRequest request = HttpRequest.GET("/api/orders?output_format=JSON&display=full").basicAuth("LCR5YG55WIR86X4JNGS4CYRGKHBDWIBU", "")

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
                pOrders.save(flush: true)
            } else {
                println "This order already exists"
            }
        }

        def response = Orders.list()
        return response

    }

    def currency(Map params) {

        def slurper = new XmlSlurper().parse("C:\\asd\\new.xml")

        Currency currency
        slurper.items.each {
            if (!(Currency.findAllByDate(it.'Tarih'))) {

                currency = new Currency()
                currency.date = it.'Tarih'
                currency.usd = it.'TP_DK_USD_A'
                currency.euro = it.'TP_DK_EUR_A'
                currency.chf = it.'TP_DK_CHF_A'
                currency.gbp = it.'TP_DK_GBP_A'
                currency.jpy = it.'TP_DK_JPY_A'

                currency.save(flush: true)
            } else {
                println 'this currency already exists'
            }
        }

        def response = Currency.list()
        return response

    }

    def tester(Map params) {

        String baseUrl = "https://evds2.tcmb.gov.tr/service/evds/series=TP.DK.USD.A-TP.DK.EUR.A-TP.DK.CHF.A-TP.DK.GBP.A-TP.DK.JPY.A&startDate=01-10-2017&endDate=01-11-2017&type=xml&key=EH2kbv5cab"
        def client = HttpClient.create(baseUrl.toURL()).toBlocking()

        HttpRequest request = HttpRequest.GET(baseUrl)

        HttpResponse<String> resp = client.exchange(request, String)
        client.close()

        String xml = resp.body()
        def object = new XmlSlurper().parseText(xml)

        //println object.items[2].TP_DK_USD_A

        Currency currency
        object.items.each {
            if (!(Currency.findAllByDate(it.'Tarih'))) {
                if (!(it.'TP_DK_USD_A' == "")) {
                    currency = new Currency()

                    currency.date = it.'Tarih'
                    currency.usd = it.'TP_DK_USD_A'
                    currency.euro = it.'TP_DK_EUR_A'
                    currency.chf = it.'TP_DK_CHF_A'
                    currency.gbp = it.'TP_DK_GBP_A'
                    currency.jpy = it.'TP_DK_JPY_A'

                    currency.save(flush: true)
                } else {
                    println "hafta sonu"
                }
            } else {
                println 'this currency already exists'
            }
        }

        def euroChange = Currency.last().euro.toFloat() - Currency.first().euro.toFloat()
        def usdChange = Currency.last().usd.toFloat() - Currency.first().usd.toFloat()
        def chfChange = Currency.last().chf.toFloat() - Currency.first().chf.toFloat()
        def jpyChange = Currency.last().jpy.toFloat() - Currency.first().jpy.toFloat()
        def gbpChange = Currency.last().gbp.toFloat() - Currency.first().gbp.toFloat()


        println "Change rate in euro = " + euroChange
        println "Change rate in usd = " + usdChange
        println "Change rate in chf = " + chfChange
        println "Change rate in jpy = " + jpyChange
        println "Change rate in gbp = " + gbpChange


        def response = Currency.list()
        return response

    }

    def shop(Map params) {
        GPathResult dd
        try {
           dd = new XmlSlurper().parseText(new File("C:\\Users\\mrnew\\Desktop\\response.xml").text)
        }catch(Exception ee){
            println "hata"
            println ee.printStackTrace()
        }

        def parsedText
        def newList = []

        dd.urun.each{
            parsedText = [
                    "isMarketplaceActive" : it.urun_aktif.text(),
                    "marketplaceProductId" : it.urun_ID.text(),
                    "name" : it.urun_ad.text(),
                    "urun_ana_kategori_ad" : it.urun_ana_kategori_ad.text(),
                    "urun_ust_kategori_ad" : it.urun_ust_kategori_ad.text(),
                    "manufacturer" : it.urun_marka_ad.text(),
                    "description" : it.urun_aciklama.text(),
                    "urun_ozellikler" : it.urun_ozellikler.text(),
                    "images" : [
                            "filename" : "",
                            "url": it.urun_resim1.text(),
                            "isMainPicture" : ""
                    ],
                    "salesPrice" : it.urun_fiyat_site.text(),
                    "currency" : it.urun_doviz.text(),
                    "vatRate" : it.urun_kdv.text(),
                    "stock" : it.urun_stok.text(),
                    "gtin" : it.urun_gtin.text(),
                    "variants" : it.urun_renk.text(),
                    "grossWeight" : it.urun_kargo_desi.text()
            ]
            newList.add(parsedText)
        }
        return newList
    }

    def online(Map params) {

        def xml = new XmlSlurper().parse("http://www.alisverispaketim.net/xml.php?c=shopphp&xmlc=25eeeb8ee3&catID=799")
        def parsedText
        def newList = []

        xml.urun.each{
            parsedText = [
                    "isMarketplaceActive" : it.urun_aktif.text(),
                    "marketplaceProductId" : it.urun_ID.text(),
                    "name" : it.urun_ad.text(),
                    "urun_ana_kategori_ad" : it.urun_ana_kategori_ad.text(),
                    "urun_ust_kategori_ad" : it.urun_ust_kategori_ad.text(),
                    "manufacturer" : it.urun_marka_ad.text(),
                    "description" : it.urun_aciklama.text(),
                    "urun_ozellikler" : it.urun_ozellikler.text(),
                    "images" : [
                            "filename" : "",
                            "url": it.urun_resim1.text(),
                            "isMainPicture" : ""
                    ],
                    "salesPrice" : it.urun_fiyat_site.text(),
                    "currency" : it.urun_doviz.text(),
                    "vatRate" : it.urun_kdv.text(),
                    "stock" : it.urun_stok.text(),
                    "gtin" : it.urun_gtin.text(),
                    "variants" : it.urun_renk.text(),
                    "grossWeight" : it.urun_kargo_desi.text()
            ]
            newList.add(parsedText)
        }
        return newList
    }


}



