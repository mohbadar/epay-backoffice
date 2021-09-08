package com.nsia.officems._identity.authentication.user.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.Authentication;

import com.nsia.officems._admin.department.Department;
import com.nsia.officems._admin.department.DepartmentService;
import com.nsia.officems._admin.job.JobService;
import com.nsia.officems._identity.authentication.auth.PasswordResetToken;
import com.nsia.officems._identity.authentication.auth.PasswordResetTokenRepository;
import com.nsia.officems._identity.authentication.group.Group;
import com.nsia.officems._identity.authentication.group.GroupService;
import com.nsia.officems._identity.authentication.user.CustomUser;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems._identity.authentication.user.UserRepository;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.EmailUtil;
import com.nsia.officems._util.FileUploadUtil;
import com.nsia.officems._util.JsonParserUtil;

@Service
public class UserServiceImpl implements UserService {
	@Value("${app.upload.user.sign}")
	private String uploadDir;

	@Value("${app.upload.user}")
	private String uploadDirUser;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FileUploadUtil fileUploadUtil;

	@Autowired
	private UserService userService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private JobService jobService;

	@Autowired
	private EmailUtil emailUtil;

	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;

	@Autowired
	private GroupService groupService;

	@Autowired
	private JsonParserUtil jsonParserUtil;

	public String encodePassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}

	@Override
	public User create(String userJson) throws Exception {
		String defaultPassword = encodePassword("secret");
		User user = new User();

		try {
			if (jsonParserUtil.isValid(userJson)) {
				JSONObject jsonObj = jsonParserUtil.parse(userJson);

				if (jsonObj.has("name"))
					user.setName(jsonObj.isNull("name") ? "" : jsonObj.getString("name"));
				if (jsonObj.has("username"))
					user.setUsername(jsonObj.isNull("username") ? "" : jsonObj.getString("username").trim());
				if (jsonObj.has("phone_no"))
					user.setPhoneNo(jsonObj.isNull("phone_no") ? "" : jsonObj.getString("phone_no"));
				if (jsonObj.has("address"))
					user.setAddress(jsonObj.isNull("address") ? "" : jsonObj.getString("address"));
				if (jsonObj.has("email"))
					user.setEmail(jsonObj.isNull("email") ? "" : jsonObj.getString("email"));
				if (jsonObj.has("active"))
					user.setActive(jsonObj.isNull("active") ? false : jsonObj.getBoolean("active"));
				if(jsonObj.has("reviewerRequired"))
					user.setReviewerRequired(jsonObj.isNull("reviewerRequired") ? true : jsonObj.getBoolean("reviewerRequired"));
				if (jsonObj.has("password")) {
					user.setPassword(jsonObj.isNull("password") ? "" : jsonObj.getString("password").trim());
				}

				if (jsonObj.has("confirm_password"))
					user.setConfirmPassword(
							jsonObj.isNull("confirm_password") ? "" : jsonObj.getString("confirm_password").trim());

				if (jsonObj.has("groups")) {
					if (!jsonObj.isNull("groups")) {
						Set<Group> groups = new HashSet<>();
						JSONArray groupsArray = jsonObj.getJSONArray("groups");

						for (int i = 0; i < groupsArray.length(); i++) {
							groups.add(groupService.findById(groupsArray.getLong(i)));
						}

						user.setGroups(groups);
					}
				}

				if (jsonObj.has("entityId")) {
					user.setEntity(departmentService.findById(jsonObj.getLong("entityId")));
				}

				if (jsonObj.has("departmentId")) {
					if (!jsonObj.isNull("departmentId")) {
						user.setDepartment(departmentService.findById(jsonObj.getLong("departmentId")));
					}
				}

				if (jsonObj.has("accessibleDepartments")) {
					if (!jsonObj.isNull("accessibleDepartments")) {
						List<Department> accessibleDepartments = new ArrayList<Department>();
						JSONArray accessibleDepartmentsArray = jsonObj.getJSONArray("accessibleDepartments");

						for (int i = 0; i < accessibleDepartmentsArray.length(); i++) {
							accessibleDepartments
									.add(departmentService.findById(accessibleDepartmentsArray.getLong(i)));
						}

						user.setAccessibleDepartments(accessibleDepartments);

					}
				}

				if (jsonObj.has("jobId")) {
					if (!jsonObj.isNull("jobId")) {
						user.setJob(jobService.findById(jsonObj.getLong("jobId")));
					}
				}

				if (user.isMatchingPasswords()) {
					String usersEncodedPassword = encodePassword(user.getPassword().trim());
					if (usersEncodedPassword == null) {
						user.setPassword(defaultPassword);
					} else {
						user.setPassword(usersEncodedPassword);
					}

					user = userRepository.save(user);
					return user;
				}
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return null;
	}

	@Override
	public User delete(Long id) {
		User project = findById(id);
		if (project != null) {
			userRepository.delete(project);
		}
		return project;
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	public List<Object> findUsersByPermission(String permission) {
		return userRepository.findUsersByPermission(permission);
	}

	@Override
	public User findById(Long id) {
		Optional<User> optionalObj = userRepository.findById(id);
		User user = optionalObj.get();
		return user;
	}

	@Override
	public User findByUsername(String username) {
		try {
			User user = userRepository.findByUsername(username);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<User> searchByName(String term) {
		return userRepository.searchByName(term);
	}

	@Override
	public boolean update(Long id, String userJson) throws JSONException {
		System.out.println("UserServiceImp > update : " + id);

		if (id != null) {
			User user = findById(id);

			if (jsonParserUtil.isValid(userJson)) {
				JSONObject jsonObj = jsonParserUtil.parse(userJson);
				if (jsonObj.has("name"))
					user.setName(jsonObj.isNull("name") ? "" : jsonObj.getString("name"));
				if (jsonObj.has("username"))
					user.setUsername(jsonObj.isNull("username") ? "" : jsonObj.getString("username").trim());
				if (jsonObj.has("phone_no"))
					user.setPhoneNo(jsonObj.isNull("phone_no") ? "" : jsonObj.getString("phone_no"));
				if (jsonObj.has("address"))
					user.setAddress(jsonObj.isNull("address") ? "" : jsonObj.getString("address"));
				if (jsonObj.has("email"))
					user.setEmail(jsonObj.isNull("email") ? "" : jsonObj.getString("email"));
				if (jsonObj.has("active"))
					user.setActive(jsonObj.isNull("active") ? false : jsonObj.getBoolean("active"));
				if(jsonObj.has("reviewerRequired"))
					user.setReviewerRequired(jsonObj.isNull("reviewerRequired") ? true : jsonObj.getBoolean("reviewerRequired"));
				if (jsonObj.has("groups"))
					if (!jsonObj.isNull("groups")) {
						Set<Group> groups = new HashSet<>();

						JSONArray groupsArray = jsonObj.getJSONArray("groups");
						for (int i = 0; i < groupsArray.length(); i++) {
							groups.add(groupService.findById(groupsArray.getLong(i)));
						}

						user.setGroups(groups);
					}

				if (jsonObj.has("entityId")) {
					user.setEntity(departmentService.findById(jsonObj.getLong("entityId")));
				}

				if (jsonObj.has("departmentId")) {
					if (!jsonObj.isNull("departmentId")) {
						user.setDepartment(departmentService.findById(jsonObj.getLong("departmentId")));
					}
				}

				if (jsonObj.has("accessibleDepartments")) {
					if (!jsonObj.isNull("accessibleDepartments")) {
						List<Department> accessibleDepartments = new ArrayList<Department>();
						JSONArray accessibleDepartmentsArray = jsonObj.getJSONArray("accessibleDepartments");

						for (int i = 0; i < accessibleDepartmentsArray.length(); i++) {
							accessibleDepartments
									.add(departmentService.findById(accessibleDepartmentsArray.getLong(i)));
						}

						user.setAccessibleDepartments(accessibleDepartments);

					}
				}

				if (jsonObj.has("jobId")) {
					if (!jsonObj.isNull("jobId")) {
						user.setJob(jobService.findById(jsonObj.getLong("jobId")));
					}
				}
			}

			userRepository.save(user);
			return true;
		}
		return false;
	}

	@Override
	public void updateAvatar(User user, String avatarFilename) {
		userRepository.updateAvatar(user.getUsername(), avatarFilename);
	}

	@Override
	public User getLoggedInUser() {
		return getLoggedInUser(false);
	}

	@Override
	public User getLoggedInUser(Boolean forceFresh) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = principal.toString();

		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		}

		return findByUsername(username);
	}

	@Override
	public List<Long> getAccessableDepartmentIds(User user) {
		List<Department> extraAccessableDepartmentObj = user.getAccessibleDepartments();

		List<Long> accessableDepartments = departmentService.findDescendantIds(user.getDepartment().getId());
		if(extraAccessableDepartmentObj != null) {
			for(Department dep: extraAccessableDepartmentObj) {
				accessableDepartments.add(dep.getId());
			}
		}
		return accessableDepartments;
	}

	@Override
	public String getSecurityContextHolderUsername(Boolean forceFresh) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = principal.toString();

		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		}

		return username;
	}

	@Override
	public boolean isAdmin() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Collection<? extends GrantedAuthority> auths = ((UserDetails) principal).getAuthorities();
		if (auths.contains(new SimpleGrantedAuthority("ADMIN"))) {
			return true;
		}

		return false;

	}

	@Override
	public User updatePreferences(String preferences) {
		// update the preferenes of currently logged-in user
		User user = getLoggedInUser();
		user.setPreferences(preferences);
		System.out.println("user updated:" + user.toString());
		return userRepository.save(user);

	}

	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public boolean updateUserPassword(String currentPassword, String newpassword) {
		User loggedInUser = getLoggedInUser();

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String newCurrentPassword = bCryptPasswordEncoder.encode(newpassword);

		if (passwordEncoder.matches(currentPassword, loggedInUser.getPassword())) {
			loggedInUser.setPassword(newCurrentPassword);
			userRepository.save(loggedInUser);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void createPasswordResetTokenForUser(User user, String token, boolean active) {
		PasswordResetToken myToken = new PasswordResetToken(token, user, active);
		passwordResetTokenRepository.save(myToken);
	}

	@Override
	public boolean validatePasswordResetToken(String token) {
		PasswordResetToken passToken = passwordResetTokenRepository.findByToken(token);
		if (passToken == null || passToken.getActive() == false) {
			return false;
		}

		LocalDateTime time = LocalDateTime.now();
		if (passToken.getExpiryDate().plusMinutes(30).isBefore(time)) {
			passToken.setActive(false);
			passwordResetTokenRepository.save(passToken);
			return false;
		}

		return true;
	}

	@Override
	public boolean createNewPassword(String newPassword, String confirmPassword, String token) {
		if (newPassword.equals(confirmPassword)) {
			PasswordResetToken userToken = passwordResetTokenRepository.findByToken(token);
			Long userId = userToken.getUser().getId();
			String newPass = encodePassword(newPassword);
			User user = userService.findById(userId);
			user.setPassword(newPass);
			userRepository.save(user);
			userToken.setActive(false);
			passwordResetTokenRepository.save(userToken);
			return true;
		}
		return false;
	}

	public User uploadSign(Long id, String file) {
		Optional<User> user = this.userRepository.findById(id);
		if (user.isPresent()) {
			// String fileName = fileUploadUtil.saveAttachment(file, uploadDir,
			// user.get().getId().toString(), "sign");
			user.get().setSigniture(file);
			return userRepository.save(user.get());
		}

		return null;

	}

	public User LoadSign(Long id) {
		Optional<User> user = this.userRepository.findById(id);
		if (user.isPresent()) {
			// String fileName = fileUploadUtil.saveAttachment(file, uploadDir,
			// user.get().getId().toString(), "sign");
			return user.get();
		}

		return null;
	}
	// Collection<? extends GrantedAuthority> getAuths

	public User uploadImage(long id, MultipartFile file) {
		User user = userService.findById(id);

		if (!user.equals(null)) {
			String fileName = fileUploadUtil.saveAttachment(file, uploadDirUser, user.getId().toString(), "user");
			user.setAvatar(fileName);
			return userRepository.save(user);
		}
		return null;
	}

	@Override
	public boolean hasAuthority(SimpleGrantedAuthority auth) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Collection<? extends GrantedAuthority> auths = ((UserDetails) principal).getAuthorities();
		if (auths.contains(auth)) {
			return true;
		}
		return false;
	}

	@Override
	public List<User> findByIdIn(List<Long> ids) {
		return userRepository.findByIdIn(ids);
	}

}
