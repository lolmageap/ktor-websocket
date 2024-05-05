package chanwoo.cherhy.ktor.domain.customer

import chanwoo.cherhy.ktor.util.BaseEntity
import chanwoo.cherhy.ktor.util.BaseEntityClass
import chanwoo.cherhy.ktor.util.BaseLongIdTable
import chanwoo.cherhy.ktor.util.CustomerId
import org.jetbrains.exposed.dao.id.EntityID

object Customers: BaseLongIdTable("customer", "id") {
    val name = varchar("name", 50)
    val email = varchar("email", 50)
    val password = varchar("password", 50)
    val phoneNumber = varchar("phone_number", 50).nullable()
}

class Customer(id: EntityID<CustomerId>): BaseEntity(
    id = id,
    table = Customers,
) {
    var name: String by Customers.name
    var email: String by Customers.email
    var password : String by Customers.password
    var phoneNumber: String? by Customers.phoneNumber

    companion object: BaseEntityClass<Customer>(Customers)
}