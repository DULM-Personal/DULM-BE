package org.example.dulm.domain.room

import org.example.dulm.domain.room.enum.RoomRole

data class RoomMember(
    val id: Long? = null,
    val userId: Long,
    val roomId: Long,
    val role: RoomRole = RoomRole.MEMBER
)