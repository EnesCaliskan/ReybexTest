package reybextest

class Orders {

    Integer id
    String product_id
    String id_cart
    String id_address_delivery
    String id_customer
    String id_currency
    static hasMany = [products: Products]

    static mapping = {

    }

    static constraints = {
        id_cart(nullable: true)
    }

}
