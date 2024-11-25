package driver;

public class ShuntngYard {
	public static String Operators = "+-*/^()";
	public static String Numbers = "1234567890";
	
	public static boolean IsNumber(String input) {
		for(char c: input.toCharArray()) {
			if (Numbers.indexOf(c) == -1) {
				return false;
			}
		}
		return true;
	}
	
	public static int getPrecedence(String input) {
		char op = input.charAt(0);
		switch (op) {
			case '(':
				return 1;
			case '+':
			case '-':
				return 2;
			case '*':
			case '/':
				return 3;
			case '^':
				return 4;
			case ')':
				return 5;
			default:
				return 0;
		}			
	}

	//parse a math expression into a linked list
    //input: the math expression as a string
    //parsed result will stored in Token list
	public static TokenList ParseFromExp(String exp) {
		TokenList lst = new TokenList();
		/*
		 * to do
		 */
		return lst;
	}
	
	//take the tokens from Tokens queue, and stored the reversed polish expression in ReversePolish queue
	public static TokenList BuildFromTokens(TokenList tokenList) {
		TokenList outputQueue = new TokenList();
		TokenList operatorStack = new TokenList();
	
		// * 1. While there are tokens to be read:
		Node<String> currentNode = tokenList.Head; // Start at the head of the list
    
		while (currentNode != null) {
			String token = currentNode.Payload;  // Access the token in the current node
	
			// * 2. If the token is a number, add it to the output queue
			if (IsNumber(token)) {
				outputQueue.Enqueue(new Node<String>(token));
			} 
			// * 3. If the token is an operator
			else if (Operators.indexOf(token.charAt(0)) != -1) {
				while (!operatorStack.IsEmpty() && 
					   getPrecedence(token) <= getPrecedence(operatorStack.Peek().Payload) &&
					   operatorStack.Peek().Payload.charAt(0) != '(') {
					// Pop operators from the stack to the output queue while the operator at the top
					// of the stack has greater precedence than the current token
					outputQueue.Enqueue(operatorStack.Pop());
				}
				// Push the current operator onto the stack
				operatorStack.Push(new Node<String>(token));
			} 
			// * 4. If the token is a left parenthesis, push it onto the stack
			else if (token.equals("(")) {
				operatorStack.Push(new Node<String>(token));
			} 
			// * 5. If the token is a right parenthesis
			else if (token.equals(")")) {
				// Pop operators from the stack to the output queue until a left parenthesis is encountered
				while (!operatorStack.IsEmpty() && !operatorStack.Peek().Payload.equals("(")) {
					outputQueue.Enqueue(operatorStack.Pop());
				}
				// Discard the left parenthesis
				operatorStack.Pop();
			}
	
			// Move to the next node in the list
			currentNode = currentNode.NextNode;
		}
	
		// * 6. Pop any remaining operators from the stack to the output queue
		while (!operatorStack.IsEmpty()) {
			outputQueue.Enqueue(operatorStack.Pop());
		}
	
		return outputQueue;
	}
	
	//process use the reverse polish format of expression to process the math result
	//output: the math result of the expression
	public static int Process(TokenList queue) {
		//to do
		return 0;
	}
}
