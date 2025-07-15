package com.aditya.trucker.config;

public class SecurityRoles {
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_OWNER = "ROLE_OWNER";
    public static final String ROLE_USER = "ROLE_USER";

    // API endpoints with required roles
    public static final class ApiRoles {
        // User endpoints
        public static final String[] USER_CREATE = {ROLE_ADMIN};
        public static final String[] USER_READ = {ROLE_ADMIN, ROLE_OWNER, ROLE_USER};
        public static final String[] USER_UPDATE = {ROLE_ADMIN, ROLE_OWNER};
        public static final String[] USER_DELETE = {ROLE_ADMIN};

        // Food Truck endpoints
        public static final String[] TRUCK_CREATE = {ROLE_ADMIN, ROLE_OWNER};
        public static final String[] TRUCK_READ = {ROLE_ADMIN, ROLE_OWNER, ROLE_USER};
        public static final String[] TRUCK_UPDATE = {ROLE_ADMIN, ROLE_OWNER};
        public static final String[] TRUCK_DELETE = {ROLE_ADMIN};

        // Menu endpoints
        public static final String[] MENU_CREATE = {ROLE_ADMIN, ROLE_OWNER};
        public static final String[] MENU_READ = {ROLE_ADMIN, ROLE_OWNER, ROLE_USER};
        public static final String[] MENU_UPDATE = {ROLE_ADMIN, ROLE_OWNER};
        public static final String[] MENU_DELETE = {ROLE_ADMIN, ROLE_OWNER};

        // Review endpoints
        public static final String[] REVIEW_CREATE = {ROLE_ADMIN, ROLE_OWNER, ROLE_USER};
        public static final String[] REVIEW_READ = {ROLE_ADMIN, ROLE_OWNER, ROLE_USER};
        public static final String[] REVIEW_UPDATE = {ROLE_ADMIN, ROLE_OWNER, ROLE_USER};
        public static final String[] REVIEW_DELETE = {ROLE_ADMIN};
    }
}
