package reybextest

class SalesHead {

    Integer id
    SalesDocType salesDocType
    String docNumber
    Date docDate
    Customer customer
    static hasMany = [items : SalesItem]

    static mapping = {
        items cascade:"all-delete-orphan"
    }


    static constraints = {

        docDate nullable: false
        salesDocType(nullable: false)
        docNumber(nullable: false)

    }
}
