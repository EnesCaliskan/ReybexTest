package reybextest

class Material {

    Integer id
    String matCode
    String sku
    String name
    String description
    String netPrice
    Float grossPrice
    Float vatRate = 18
    Float stock = 0

    static constraints = {
        name(nullable: true)
        description(nullable: true)
        netPrice(nullable: true)
        stock(nullable: true)
        sku(nullable: true)
        grossPrice(nullable: true)
        vatRate(nullable: true)
        matCode(nullable: true)
    }
}


