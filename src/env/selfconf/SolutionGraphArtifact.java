// CArtAgO artifact code for project musa_2_0

package selfconf;

import cartago.*;
import jason.asSyntax.Term;
import layer.semantic.StateOfWorld;
import pmr.SolutionGraph;
import pmr.probexp.ENode;
import pmr.probexp.ExpansionNode;
import translator.JasonENode;
import translator.JasonExpansionNode;
import translator.JasonStateOfWorld;
import translator.TranslateError;

public class SolutionGraphArtifact extends Artifact {
	
	private SolutionGraph graph;
	
	void init() {
		// initialize graph
		graph = new SolutionGraph();
		
		System.out.println("creato artefatto solution graph");
	}
	

	@LINK
	void set_initial_state(String node_string) {
		System.out.println("Stato Iniziale: "+node_string);
		
		try {
			StateOfWorld w = JasonStateOfWorld.term_string_to_object(node_string);
			//TODO add the initial state to the graph
		} catch (TranslateError e1) {
			e1.printStackTrace();
		}
	}

	/* interface: EXPAND */
	@LINK
	void expand(String expansion_node, String spec_id_string) {
		ExpansionNode exp;
		try{
		exp = JasonExpansionNode.term_string_to_object(expansion_node);
		}catch(TranslateError t){return;}
		graph.addNode(exp);
		System.out.println("Ho aggiunto un nodo al grafo. Il grafo coniente ora " + this.graph.getWTSHashmap().size() + "nodi.");
	}

	/* interface: VISIT */
	@OPERATION
	void getStartState() {
		//
	}

	@OPERATION
	void getState_ById() {
		//
	}
	
	@OPERATION
	void getAllTransitions_FromState() {
	}
	
	@OPERATION
	void getAllTransitions_ToState() {
	}
	
	@OPERATION
	void pickConjointTransition_FromState_ByCapability() {
	}
	
	@OPERATION
	void pickConjointTransition_ToState_ByCapability() {
	}
	
	@LINK
	void printGraph(){
		this.graph.printGraph();
	}

}

