// Agent explorer1 in project musa_2

/* Initial beliefs and rules */

/* Initial goals */

!start.


{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have a agent that always complies with its organization  
//{ include("$jacamoJar/templates/org-obedient.asl") }

/* Plans */


+!start 
<- 
	focusWhenAvailable("agent_directory");
	.my_name(MyName);
	register(MyName);
	
.

/* 
 * REACT TO WTS CREATION 
 * by
 * 1) preparing a local WTS artifact with empty list of nodes 
 * 2) preparing an empty list of nodes-to-visit and  of visited-nodes
 * 3) preparing an empty list of expanded-nodes
 * 4) focuses on global WTS artifact for New Node Event
 * 5) start a Expand-Evaluation Loop
 */
+announcement_WTS_creation(SpecIdString,GraphAccessManager)
<-
	focusWhenAvailable(GraphAccessManager);
	
	.concat("pe_",SpecIdString,ArtifactName);
	makeArtifact(ArtifactName,"selfconf.ProblemExplorationArtifact",[],PEId);
	.println("Created ", ArtifactName ," for ( ", SpecIdString," )");
	
	+problem_exploration_info(SpecIdString,PEId);	// this for storing essential info about the problem exploration
	+expand_loop_dalay(SpecIdString,200);				// this allows to change loop frequency during the execution
	
	//!expand_local_graph_loop(SpecIdString);
	
	.abolish( announcement_WTS_creation(SpecIdString,GraphAccessManager) );
.

+announcement_new_node(SpecIdString,Node)
<-
	Node = enode(W,TokenList,Score,Exit);
	.println("new node - w:",W," tokens:",TokenList," score:",Score);

	?problem_exploration_info(SpecIdString,PEId);
	//addToVisit(Node)[artifact_id(PEId)];
.

+announcement_new_auction(SpecIdString,AuctionId)[artifact_id(AccessManagerId)]
<-
	?problem_exploration_info(SpecIdString,PEId);
	getMostPromisingExpansion(Expansion)[artifact_id(PEId)];
	
	.my_name(MyName);
	Expansion = enode(_,_,_Score);
	bid(AuctionId,MyName,Score) [auction_id(AccessManagerId)];
	-+placed_bid(AuctionId,SpecIdString,Expansion);	// remember the Expansion selected for bidding
.

+announcement_winner_auction(SpecIdString,AuctionId,WinnerName)[artifact_id(AccessManagerId)]
:
	.my_name(Me) & .term2string(Me,WinnerName)
<-
	?placed_bid(AuctionId,SpecIdString,Expansion);
	apply_changes(Expansion) [auction_id(AccessManagerId)];
.


+!expand_local_graph_loop(SpecIdString)
<-
	?problem_exploration_info(SpecIdString,PEId);
	expand_local_graph[artifact_id(PEId)];
	
	?expand_loop_dalay(SpecIdString,ExpandLoopDelay);
	.wait(ExpandLoopDelay);
	!!expand_local_graph_loop(SpecIdString);
.
