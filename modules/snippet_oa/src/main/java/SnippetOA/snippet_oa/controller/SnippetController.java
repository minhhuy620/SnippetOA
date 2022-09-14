package SnippetOA.snippet_oa.controller;

import SnippetOA.snippet_oa.dto.SnippetDto;
import SnippetOA.snippet_oa.entity.Snippet;
import SnippetOA.snippet_oa.service.SnippetService;
import SnippetOA.snippet_oa.wrapper.ApiWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/snippet")
public class SnippetController {

	private static final Logger LOGGER = LogManager.getLogger(SnippetController.class);

	@Autowired
	private SnippetService snippetService;

	@GetMapping("/init")
	public ResponseEntity<ApiWrapper> getAllSnippets(){
		ApiWrapper data = snippetService.getAllSnippets();
		return ResponseEntity.ok(data);
	}
	@PostMapping("/save")
	public ResponseEntity<ApiWrapper> createSnippet(@RequestBody SnippetDto snippet) {
		ApiWrapper data = snippetService.saveSnippet(snippet);
		return ResponseEntity.ok(data);
	}
//	@PostMapping("/queue/ticket/add")
//	public ApiWrapper sendTicketsMessage(@RequestBody TicketObject ticketObject) {
//		LOGGER.info("Start add ticket");
//		int count = 0;
//		try {
//			if (ticketObject != null) {
//				List<TicketRawDto> listTicketRawDto = ticketObject.getData();
//				count = listTicketRawDto.size();
//				for (TicketRawDto ticketRaw : listTicketRawDto) {
//					Ticket ticket = new Ticket();
//					ticket.setTableName(ticketObject.getTableName());
//					ticket.setData(ticketRaw);
//					amqpTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, ticket);
//				}
//			} else {
//				return new ApiWrapper(true, 1, "Data not found", null);
//			}
//			return new ApiWrapper(true, 0, "Added " + count + " items to queue sucessfully", null);
//		} catch (Exception e) {
//			return new ApiWrapper(true, 1, "Data added to queue failed", null);
//		}
//	}
//
//	@PostMapping("/queue/ticket/update")
//	public ApiWrapper updateTicket(@RequestBody TicketObject ticketObject) {
//		LOGGER.info("Start update ticket ");
//		int count = 0;
//		try {
//			if (ticketObject != null) {
//				List<TicketRawDto> ticketList = ticketObject.getData();
//				count = ticketList.size();
//				for (TicketRawDto ticketRaw : ticketList) {
//					Ticket ticket = new Ticket();
//					ticket.setTableName(ticketObject.getTableName());
//					ticket.setData(ticketRaw);
//					amqpTemplate.convertAndSend(MessagingConfig.EXCHANGE_UDP,
//							MessagingConfig.ROUTING_KEY_UDP, ticket);
//				}
//			} else {
//				return new ApiWrapper(true, 1, "Data not found", null);
//			}
//			return new ApiWrapper(true, 0, "Added " + count + "items to update queue sucessfully", null);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ApiWrapper(true, 1, e.getMessage(), null);
//		}
//	}
//
//	@PostMapping("/queue/ticket/delete")
//	public ApiWrapper deleteTicket(@RequestBody TicketObject ticketObject) {
//		LOGGER.info("Start delete ticket");
//		int count = 0;
//		try {
//			if (ticketObject != null) {
//				List<TicketRawDto> ticketList = ticketObject.getData();
//				count = ticketList.size();
//				for (TicketRawDto ticketRaw : ticketList) {
//					Ticket ticket = new Ticket();
//					ticket.setTableName(ticketObject.getTableName());
//					ticket.setData(ticketRaw);
//					amqpTemplate.convertAndSend(MessagingConfig.EXCHANGE_DELETE,
//							MessagingConfig.ROUTING_KEY_DELETE, ticket);
//				}
//			} else {
//				return new ApiWrapper(true, 1, "Data not found", null);
//			}
//			return new ApiWrapper(true, 0, "Added " + count + " items to delete queue sucessfully", null);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ApiWrapper(true, 1, e.getMessage(), null);
//		}
//	}
}
