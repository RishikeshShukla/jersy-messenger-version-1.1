package org.learn.rs.messenger.resource;

import java.util.List;

import javax.ws.rs.Consumes;
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

import org.learn.rs.messenger.model.Comment;
import org.learn.rs.messenger.service.CommentService;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class CommentResource {

	private CommentService commentService = new CommentService();

	@GET
	public Response getAllComments(@PathParam("messageId") long messageId) {
		List<Comment> comments = commentService.getAllComments(messageId);
		GenericEntity<List<Comment>> commentList = new GenericEntity<List<Comment>>(comments) {
		};
		return Response.ok(commentList).status(Status.OK).build();
	}

	@POST
	public Response addComment(@PathParam("messageId") long messageId, Comment comment) {
		Comment createdComment = commentService.addComment(messageId, comment);
		return Response.ok(createdComment).status(Status.CREATED).build();
	}

	@PUT
	@Path("/{commentId}")
	public Response updateComment(@PathParam("messageId") long messageId, @PathParam("commentId") long id,
			Comment comment) {
		comment.setId(id);
		Comment updatedComment = commentService.updateComment(messageId, comment);
		return Response.ok(updatedComment).status(Status.OK).build();
	}

	@DELETE
	@Path("/{commentId}")
	public void deleteComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		commentService.removeComment(messageId, commentId);
	}

	@GET
	@Path("/{commentId}")
	public Response getMessage(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		Comment comment = commentService.getComment(messageId, commentId);
		return Response.ok(comment).status(Status.OK).build();
	}

}
