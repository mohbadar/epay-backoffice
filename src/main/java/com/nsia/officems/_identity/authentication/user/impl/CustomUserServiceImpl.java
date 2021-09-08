package com.nsia.officems._identity.authentication.user.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nsia.officems._admin.department.DepartmentService;
import com.nsia.officems._admin.department.Department;
import com.nsia.officems._identity.authentication.group.Group;
import com.nsia.officems._identity.authentication.group.GroupService;
import com.nsia.officems._identity.authentication.permission.Permission;
import com.nsia.officems._identity.authentication.permission.PermissionService;
import com.nsia.officems._identity.authentication.role.Role;
import com.nsia.officems._identity.authentication.user.CustomUser;
import com.nsia.officems._identity.authentication.user.CustomUserService;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems._identity.authentication.user.UserRepository;

@Service
public class CustomUserServiceImpl implements CustomUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private GroupService groupService;

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private DepartmentService departmentService;

	// @Autowired
	// private BCryptPasswordEncoder passwordEncoder;

	// public User findByEmail(String email) {
	// return userRepository.findByEmail(email);
	// }

	@Override
	public CustomUser loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Entry to CustomUserService: " + username);

		return loadUserByUsername(username, null, null);
	}

	@Override
	public CustomUser loadUserByUsername(String username, String currentEnv, String currentLang) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		Collection<Group> userGroups = new ArrayList<>();
		Department entityObj = user.getEntity();
		Department departmentObj = user.getDepartment();
		List<Department> accessableDepartmentObj = user.getAccessibleDepartments();
		String entity = "";
		Long entityId = null;
		String department = "";
		Long departmentId = null;
		
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}

		if (entityObj != null) {
			entity = entityObj.getId().toString();
			entityId = entityObj.getId();
			
		}

		if (departmentObj != null) {
			department = departmentObj.getId().toString();
			departmentId = departmentObj.getId();
		}

		List<Long> accessableDepartments = new ArrayList<>();
		// List<Long> accessableDepartments = departmentService.findDescendantIds(departmentId);
		// if(accessableDepartmentObj != null) {
		// 	for(Department dep: accessableDepartmentObj) {
		// 		accessableDepartments.add(dep.getId());
		// 	}
		// }


		System.out.println("*** ENVIRONMENT ID  ****"+currentEnv);
		// if(currentEnv != null) {
		// Collection<Permission> userPermissions =
		// permissionService.findAllByUserAndEnv(user, currentEnv);
		Collection<Permission> userPermissions = permissionService.findAllByUser(user);
		return new CustomUser(user.getUsername(), user.getPassword(), user.isActive(), true, true, true,
				getGrantedAuthorities(userPermissions), currentEnv, currentLang, entityId, entity, departmentId, department, accessableDepartments);
		// }

		// userGroups = user.getGroups();

		// CustomUser userDetails = new CustomUser(user.getUsername(),
		// user.getPassword(),
		// user.isActive(), true, true, true, getAuthorities(userGroups), currentEnv,
		// currentLang);
		// return userDetails;
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Collection<Group> groups) {
		HashSet<Role> roles = new HashSet<>();
		for (Group group : groups) {
			roles.addAll(group.getRoles());
		}

		// make a hashset to remove the duplicated roles
		// HashSet hs = new HashSet();
		// hs.addAll(roles);
		// roles.clear();
		// roles.addAll(hs);

		return getGrantedAuthorities(getPermissions(roles));
	}

	private List<String> getPermissions(Collection<Role> roles) {
		List<String> privileges = new ArrayList<>();
		List<Permission> collection = new ArrayList<>();
		for (Role role : roles) {
			collection.addAll(role.getPermissions());
		}
		for (Permission item : collection) {
			privileges.add(item.getName());
		}
		return privileges;
	}

	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}

	private List<GrantedAuthority> getGrantedAuthorities(Collection<Permission> permissions) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Permission permission : permissions) {
			authorities.add(new SimpleGrantedAuthority(permission.getName()));
		}
		return authorities;
	}

	public HashSet<Role> getRoles(Collection<Group> groups) {
		HashSet<Role> roles = new HashSet<>();
		for (Group group : groups) {
			roles.addAll(group.getRoles());
		}
		return roles;
	}

	@Override
	public String getCurrentEnv() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null && (authentication.getPrincipal() instanceof CustomUser)) {
			CustomUser userDetails = (CustomUser) authentication.getPrincipal();
			return userDetails.getCurrentEnv();
		}
		return null;
	}

	@Override
	public String getCurrentLang() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null && (authentication.getPrincipal() instanceof CustomUser)) {
			CustomUser userDetails = (CustomUser) authentication.getPrincipal();
			return userDetails.getCurrentLang();
		}
		return null;
	}

	@Override
	public String getEntity() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null && (authentication.getPrincipal() instanceof CustomUser)) {
			CustomUser userDetails = (CustomUser) authentication.getPrincipal();
			return userDetails.getEntity();
		}
		return null;
	}

	@Override
	public Long getEntityId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null && (authentication.getPrincipal() instanceof CustomUser)) {
			CustomUser userDetails = (CustomUser) authentication.getPrincipal();
			return userDetails.getEntityId();
		}
		return null;
	}

	@Override
	public String getDepartment() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null && (authentication.getPrincipal() instanceof CustomUser)) {
			CustomUser userDetails = (CustomUser) authentication.getPrincipal();
			return userDetails.getDepartment();
		}
		return null;
	}

}
