package pmr.graph;

import layer.awareness.AbstractCapability;

/**
 * The Class NormalEdge. it connects a WorldNode to another.
 */
public class NormalEdge implements Edge {
	
	/** The source. */
	private WorldNode source;
	
	/** The destination. */
	private WorldNode destination;
	
	/** The capability. */
	private AbstractCapability capability;

	/**
	 * Instantiates a new normal edge.
	 *
	 * @param source
	 *            the source
	 * @param destination
	 *            the destination
	 * @param capability
	 *            the capability
	 */
	public NormalEdge(WorldNode source, WorldNode destination, AbstractCapability capability){
		super();
		this.source = source;
		this.destination = destination;
		this.capability = capability;
	}
	
	/**
	 * Gets the source.
	 *
	 * @return the source node of this edge.
	 */
	public WorldNode getSource(){
		return this.source;
	}

	/**
	 * Gets the destination.
	 *
	 * @return the destination node of this edge
	 */
	public WorldNode getDestination() {
		return this.destination;
	}
	
	/**
	 * Gets the capability.
	 *
	 * @return the capability used on the expand of the sourceNode of this edge
	 */
	public AbstractCapability getCapability() {
		return this.capability;
	}
	
	/**
	 * Sets the source.
	 *
	 * @param source
	 *            the new source
	 */
	public void setSource(WorldNode source){
		this.source = source;
	}

	/**
	 * Sets the destination.
	 *
	 * @param destination
	 *            the new destination
	 */
	public void setDestination(WorldNode destination) {
		this.destination = destination;
	}
	
	/**
	 * Sets the capability.
	 *
	 * @param capability
	 *            the new capability
	 */
	public void setCapability(AbstractCapability capability) {
		this.capability = capability;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj){
		if(obj instanceof NormalEdge){
			NormalEdge temp = (NormalEdge)obj;
			
			if(temp.getSource() == null && this.source != null)					return false;
			else if(temp.getSource() != null && this.source == null)			return false;
	
			if(temp.getDestination() == null && this.destination != null)		return false;
			else if(temp.getDestination() != null && this.destination == null)	return false;
			
			if(this.source == null && temp.getSource() == null)
				if(this.destination == null && temp.getDestination() == null )	return true;
			
			if(this.source.equals(temp.getSource()) == true)					
				if(this.destination.equals(temp.getDestination()) == true)	return true;
			
			return false;
			
		}
		else return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode(){
		return this.source.hashCode() + this.destination.hashCode() + this.capability.hashCode();
	}
	}

