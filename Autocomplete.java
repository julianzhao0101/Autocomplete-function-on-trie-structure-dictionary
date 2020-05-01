/*
THIS CODE WAS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING ANY
SOURCES OUTSIDE OF THOSE APPROVED BY THE INSTRUCTOR. Julian Zhao
*/

import java.util.*;


public class Autocomplete<T> extends AbstractAutocomplete<List<String>> {
	

	// The goal is shorter length first, alphabetical order second
	// So I create a queue to do the BSF (breadth search first) for this trie.	

	public Autocomplete(String dict_file, int max){
		
		super(dict_file,max);

	}


	public List<String> getCandidates(String prefix){

		//the list for candidates
		List<String> list = new ArrayList<>();

		//if prefix is not in the dictionary
		if (find(prefix) == null){
			System.out.println("This prefix is not in the dictionary!");
			return list;
		}

		//queue stores the node
		Queue<TrieNode<List<String>>> queue = new LinkedList<>();
		//word stores the string
		Queue<String> word = new LinkedList<>();		

		//The current node
		TrieNode<List<String>> preNode = find(prefix);

		//The value of this key
		List<String> valPrefix = get(prefix);


		//If there's a previous value for preNode
		if (preNode.hasValue()){
			
			//Input the relevant candidates
			for (String s: valPrefix){
				list.add(s);

				//In case the previous relevant word exceeds the max amount
				if (list.size() == getMax())
					return list;
			}

		}


		//add preNode and prefix
		queue.add(preNode);
		word.add(prefix);

		//remove those from the while


		// when the list is full, stop the while loop and return the list
		// or when the queue is empty, stop and return everything in the list
		while (list.size() < getMax() && !queue.isEmpty()) {

			//new Node from the queue
			TrieNode<List<String>> currentNode = queue.poll();
			//new string from the queue
			String currentWord = word.poll();

			//If currentNode isEndState, then it is a word
			//However, if this word already exists in the list (preNode value), then don't add it
			if (currentNode.isEndState() && !list.contains(currentWord)){
				list.add(currentWord);
			}

			//Map of it's children's char
			Map<Character,TrieNode<List<String>>> childrenMap = currentNode.getChildrenMap();

			//If it is not empty
			if (!childrenMap.isEmpty()){


				//Get all the char key from the map and put it in a list
				Set<Character> children = childrenMap.keySet();
				List<Character> childrenList = new ArrayList<Character>();

				//Put all char in list (because we can't sort set)
				for (char c: children){
					childrenList.add(c);
				}

				//Sort the characters alphabetically
				Collections.sort(childrenList);


				//add the new word to the wordqueue
				//add the new node to the queue
				for (char c: childrenList){

					String newWord = currentWord + c;

					queue.add(currentNode.getChild(c));

					word.add(newWord);

				}

			}


		}

		return list;

	}


	public void pickCandidate(String prefix, String candidate){

		//If the candidate is not a word in the dictionary
		if (find(candidate) == null || !find(candidate).isEndState()){
			
			System.out.println("This word is not in the dictionary!");

			//it is a word, but it has different prefix
		} else if (!candidate.startsWith(prefix)){

			System.out.println("This word does not have the prefix of: " + prefix);

		} else {
			

			// ls is the original value
			List<String> ls = get(prefix);
			// newList is the new list
			List<String> newList = new LinkedList<>();

			//add the recent candidate
			newList.add(candidate);
			
			//if not null, add all the candidate back to the list
			if (ls != null){
				for (String s:ls){		
					if (!candidate.equals(s))
						newList.add(s);
				}
			}
			//store the value
			put(prefix,newList);
			System.out.println("Picked:  " + candidate + "  for prefix:  " + prefix);

		}

	}


}

