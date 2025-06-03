package com.example.demo.service.service_implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.coyote.RequestInfo;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Doctor;
import com.example.demo.domain.Role;
import com.example.demo.domain.Supporter;
import com.example.demo.domain.User;
import com.example.demo.domain.User_Role;
import com.example.demo.dto.user.UserInformationDTO;
import com.example.demo.dto.user.UserRoleDTO;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.SupporterRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.IUserService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final DoctorRepository doctorRepository;
    private final SupporterRepository supporterRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor injection for userRepository and passwordEncoder
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,RoleRepository roleRepository, DoctorRepository doctorRepository,SupporterRepository supporterRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.doctorRepository = doctorRepository;
        this.supporterRepository=supporterRepository;
    }

    // Get one user by id
    @Override
    public User getUser(long id) {
        Optional<User> userOptional = this.userRepository.findById(id);
        return userOptional.orElse(null);
    }

    // Get all users
    @Override
    public List<UserRoleDTO> getAllUsers() {
        List<UserRoleDTO> res = new ArrayList<>();
        List<User> users = this.userRepository.findAll();
        for(User user:users){
           List<String>roles =  userRepository.findAllRolesByUsername(user.getUsername());
           res.add(new UserRoleDTO(user.getId(), user.getUsername(), roles));
        }
        
        System.out.println("Enter line 55");
        return res;
    }

    // Create a new user with encoded password
    @Override
    public User createUser(User user) {
        // Encode the password before saving it to the database
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user);
        System.out.println("enter create user");
        return this.userRepository.save(user);
    }

    // Update user by id
    @Override
    public User updateUser(long id, User user) {
        User userCurrent = this.getUser(id);

        if (userCurrent != null) {
            userCurrent.setName(user.getName());
            userCurrent.setEmail(user.getEmail());
            userCurrent.setPassword(passwordEncoder.encode(user.getPassword())); // Update password with encoding
            return this.userRepository.save(userCurrent);
        }

        return null;
    }

    // Authenticate user by username and password
    @Override
    public boolean authenticate(String name, String password) {
        Optional<User> userOptional = userRepository.findByUsername(name);
        
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            System.out.print(user.getPassword());
            boolean isPasswordMatch = passwordEncoder.matches(password, user.getPassword());
            
            return isPasswordMatch;
        } else {
            System.out.println("HUHUH");
        }
        
        return true;
    }
    // Delete a user by id
    @Override
    public void deleteUser(long id) {
        this.userRepository.deleteById(id);
    }

    // Check if a username already exists in the database
    @Override
    public boolean existsByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent();
    }

    // Check if an email already exists in the database
    @Override
    public boolean existsByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }
    @Override
    public List<String>getUserRolesByUserName(String username){
        var user_roles = userRepository.findAllRolesByUsername(username);
        return user_roles;
    }
    @Override
    public List<String>getRolesByUserId(Long user_id){
        var user_roles = userRepository.findAllRolesByUserId(user_id);
        return user_roles;
    }
    @Override
    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    @Override
    public List<String> findAllRolesByUserName(String username){
        return userRepository.findAllRolesByUsername(username);
    }

    // @Override
    // public List<String> findAllPermissionsByUserName(String username) {
    //     return userRepository.findAllPermissionsByUserId(username);
    // }

    @Override
    public List<Object[]> findAllPermissionByUserNameAndUserRoleId(String username, int role_id) {
        return userRepository.findAllPermissionsByUserNameAndUserRole(username, role_id);
    }

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public Boolean assignRoleToUser(String username,List<Integer>roleIdList) {
        // Fetch the user by username
        Optional<User> optionalUser = userRepository.findByUsername(username);
        System.out.println("Assign role to user service impl");
        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        User user = optionalUser.get();

        user.getRoles().clear(); 
        for (Integer roleId : roleIdList) {
            Role role = roleRepository.findById(roleId)
                            .orElseThrow(() -> new RuntimeException("Role not found: "+roleId));
            User_Role newLink = new User_Role(user, role);
            user.getUserRoles().add(newLink);
        }

        userRepository.save(user); // will cascade insert/update/delete for User_Role
        return true;
    }

    @Override
    public int getRole_id(String rolename) {
        return roleRepository.getRoleIdByRoleName(rolename);
    }

    @Override
    public List<String> findAllPermissionsByUserName(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllPermissionsByUserName'");
    }

    @Override
    public Boolean revokeRoleFromUser(String username, int roleId) { 
        Optional<User> optionalUser = userRepository.findByUsername(username);
        System.out.println("Assign role to user service impl");
        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        User user = optionalUser.get();
        System.out.println(user.getId());
        System.out.println(roleId);
        userRepository.revokeRoleFromUser(user.getId(), roleId);
        return true;
    }

    @Override
    public List<Object[]> findAllPermissionsByRoleId(Integer roleId) {
        return userRepository.findAllPermissionsByRoleId(roleId);
    }

    @Override
    @Transactional
    public Boolean updateUserInfo(int id, UserInformationDTO requestUserInfo) {
        Optional<User> optionalUser = userRepository.findById((long) id);
        if (optionalUser.isEmpty()) {
            throw new EntityNotFoundException("User not found with ID: " + id);
        }

        User existingUser = optionalUser.get();

        // Update common fields
        existingUser.setUsername(requestUserInfo.getUsername());
        existingUser.setEmail(requestUserInfo.getEmail());
        existingUser.setFullName(requestUserInfo.getFullName());
        existingUser.setAddress(requestUserInfo.getAddress());
        existingUser.setPhone(requestUserInfo.getPhone());

        if (requestUserInfo.getTitle() == null || requestUserInfo.getTitle().isBlank()) {
            // Update as Doctor
            if (existingUser instanceof Doctor doctor) {
                doctor.setQualification(requestUserInfo.getQualification());
                doctor.setYearsOfExperience(requestUserInfo.getYearsOfExperience());
                doctor.setSpecialization(requestUserInfo.getSpecialization());
                doctorRepository.save(doctor);
            } else {
                throw new IllegalArgumentException("User is not a doctor.");
            }
        } else {
            // Update as Supporter
            if (existingUser instanceof Supporter supporter) {
                supporter.setTitle(requestUserInfo.getTitle());
                supporterRepository.save(supporter);
            } else {
                throw new IllegalArgumentException("User is not a supporter.");
            }
        }

        return true;
    }


}
