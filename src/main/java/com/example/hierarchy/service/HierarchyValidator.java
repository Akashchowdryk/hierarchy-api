package com.example.hierarchy.service;

import com.example.hierarchy.exception.HierarchyException;
import com.example.hierarchy.model.UserNode;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HierarchyValidator {

    private UserNode adminRoot;

    private static final Map<String, Integer> ROLE_LEVEL = Map.of(
            "ADMIN", 1,
            "BA", 2,
            "OA", 3,
            "FE", 4,
            "FRT", 5,
            "PAT", 6
    );

   
    public void addAdmin(String userId) {

        if (adminRoot != null) {
            throw new HierarchyException(
                    "Admin Already Exists",
                    "System allows only one ADMIN at root level"
            );
        }

        adminRoot = new UserNode(userId, "ADMIN", new ArrayList<>());
    }

   
    public void addUser(String parentId, String userId, String role) {

        if (adminRoot == null) {
            throw new HierarchyException(
                    "Admin Missing",
                    "Create ADMIN before adding users"
            );
        }

        UserNode parent = findUser(adminRoot, parentId);

        if (parent == null) {
            throw new HierarchyException(
                    "Parent Not Found",
                    "No user exists with ID: " + parentId
            );
        }

       
        if (findUser(adminRoot, userId) != null) {
            throw new HierarchyException(
                    "Duplicate User",
                    "User with ID '" + userId + "' already exists"
            );
        }

        validateRole(parent.getRole(), role);

        UserNode newUser = new UserNode(userId, role.toUpperCase(), new ArrayList<>());
        parent.getChildren().add(newUser);
    }

    
    public UserNode getHierarchy() {
        return adminRoot;
    }

    private UserNode findUser(UserNode node, String userId) {

        if (node.getUserId().equals(userId)) {
            return node;
        }

        for (UserNode child : node.getChildren()) {
            UserNode found = findUser(child, userId);
            if (found != null) return found;
        }

        return null;
    }


    private void validateRole(String parentRole, String childRole) {

        parentRole = parentRole.toUpperCase();
        childRole = childRole.toUpperCase();

        if (!ROLE_LEVEL.containsKey(childRole)) {
            throw new HierarchyException(
                    "Invalid Role",
                    "Role '" + childRole + "' is not recognized in system"
            );
        }

        int parentLevel = ROLE_LEVEL.get(parentRole);
        int childLevel = ROLE_LEVEL.get(childRole);

        if (parentLevel == childLevel) {
            throw new HierarchyException(
                    "Invalid Hierarchy",
                    "Same role cannot be assigned under same role: " + parentRole
            );
        }

        if (childLevel < parentLevel) {
            throw new HierarchyException(
                    "Invalid Hierarchy",
                    childRole + " is a superior role and cannot be assigned under " + parentRole
            );
        }

    
        if (parentRole.equals("ADMIN") && childRole.equals("ADMIN")) {
            throw new HierarchyException(
                    "Invalid Hierarchy",
                    "ADMIN cannot be assigned under another ADMIN"
            );
        }
    }
}
