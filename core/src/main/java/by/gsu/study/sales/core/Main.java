package by.gsu.study.sales.core;

import by.gsu.study.sales.core.context.LiquibaseManager;
import by.gsu.study.sales.core.entity.User;
import by.gsu.study.sales.core.repository.impl.UserRepository;
import lombok.SneakyThrows;

import java.util.List;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        LiquibaseManager.initDatabase();

        User user = new User(null, "test36@mail.com");
        UserRepository repository = new UserRepository();
        repository.save(user);


//        repository.deleteById(11);

//        User user1 = repository.findById(8);
//        user1.setEmail("newEmail2");
//        repository.save(user1);

        List<User> all = repository.findAll();
        all.forEach(System.out::println);
    }
}
