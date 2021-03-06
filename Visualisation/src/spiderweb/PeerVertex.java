/**
 * Contains the class definition for a PeerVertex in the P2P Network Graph project.
 * @author Matthew Smith
 * @version May 12, 2011
 */
package spiderweb;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>A class which represents a peer as a vertex in the P2P network visualization.</p>
 * <ul>
 * <li>A peer is drawn differently than a document, as well, it has more states.</li>]
 * <li>Maintains a list of outgoing and incoming queries.</li>
 * <li>Has a state which describes if it has sent a query or not.</li>
 * <li>Has a state which describes if it has received a query or not.</li>
 * </ul>
 * @author Matthew Smith
 * @version May 12, 2011
 */
public class PeerVertex extends P2PVertex {

	//[start] Private Attributes
	private List<Integer> outgoingQueries; //List of the queries that this peer has sent.
	private List<Integer> incomingQueries; //List of the queries that have reached this peer.
	//[end] Private Attributes
	
	//[start] Constructors
	
	/**
	 * Create a vertex which represents a peer in the P2P network visualization.
	 * @param key The identifier which defines this Peer
	 */
	public PeerVertex(Integer key) {
		super(key);
		outgoingQueries = new LinkedList<Integer>();
		incomingQueries = new LinkedList<Integer>();
	}
	//[end] Constructors
	
	//[start] Query Manager Methods
	/**
	 * Adds the query ID to a list of outgoing queries.
	 * @param queryMessageID The ID of the outgoing query.
	 * @return <code>true</code> if the query was added properly.
	 */
	public boolean query(int queryMessageID) {
		return outgoingQueries.add(new Integer(queryMessageID));
	}
	
	/**
	 * Removes the query ID from the list of outgoing queries.
	 * @param queryMessageID The ID of the outgoing query to be removed.
	 * @return <code>true</code> if the query was removed properly.
	 */
	public boolean endQuery(int queryMessageID) {
		return outgoingQueries.remove(new Integer(queryMessageID));
	}
	
	/**
	 * Adds the query ID to a list of incoming queries.
	 * @param queryMessageID The ID of the incoming query.
	 * @return <code>true</code> if the query was added properly.
	 */
	public boolean receiveQuery(int queryMessageID) {
		return incomingQueries.add(new Integer(queryMessageID));
	}
	
	/**
	 * Removes the query ID from the list of incoming queries.
	 * @param queryMessageID The ID of the incoming query to be removed.
	 * @return <code>true</code> if the query was removed properly.
	 */
	public boolean endReceivedQuery(int queryMessageID) {
		return incomingQueries.remove(new Integer(queryMessageID));
	}
	//[end] Query Manager Methods
	
	//[start] State Methods
	
	/**
	 * Checks the if any queries are present in the list of outgoing queries.
	 * @return <code>true</code> if the peer has any active outgoing queries.
	 */
	public boolean hasOutgoingQueries() {
		return !outgoingQueries.isEmpty();
	}
	
	/**
	 * Checks the if any queries are present in the list of incoming queries.
	 * @return <code>true</code> if the peer has any active incoming queries.
	 */
	public boolean hasIncomingQueries() {
		return !incomingQueries.isEmpty();
	}
	
	/**
	 * Checks the list of outgoing queries for a query with a specific ID.
	 * @param queryMessageID	The query ID to look for.
	 * @return	<code>true</code> if the peer has an outgoing query with the passed ID.
	 */
	public boolean hasOutgoingQuery(Integer queryMessageID){
		if(outgoingQueries.contains(queryMessageID)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks the list of incoming queries for a query with a specific ID.
	 * @param queryMessageID	The query ID to look for.
	 * @return	<code>true</code> if the peer has an incoming query with the passed ID.
	 */
	public boolean hasIncomingQuery(Integer queryMessageID) {
		if(incomingQueries.contains(queryMessageID)) {
			return true;
		}
		return false;
	}
	//[end] State Methods
	
	//[start] Overridden Methods
	
	@Override
	public String toString() {
		return "P"+super.toString();
	}
	//[end] Overridden Methods
}