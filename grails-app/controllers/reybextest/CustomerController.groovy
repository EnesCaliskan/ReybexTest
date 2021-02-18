package reybextest

import grails.converters.JSON
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CustomerController {

    def customerApiService

    static scaffold = Customer


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
