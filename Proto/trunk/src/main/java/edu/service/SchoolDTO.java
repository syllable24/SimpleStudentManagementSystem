package edu.service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import edu.data.Schools;



@XmlRootElement(name="school")
@XmlAccessorType(XmlAccessType.FIELD)
public class SchoolDTO
{
	/*
	 * A default constructor is needed to make the JAXB marshaler happy.
	 */
	protected SchoolDTO()
	{
		this(0, 0, 0);
	}
	
	public SchoolDTO(int id, int org, int model)
	{
		setId(id);
		setOrg(org);
                setModel(model);
	}
	
	
	/*
	 * Converter methods: Schools <=> SchoolDTO
	 */
	
	public SchoolDTO(Schools school)
	{
		this(school.getId(), school.getOrg(),school.getModel());
	}
	
	public Schools toSchools()
	{
		return new Schools(getId(), getOrg());
	}
	
	public static List<Schools> toUserList(List<SchoolDTO> dtoList)
	{
		if(dtoList == null)
			throw new IllegalArgumentException("Parameter List<UserDTO> is null!");
		
		List<Schools> result = new ArrayList<>();
                dtoList.forEach((dto) -> {
                    result.add(dto.toSchools());
            });
		return result;
	}
	
	public static List<SchoolDTO> toSchoolDTOList(List<Schools> list)
	{
		List<SchoolDTO> result = new ArrayList<>();
                list.forEach((school) -> {
                    result.add(new SchoolDTO(school));
            });
		return result;
	}
	
	
	/*
	 * Property: id:int
	 */
	@XmlAttribute
	private int id;
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	
	
	/*
	 * Property: description:String
	 */
	@XmlElement
	private int org;	
	public int getOrg()
	{
		return org;
	}
	public void setOrg(int org)
	{
		if(org == -1)
			throw new IllegalArgumentException("Invalid parameter description!");
		this.org = org;
	}
	
	/*
	 * Property: model:String
	 */
        @XmlElement
	private int model;	
	public int getModel()
	{
		return model;
	}
	public void setModel(int model)
	{
		if(model == -1)
			throw new IllegalArgumentException("Invalid parameter description!");
		this.model = model;
	}
	
	
		
	/*
	 * Object methods
	 */
	
	@Override
	public String toString()
	{
		return "School [id=" + getId() + ", org=" + getOrg() + "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SchoolDTO other = (SchoolDTO) obj;
		return id == other.id;
	}	
}
