package reybextest

class SalesDocType {

    Integer id
    String prefix
    String name

    static constraints = {
        prefix(maxSize: 3)
        name(nullable: false)
    }
}
