/**
 * @author Minqiu Yu
 *
 *This Class is a prepared skeleton(no functionality) in anticipation for the integration of AssignForce into RevaturePro. 
 *The bellow methods are guidelines to help you identify the intended functionality we had in mind. Your batch may have a
 * shift in purpose by the time this project falls into your hands.
 *
 *The purpose of this class is to house methods for either retrieving and/or modifying information from the Revature Pro API.
 *Each method needs to handle both the communication with the Revature Pro API and the conversion of the data to 
 *AssignForce.
 *
 *Keep in mind that it is important that the information originating from RevPro needs to be identifiable as such and not
 *confused with information that is originating from within AssignForce. 
 */

package com.revature.assignforce.revpro;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.assignforce.repos.BuildingRepository;
import com.revature.assignforce.repos.LocationRepository;

public class Location
{
	/**@author Minqiu Yu
	 * 
	 * The purpose of this method is to return all currently listed values that are classified as Location in the RevaturePro database.
	 * It should send a GET request and convert the responses into a List, preferrably through Jackson or GSON.
	 * 
	 * @return This method should return a list of all locations listed in RevaturePro as a List.
	 */
	public List<Location> getAll()
	{
		List<Location> allLocations = new ArrayList<Location>();
		return null;
	}
	/**@author Minqiu Yu
	 * 
	 * The purpose of this method is to return an optional value that corresponds to a particular Location in the RevaturePro database.
	 * It should send a GET request and convert the responses into a List, preferrably through Jackson or GSON. Take advantage of the 
	 * Optional type to check if the Location in question is actually found or not. The isPresent() method will tell you if the object 
	 * exists or is null, and the get() method will actually return the value.
	 * 
	 * @param id: This is the parameter referring to the internal numerical identifier of a Location listed by Revature. 
	 * @return This method returns an Optional value depending on whether the specific Location is found.
	 */
	public Optional<Location> findByID(int id)
	{
		return null;
	}
	/**@author Minqiu Yu
	 * 
	 * The purpose of this method is to update and save the new state of Location object l. It should utilize a PUT request with the
	 * updated record to the RevaturePro database.
	 * 
	 * @param l: This is a string that the Location is associated with. 
	 * @return This method alters the record of Location b as the user sees fit, and returns the updated record. 
	 */
	public Location update(Location b) 
	{
		return null;
	}
	/**@author Minqiu Yu
	 * 
	 * The purpose of this method is to create and save a new Location l. It should utilize a POST request to insert the new Location
	 * into the RevaturePro database.
	 * 
	 * @param l: This is a string that the Location is associated with.  
	 * @return This method allows for the insertion of a new Location to be used in the system and returns the newly-created record. 
	 */
	public Location create(Location b) 
	{
		return null;
	}
	/**@author Minqiu Yu
	 * 
	 * The purpose of this method is to delete Location l as specified by the user. It should send a DELETE request 
	 * to delete the Location object from the RevaturePro database. 
	 * 
	 * @param l: This is a string that the Location is associated with.
	 * @return This method purges the specific Location l from the database.
	 */
	public void delete(Location l) 
	{
	}
//	public List<Location> findByName(String query)
//	{
//		return null;
//	}
}
