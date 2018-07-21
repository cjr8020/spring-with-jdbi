package org.demo.spring;


import java.sql.ResultSet;
import java.sql.SQLException;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

public class ExampleMapper implements RowMapper<Example> {

  @Override
  public Example map(ResultSet rs, StatementContext ctx) throws SQLException {
    Example example = new Example();

    example.setId(rs.getLong("id"));
    example.setName(rs.getString("name"));

    return example;
  }
}
