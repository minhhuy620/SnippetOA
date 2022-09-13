package SnippetOA.snippet_oa.service;

//import com.op247.ticket.dto.Projects;
//import com.op247.ticket.entity.PerformanceReportEntity;
//import com.op247.ticket.entity.TicketRawEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class ReportServices {
//    @Autowired
//    JdbcTemplate jdbcTemplate;
//
//    public List<PerformanceReportEntity> report(List<TicketRawEntity> tickets) {
//        List<PerformanceReportEntity> result = new ArrayList<>();
//        tickets.forEach(ticketEntity -> {
//            if (ticketEntity.getTicketId() != null && !ticketEntity.getTicketId().isEmpty()) {
//                PerformanceReportEntity pr = new PerformanceReportEntity();
//                pr.setPriority(ticketEntity.getPriority());
//                pr.setTicketId(Long.parseLong(ticketEntity.getTicketId()));
//                if (ticketEntity.getActualTimeResolution() != null && ticketEntity.getActualTimeResonse() != null) {
//                    pr.setResolvedTime(ticketEntity.getActualTimeResolution().getTime()
//                            - ticketEntity.getActualTimeResonse().getTime());
//                }
//                if (ticketEntity.getRequestTime() != null && ticketEntity.getActualTimeResonse() != null) {
//                    pr.setResponseTime(
//                            ticketEntity.getActualTimeResonse().getTime() - ticketEntity.getRequestTime().getTime());
//                }
//                pr.setProjectName(ticketEntity.getProjectName());
//                pr.setCreatedAt(ticketEntity.getRequestTime());
//                pr.setAssignee(ticketEntity.getAssignee());
//                result.add(pr);
//            }
//        });
//
//        return result;
//    }
//
//    private boolean dateDiff(Date date1, Date date2) {
//        LocalDateTime lc1 = LocalDateTime.ofInstant(date1.toInstant(), ZoneId.systemDefault());
//        LocalDateTime lc2 = LocalDateTime.ofInstant(date2.toInstant(), ZoneId.systemDefault());
//        if (lc1.getDayOfMonth() == lc2.getDayOfMonth() && lc1.getMonthValue() == lc2.getMonthValue()
//                && lc1.getYear() == lc2.getYear()) {
//            return false;
//        } else {
//            return true;
//        }
//
//    }
//
//    public static Date atStartOfDay(Date date) {
//        LocalDateTime localDateTime = dateToLocalDateTime(date);
//        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
//        return localDateTimeToDate(startOfDay);
//    }
//
//    private static LocalDateTime dateToLocalDateTime(Date date) {
//        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
//    }
//
//    private static Date localDateTimeToDate(LocalDateTime localDateTime) {
//        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
//    }
//
//    public void checkExistTable() {
//        List<Projects> projectsList = jdbcTemplate.query(
//                "SELECT * FROM projects",
//                new RowMapper<Projects>() {
//                    public Projects mapRow(ResultSet rs, int rowNum) throws SQLException {
//                        Projects p = new Projects();
//                        p.setId(rs.getInt("id"));
//                        p.setName(rs.getString("name"));
//                        p.setCode(rs.getString("code"));
//                        return p;
//                    }
//                });
//        projectsList.forEach(projects -> {
//            excute(projects.getName().toLowerCase(Locale.ROOT));
//        });
//    }
//
//    public void excute(String projectName) {
//        List<TicketRawEntity> export = jdbcTemplate.query(
//                "SELECT * FROM ticket_raw_" + projectName,
//                new RowMapper<TicketRawEntity>() {
//                    public TicketRawEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
//                        TicketRawEntity t = new TicketRawEntity();
//                        t.setNo(rs.getInt("no"));
//                        t.setLevel(rs.getString("level"));
//                        t.setProjectName(rs.getString("project_name"));
//                        t.setTicketId(rs.getString("ticket_id"));
//                        t.setTicketType(rs.getString("ticket_type"));
//                        t.setTitle(rs.getString("title"));
//                        t.setPriority(rs.getString("priority"));
//                        t.setAssignee(rs.getString("assignee"));
//                        t.setSolution(rs.getString("solution"));
//                        t.setCategory(rs.getString("category"));
//                        t.setModule(rs.getString("module"));
//                        t.setRequestBy(rs.getString("request_by"));
//                        t.setRequestTime(rs.getDate("request_time"));
//                        t.setActualTimeResonse(rs.getDate("actual_time_resonse"));
//                        t.setActualTimeResolution(rs.getDate("actual_time_resolution"));
//                        t.setResolveEffort(rs.getFloat("resolve_effort"));
//                        t.setDueTimeResonse(rs.getDate("due_time_resonse"));
//                        t.setDueTimeResolution(rs.getDate("due_time_resolution"));
//                        t.setOverdueFirstResonseStatus(rs.getString("overdue_first_resonse_status"));
//                        t.setOverdueResolutionStatus(rs.getString("overdue_resolution_status"));
//                        t.setGroup(rs.getString("group"));
//                        t.setIsSync(rs.getBoolean("is_sync"));
//                        t.setTicketStatus(rs.getString("ticket_status"));
//                        return t;
//                    }
//                });
//        List<PerformanceReportEntity> rs = report(export);
//    }
//
//    public void saveReport(List<PerformanceReportEntity> rs, String projectName) {
//        jdbcTemplate.batchUpdate(
//                "insert into pbi_ticket_time" + projectName
//                        + " (ticket_id, project_name, response_time, resolved_time, priority) values(?,?,?,?,?)",
//                new BatchPreparedStatementSetter() {
//
//                    public void setValues(PreparedStatement ps, int i) throws SQLException {
//                        ps.setLong(1, rs.get(i).getTicketId());
//                        ps.setString(5, rs.get(i).getPriority());
//                        ps.setString(2, rs.get(i).getProjectName());
//                        if (rs.get(i).getResponseTime() != null) {
//                            ps.setLong(3, rs.get(i).getResponseTime());
//                        } else {
//                            ps.setNull(3, java.sql.Types.NULL);
//                        }
//                        if (rs.get(i).getResolvedTime() != null) {
//                            ps.setLong(4, rs.get(i).getResolvedTime());
//                        } else {
//                            ps.setNull(4, java.sql.Types.NULL);
//                        }
//                    }
//
//                    public int getBatchSize() {
//                        return rs.size();
//                    }
//                });
//    }
}
