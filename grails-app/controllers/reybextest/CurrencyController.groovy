package reybextest

import grails.converters.JSON
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CurrencyController {

    def CurrencyApiService
    static scaffold = Currency

    def show() {
        def response = CurrencyApiService.show(params)
        render response as JSON
    }






}
