#include"methods.h"

GameState depthFirstSearch(GameState startingGS)
{	
	GameState currentState = startingGS;
	int numOfExploredStates = 0;
	int wrongTime = 0;
	gameStateStack exploredState;
	exploredState.push(currentState);
	GameState tempState;
	numStack tempNumSuccessors;
	Coord2D tempCO;
	while(1)
	{
		if(exploredState.getLen() == 1)
		{
			//cout<<"No Solution!\n";
			break;
		}
		currentState = exploredState.pop();
		if(currentState.isGoalState())
			break;
		tempCO = currentState.getCoordOfLeastNumSuccessor();
		tempNumSuccessors = currentState.getNumSuccessorsOfPos(tempCO);
		for(int i = 1; i < tempNumSuccessors.getLen(); i++)
		{
			tempState = currentState;
			OPERATION tempOP(tempCO, tempNumSuccessors[i]);
			tempState.doOPRT(tempOP);
			if(!tempState.isContradiction())	
			{
				numOfExploredStates++;
				exploredState.push(tempState);
			}
			else
				wrongTime++;
		}
	}
	/*cout<<" by DFS:\n";
	currentState.getCurrentState().print();
	cout<<"\n num of states explored:  "<<numOfExploredStates
		<<"\twrong time:  "<<wrongTime<<endl<<endl;*/
	return currentState;
}
GameState depthFirstSearchWithSweep(GameState startingGS)
{	
	GameState currentState = startingGS;
	currentState.sweep();
	int numOfExploredStates = 0;
	int wrongTime = 0;
	gameStateStack exploredState;
	exploredState.push(currentState);
	GameState tempState;
	numStack tempNumSuccessors;
	Coord2D tempCO;
	while(1)
	{
		if(exploredState.getLen() == 1)
		{
			//cout<<"No Solution!\n";
			break;
		}
		currentState = exploredState.pop();
		if(currentState.isGoalState())
			break;
		tempCO = currentState.getCoordOfLeastNumSuccessor();
		tempNumSuccessors = currentState.getNumSuccessorsOfPos(tempCO);
		for(int i = 1; i < tempNumSuccessors.getLen(); i++)
		{
			tempState = currentState;
			OPERATION tempOP(tempCO, tempNumSuccessors[i]);
			tempState.doOPRT(tempOP);
			tempState.sweep();
			if(!tempState.isContradiction())
			{
				numOfExploredStates++;
				exploredState.push(tempState);
			}
			else
				wrongTime++;
		}
	}
	/*cout<<" by DFS with sweep:\n";
	currentState.getCurrentState().print();
	cout<<"\n num of states explored:  "<<numOfExploredStates
		<<"\twrong time:  "<<wrongTime<<endl<<endl;*/
	currentState.getCurrentState().outputToFile("DFS with sweep.txt");
	return currentState;
}
GameState depthFirstSearchWithSweep2(GameState startingGS)
{	
	GameState currentState = startingGS;
	currentState.sweep2();
	int numOfExploredStates = 0;
	int wrongTime = 0;
	gameStateStack exploredState;
	exploredState.push(currentState);
	GameState tempState;
	numStack tempNumSuccessors;
	Coord2D tempCO;
	while(1)
	{
		if(exploredState.getLen() == 1)
		{
			//cout<<"No Solution!\n";
			break;
		}
		currentState = exploredState.pop();
		if(currentState.isGoalState())
			break;
		tempCO = currentState.getCoordOfLeastNumSuccessor();
		tempNumSuccessors = currentState.getNumSuccessorsOfPos(tempCO);
		for(int i = 1; i < tempNumSuccessors.getLen(); i++)
		{
			tempState = currentState;
			OPERATION tempOP(tempCO, tempNumSuccessors[i]);
			tempState.doOPRT(tempOP);
			tempState.sweep2();
			if(!tempState.isContradiction())
			{
				numOfExploredStates++;
				exploredState.push(tempState);
			}
			else
				wrongTime++;
		}
	}
	/*cout<<" by DFS with sweep2:\n";
	currentState.getCurrentState().print();
	cout<<"\n num of states explored:  "<<numOfExploredStates
		<<"\twrong time:  "<<wrongTime<<endl<<endl;*/
	currentState.getCurrentState().outputToFile("DFS2 with sweep2.txt");
	return currentState;
}
void breadthFirstSearchWithSweep2(GameState startingGS)
{	
	//ofstream outFile("BFS.txt");
	//outFile.close();
	GameState currentState = startingGS;
	currentState.sweep2();
	int numOfExploredStates = 0;
	int wrongTime = 0;
	gameStateQueue exploredState;
	exploredState.push(currentState);
	GameState tempState;
	numStack tempNumSuccessors;
	Coord2D tempCO;
	//char ch[6] = "0.txt";
	while(1)
	{
		if(exploredState.getLen() == 1)
			break;
		currentState = exploredState.pop();
		while(currentState.isGoalState())
		{
			/*cout<<"by BFS with sweep2:\n";
			currentState.getCurrentState().print();*/
			//ch[0]++;
			//currentState.getCurrentState().outputToFile("BFS.txt");
			if(exploredState.getLen() == 1)
				break;
			currentState = exploredState.pop();
		}
		tempCO = currentState.getCoordOfLeastNumSuccessor();
		tempNumSuccessors = currentState.getNumSuccessorsOfPos(tempCO);
		for(int i = 1; i < tempNumSuccessors.getLen(); i++)
		{
			tempState = currentState;
			OPERATION tempOP(tempCO, tempNumSuccessors[i]);
			tempState.doOPRT(tempOP);
			tempState.sweep2();
			if(!tempState.isContradiction())
			{
				numOfExploredStates++;
				//tempState.getCurrentState().print();
				exploredState.push(tempState);
			}
			else
				wrongTime++;
		}
	}
	/*cout<<"\n num of states explored:  "<<numOfExploredStates
		<<"\twrong time:  "<<wrongTime<<endl<<endl;*/
}

//OprtStack breadthFirstSearchForPath(sudokuLoopSearchProblem problem)
//{
//	OprtStack OPs;
//	graphSuccessorStack tempSuccessors;
//	if(!problem.isSolveable())
//		return OPs;
//	int totalSearchTime = problem.numOfPossibleStartingState();
//	for(int searchTime = 1; searchTime <= totalSearchTime; searchTime++)
//	{
//		problem.setStartingState(searchTime);
//		problem.currentState.getV() = problem.startingState.getV();
//		problem.exploredVertexes.push(problem.currentState);
//		while(1)
//		{
//			if(problem.exploredVertexes.getLen() == 1)
//				break;
//			problem.currentState = problem.exploredVertexes.pop();
//			if(problem.isGoalState())
//			{
//				OPERATION tempOP(problem.getCoordOfVertex(problem.beginningVertex), problem.startingState.getE());
//				OPs.push(tempOP);
//			}
//			tempSuccessors = problem.getSuccessors();
//			for(int i = 1; i < tempSuccessors.getLen(); i++)
//			{
//				problem.currentState = tempSuccessors[i];
//				problem.exploredVertexes.push(problem.currentState);
//				problem.visitedVertexes.push(problem.currentState.getV());
//			}
//		}
//	}
//	return OPs;
//}