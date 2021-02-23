package reybextest

import grails.converters.JSON
import grails.validation.ValidationException
import groovy.json.JsonSlurper
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.HttpClient

import static org.springframework.http.HttpStatus.*

class SalesHeadController {

    def salesHeadApiService
    static scaffold = SalesHead

    def show() {
        def response = salesHeadApiService.show(params)
        render response as JSON
    }

    def save() {
        def response = salesHeadApiService.save(request.JSON)
        render "saved"
        render response as JSON
    }

    def delete() {
        def response = salesHeadApiService.delete(params)
        render response as JSON
    }

    def products() {
        def response = salesHeadApiService.products(request.JSON)
        render response as JSON
    }



}
