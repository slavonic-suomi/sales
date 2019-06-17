package by.gsu.study.sales.core.repository;

import by.gsu.study.sales.core.entity.IEntity;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public interface Parser<E extends IEntity> {
    E parseRow(ResultSet resultSet, int rowIndex);

    @SneakyThrows(java.sql.SQLException.class)
    default E parseSingleRow(ResultSet resultSet) {
        if (resultSet.next()) {
            return parseRow(resultSet, 1);
        }

        return null;
    }

    @SneakyThrows(java.sql.SQLException.class)
    default List<E> parseList(ResultSet resultSet) {
        int index = 0;
        List<E> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(parseRow(resultSet, index++));
        }
        return result;
    }
}
