package org.example.dulm.global.security.auth

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AuthDetails(
    private val userId: Long,
): UserDetails {

    override fun getUsername(): String = userId.toString()
    override fun getAuthorities(): Collection<out GrantedAuthority> = emptyList()
    override fun getPassword(): String? = null
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true

}