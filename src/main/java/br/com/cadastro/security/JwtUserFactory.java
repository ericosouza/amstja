package br.com.cadastro.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.com.cadastro.model.Usuario;

public class JwtUserFactory {

	private JwtUserFactory() {
	}

	/**
	 * Converte e gera um JwtUser com base nos dados de um funcionário.
	 *
	 * @param usuario
	 * @return JwtUser
	 */
	public static JwtUser create(Usuario usuario) {
		Long id = (long) usuario.getId().getCounter();
		return new JwtUser(id, usuario.getEmail(), usuario.getSenha(), JwtUserFactory.mapToGrantedAuthorities(usuario.getPerfil()));
	}

	/**
	 * Converte o perfil do usuário para o formato utilizado pelo Spring
	 * Security.
	 *
	 * @param perfilEnum
	 * @return List<GrantedAuthority>
	 */
	private static List<GrantedAuthority> mapToGrantedAuthorities(String perfilEnum) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(perfilEnum.toString()));
		return authorities;
	}
}
