package by.gsu.study.sales.core.repository.impl.parser;

import by.gsu.study.sales.core.entity.Category;
import by.gsu.study.sales.core.repository.Parser;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryParser
        extends BeanPropertyRowMapper<Category>
        implements Parser<Category>  {

    public CategoryParser() {
        super(Category.class);
    }
}
