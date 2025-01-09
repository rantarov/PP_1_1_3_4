package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Anton", "Burlakov", (byte) 25);
        userService.saveUser("Sofia", "Petrova", (byte) 24);
        userService.saveUser("Olga", "Kovaleva", (byte) 23);
        userService.saveUser("Vera", "Ivanova", (byte) 43);
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
