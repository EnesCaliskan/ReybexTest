package reybextest

import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.HttpClient

@Transactional
class CurrencyApiService {

    def show(Map params) {

        //tcmb api = EH2kbv5cab

        String baseUrl = "https://www.tcmb.gov.tr/kurlar/today.xml"
        def client = HttpClient.create(baseUrl.toURL()).toBlocking()

        HttpRequest request = HttpRequest.GET("/api/orders?output_format=JSON&display=full").basicAuth("LCR5YG55WIR86X4JNGS4CYRGKHBDWIBU","")

        HttpResponse<String> resp = client.exchange(request, String)
        client.close()

        String json = resp.body()
        def object = new JsonSlurper().parseText(json)




    }





}
