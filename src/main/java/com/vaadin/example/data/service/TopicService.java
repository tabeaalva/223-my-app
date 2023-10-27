package com.vaadin.example.data.service;

import com.vaadin.example.data.entity.Grade;
import com.vaadin.example.data.entity.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class TopicService {

    @Autowired
    private JdbcTemplate template;

    public List<Topic> getTopicforUser(Long userId) {
        final String sql = TopicRowMapper.SELECT_ALL_FOR_USER;

        final List<Topic> topics = template.query(sql, new Object[]{userId}, new TopicRowMapper());
        return topics;
    }

    public static class TopicRowMapper implements RowMapper<Topic> {

        public static final String SELECT_ALL_FOR_USER = "SELECT * from topic WHERE userId = ?";
        @Override
        public Topic mapRow(ResultSet rs, int rowNum) throws SQLException {
            Topic topic = new Topic();
            topic.setTopicId(rs.getLong("topicId"));
            topic.setName(rs.getString("name"));
            return topic;
        }
    }
}
