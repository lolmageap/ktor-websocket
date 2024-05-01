package chanwoo.cherhy.ktor.util

data class PageRequest(
    val page: Int,
    val size: Int,
) {
    val offset = (page - 1) * size.toLong()
}