package reybextest

import grails.converters.JSON
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class MaterialController {

    def materialApiService
    static scaffold = Material

    def show() {
        def response = materialApiService.show(params)
        render response as JSON
    }

    def save() {
        def response = materialApiService.save(request.JSON)
        render "saved"
        render response as JSON
    }

    def delete() {
        def response = materialApiService.delete(params)
        render response as JSON
    }







}
