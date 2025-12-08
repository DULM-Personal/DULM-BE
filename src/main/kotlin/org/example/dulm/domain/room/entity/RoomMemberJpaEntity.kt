package org.example.dulm.domain.room.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.example.dulm.domain.room.RoomMember
import org.example.dulm.domain.room.enum.RoomRole
import org.example.dulm.domain.user.entity.UserJpaEntity

@Entity
@Table(name = "room_member")
class RoomMemberJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: UserJpaEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    val room: RoomJpaEntity,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val role: RoomRole
) {
    fun toDomain() = RoomMember(
        id = id,
        userId = user.id!!,
        roomId = room.id!!,
        role = role
    )

    companion object {
        fun from(domain: RoomMember, user: UserJpaEntity, room: RoomJpaEntity) =
            RoomMemberJpaEntity(
                id = domain.id,
                user = user,
                room = room,
                role = domain.role
            )
    }
}
