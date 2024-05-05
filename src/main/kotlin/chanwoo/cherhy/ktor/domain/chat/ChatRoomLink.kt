package chanwoo.cherhy.ktor.domain.chat

import chanwoo.cherhy.ktor.domain.customer.Customers
import chanwoo.cherhy.ktor.util.*
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Column

object ChatRoomLinks : BaseLongIdTable("chat_room", "chat_room_id") {
    var customer: Column<EntityID<CustomerId>> = reference("customer_id", Customers)
    var chatRoom: Column<EntityID<ChatRoomId>> = reference("chat_room_id", ChatRooms)
}

class ChatRoomLink(id: EntityID<ChatRoomLinkId>) : BaseEntity(id, ChatRoomLinks) {
    companion object : BaseEntityClass<ChatRoomLink>(ChatRoomLinks)
    var customer by ChatRoomLinks.customer
    var chatRoom by ChatRoomLinks.chatRoom
}