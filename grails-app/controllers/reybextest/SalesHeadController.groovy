package reybextest

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SalesHeadController {

    SalesHeadService salesHeadService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond salesHeadService.list(params), model:[salesHeadCount: salesHeadService.count()]
    }

    def show(Long id) {
        respond salesHeadService.get(id)
    }

    def create() {
        respond new SalesHead(params)
    }

    def save(SalesHead salesHead) {
        if (salesHead == null) {
            notFound()
            return
        }

        try {
            salesHeadService.save(salesHead)
        } catch (ValidationException e) {
            respond salesHead.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'salesHead.label', default: 'SalesHead'), salesHead.id])
                redirect salesHead
            }
            '*' { respond salesHead, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond salesHeadService.get(id)
    }

    def update(SalesHead salesHead) {
        if (salesHead == null) {
            notFound()
            return
        }

        try {
            salesHeadService.save(salesHead)
        } catch (ValidationException e) {
            respond salesHead.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'salesHead.label', default: 'SalesHead'), salesHead.id])
                redirect salesHead
            }
            '*'{ respond salesHead, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        salesHeadService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'salesHead.label', default: 'SalesHead'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesHead.label', default: 'SalesHead'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
