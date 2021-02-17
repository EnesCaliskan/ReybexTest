package reybextest

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class MaterialController {

    MaterialService materialService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond materialService.list(params), model:[materialCount: materialService.count()]
    }

    def show(Long id) {
        respond materialService.get(id)
    }

    def create() {
        respond new Material(params)
    }

    def save(Material material) {
        if (material == null) {
            notFound()
            return
        }

        try {
            materialService.save(material)
        } catch (ValidationException e) {
            respond material.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'material.label', default: 'Material'), material.id])
                redirect material
            }
            '*' { respond material, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond materialService.get(id)
    }

    def update(Material material) {
        if (material == null) {
            notFound()
            return
        }

        try {
            materialService.save(material)
        } catch (ValidationException e) {
            respond material.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'material.label', default: 'Material'), material.id])
                redirect material
            }
            '*'{ respond material, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        materialService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'material.label', default: 'Material'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'material.label', default: 'Material'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
