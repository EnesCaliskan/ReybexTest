package reybextest

import grails.converters.JSON

class SalesDocTypeController {

    def salesDocTypeApiService
    static scaffold = SalesDocType

    def show() {
        def response = salesDocTypeApiService.show(params)
        render response as JSON
    }

    def save() {
        def response = salesDocTypeApiService.save(request.JSON)
        render "saved"
        render response as JSON
    }

    def delete() {
        def response = salesDocTypeApiService.delete(params)
        render response as JSON
    }



}
