package reybextest

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CurrencyController {

    def CurrencyApiService
    static scaffold = Currency

    def show() {
        def response = CurrencyApiService
        println response
    }






}
