package reybextest

class Orders {

    Integer id
    String id_cart

    static constraints = {
        id()
        id_cart(nullable: true)
    }

}
