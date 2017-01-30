package pmrtest.graph;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;

import layer.awareness.AbstractCapability;
import layer.semantic.StateOfWorld;
import net.sf.tweety.lp.asp.parser.ParseException;
import pmr.graph.WTS;
import pmr.graph.WorldNode;
import pmr.graph.XORNode;
import pmr.probexp.ENode;
import pmr.probexp.ExpansionNode;
import pmr.probexp.MultipleExpansionNode;
import pmr.probexp.NormalExpansionNode;

public class WTSTest {
		private WTS wts;
		
		private StateOfWorld w1;
		private StateOfWorld w2;
		private StateOfWorld w3;
		private StateOfWorld w4;
		private StateOfWorld w5;
		private StateOfWorld w6;
		private StateOfWorld w7;
		
		private WorldNode n1;
		private WorldNode n2;
		private WorldNode n3;
		private WorldNode n4;
		private WorldNode n5;
		
		private XORNode x1;
		private XORNode x2;
		private XORNode x3;
		private XORNode x4;
		
		private ENode e1;
		private ENode e2;
		private ENode e3;
		private ENode e4;
		private ENode e5;
		private ENode e6;
		
		private ExpansionNode ex1;
		private ExpansionNode ex2;
		private ExpansionNode ex3;
		private ExpansionNode ex4;
		private ExpansionNode ex5;
		private ExpansionNode ex6;
		
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
			w5.addFact_asString("Eagle(berry).");
			w5.addFact_asString("can_fly(berry).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}
		this.w6 = new StateOfWorld();
		try {
			w6.addFact_asString("Eagle(sid).");
			w6.addFact_asString("broken_wing(sid).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}
		this.w7 = new StateOfWorld();
		try {
			w7.addFact_asString("sparrow(claire).");
			w7.addFact_asString("can_fly(claire).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}
		

		this.n1 = new WorldNode(w4);
		this.n2 = new WorldNode(w3);
		this.n3 = new WorldNode(w1);
		this.n4 = new WorldNode(w2);
		this.n5 = new WorldNode(w5);
		
		this.e1 = new ENode(n1);
		this.e2 = new ENode(n2);
		this.e3 = new ENode(n3);
		this.e4 = new ENode(n4);
		this.e5 = new ENode(new WorldNode(null));
		this.e6 = new ENode(n5);
		
		ArrayList<ENode> ENodeList1 = new ArrayList<ENode>();
		ENodeList1.add(e1);
		ENodeList1.add(e2);
		ENodeList1.add(e3);
		
		ArrayList<ENode> ENodeList2 = new ArrayList<ENode>();
		ENodeList2.add(e2);
		
		ArrayList<ENode> ENodeList3 = new ArrayList<ENode>();
		ENodeList3.add(e3);

		this.ex1 = new NormalExpansionNode(e5, ENodeList2, cap1);
		this.ex2 = new NormalExpansionNode(e2, ENodeList3, cap2);
		this.ex3 = new NormalExpansionNode(e3, ENodeList1, cap3);
		this.ex4 = new MultipleExpansionNode(e4, ENodeList1, cap1);
		this.ex5 = new MultipleExpansionNode(e5, ENodeList1, cap1);
		this.ex6 = new MultipleExpansionNode(e6, ENodeList1, cap1);
		
		this.wts = new WTS();
	}

	/* w1 ha lo stesso DLPHead Set di w2
	 * n3 ha lo stesso StateOfWorld di n4
	 * e3 ha lo stesso StateOfWorld di e4
	 * e5 ha null come StateOfWorld
	 * x1 ha la stessa capability di x4
	 * ex1 ha la stessa capability di ex4
	 * ex1 contiene e5 che ha null come StateOfWorld
	 * ex3 ha la stessa destNodeList di ex4
	 * ex4 � di tipo MultipleExpansionNode
	 * ex5 � di tipo MultipleExpansionNode ed ha null come source
	 */
	
	
	@Test
	public void testNormalExpansionNode_2DifferentNodesNullSource() {
		wts.addNode(this.ex1);
		assertEquals(true, wts.getWTS().containsKey(ex1.getSource().getWorldNode()));
	}
	
	@Test
	public void testNormalExpansionNode_2DifferentNodesSource() {
		wts.addNode(this.ex2);
		assertEquals(true, wts.getWTS().containsKey(ex1.getSource().getWorldNode()));
	}
	
	@Test
	public void testNormalExpansionNode_2DifferentNodesDest() {
		wts.addNode(this.ex1);
		assertEquals(true, wts.getWTS().containsKey(ex1.getDestination().get(0).getWorldNode()));
	}
	
	@Test
	public void testNormalExpansionNode_FalseNode() {
		//ex2 = Source: w3, Dest: w1.
		//ex1 = Source: null, Dest: w3.
		wts.addNode(this.ex1);
		assertEquals(false, wts.getWTS().containsKey(ex2.getDestination().get(0).getWorldNode()));
	}
	
	@Test
	public void testMultipleExpansionNode_2DifferentNodesNullSource() {
		wts.addNode(this.ex5);
		assertEquals(true, wts.getWTS().containsKey(ex1.getSource().getWorldNode()));
	}
	
	@Test
	public void testMultipleExpansionNode_2DifferentNodesSource() {
		wts.addNode(this.ex4);
		assertEquals(true, wts.getWTS().containsKey(ex1.getSource().getWorldNode()));
	}
	
	@Test
	public void testMultipleExpansionNode_2DifferentNodesDest() {
		wts.addNode(this.ex4);
		assertEquals(true, (wts.getWTS().containsKey(ex4.getDestination().get(0).getWorldNode()) && 
				wts.getWTS().containsKey(ex4.getDestination().get(1).getWorldNode())) && 
				wts.getWTS().containsKey(ex4.getDestination().get(2).getWorldNode()));		
	}
	
	@Test
	public void testMultipleExpansionNode_FalseNodeEqualsWorld() {
		//ex5 = Source: null, Dest: w4, w3, w1.
		//ex4 = Source: w2, Dest: w4, w3, w1.
		//DLPHead w1 = DLPHead w2
		wts.addNode(this.ex5);
		assertEquals(true, wts.getWTS().containsKey(ex4.getSource().getWorldNode()));
	}
	@Test
	public void testMultipleExpansionNode_FalseNode() {
		//ex5 = Source: null, Dest: w4, w3, w1.
		//ex4 = Source: w2, Dest: w4, w3, w1.
		//DLPHead w1 = DLPHead w2
		wts.addNode(this.ex5);
		assertEquals(false, wts.getWTS().containsKey(ex6.getSource().getWorldNode()));
	}
	
	@Test
	public void testMultipleExpansionNode_Link() {
		//ex4 = Source: w2, Dest: w4, w3, w1.
		//DLPHead w1 = DLPHead w2
		//Testo se si crea l'OPNode
		wts.addNode(this.ex4);
		assertEquals(1, wts.getWTS().get(ex4.getSource().getWorldNode()).getOPNodeList().size());
	}
}