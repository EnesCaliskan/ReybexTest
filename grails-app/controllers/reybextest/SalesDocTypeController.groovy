package reybextest

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SalesDocTypeController {

    SalesDocTypeService salesDocTypeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond salesDocTypeService.list(params), model:[salesDocTypeCount: salesDocTypeService.count()]
    }

    def show(Long id) {
        respond salesDocTypeService.get(id)
    }

    def create() {
        respond new SalesDocType(params)
    }

    def save(SalesDocType salesDocType) {
        if (salesDocType == null) {
            notFound()
            return
        }

        try {
            salesDocTypeService.save(salesDocType)
        } catch (ValidationException e) {
            respond salesDocType.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'salesDocType.label', default: 'SalesDocType'), salesDocType.id])
                redirect salesDocType
            }
            '*' { respond salesDocType, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond salesDocTypeService.get(id)
    }

    def update(SalesDocType salesDocType) {
        if (salesDocType == null) {
            notFound()
            return
        }

        try {
            salesDocTypeService.save(salesDocType)
        } catch (ValidationException e) {
            respond salesDocType.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'salesDocType.label', default: 'SalesDocType'), salesDocType.id])
                redirect salesDocType
            }
            '*'{ respond salesDocType, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        salesDocTypeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'salesDocType.label', default: 'SalesDocType'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesDocType.label', default: 'SalesDocType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
