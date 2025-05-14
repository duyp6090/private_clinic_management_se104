package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.User;

import jakarta.transaction.Transactional;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    // Find user by username
    Optional<User> findByUsername(String username);
    
    // Find user by id
    Optional<User> findById(long id);

    // Delete user by id
    void deleteById(long id);

    // Correct query method for fetching roles by userId
    @Query("SELECT r.role_name FROM Role r JOIN User_Role ur ON r.id = ur.role.id WHERE ur.user.id = :userId")
    List<String> findAllRolesByUserId(@Param("userId") long userId);

    
    @Query("SELECT r.role_name " +
    "FROM Role r " +
    "JOIN User_Role ur ON ur.role.role_id = r.role_id " +
    "JOIN User u ON ur.user.id = u.id " +
    "WHERE u.username = :username")
    List<String> findAllRolesByUsername(@Param("username") String username);

    @Query(value = """
        SELECT p.permission_id,p.permission,rp.can_create,rp.can_read, rp.can_update,rp.can_delete 
        FROM users u
        JOIN tbl_user_role ur ON u.id = ur.user_id
        JOIN tbl_role_permission rp ON ur.role_id = rp.role_id
        JOIN tbl_permissions p ON rp.permission_id = p.permission_id
        WHERE u.username = :userName
        """, nativeQuery = true)
    List<Object[]> findAllPermissionsByUserId(@Param("userName") String userName);
    

    @Query(value = """
        SELECT p.permission_id,p.permission,rp.can_create,rp.can_read, rp.can_update,rp.can_delete 
        FROM users u
        JOIN tbl_user_role ur ON u.id = ur.user_id
        JOIN tbl_role_permission rp ON ur.role_id = rp.role_id
        JOIN tbl_permissions p ON rp.permission_id = p.permission_id
        WHERE u.username = :userName AND ur.role_id = :role_id
        """, nativeQuery = true)
    List<Object[]> findAllPermissionsByUserNameAndUserRole(@Param("userName") String userName,@Param("role_id")int userRoleId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tbl_user_role (user_id, role_id) VALUES (:userId, :roleId)", nativeQuery = true)
    void assignRoleToUser(@Param("userId") Long userId, @Param("roleId") int roleId);
    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tbl_user_role ur WHERE ur.role_id = :roleId AND ur.user_id = :userId", nativeQuery = true)
    void revokeRoleFromUser(@Param("userId") Long userId, @Param("roleId") int roleId);    
    
}
