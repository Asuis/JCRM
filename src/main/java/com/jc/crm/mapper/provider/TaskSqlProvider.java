package com.jc.crm.mapper.provider;

import com.jc.crm.query.TaskQuery;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author asuis
 * @version: TaskSqlProvider.java 18-12-9:下午8:47
 */
public class TaskSqlProvider {

    private static Logger logger = LoggerFactory.getLogger(TaskSqlProvider.class);

    public String query(TaskQuery query) {
        //todo sql优化
        String sql = new SQL(){{
            SELECT("task.task_id, task.avatar, task.theme, task.start_time, task.deadline, task.description, task.state, task.priority, " +
                    "tasks_business_opp_link.opp_id, tasks_consumer_link.consumer_id");
            FROM("task LEFT JOIN tasks_business_opp_link ON task.task_id = tasks_business_opp_link.task_id LEFT JOIN tasks_consumer_link ON task.task_id = tasks_consumer_link.task_id, tasks_holder_link ");
            WHERE("tasks_holder_link.task_id = task.task_id AND tasks_holder_link.state = 1");
            AND();
            WHERE("task.state = #{state}");
            if (query.getUids().size()>0) {
                AND();
                StringBuilder where = new StringBuilder("tasks_holder_link.holder_id IN (" + query.getUids().get(0));
                for (int i = 1; i < query.getUids().size(); i++) {
                  where.append(",").append(query.getUids().get(i));
                }
                where.append(") ");
                WHERE(where.toString());
            }

            if (query.getKeyword()!=null) {
                if (!"".equals(query.getKeyword().trim())) {
                    AND();
                    WHERE("task.theme LIKE CONCAT('%', #{keyword}, '%')");
                }
            }
            if (query.getOrderBy()!=null) {
                if (!"".equals(query.getOrderBy().trim())) {
                    ORDER_BY("#{orderBy} ASC");
                }
            }
        }}.toString();
        logger.info("执行sql: " + sql);
        return sql;
    }
}
