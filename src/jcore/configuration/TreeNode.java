package configuration;

/**
 * This Class describes a generic node in the solution Tree.
 * 
 * @author Mirko Avantaggiato
 */
public abstract class TreeNode {
	private int nodeType;
	private String generatingCapability;

	public int getNodeType() {
		return nodeType;
	}

	public void setNodeType(int nodeType) {
		this.nodeType = nodeType;
	}

	public String getGeneratingCapability() {
		return generatingCapability;
	}

	public void setGeneratingCapability(String generatingCapability) {
		this.generatingCapability = generatingCapability;
	}

}
