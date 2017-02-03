package pmr.probexp;

import java.util.ArrayList;
import java.util.Comparator;

import layer.awareness.net.Token;
import pmr.graph.Node;
import pmr.graph.WorldNode;
/**
 * The Class ENode.
 * @author Alessandro Fontana, Mirko Zichichi
 */
public class ENode implements Node{
	
	/** The WorldNode coming from the WTS */
	private WorldNode node;
	
	/** The Tokens to put on the Net associated to the node */
	private ArrayList<Token> tokens;
	
	/** The Score associated to the node */
	private int score;
	
	/** Indicates if the node is an ExitNode */
	private boolean exit;
	
	/**
	 * Instantiates a new empty ENode with only the WorldState.
	 *
	 * @param node
	 *            the WorldNode
	 */
	public ENode( WorldNode node ) {
		this.node = node;
		this.exit = false;
	}
	
	/**
	 * Instantiates a new full ENode with all the attributes.
	 *
	 * @param node
	 *            the WorldNode
	 * @param tokens
	 *            the node's Tokens
	 * @param score
	 *            the node's Score
	 * @param exit
	 *            the Exit condition
	 */
	public ENode( WorldNode node, ArrayList<Token> tokens, int score, boolean exit ) {
		this.node = node;
		this.tokens = tokens;
		this.score = score;
		this.exit = exit;
	}
	
	/**
	 * Gets the world node.
	 *
	 * @return the WorldNode
	 */
	public WorldNode getWorldNode() {
		return node;
	}
	
	/**
	 * Sets the Tokens List.
	 *
	 * @param tokens
	 *            the new Tokens List
	 */
	public void setTokens(ArrayList<Token> tokens){
		this.tokens = tokens;
	}
	
	/**
	 * Sets the Exit condition.
	 *
	 * @param exit
	 *            Condition that states if the node is ExitNode or not
	 */
	public void setExit(boolean exit){
		this.exit = exit;
	}
	
	/**
	 * Adds a new Token.
	 *
	 * @param token
	 *            the new Token
	 */
	public void addToken(Token token){
		this.tokens.add(token);
	}
	
	/**
	 * Gets the Tokens List
	 *
	 * @return the Tokens List
	 */
	public ArrayList<Token> getTokens() {
		return tokens;
	}
	
	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public int getScore(){
		return this.score;
	}
	
	/**
	 * Sets the score.
	 *
	 * @param score
	 *            the new score
	 */
	public void setScore(int score){
		this.score = score;
	}
	
	/**
	 * Checks if is exit node.
	 *
	 * @return true, if is exit node
	 */
	public boolean isExitNode(){
		return this.exit;
	}
	
	/**
	 * Gets the score comparator, used to keep lists updated using the score
	 *
	 * @return the score comparator
	 */
	public static Comparator<ENode> getScoreComparator(){
		Comparator<ENode> comp = new Comparator<ENode>(){
			@Override
			public int compare(ENode e1, ENode e2){
				return e2.score - e1.score;
			}
		};
		return comp;
	}
	
}
