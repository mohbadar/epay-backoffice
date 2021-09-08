package com.nsia.officems._identity.authentication.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CustomUserService extends UserDetailsService {

	// User findByEmail(String email);

	public CustomUser loadUserByUsername(String username);

	// public CustomUser loadUserByUsername(String username, String currentEnv, String currentLang);
	public CustomUser loadUserByUsername(String username, String currentEnv, String currentLang);

	public String getCurrentEnv();
	public String getCurrentLang();
	public String getEntity();
	public Long getEntityId();
	public String getDepartment();
}
