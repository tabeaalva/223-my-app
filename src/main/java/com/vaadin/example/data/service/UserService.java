package com.vaadin.example.data.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.vaadin.example.data.entity.DBUser;
import com.vaadin.example.data.entity.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.vaadin.example.ApplicationServiceInitListener;

/**
 * Standard Spring Service class, where we access the database to get our data.
 * <p>
 * See the <code>application.properties</code> file for database connections
 * properties.
 *
 * @see ApplicationServiceInitListener Dummy data generation
 */
@Service
public class UserService {

    /**
     * Access point to our DB, automatically configured by Spring.
     */
    @Autowired
    private JdbcTemplate template;

    /**
     * @return All movies in the database, with director information.
     */

    public void addUser(DBUser user) {

        final String sql = UserRowMapper.INSERT_INTO;
        template.update(
                sql,
                user.getName(),
                user.getNachname(),
                user.getUsername(),
                user.getPasswort(),
                user.getRecht()
        );

    }

    public void deleteUser(Long benutzerId) {

        final String sql = UserRowMapper.DELETE_ALL;
        template.update(sql, new Object[]{benutzerId});

    }

    public List<DBUser> getUser() {

        // Use a query the sql mapper class understands
        final String sql = UserRowMapper.SELECT_ALL;


        // Use Spring's JdbcTemplate helper class to run the sql
        final List<DBUser> list = template.query(sql, new UserRowMapper());

        return list;
    }




    /**
     * JDBCTemplate mapper class, where we take a SQL ResultSet and parse out our
     * Java DTO(s).
     * <p>
     * The ResultSet is dependent on the SQL query we do in the Service class, so we
     * define the query here as a public constant.
     */
    public static class UserRowMapper implements RowMapper<DBUser> {

        /**
         * SQL clause to fetch all Movies from the DB, with Director names included.
         */
        public static final String SELECT_ALL = "SELECT * FROM db_user use";
        public static final String INSERT_INTO = "INSERT INTO db_user(name,nachname,username,passwort,recht) values(?,?,?,?,?)";

        public static final String DELETE_ALL = "DELETE FROM db_user WHERE benutzerId = ?";



        @Override
        public DBUser mapRow(ResultSet rs, int rowNum) throws SQLException {

            final DBUser user = new DBUser();
            user.setBenutzerId(rs.getLong("benutzerid"));
            user.setName(rs.getString("name"));
            user.setNachname(rs.getString("nachname"));
            user.setUsername(rs.getString("username"));
            user.setPasswort(rs.getString("passwort"));
            user.setRecht(rs.getString("recht"));

            /*final Topic topic = new Topic();
            topic.setTopicId(rs.getLong("topicId"));
            topic.setName(rs.getString("name"));
            user.getTopics();
*/


            return user;
        }
    }
}