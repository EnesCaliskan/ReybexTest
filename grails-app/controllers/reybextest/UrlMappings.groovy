package reybextest

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/api/customer/$id?"(controller: "customer") { action = [GET: "show", POST: "save", DELETE: "delete"] }
        "/api/material/$id?"(controller: "material") { action = [GET: "show", POST: "save", DELETE: "delete"] }
        "/api/salesHead/currency"(controller: "salesHead") { action = [GET: "currency"] }
        "/api/salesHead/tester"(controller: "salesHead") { action = [GET: "tester"] }
        "/api/salesHead/shop"(controller: "salesHead") { action = [GET: "shop"] }
        "/api/salesHead/online"(controller: "salesHead") { action = [GET: "online"] }
        "/api/material/prestashop/$id?"(controller: "material") { action = [GET: "product", POST: "test"] }
        "/api/salesHead/products"(controller: "salesHead") { action = [GET: "products"] }
        "/api/salesHead/orders"(controller: "salesHead") { action = [GET: "uOrders"] }
        "/api/salesDocType/$id?"(controller: "salesDocType") { action = [GET: "show", POST: "save", DELETE: "delete"] }
        "/api/salesHead/$id?"(controller: "salesHead") { action = [GET: "show", POST: "save", DELETE: "delete"] }
        "/api/salesItem/$id?"(controller: "salesItem") { action = [GET: "show", POST: "save", DELETE: "delete"] }


        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
