package pmrtest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import layer.awareness.AbstractCapability;
import layer.semantic.StateOfWorld;
import net.sf.tweety.lp.asp.parser.ParseException;
import pmr.WTS;
import pmr.graph.Node;
import pmr.graph.WorldNode;

public class WTSVisitTest {

	
	private WTS wts;
	
	private StateOfWorld w1;
	private StateOfWorld w2;
	private StateOfWorld w3;
	private StateOfWorld w4;
	private StateOfWorld w5;
	
	private Node n1;
	private Node n2;
	private Node n3;
	private Node n4;
	private Node n5;
	
	private AbstractCapability cap1;
	private AbstractCapability cap2;
	private AbstractCapability cap3;
	
@Before
public void setUp(){
	this.cap1 = new AbstractCapability("uno",null,null,null);
	this.cap2 = new AbstractCapability("due",null,null,null);
	this.cap3 = new AbstractCapability("tre",null,null,null);
	
	this.w1 = new StateOfWorld();
	try {
		w1.addFact_asString("penguin(tweety).");
		w1.addFact_asString("penguin(tweety).");
	} catch (ParseException e) {
		e.printStackTrace();
	} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
		e.printStackTrace();
	}
	this.w2 = new StateOfWorld();
	try {
		w2.addFact_asString("penguin(tweety).");
		w2.addFact_asString("penguin(tweety).");
	} catch (ParseException e) {
		e.printStackTrace();
	} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
		e.printStackTrace();
	}
	this.w3 = new StateOfWorld();
	try {
		w3.addFact_asString("penguin(tweety).");
		w3.addFact_asString("parrot(polly).");
	} catch (ParseException e) {
		e.printStackTrace();
	} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
		e.printStackTrace();
	}
	this.w4 = new StateOfWorld();
	try {
		w4.addFact_asString("sparrow(sid).");
		w4.addFact_asString("broken_wing(sid).");
	} catch (ParseException e) {
		e.printStackTrace();
	} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
		e.printStackTrace();
	}
	this.w5 = new StateOfWorld();
	try {
		w4.addFact_asString("crow(Victor).");
		w4.addFact_asString("broken_wing(Victor).");
	} catch (ParseException e) {
		e.printStackTrace();
	} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
		e.printStackTrace();
	}
	
	//Radice(n1) due rami figli: (n2) (n3), figlio(n2) : un ramo figlio(n4)
	//n4 contiene uno stato del mondo uguale a n3, quindi l'ipotetico figlio di n2 � in realt� n3, quindi
	//dovrebbe nascere un nuovo arco da (n2) a (n3)
	this.n1 = new WorldNode(w4);
	this.n2 = new WorldNode(w3);
	this.n3 = new WorldNode(w1);
	this.n4 = new WorldNode(w2);
	this.n5 = new WorldNode(w5);
	
	this.wts = new WTS();
	this.wts.addNode(this.n1);
	this.wts.addNode(this.n2);
	this.wts.addNode(this.n3);
	this.wts.addNode(this.n4);
}
	@Test
	public void test_size_1() {
		this.wts.addEdge(new WorldNode(null), n1, this.cap1);
		this.wts.addEdge(new WorldNode(null), n2, this.cap2);
		this.wts.addEdge(n1, n3, this.cap3);
		this.wts.addEdge(n2, n4, this.cap1);
		this.wts.addEdge(n1, n4, this.cap1);
		
		//Radice -> n1,n2.  n1->n3,n4.  n2->n4.
		//Numero nodi in pathlist = 4 partendo da WorldNode(null)
		assertEquals(4, this.wts.WTSVisit(new WorldNode(null)).size());
	}
	
	@Test
	public void test_size_2() {
		this.wts.addNode(this.n5);
		this.wts.addEdge(new WorldNode(null), n1, this.cap1);
		this.wts.addEdge(new WorldNode(null), n2, this.cap2);
		this.wts.addEdge(n1, n3, this.cap3);
		this.wts.addEdge(n2, n4, this.cap1);
		this.wts.addEdge(n1, n4, this.cap1);
		this.wts.addEdge(n2, n5, this.cap2);
		
		//Nuovo nodo n5.
		//Radice -> n1,n2.  n1->n3,n4.  n2->n4,n5.
		//Numero nodi in pathlist = 4 partendo da WorldNode(null)
		assertEquals(5, this.wts.WTSVisit(new WorldNode(null)).size());
	}

}
