package com.nsia.officems._identity.authentication.user;

import java.util.List;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    public User create(String userJson) throws Exception;

    public User delete(Long id);

    public List findAll();

    public List<Object> findUsersByPermission(String permission);

    public User findById(Long id);

    public List<User> findByIdIn(List<Long> ids);

    public User findByUsername(String username);

    public List<User> searchByName(String term);

    public boolean update(Long id, String userJson) throws JSONException;

    public void updateAvatar(User user, String avatarFilename);

    public User getLoggedInUser();

    public User getLoggedInUser(Boolean forceFresh);

    public List<Long> getAccessableDepartmentIds(User user);

    public String getSecurityContextHolderUsername(Boolean forceFresh);

    public boolean isAdmin();

    public User updatePreferences(String preferences);

    public boolean updateUserPassword(String currentPassword, String newPassword);

    public User findByEmail(String email);

    public void createPasswordResetTokenForUser(User user, String token, boolean active);

    public boolean validatePasswordResetToken(String token);

    public boolean createNewPassword(String newPassword, String confirmPassword, String token);

    public User uploadSign(Long id, String file);

    public User LoadSign(Long id);

    public User uploadImage(long id, MultipartFile file);

    public boolean hasAuthority(SimpleGrantedAuthority auth);

}
