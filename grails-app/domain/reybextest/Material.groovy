package reybextest

class Material {

    Integer id
    String matCode
    String sku
    String name
    String description
    Float netPrice
    Float grossPrice
    Float vatRate = 18
    Float stock = 0

    static constraints = {
    }
}
