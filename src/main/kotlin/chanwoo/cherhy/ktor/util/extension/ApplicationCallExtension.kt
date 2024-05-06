package chanwoo.cherhy.ktor.util.extension

import chanwoo.cherhy.ktor.util.ChatRoomId
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import java.nio.file.AccessDeniedException

val mapper = jacksonObjectMapper()

val ApplicationCall.id: Long
    get() = this.parameters["id"]?.toLongOrNull()
        ?: throw IllegalArgumentException("Invalid entity id")

val ApplicationCall.jwt: JWTPrincipal
    get() = this.principal<JWTPrincipal>()
        ?: throw AccessDeniedException("Invalid token")

val ApplicationCall.chatRoomId: ChatRoomId
    get() = this.parameters["room-id"]
        ?.toLong()
        ?: throw IllegalArgumentException("room-id is required.")

inline fun <reified T : Any> ApplicationCall.getQueryParams(): T {
    return this.request.queryParameters.toClass()
}

inline fun <reified T : Any> Parameters.toClass(): T {
    val map = entries().associate {
        it.key to (it.value.getOrNull(0)
            ?: throw IllegalArgumentException("Missing value for key ${it.key}"))
    }
    return mapper.convertValue(map, T::class.java)
}