package edu.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import edu.data.Schools;
import edu.data.SchoolDao;
import javax.inject.Named;

@Path("/v1/schools")
@Stateless
@Named
public class SchoolResourceEJB
{
	private final Logger LOG = Logger.getLogger(SchoolResourceEJB.class);
	
	private List<Schools> schoolList;
        private Integer id;
        private Schools school;
        
        @Inject SchoolDao dao;
        
        //@Inject @Dao
        //CrudDao<Schools,Integer> genericDao;
      		
	public SchoolResourceEJB()
	{
		LOG.debug(SchoolResourceEJB.class.getName() + " created");
	}
	
	
	@POST
	@Consumes({"application/xml", "application/json"})
	public void insert(SchoolDTO school)
	{
		LOG.debug("insert: " + school);
		//dao.create(school.getOrg());
	}
	
	
	@PUT
	@Consumes("application/xml")
	public void update(SchoolDTO dto) throws Exception
	{
		LOG.debug("update to " + dto.toString());
		
                dao.update(school);
	}

	
	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") int id)
	{
		LOG.debug("delete: " + id);
		
		// TODO
	}
	
	
	@GET
	@Produces({"application/json"})
	public List<SchoolDTO> findAll()
	{
		LOG.debug("findAll()");
		
		List<Schools> list = dao.findAll();
		List<SchoolDTO> result = SchoolDTO.toSchoolDTOList(list);
		LOG.info("size = " + result.size());
		return result;
	}
	
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public SchoolDTO findById(@PathParam("id") int id) 
	{
		LOG.debug("findById(" + id + ")");

		Schools school = (Schools) dao.find(id);
                return new SchoolDTO(school);
	}
}
