package chanwoo.cherhy.ktor.domain.chat

import chanwoo.cherhy.ktor.domain.customer.Customers
import chanwoo.cherhy.ktor.util.model.BaseEntity
import chanwoo.cherhy.ktor.util.model.BaseEntityClass
import chanwoo.cherhy.ktor.util.model.BaseLongIdTable
import org.jetbrains.exposed.dao.id.EntityID

object ChatRoomLinks : BaseLongIdTable("chat_room", "chat_room_id") {
    var customer = reference("customer_id", Customers)
    var chatRoom = reference("chat_room_id", ChatRooms)
}

class ChatRoomLink(
    id: EntityID<ChatRoomLinkId>,
) : BaseEntity(
    id = id.unwrap(),
    table = ChatRoomLinks,
) {
    companion object : BaseEntityClass<ChatRoomLink>(ChatRoomLinks)
    var customer by ChatRoomLinks.customer
    var chatRoom by ChatRoomLinks.chatRoom
}

@JvmInline
value class ChatRoomLinkId(
    val value: Long,
): Comparable<ChatRoomLinkId> {
    override fun compareTo(
        other: ChatRoomLinkId,
    ) = value.compareTo(other.value)
}

private fun EntityID<ChatRoomLinkId>.unwrap() = EntityID(value.value, ChatRoomLinks)