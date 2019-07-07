package by.gsu.study.sales.core.repository;

import by.gsu.study.sales.core.entity.IEntity;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public interface Parser<E extends IEntity> extends RowMapper<E> {

    @SneakyThrows(java.sql.SQLException.class)
    default E parseSingleRow(ResultSet resultSet) {
        if (resultSet.next()) {
            return mapRow(resultSet, 1);
        }

        return null;
    }

    @SneakyThrows(java.sql.SQLException.class)
    default List<E> parseList(ResultSet resultSet) {
        int index = 0;
        List<E> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(mapRow(resultSet, index++));
        }
        return result;
    }
}
