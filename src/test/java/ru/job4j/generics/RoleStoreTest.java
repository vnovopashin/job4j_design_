package ru.job4j.generics;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoleStoreTest {

    private RoleStore store;

    @BeforeEach
    void init() {
        store = new RoleStore();
        store.add(new Role("1", "Admin"));
    }

    @AfterEach
    void clear() {
        store = null;
    }

    @Test
    void whenAddAndFindThenUserRoleIsAdmin() {
        Role result = store.findById("1");
        assertThat(result.getUserRole()).isEqualTo("Admin");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindUserRoleIsAdmin() {
        store.add(new Role("1", "User"));
        Role result = store.findById("1");
        assertThat(result.getUserRole()).isEqualTo("Admin");
    }

    @Test
    void whenReplaceThenUserRoleIsUser() {
        store.replace("1", new Role("1", "User"));
        Role result = store.findById("1");
        assertThat(result.getUserRole()).isEqualTo("User");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeUserRole() {
        store.replace("10", new Role("10", "User"));
        Role result = store.findById("1");
        assertThat(result.getUserRole()).isEqualTo("Admin");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenUserRoleIsAdmin() {
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getUserRole()).isEqualTo("Admin");
    }

    @Test
    void whenReplaceOkThenTrue() {
        boolean result = store.replace("1", new Role("1", "User"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        boolean result = store.replace("10", new Role("10", "User"));
        assertThat(result).isFalse();
    }
}
