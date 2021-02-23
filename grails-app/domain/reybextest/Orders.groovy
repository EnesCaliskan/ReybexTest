package reybextest

class Orders {

    Integer id
    String id_cart
    String id_address_delivery
    String id_customer
    String id_currency

    static constraints = {
        id_cart(nullable: true)
    }

}
