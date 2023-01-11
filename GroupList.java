import java.util.ArrayList;
import java.util.List;

public class GroupList {

	//a GroupList object represents a line of people waiting for something, divided into groups.
	//the front of the line is the first index, and the end of the line is the last used index.
	//the Persons in the line are organized into groups.
	//a Person flagged as a divider is just marking the boundaries between groups of people.
	//the GroupList can be closed or open, depending on whether the last group in line has been closed off with a divider.
	//there should never be a divider at the front of the line (index 0) or two dividers next to each other
	private ArrayList<Person> line;



	/**
	 * default constructor creates an empty GroupList, with no dividers.
	 */
	public GroupList() {
		//TODO create an empty ArrayList of Persons

		//Creating empty ArrayList
		this.line = new ArrayList<Person>();
	}


	/**
	 * specifying constructor creates a GroupList from an array of Persons.
	 * @throws IllegalArgumentException if the array is null, or contains a null Person,
	 *  or has 2 dividers in a row, or a divider at index 0
	 */
	public GroupList(Person[] line) {
		//TODO create an ArrayList of Persons and add all the persons in the array to the ArrayList
		// check for the illegal argument cases

		//Checking if Array is null
		if(line==null)
			throw new IllegalArgumentException("Array is null.");

		//Creating ArrayList with size of line array
		this.line = new ArrayList<Person>(line.length);

		//Searching through array
		for(int i=0; i<line.length; i++) {

			//Checking for null element
			if(line[i] == null) {
				throw new IllegalArgumentException("Element is null");
			}

			//Checking for divider at index 0
			if(line[0].isDivider()) {
				throw new IllegalArgumentException("Divider at index 0.");
			}
			//Checking if array has two dividers back to back
			if(i<line.length-1) {
				if(line[i].isDivider() && line[i+1].isDivider()) {//need to fix
					throw new IllegalArgumentException("Two dividers in a row.");
				}
			}

			//Adding input to ArrayList
			this.line.add(line[i]);
		}
	}



	/**
	 * the GroupList is ready for a new group if the last Person in the line is a divider, or if the line is empty.
	 * @return true if ready for a new group. false if not.
	 */
	public boolean readyForGroup() {
		//TODO implement per javadoc

		//Checking if ArrayList is empty or last element is a divider
		if(line.isEmpty() || line.get(line.size()-1).isDivider()) {
			return true;
		}
		return false;
	}


	/**
	 * attempts to 'close' the current group by placing a divider. Will only close a group of more than zero people.
	 * @throws IllegalStateException if there is no group to close
	 */
	public void closeGroup() {
		//TODO implement per javadoc

		//Checking if ArrayList is empty
		if(line.isEmpty()) {
			throw new IllegalStateException("No Group in Array.");
		}
		//Checking if the group is already closed
		if(line.get(line.size()-1).isDivider()) {
			throw new IllegalStateException("Group already closed.");
		}
		//Checking if last element is not a divider and adding divider
		else {
			line.add(new Person());
		}
	}


	/**
	 * removes and returns the 'next' group, meaning the one that starts at index 0
	 * do this whether the group is closed or not
	 * remove the divider at the end of the group, if there is one
	 * @return a List object with the removed people inside (do not include the divider)
	 * @throws IllegalStateException if the line is empty
	 */
	public List<Person> removeNextGroup() {
		//TODO implement per javadoc

		//Checking exception
		if(line.isEmpty()) {
			throw new IllegalStateException("ArrayList is empty.");
		}

		//Creating temp ArrayList to hold removed group
		ArrayList<Person> temp = new ArrayList<Person>();

		int count = 0;
		//Searching for divider
		for(int i=0; i<line.size(); i++) {
			//Keeping track of the index the divider is at
			if(line.get(i).isDivider()) {
				count = i;
				break;
			}
			else {
				count = line.size()-1;
			}
		}
		//Looping through to add to temp and remove from line
		for(int i=0; i<=count; i++) {
			temp.add(line.get(0));
			line.remove(0);
		}
		//Remove divider
		if(temp.get(temp.size()-1).isDivider()) {
			temp.remove(temp.size()-1);
		}
		return temp;
	}

	/**
	 * 
	 * add an individual Person to the line (cannot be null), adding to the open group if there is one,
	 * or else starting a new group.
	 * @param p a Person to add. will add p to the line whether the last group was closed or not,
	 * and will not close the group after p is added.
	 * @return true if the person was successfully added
	 * @throws IllegalArgumentException if the person to add is null or is a divider
	 */
	public void addIndividual(Person p) {
		//TODO implement per javadoc

		//Checking if input is a divider or null
		if(p==null || p.isDivider()) {
			throw new IllegalArgumentException("Person not valid.");
		}
		
		//Checking if line is ready for input to be added

		line.add(p);
	}

	/**
	 * 
	 * @param an Array of Persons to add to this Line. Adding is always done at the "end",
	 * and a group can only be added if the GroupList is ready for a group. (does not have an open group)
	 * the new group should be closed immediately
	 * @throws IllegalArgumentException if the group to add is null or empty, or contains a null Person or a divider
	 * @throws IllegalStateException if there is a group still open
	 */
	public void addGroup(Person[] group) {
		//TODO implement per javadoc

		if(group==null){
			throw new IllegalArgumentException("Group is invalid.");
		}
		if(group.length<=0) {
			throw new IllegalArgumentException("Group is invalid.");
		}
		//Looping through input array
		for(int i=0; i<group.length; i++) {

			//Checking if elements in array are dividers, null or empty
			if(group[i] == null || group[i].isDivider() || group.length < 0){
				throw new IllegalArgumentException("Group is invalid.");
			}
			
		}
		
		
		//Checking if last element is not a divider and ArrayList is larger than 1 element
		//if((!line.get(line.size()-1).isDivider()) && (line.size()>1)) {
		if(!readyForGroup()) {
			throw new IllegalStateException("Group is not closed.");
		}
		
		else {
			//Looping through group and adding array elements to ArrayList
			for(int i=0; i<group.length; i++) {
				line.add(group[i]);
			}
			this.line.add(new Person());
		}
	}



	/**
	 * compares lists and the people inside of them, ignoring dividers. You may be able to utilize
	 * errors being thrown as a way to find when the the lists are not the same.
	 * @param list a list of Persons to compare to this GroupList
	 * @return true if the lists have the same people in the same order, ignoring of dividers,
	 * or false otherwise.
	 */
	public boolean containsInOrder(List<Person> list) {
		//TODO implement per javadoc
		// keep track of two indices, one for each list
		// whenever you see a divider in either list, move the index forward
		// if you see two non-dividers, if they are not equal, return false
		// otherwise, move both indices forward
		// when one list runs out, if the other list has any NON-dividers left return false
		// if you didn't return false after checking everything, return true
		
		//chaecking if either list or line is null
		if(list==null || line==null) {
			return false;
		}
		
		//Checking if one is empty and the other isn't
		if(line.isEmpty() && list.size()>0) {return false;}
		if(list.isEmpty() && line.size()>0) {return false;}
		
		//Making a copy of line and list
		List<Person> lineCopy = new ArrayList<Person>(line);
		List<Person> listCopy = new ArrayList<Person>(list);
		
		//Looping through list and line and adding non-dividers to copies
		for(int i=0; i<lineCopy.size(); i++) {
			if(lineCopy.get(i).isDivider()) {
				lineCopy.remove(i);
				i--;;
			}
		}
		for(int i=0; i<listCopy.size(); i++) {
			if(listCopy.get(i).isDivider()) {
				listCopy.remove(i);
				i--;
			}
		}
		
		//Checking length of copies of list and line
		if(lineCopy.size() != listCopy.size()) {return false;}

		//Checking if elements are equal
		for(int i=0; i<lineCopy.size(); i++) {
				if(!lineCopy.get(i).equals(listCopy.get(i))) {
					return false;
				}
			
		}
		return true;
	}

}
