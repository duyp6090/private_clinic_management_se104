CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    full_name VARCHAR(255),
    address VARCHAR(255),
    phone VARCHAR(20)
);
CREATE TABLE refresh_tokens (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    token VARCHAR(255) NOT NULL,
    expiration_date DATETIME NOT NULL,
    is_revoke BOOLEAN NOT NULL,
    is_expired BOOLEAN NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- HAVE NOT USE
CREATE TABLE tbl_role (
    role_id INT AUTO_INCREMENT PRIMARY KEY,    -- role_id is the primary key with auto-increment
    role_name VARCHAR(255) NOT NULL,            -- role_name is a string column, cannot be null
    description TEXT,                           -- description is an optional text field
    UNIQUE (role_name)                          -- role_name is unique, ensuring no two roles have the same name
);
CREATE TABLE tbl_permission (
    permission_id INT AUTO_INCREMENT PRIMARY KEY,   -- permission_id is the primary key with auto-increment
    permission VARCHAR(255) NOT NULL,                -- permission is a string column, cannot be null
    description TEXT,                               -- description is an optional text field
    UNIQUE (permission)                             -- permission is unique, ensuring no two permissions have the same value
);
CREATE TABLE tbl_role_has_permission (
    role_id INT,                                  -- role_id referencing the Role table
    permission_id INT,                            -- permission_id referencing the Permission table
    PRIMARY KEY (role_id, permission_id),         -- composite primary key (role_id, permission_id)
    FOREIGN KEY (role_id) REFERENCES tbl_role(role_id) ON DELETE CASCADE,   -- foreign key for role_id
    FOREIGN KEY (permission_id) REFERENCES tbl_permission(permission_id) ON DELETE CASCADE   -- foreign key for permission_id
);
CREATE TABLE tbl_user_role (
    user_id INT,                                  -- user_id referencing the User table
    role_id INT,                                  -- role_id referencing the Role table
    PRIMARY KEY (user_id, role_id),               -- composite primary key (user_id, role_id)
    FOREIGN KEY (user_id) REFERENCES tbl_user(user_id) ON DELETE CASCADE,   -- foreign key for user_id
    FOREIGN KEY (role_id) REFERENCES tbl_role(role_id) ON DELETE CASCADE    -- foreign key for role_id
);

-------ENTER DOCKER WITH COMMAND:
command 1: sudo docker exec -it mysql_container bash
command 2: mysql -u root -p
password: 12345
command 3: USE clinic_db

------MOCK DATA FOR THE ROLE PERMISSION---
-----CREATE ADMIN-------
INSERT INTO users (username, email, password, full_name, address, phone)
VALUES ('admin', 'admin@example.com', 'hashed_password_here', 'Admin User', '123 Admin St.', '123-456-7890');


INSERT INTO tbl_user_role (user_id, role_id)
VALUES(3,3);

-------CREATE MANAGER-----------
INSERT INTO roles (role_name, description) VALUES
('User', 'Regular user with limited access'),
('Manager', 'User with management permissions'),
('Guest', 'Guest user with read-only access');

INSERT INTO tbl_permissions (permission, description) VALUES
('CREATE_USER', 'Permission to create new users'),
('DELETE_USER', 'Permission to delete users'),
('UPDATE_USER', 'Permission to update user details'),
('VIEW_USER', 'Permission to view user details'),
('ACCESS_DASHBOARD', 'Permission to access the dashboard'),
('MANAGE_ROLES', 'Permission to manage user roles');

INSERT INTO tbl_role_permission (role_id, permission_id) VALUES
(1, 1),  -- Admin has CREATE_USER permission
(1, 2),  -- Admin has DELETE_USER permission
(1, 3),  -- Admin has UPDATE_USER permission
(1, 4),  -- Admin has VIEW_USER permission
(1, 5),  -- Admin has ACCESS_DASHBOARD permission
(1, 6),  -- Admin has MANAGE_ROLES permission
(2, 4),  -- User has VIEW_USER permission
(2, 5),  -- User has ACCESS_DASHBOARD permission
(3, 4),  -- Manager has VIEW_USER permission
(3, 5),  -- Manager has ACCESS_DASHBOARD permission
(3, 6),  -- Manager has MANAGE_ROLES permissionse


INSERT INTO tbl_user_role (user_id, role_id) VALUES
(1, 1);  -- User 1 is Admin
(2, 2),  -- User 2 is User
(3, 3),  -- User 3 is Manager
(4, 4);  -- User 4 is Guest




-- Mock

INSERT INTO tbl_permissions (description, permission)
VALUES 
  ('staff page', 'staff'),
  ('dashboard page', 'dashboard'),
  ('admin page', 'admin'),
  ('exams page', 'exams'),
  ('waiting page', 'waiting'),
  ('records page', 'records'),
  ('permission to patients page', 'patients'),
  ('drugs page', 'drugs'),
  ('invoice page', 'invoice'),
  ('report page', 'reports'),
  ('setting page', 'integrations');
