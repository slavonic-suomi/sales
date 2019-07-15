package by.gsu.study.sales.core.web;

import by.gsu.study.sales.core.entity.User;
import by.gsu.study.sales.core.repository.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("/test")
public class TestSpringController {

    @Autowired
    protected IRepository<User> userIRepository;

    @RequestMapping("/some_url")
    public void doSomething(ModelAndView mnv) {

    }
}
