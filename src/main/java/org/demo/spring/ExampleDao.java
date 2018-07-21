package org.demo.spring;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.stringtemplate4.UseStringTemplateSqlLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ExampleDao {

    private ExampleDb db;

    @Autowired
    public void setDbi(Jdbi dbi) {
        this.db = dbi.onDemand(ExampleDb.class);
    }

    public long create(Example example) {
        return db.create(example);
    }

    public Example get(long id) {
        return db.get(id);
    }

    public List<Example> getWhere(String name, long minimumId) {
        String where = String.format("name = '%s' AND id >= %s", name, minimumId);
        return db.getWhere(where);
    }


//    @UseStringTemplateSqlLocator
    @RegisterRowMapper(ExampleMapper.class)
    public interface ExampleDb {

        @SqlUpdate("INSERT INTO example (name) values (:name)")
        @GetGeneratedKeys
        long create(@BindBean Example example);


        @SqlQuery("SELECT * FROM example WHERE id = :id")
        Example get(@Bind("id") long id);

        @SqlQuery("SELECT * FROM example WHERE <where>")
        List<Example> getWhere(@Define("where") String where);
    }
}

