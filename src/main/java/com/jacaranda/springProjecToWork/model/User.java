package com.jacaranda.springProjecToWork.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User implements UserDetails{
	
	@Id
	private String user;
	private String password;
	private String role;
	private String verificationCode;
	private Boolean enabled;
	private String email;

	public User(String user, String password, String role, String verificationCode, Boolean enabled, String email) {
		super();
		this.user = user;
		this.password = password;
		this.role = role;
		this.verificationCode = verificationCode;
		this.enabled = enabled;
		this.email = email;
	}
	
	public User() {
		super();
	}
	
	

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, enabled, password, role, user, verificationCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(enabled, other.enabled)
				&& Objects.equals(password, other.password) && Objects.equals(role, other.role)
				&& Objects.equals(user, other.user) && Objects.equals(verificationCode, other.verificationCode);
	}

	@Override
	public String toString() {
		return "User [user=" + user + ", password=" + password + ", role=" + role + ", verificationCode="
				+ verificationCode + ", enabled=" + enabled + ", email=" + email + "]";
	}

	
	/* ------------------------- */
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 String role = this.getRole();
		 List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		 authorities.add(new SimpleGrantedAuthority(this.role));

		 return authorities;

	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.user;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.enabled;
	}

}
