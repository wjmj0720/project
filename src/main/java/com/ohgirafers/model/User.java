package com.ohgirafers.model;

import java.time.LocalDateTime;

public class User {
    private int userId;
    private String email;
    private String password;
    private String name;
    private String roleId;
    private LocalDateTime createdAt;

    public User(int userId, String email, String password, String name, String roleId, LocalDateTime createdAt) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.roleId = roleId;
        this.createdAt = createdAt;
    }
//User 클래스에서만 쓸 수 있는 id와 email -> 외부에서 쓰려면 생성자를 생성해서 사용

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", roleId='" + roleId + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
