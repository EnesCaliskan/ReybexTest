package reybextest

import grails.gorm.transactions.Transactional

@Transactional
class MaterialApiService {

    def show(Map params) {
        def response
        if(params.id){
            response = Material.read(params.id?.toInteger())
            if(!response) {
                response = [message: "Material not found"]
            }
        } else {
            response = Material.list([readOnly:true])
        }
        return response
    }

    def save(Map params) {
        def response
        Material material
        if(params.id) {
            material = Material.get(params.id)
            if(!material) {
                return [error: "material not found"]
            }
        } else {
            material = new Material()
        }

        material.properties = params

        if (!material.save(flush: true)) {
            String msg = ""
            material.errors.each {
                msg += it
                println it
            }
            response = [error: msg]
        }
        println material
        response = [id: material.id]
        return response
    }

    Map delete(Map params) {
        println "service delete"
        Map response
        Material material = Material.get(params.id)
        if(!material) {
            response = [error: "material not found"]
        }
        material.delete(flush: true)
        response = [id: material.id]
        return response
    }





}
