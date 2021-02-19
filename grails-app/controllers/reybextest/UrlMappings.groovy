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
        "/api/material/prestashop"(controller: "material") { action = [GET: "product"] }
        "/api/salesHead/orders"(controller: "salesHead") { action = [GET: "orders"] }
        "/api/salesDocType/$id?"(controller: "salesDocType") { action = [GET: "show", POST: "save", DELETE: "delete"] }
        "/api/salesHead/$id?"(controller: "salesHead") { action = [GET: "show", POST: "save", DELETE: "delete"] }
        "/api/salesItem/$id?"(controller: "salesItem") { action = [GET: "show", POST: "save", DELETE: "delete"] }


        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
