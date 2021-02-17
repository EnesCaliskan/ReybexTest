package reybextest

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SalesItemController {

    SalesItemService salesItemService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond salesItemService.list(params), model:[salesItemCount: salesItemService.count()]
    }

    def show(Long id) {
        respond salesItemService.get(id)
    }

    def create() {
        respond new SalesItem(params)
    }

    def save(SalesItem salesItem) {
        if (salesItem == null) {
            notFound()
            return
        }

        try {
            salesItemService.save(salesItem)
        } catch (ValidationException e) {
            respond salesItem.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'salesItem.label', default: 'SalesItem'), salesItem.id])
                redirect salesItem
            }
            '*' { respond salesItem, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond salesItemService.get(id)
    }

    def update(SalesItem salesItem) {
        if (salesItem == null) {
            notFound()
            return
        }

        try {
            salesItemService.save(salesItem)
        } catch (ValidationException e) {
            respond salesItem.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'salesItem.label', default: 'SalesItem'), salesItem.id])
                redirect salesItem
            }
            '*'{ respond salesItem, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        salesItemService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'salesItem.label', default: 'SalesItem'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesItem.label', default: 'SalesItem'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
