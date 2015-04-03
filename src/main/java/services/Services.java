package services;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Emp;
import model.Survey;
import util.Constants;

import com.fasterxml.jackson.databind.ObjectMapper;

import command.CreateEmp;
import command.GetReport;
import command.ListEmp;
import command.SubmitSurvey;

@Path("empp")
public class Services {
	ObjectMapper mapper = new ObjectMapper();

	// get all employee
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response browseSongs(@QueryParam("offset") int offset,
			@QueryParam("count") int count) {
		ListEmp command = new ListEmp();
		ArrayList<Emp> list = command.execute();
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put(Constants.Pagination.DATA, list);
		hm.put(Constants.Pagination.OFFSET, offset);
		hm.put(Constants.Pagination.COUNT, count);
		String songString = null;
		try {
			songString = mapper.writeValueAsString(hm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(songString).build();
	}

	// Add a song
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response createSongs(String payload) {
		CreateEmp create = new CreateEmp();
		Emp s = null;
		String i = "";
		try {
			s = mapper.readValue(payload, Emp.class);
			
			System.out.println(s.getName());
			System.out.println(s.getPosition());
		} catch (Exception ex) {
			ex.printStackTrace();
			Response.status(400).entity("could not read string").build();
		}
		try {
			i = create.execute(s);
		} catch (Exception e) {
			e.printStackTrace();
			Response.status(500).build();
		}
		return Response.status(200).entity(i).build();
	}
	
	
	
	//***********************************************************************************************//
	
	// get all employee
	@Path("report")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getreports(@QueryParam("offset") int offset,
			@QueryParam("count") int count) {
		GetReport command = new GetReport();
		
		ArrayList<Survey> list = command.execute();
		for(Survey li : list){
			System.out.println(li.getName());
			System.out.println(li.getOverall());
		
		}
		
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put(Constants.Pagination.DATA, list);
		hm.put(Constants.Pagination.OFFSET, offset);
		hm.put(Constants.Pagination.COUNT, count);
		String str = null;
		try {
			str = mapper.writeValueAsString(hm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(str).build();
	}

	// Add a song
	@Path("survey")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response submitsurvey(String payload) {
		SubmitSurvey create = new SubmitSurvey();
		Survey s = null;
		String i = "";
		try {
			s = mapper.readValue(payload, Survey.class);
		
		} catch (Exception ex) {
			ex.printStackTrace();
			Response.status(400).entity("could not read string").build();
		}
		try {
			i = create.execute(s);
		} catch (Exception e) {
			e.printStackTrace();
			Response.status(500).build();
		}
		return Response.status(200).entity(i).build();
	}
	
	
	/*
	// Update a song
	@POST
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response updateSongs(String payload, @PathParam("id") int id) {
		UpdateSongCommand update = new UpdateSongCommand();
		Song s = null;
		try {
			s = mapper.readValue(payload, Song.class);
			s.setId(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			Response.status(400).entity("could not read string").build();
		}
		try {
			update.execute(s);
		} catch (Exception e) {
			e.printStackTrace();
			Response.status(500).build();
		}
		return Response.status(200).build();
	}
	
	*/
	
	/*
	// get song by Id
	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getSong(@PathParam("id") int id) {
		GetSongCommand command = new GetSongCommand();
		String songString = null;
		try {
			songString = mapper.writeValueAsString(command.execute(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(songString).build();
	}
*/
	// Delete a song
	// Search songs
}
