package reybextest

class BootStrap {

    def init = { servletContext ->
        new Customer(id: 1,customerNo: 1,name: "Enes", mail: "enes@gmail.com",city: "Sakarya",address: "Sakarya",phone: "12345").save()
        new Customer(id: 2,customerNo: 2,name: "Ali", mail: "ali@gmail.com",city: "Sakarya",address: "Sakarya",phone: "12345").save()
        new Customer(id: 3,customerNo: 3,name: "Veli", mail: "veli@gmail.com",city: "Sakarya",address: "Sakarya",phone: "12345").save()
        new Customer(id: 4,customerNo: 4,name: "Talha", mail: "talha@gmail.com",city: "Sakarya",address: "Sakarya",phone: "12345").save()

    }
    def destroy = {
    }
}
