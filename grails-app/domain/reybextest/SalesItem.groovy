package reybextest

class SalesItem {

    Integer id
    Material material
    String shortDescription
    Float netPrice
    Float grossPrice
    Float vatRate
    Float quantity
    Float discount
    static belongsTo = [salesHead: SalesHead]

    static constraints = {
    }
}
