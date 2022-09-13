package SnippetOA.snippet_oa.service;

import java.io.InputStream;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.op247.ticket.configMessage.MessagingConfig;
//import com.op247.ticket.dto.Ticket;
//import com.op247.ticket.dto.TicketRawDto;
//import com.op247.ticket.entity.TicketRawEntity;
//import com.op247.ticket.repository.TicketRepository;

@Service
public class TicketService {
//	private static final Logger LOGGER = LogManager.getLogger(TicketService.class);
//	@Autowired
//	private TicketRepository ticketRepository;
//
//	@Autowired
//	private EntityManager em;
//
//	@Autowired
//	private AmqpTemplate amqpTemplate;
//
//	@Autowired
//	private RabbitAdmin rabbitAdmin;
//
//	@Autowired
//	private ReportServices reportServices;
//
////	@Value("${backend.url}")
////	private String backendtUrl;
//	@Value("url")
//	private String backendtUrl;
//
//	@Transactional
//	public void save(Ticket message) {
//		TicketRawDto ticketRawDto = message.getData();
//		if (ticketRawDto != null) {
//			TicketRawEntity ticket = convertDtoToEntity(ticketRawDto);
//			try {
//				ticketRepository.insertTicket(message.getTableName(), ticket.getLevel(),
//						ticket.getProjectName(), ticket.getTicketId(), ticket.getTicketType(), ticket.getTitle(),
//						ticket.getPriority(), ticket.getAssignee(), ticket.getSolution(), ticket.getCategory(),
//						ticket.getModule(), ticket.getRequestBy(), ticket.getRequestTime(),
//						ticket.getActualTimeResonse(), ticket.getActualTimeResolution(), ticket.getResolveEffort(),
//						ticket.getDueTimeResonse(), ticket.getDueTimeResolution(),
//						ticket.getOverdueFirstResonseStatus(), ticket.getOverdueResolutionStatus(), ticket.getGroup(),
//						ticket.getTicketStatus(), ticket.getDueByTime());
//				// call api for sla
//				sendToSLA(ticket.getProjectName(), ticket.getTicketId());
//				// for update report
//				String reportQuery = getInsertReportQuery(message.getTableName(), ticket);
//				if (reportQuery != "") {
//					em.createNativeQuery(reportQuery).executeUpdate();
//				}
//				reportServices.saveReport(reportServices.report(Collections.singletonList(ticket)),
//						message.getTableName().split("ticket_raw")[1]);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	private void sendToSLA(String projectName, String ticketId) {
//		try {
//			String param = "/api/slaticket?project_name=" + projectName + "&ticket_id=" + ticketId;
//			String requestUrl = backendtUrl + param;
//			LOGGER.info("Request URL: " + requestUrl);
//			URL url = new URL(requestUrl);
//			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
//			con.setDoOutput(true);
//			con.setRequestMethod("POST");
//			con.setRequestProperty("accept", "application/json");
//			InputStream responseStream = con.getInputStream();
//			// Manually converting the response body InputStream to APOD using Jackson
//			ObjectMapper mapper = new ObjectMapper();
//			// parse response
//			LOGGER.info(responseStream.toString());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Transactional
//	@SuppressWarnings("unchecked")
//	public int updateTicket(Ticket ticket) {
//		int result = 0;
//		TicketRawDto ticketRawDto = ticket.getData();
//		if (ticketRawDto != null) {
//			try {
//				TicketRawEntity ticketRaw = convertDtoToEntity(ticketRawDto);
//				String query = convertToQuery(ticket.getTableName(), ticketRaw);
//				result = em.createNativeQuery(query).executeUpdate();
//				LOGGER.info("number record update: " + result);
//				if (result == 0) {
//					// check queue
//					if (rabbitAdmin.getQueueInfo(MessagingConfig.QUEUE).getMessageCount() != 0) {
//						amqpTemplate.convertAndSend(MessagingConfig.EXCHANGE_UDP,
//								MessagingConfig.ROUTING_KEY_UDP, ticket);
//					}
//				} else {
//					// for update report
//					String reportQuery = getInsertReportQuery(ticket.getTableName(), ticketRaw);
//					if (reportQuery != "") {
//						em.createNativeQuery(reportQuery).executeUpdate();
//					}
//
//				}
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return result;
//	}
//
//	public TicketRawEntity convertDtoToEntity(TicketRawDto ticketRawDto) {
//		TicketRawEntity ticketRawEntity = new TicketRawEntity();
//		ticketRawEntity.setNo(ticketRawDto.getNo());
//		ticketRawEntity.setLevel(ticketRawDto.getLevel());
//		ticketRawEntity.setProjectName(ticketRawDto.getProjectName());
//		ticketRawEntity.setTicketId(ticketRawDto.getTicketId());
//		ticketRawEntity.setTicketType(ticketRawDto.getTicketType());
//		ticketRawEntity.setTitle(ticketRawDto.getTitle());
//		ticketRawEntity.setPriority(ticketRawDto.getPriority());
//		ticketRawEntity.setAssignee(ticketRawDto.getAssignee());
//		ticketRawEntity.setSolution(ticketRawDto.getSolution());
//		ticketRawEntity.setCategory(ticketRawDto.getCategory());
//		ticketRawEntity.setModule(ticketRawDto.getModule());
//		ticketRawEntity.setRequestBy(ticketRawDto.getRequestBy());
//		ticketRawEntity.setRequestTime(convertStringToTimestamp(ticketRawDto.getRequestTime()));
//		ticketRawEntity.setActualTimeResonse(convertStringToTimestamp(ticketRawDto.getActualTimeResonse()));
//		ticketRawEntity.setActualTimeResolution(convertStringToTimestamp(ticketRawDto.getActualTimeResolution()));
//		ticketRawEntity.setResolveEffort(ticketRawDto.getResolveEffort());
//		ticketRawEntity.setDueTimeResonse(convertStringToTimestamp(ticketRawDto.getDueTimeResolution()));
//		ticketRawEntity.setDueTimeResolution(convertStringToTimestamp(ticketRawDto.getDueTimeResolution()));
//		ticketRawEntity.setOverdueFirstResonseStatus(ticketRawDto.getOverdueFirstResonseStatus());
//		ticketRawEntity.setOverdueResolutionStatus(ticketRawDto.getOverdueResolutionStatus());
//		ticketRawEntity.setGroup(ticketRawDto.getGroup());
//		ticketRawEntity.setTicketStatus(ticketRawDto.getTicketStatus());
//		ticketRawEntity.setDueByTime(convertStringToTimestamp(ticketRawDto.getDueByTime()));
//		return ticketRawEntity;
//	}
//
//	public Timestamp convertStringToTimestamp(String str) {
//		if (str == null)
//			return null;
//		try {
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			Date parsedDate = dateFormat.parse(str);
//			Timestamp timestamp = new Timestamp(parsedDate.getTime());
//			return timestamp;
//		} catch (ParseException e) {
//			// System.out.println("Exception :" + e);
//			return null;
//		}
//	}
//
//	private String convertToQuery(String tableName, TicketRawEntity ticketRaw) {
//		StringBuffer sql = new StringBuffer()
//				.append("Update ")
//				.append(tableName).append(" set ");
//
//		// set values
//		List<String> updateValues = new ArrayList<>();
//		if (ticketRaw.getLevel() != null) {
//			updateValues.add("level = \"" + ticketRaw.getLevel() + "\"");
//		}
//		if (ticketRaw.getProjectName() != null) {
//			updateValues.add("project_name = \"" + ticketRaw.getProjectName() + "\"");
//		}
//		if (ticketRaw.getTicketType() != null) {
//			updateValues.add("ticket_type = \"" + ticketRaw.getTicketType() + "\"");
//		}
//		if (ticketRaw.getTitle() != null) {
//			updateValues.add("title = \"" + ticketRaw.getTitle() + "\"");
//		}
//		if (ticketRaw.getPriority() != null) {
//			updateValues.add("priority = \"" + ticketRaw.getPriority() + "\"");
//		}
//		if (ticketRaw.getAssignee() != null) {
//			updateValues.add("assignee = \"" + ticketRaw.getAssignee() + "\"");
//		}
//		if (ticketRaw.getSolution() != null) {
//			updateValues.add("solution = \"" + ticketRaw.getSolution() + "\"");
//		}
//		if (ticketRaw.getCategory() != null) {
//			updateValues.add("category = \"" + ticketRaw.getCategory() + "\"");
//		}
//		if (ticketRaw.getModule() != null) {
//			updateValues.add("module = \"" + ticketRaw.getModule() + "\"");
//		}
//		if (ticketRaw.getRequestBy() != null) {
//			updateValues.add("request_by = \"" + ticketRaw.getRequestBy() + "\"");
//		}
//		if (ticketRaw.getRequestTime() != null) {
//			updateValues.add("request_time = \"" + ticketRaw.getRequestTime() + "\"");
//			updateValues.add("request_time_key = DATE_FORMAT('" + ticketRaw.getRequestTime()
//					+ "','%Y%m%d%H')");
//		}
//		if (ticketRaw.getActualTimeResonse() != null) {
//			updateValues.add("actual_time_resonse = \"" + ticketRaw.getActualTimeResonse() + "\"");
//		}
//		if (ticketRaw.getActualTimeResolution() != null) {
//			updateValues.add("actual_time_resolution = \"" + ticketRaw.getActualTimeResolution() + "\"");
//			updateValues.add("resolution_time_key = DATE_FORMAT('" + ticketRaw.getActualTimeResolution()
//					+ "','%Y%m%d%H')");
//		}
//		if (ticketRaw.getResolveEffort() != 0) {
//			updateValues.add("resolve_effort = " + ticketRaw.getResolveEffort());
//		}
//		if (ticketRaw.getDueTimeResonse() != null) {
//			updateValues.add("due_time_resonse = \"" + ticketRaw.getDueTimeResonse() + "\"");
//		}
//		if (ticketRaw.getDueTimeResolution() != null) {
//			updateValues.add("due_time_resolution = \"" + ticketRaw.getDueTimeResolution() + "\"");
//		}
//		if (ticketRaw.getOverdueFirstResonseStatus() != null) {
//			updateValues.add("overdue_first_resonse_status = \"" + ticketRaw.getOverdueFirstResonseStatus() + "\"");
//		}
//		if (ticketRaw.getOverdueResolutionStatus() != null) {
//			updateValues.add("overdue_resolution_status = \"" + ticketRaw.getOverdueResolutionStatus() + "\"");
//		}
//		if (ticketRaw.getGroup() != null) {
//			updateValues.add("`group` = \"" + ticketRaw.getGroup() + "\"");
//		}
//		if (ticketRaw.getTicketStatus() != null) {
//			updateValues.add("ticket_status = \"" + ticketRaw.getTicketStatus() + "\"");
//		}
//		// all of set value is null
//		if (updateValues.isEmpty()) {
//			return "";
//		}
//		for (int i = 0; i < updateValues.size(); i++) {
//			if (i != updateValues.size() - 1) {
//				sql.append(updateValues.get(i) + ",");
//			} else {
//				sql.append(updateValues.get(i));
//			}
//		}
//
//		// add where condition
//		if (ticketRaw.getTicketId() != null) {
//			sql.append(" where ticket_id = \"" + ticketRaw.getTicketId() + "\"");
//		} else {
//			return "";
//		}
//		LOGGER.info("Update query: " + sql.toString());
//		return sql.toString();
//	}
//
//	private String getInsertReportQuery(String tableName, TicketRawEntity ticketRaw) {
//		String[] tableArr = tableName.split("_");
//		String tableNameReport = "pbi_ticket_action_" + tableArr[tableArr.length - 1];
//		StringBuffer sql = new StringBuffer()
//				.append("insert ignore into ")
//				.append(tableNameReport).append("(`ticket_id`, `date_time_key`, `ticket_action`) values ");
//		// set values
//		if (ticketRaw.getActualTimeResolution() != null) {
//			sql.append("(" + ticketRaw.getTicketId() + ", DATE_FORMAT('" + ticketRaw.getActualTimeResolution()
//					+ "','%Y%m%d%H'), 'Closed')");
//			if (ticketRaw.getRequestTime() != null) {
//				sql.append(", (" + ticketRaw.getTicketId() + ", DATE_FORMAT('" + ticketRaw.getRequestTime()
//						+ "','%Y%m%d%H'), 'Open')");
//			}
//		} else if (ticketRaw.getRequestTime() != null) {
//			sql.append("(" + ticketRaw.getTicketId() + ", DATE_FORMAT('" + ticketRaw.getRequestTime()
//					+ "','%Y%m%d%H'), 'Open')");
//		} else {
//			return "";
//		}
//
//		LOGGER.info("Update query for report table: " + sql.toString());
//		return sql.toString();
//	}
//
//	@Transactional
//	@SuppressWarnings("unchecked")
//	public void deleteTicket(Ticket ticket) {
//		TicketRawDto ticketRawDto = ticket.getData();
//		if (ticketRawDto != null) {
//			TicketRawEntity ticketRaw = convertDtoToEntity(ticketRawDto);
//			try {
//				int numberDeleteTicket = 0;
//				numberDeleteTicket = em
//						.createNativeQuery(
//								convertToQueryDeleteTicketId(ticket.getTableName(), ticketRaw.getTicketId()))
//						.executeUpdate();
//				LOGGER.info("number record delete: " + numberDeleteTicket);
//				if (numberDeleteTicket == 0) {
//					// check queue
//					if (rabbitAdmin.getQueueInfo(MessagingConfig.QUEUE).getMessageCount() != 0) {
//						LOGGER.info("Queue count " + rabbitAdmin.getQueueInfo(MessagingConfig.QUEUE).getMessageCount());
//						amqpTemplate.convertAndSend(MessagingConfig.EXCHANGE_DELETE,
//								MessagingConfig.ROUTING_KEY_DELETE, ticket);
//					}
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	private String convertToQueryDeleteTicketId(String tableName, String ticketId) {
//		StringBuffer sql = new StringBuffer()
//				.append("Delete From ")
//				.append(tableName);
//
//		// add where condition
//		if (ticketId != null) {
//			sql.append(" where ticket_id = \"" + ticketId + "\"");
//		} else {
//			return null;
//		}
//		LOGGER.info("Delete query: " + sql.toString());
//		return sql.toString();
//	}
}
