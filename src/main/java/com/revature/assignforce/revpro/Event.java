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

public class Event
{	
	/**@author Minqiu Yu
	 * 
	 * The purpose of this method is to return all currently listed values that are classified as Event in the RevaturePro database.
	 * It should send a GET request and convert the responses into a List, preferably through Jackson or GSON.
	 * 
	 * @return This method should return a list of all events listed in RevaturePro as a List.
	 */
	public List<Event> getAll()
	{
		List<Event> allBuildings = new ArrayList<Event>();
		return null;
	}
	/**@author Minqiu Yu
	 * 
	 * The purpose of this method is to return an optional value that corresponds to a particular event in the RevaturePro database.
	 * It should send a GET request and convert the responses into a List, preferably through Jackson or GSON. Take advantage of the 
	 * Optional type to check if the event in question is actually found or not. The isPresent() method will tell you if the object 
	 * exists or is null, and the get() method will actually return the value.
	 * 
	 * @param id: This is the parameter referring to the internal numerical identifier of an event listed by Revature. 
	 * @return This method returns an Optional value depending on whether the specific Event is found.
	 */
	public Optional<Event> findByID(int id)
	{
		return null;
	}
	/**@author Minqiu Yu
	 * 
	 * The purpose of this method is to update and save the new state of Event object e. It should utilize a PUT request with the
	 * updated record to the RevaturePro database.
	 * 
	 * @param e: This is a string that the event is associated with. 
	 * @return This method alters the record of event e as the user sees fit, and returns the updated record. 
	 */
	public Event update(Event e) 
	{
		return null;
	}
	/**@author Minqiu Yu
	 * 
	 * The purpose of this method is to create and save a new Event e. It should utilize a POST request to insert the new Event
	 * into the RevaturePro database.
	 * 
	 * @param e: This is a string that the event is associated with.  
	 * @return This method allows for the insertion of a new event to be used in the system and returns the newly-created record. 
	 */
	public Event create(Event e) 
	{
		return null;
	}
	/**@author Minqiu Yu
	 * 
	 * The purpose of this method is to delete Event e as specified by the user. It should send a DELETE request 
	 * to delete the Event object from the RevaturePro database. 
	 * 
	 * @param e: This is a string that the building is associated with.
	 * @return This method purges the specific event e from the database.
	 */
	public void delete(Event e) 
	{
		
	}
//	public List<Event> findByName(String query)
//	{
//		return null;
//	}
//	public List<Event> findByLocation(String query)
//	{
//		return null;
//	}
//	public List<Event> findByBuilding(String query)
//	{
//		return null;
//	}
}
