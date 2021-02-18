package reybextest

import grails.converters.JSON
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SalesItemController {

    def salesItemApiService
    static scaffold = SalesItem

    def show() {
        def response = salesItemApiService.show(params)
        render response as JSON
    }

    def save() {
        def response = salesItemApiService.save(request.JSON)
        render response as JSON
    }

    def delete() {
        def response = salesItemApiService.delete(params)
        render response as JSON
    }


}
