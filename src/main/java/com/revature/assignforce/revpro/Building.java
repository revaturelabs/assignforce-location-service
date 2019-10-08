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

import com.revature.assignforce.beans.Location;
import com.revature.assignforce.repos.BuildingRepository;
import com.revature.assignforce.repos.LocationRepository;

public class Building
{
	/**@author Minqiu Yu
	 * 
	 * The purpose of this method is to return all currently listed values that are classified as Building in the RevaturePro database.
	 * It should send a GET request and convert the responses into a List, preferably through Jackson or GSON.
	 * 
	 * @return This method should return a list of all buildings listed in RevaturePro as a List.
	 */
	public List<Building> getAll()
	{
		List<Building> allBuildings = new ArrayList<Building>();
		return null;
	}
	/**@author Minqiu Yu
	 * 
	 * The purpose of this method is to return an optional value that corresponds to a particular building in the RevaturePro database.
	 * It should send a GET request and convert the responses into a List, preferably through Jackson or GSON. Take advantage of the 
	 * Optional type to check if the building in question is actually found or not. The isPresent() method will tell you if the object 
	 * exists or is null, and the get() method will actually return the value.
	 * 
	 * @param id: This is the parameter referring to the internal numerical identifier of a building listed by Revature. 
	 * @return This method returns an Optional value depending on whether the specific Building is found.
	 */
	public Optional<Building> findByID(int id)
	{
		return null;
	}
	/**@author Minqiu Yu
	 * 
	 * The purpose of this method is to update and save the new state of Building object b. It should utilize a PUT request with the
	 * updated record to the RevaturePro database.
	 * 
	 * @param b: This is a string that the building is associated with. 
	 * @return This method alters the record of building b as the user sees fit, and returns the updated record. 
	 */
	public Building update(Building b) 
	{
		return null;
	}
	/**@author Minqiu Yu
	 * 
	 * The purpose of this method is to create and save a new Building b. It should utilize a POST request to insert the new Building
	 * into the RevaturePro database.
	 * 
	 * @param b: This is a string that the building is associated with.  
	 * @return This method allows for the insertion of a new building to be used in the system and returns the newly-created record. 
	 */
	public Building create(Building b) 
	{
		return null;
	}
	/**@author Minqiu Yu
	 * 
	 * The purpose of this method is to delete Building b as specified by the user. It should send a DELETE request 
	 * to delete the Building object from the RevaturePro database. 
	 * 
	 * @param b: This is a string that the building is associated with.
	 * @return This method purges the specific building b from the database.
	 */
	public void delete(Building b) 
	{
	}
//	public List<Building> findByName(String query)
//	{
//		return null;
//	}
}
