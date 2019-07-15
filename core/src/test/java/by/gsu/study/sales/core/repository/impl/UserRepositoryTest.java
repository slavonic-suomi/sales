package by.gsu.study.sales.core.repository.impl;

import by.gsu.study.sales.core.context.CommonConfig;
import by.gsu.study.sales.core.entity.User;
import by.gsu.study.sales.core.repository.IRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringJUnitConfig(CommonConfig.class)
public class UserRepositoryTest {

    @Autowired
    private IRepository<User> userRepository;

    @Test
    public void testFindAll() {
        List<User> all = userRepository.findAll();

        assertNotNull(all);

        System.out.println(all);
    }

//    @Test
//    public void testCreate() {
//        User user = new User(null, "test@test");
//        userRepository.save(user);
//
//        assertNotNull(user.getId());
//    }

    @After
    public void destroy() {
        userRepository.findAll()
                .stream()
                .filter(u -> u.getEmail().equals("test@test"))
                .findFirst()
                .ifPresent(user -> {
                    userRepository.deleteById(user.getId());
                });
    }

}