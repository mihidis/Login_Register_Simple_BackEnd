package org.project.Project.util.constants;

public enum Authorities {
    RESET_ANY_USER_PASSWORD(1l, "RESET_ANY_USER_PASSWORD"),
    ACCESS_ADMIN_PANEL(2l, "ACCESS_ADMIN_PANEL");

    private final Long authorityId;
    private final String authorityString;

    private Authorities(Long authorityId, String authorityString) {
        this.authorityId = authorityId;
        this.authorityString = authorityString;
    }

    public Long getAuthorityId() {
        return authorityId;
    }

    public String getAuthorityString() {
        return authorityString;
    }
}
