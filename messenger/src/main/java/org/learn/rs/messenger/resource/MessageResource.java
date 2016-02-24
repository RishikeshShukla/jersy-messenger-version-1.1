package org.learn.rs.messenger.resource;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.learn.rs.messenger.model.Message;
import org.learn.rs.messenger.service.MessageService;

@Path("/messages")
@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class MessageResource {

	MessageService messageMervice = new MessageService();

	@GET
	public Response getMessages() {
		List<Message> messages = messageMervice.getMessages();
		GenericEntity<List<Message>> messagesList = new GenericEntity<List<Message>>(messages) {
		};
		return Response.ok(messagesList).status(Status.OK).build();
	}

	@GET
	@Path("/{messageId}")
	public Response getMessage(@PathParam("messageId") Long messageId) {
		Message message = messageMervice.getMessage(messageId);
		return Response.ok(message).status(Status.OK).build();
	}

	@POST
	public Response addMessage(Message message) {
		Message addedMessage = messageMervice.addMessage(message);
		return Response.ok(addedMessage).status(Status.CREATED).build();

	}

	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") Long messageId, Message message) {
		message.setId(messageId);
		return messageMervice.updateMessage(message);
	}

	@DELETE
	@Path("/{messageId}")
	public Response deleteMessage(@PathParam("messageId") Long messageId) {
		String response = messageMervice.deleteMessage(messageId);
		return Response.ok(response).status(Status.OK).build();
	}

	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
}
