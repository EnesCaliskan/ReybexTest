package reybextest

class Orders {

    Integer id
    String product_id
    String product_name
    String product_price
    String unit_price_tax_incl

    static constraints = {
        product_id(nullable: true)
        product_name(nullable: true)
        product_price(nullable: true)
        unit_price_tax_incl(nullable: true)
    }
}
