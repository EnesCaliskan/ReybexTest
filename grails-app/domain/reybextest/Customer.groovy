package reybextest

class Customer {

    Integer id
    String customerNo
    String name
    String mail
    String city
    String address
    String phone

    static constraints = {
        phone(nullable: true)
    }
}
