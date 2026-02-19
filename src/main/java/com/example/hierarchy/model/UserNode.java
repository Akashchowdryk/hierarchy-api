package com.example.hierarchy.model;
import java.util.List;

public class UserNode {

    private String userId;
    private String role;
    private List<UserNode> children;

    public UserNode() {}

    public UserNode(String userId, String role, List<UserNode> children) {
        this.userId = userId;
        this.role = role;
        this.children = children;
    }

    public String getUserId() {
        return userId;
    }

    public String getRole() {
        return role;
    }

    public List<UserNode> getChildren() {
        return children;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setChildren(List<UserNode> children) {
        this.children = children;
    }
}
