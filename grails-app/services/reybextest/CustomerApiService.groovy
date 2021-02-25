package reybextest

import grails.gorm.transactions.Transactional

@Transactional
class CustomerApiService {

    def show(Map params) {
        def response
        if(params.id){
            response = Customer.read(params.id?.toInteger())
            if(!response) {
                response = [message: "Customer not found"]
            }
        }
        else {
            response = Customer.list([readOnly:true])
        }
        return response
    }

    def save(Map params) {
        def response
        Customer customer
        if(params.id) {
            customer = Customer.get(params.id)
            if(!customer) {
                return [error: "customer not found"]
            }
        } else {
            customer = new Customer()
        }

        customer.properties = params

        if (!customer.save(flush: true)) {
            String msg = ""
            customer.errors.each {
                msg += it
                println it
            }
            response = [error: msg]
        }
        println customer
        response = [id: customer.id]
        return response
    }

    def delete(Map params) {
        println "service delete"
        Map response
        Customer customer = Customer.get(params.id)
        if(!customer) {
            response = [error: "customer not found"]
        }
        customer.isDelete = 1
        customer.delete(flush: true)
        response = [id: customer.id]
        return response
    }




}
