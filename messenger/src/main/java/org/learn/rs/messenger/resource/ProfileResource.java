package org.learn.rs.messenger.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import org.learn.rs.messenger.model.Message;
import org.learn.rs.messenger.model.Profile;
import org.learn.rs.messenger.service.MessageService;
import org.learn.rs.messenger.service.ProfileService;

@Path("/profiles")
@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class ProfileResource {

	private ProfileService profileService = new ProfileService();

	/* Simple response without all dependent Objects */
	/*
	 * @GET public List<Profile> getProfiles() { return
	 * profileService.getAllProfiles(); }
	 */

	/* Response with all dependent Object */
	@GET
	public Response getProfiles() {
		List<Profile> profiles = profileService.getAllProfiles();
		List<Profile> resultList = new ArrayList<>();
		Map<Long, Message> messages = new MessageService().getAllMessages();
		for (Profile profile : profiles) {
			profile.addMessage(messages.get(profile.getId()));

			resultList.add(profile);
		}
		GenericEntity<List<Profile>> profileList = new GenericEntity<List<Profile>>(resultList) {
		};

		return Response.ok(profileList).status(Response.Status.OK).build();
	}

	@POST
	public Response addProfile(Profile profile) {
		Profile addedProfile = profileService.addProfile(profile);
		return Response.ok(addedProfile).status(Response.Status.CREATED).build();
	}

	@GET
	@Path("/{profileId}")
	public Response getProfile(@PathParam("profileId") Long id) {
		Profile profile = profileService.getProfile(id);
		return Response.ok(profile).status(Response.Status.OK).build();
	}

	@PUT
	@Path("/{profileId}")
	public Response updateprofile(@PathParam("profileId") Long profileId, Profile profile) {
		profile.setId(profileId);
		Profile updatedProfile = profileService.updateProfile(profile);
		return Response.ok(updatedProfile).status(Response.Status.OK).build();
	}

	@DELETE
	@Path("/{profileId}")
	public Response deleteProfile(@PathParam("profileId") Long profileId) {
		Profile deletedProfile = profileService.removeProfile(profileId);
		return Response.ok(deletedProfile).status(Response.Status.OK).build();
	}

}