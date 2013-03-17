package com.dvlcube.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * 
 * @author wonka
 * @since 17/03/2013
 */
public class CubeUserDetails extends User {
	private static final long serialVersionUID = 4771731045224743162L;

	private Long idUsuario;

	private String nome;

	private String resumo;

	private String salt;

	public CubeUserDetails(Long idUsuario, String nome, String resumo, String salt, String email,
			String username, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities)
			throws IllegalArgumentException {
		super(username, resumo, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
				authorities);

		this.idUsuario = idUsuario;
		this.nome = nome;
		this.resumo = resumo;
		this.salt = salt;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public String getResumo() {
		return resumo;
	}

	public String getSalt() {
		return salt;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
}
