package app.domain.entity

sealed class UserEntity {
    data class UserData(
        val code: Int,
        val meta: MetaData?,
        val data: MutableList<UserItemData>? = null
    ) : UserEntity()

    data class MetaData(val pagination: Pagination?)

    data class Pagination(val total: Int, val pages: Int, val page: Int, val limit: Int)

    data class UserItemData(
        val id: Int?,
        val name: String?,
        val email: String? = null,
        val gender: String? = null,
        val status: String? = null
    )
}
