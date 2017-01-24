package pmr.probexp;

import java.util.ArrayList;

import layer.awareness.AbstractCapability;
import pmr.graph.Node;
import pmr.graph.WorldNode;

public abstract class ExpansionNode implements Node{
	
	private AbstractCapability capability;
	private ArrayList<ENode> destination;
	private ENode source;
	
	public ExpansionNode(ENode source, ArrayList<ENode> destination, AbstractCapability capability){
		this.capability = capability;
		this.source = source;
		this.destination = destination;
	}
	
	public AbstractCapability getCapability() {
		return capability;
	}
	
	public ENode getSource() {
		return source;
	}	
	
	public ArrayList<ENode> getDestination(){
		return this.destination;
	}
	
	public void setDestination(ArrayList<ENode> destination){
		this.destination = destination;
	}
	
	public void addDestination(ENode destination){
		this.destination.add(destination);
	}
	
	public void setCapability(AbstractCapability capability) {
		this.capability = capability;
	}
	
	public void setSource(ENode source) {
		this.source = source;
	}


	

}