package reybextest

import com.fasterxml.jackson.databind.ObjectMapper
import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.HttpClient

import javax.naming.directory.SearchResult

@Transactional
class MaterialApiService {

    def show(Map params) {
        def response
        if(params.id){
            response = Material.read(params.id?.toInteger())
            if(!response) {
                response = [message: "Material not found"]
            }
        } else {
            response = Material.list([readOnly:true])
        }
        return response
    }

    def save(Map params) {
        def response
        Material material
        if(params.id) {
            material = Material.get(params.id)
            if(!material) {
                return [error: "material not found"]
            }
        } else {
            material = new Material()
        }

        material.properties = params

        if (!material.save(flush: true)) {
            String msg = ""
            material.errors.each {
                msg += it
                println it
            }
            response = [error: msg]
        }
        println material
        response = [id: material.id]
        return response
    }

    def delete(Map params) {
        println "service delete"
        Map response
        Material material = Material.get(params.id)
        if(!material) {
            response = [error: "material not found"]
        }
        material.delete(flush: true)
        response = [id: material.id]
        return response
    }

    def product(Map params) {

        // url objects --> kendi objem
        String baseUrl = "http://prestashop.p409543.webspaceconfig.de"
        def client = HttpClient.create(baseUrl.toURL()).toBlocking()

        HttpRequest request = HttpRequest.GET("/api/products?output_format=JSON&display=full").basicAuth("LCR5YG55WIR86X4JNGS4CYRGKHBDWIBU","")

        HttpResponse<String> resp = client.exchange(request, String)
        client.close()

        String json = resp.body()
        def object = new JsonSlurper().parseText(json)
        //*----*

//        println object.products.getAt(0).id
//        println object.products.getAt(0)

//        object.products.each{
//            Material material = new Material()
//            //println it.name
//            material.name = it.name
//            material.stock = it.stock_availables
//            material.netPrice = it.price
//            material.save(flush: true)
//            println material

        //println object

        Material material
        object.products.each{
            material = new Material()
            material.name = it.name.value.getAt(0)
            material.netPrice = it.price
            material.save(flush:false)

            println material.netPrice
        }

        def response = Material.list()
        return response

    }

}
