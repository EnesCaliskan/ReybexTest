package reybextest

import grails.converters.JSON
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CustomerController {

    def csId = request.JSON

    def customerApiService

    static scaffold = Customer

//    def index(JSON csID) {
//        def customers = Customer.list() as JSON
//        render customers
//
//        if (customers.id == csID) {
//            render Customer.findById(csId.toString().toInteger())
//        }
//
//    }

    def show(){
        def response = customerApiService.show(params)
        render response as JSON
    }

    def save(){
        def response = customerApiService.save(request.JSON)
        render response as JSON
    }

    def delete(){
        println "delete"
        def response = customerApiService.delete(params)
        render response as JSON
    }







}
