package sysExpert;

/* RuleBase class

 Constructing Intelligent Agents with Java
   (C) Joseph P.Bigus and Jennifer Bigus 1997

*/
import java.util.*;
import java.awt.*;


public class RuleBase {
	String name;
	Hashtable variableList; // all variables in the rulebase
	Clause clauseVarList[];
	Vector ruleList; // list of all rules
	Vector conclusionVarList; // queue of variables
	Rule rulePtr; // working pointer to current rule
	Clause clausePtr; // working pointer to current clause
	Stack goalClauseStack; // for goals (cons clauses ) and subgoals

	
	
	public Hashtable getVariableList() {
		return variableList;
	}



	public RuleBase(String Name) {
		name = Name;
	}

	public static void appendText(String text) {
	}

		

	public void forwardChain() {
		Vector conflictRuleSet = new Vector();
		// first test all rules, based on initial data
		conflictRuleSet = match(true); // see which rules can fire
		while (conflictRuleSet.size() > 0) {
			Rule selected = selectRule(conflictRuleSet); // select the "best"
															// rule
			selected.fire(); // fire the rule
								// do the consequent action/assignment
								// update all clauses and rules
			conflictRuleSet = match(false); // see which rules can fire
			// displayVariables("Forward Chaining") ; // display variable
			// bindings
		}
	}

	// used for forward chaining only
	// determine which rules can fire, return a Vector
	public Vector match(boolean test) {
		Vector matchList = new Vector();
		Enumeration enume = ruleList.elements();
		while (enume.hasMoreElements()) {
			Rule testRule = (Rule) enume.nextElement();
			if (test)
				testRule.check(); // test the rule antecedents
			if (testRule.truth == null)
				continue;
			// fire the rule only once for now
			if ((testRule.truth.booleanValue() == true) && (testRule.fired == false))
				matchList.addElement(testRule);
		}
		return matchList;
	}

	// used for forward chaining only
	// select a rule to fire based on specificity
	public Rule selectRule(Vector ruleSet) {
		Enumeration enume = ruleSet.elements();
		long numClauses;
		Rule nextRule;
		Rule bestRule = (Rule) enume.nextElement();
		long max = bestRule.numAntecedents();
		while (enume.hasMoreElements()) {
			nextRule = (Rule) enume.nextElement();
			if ((numClauses = nextRule.numAntecedents()) > max) {
				max = numClauses;
				bestRule = nextRule;
			}
		}
		return bestRule;
	}

	
	public void backwardChain(String goalVarName) {
		RuleVariable goalVar = (RuleVariable) variableList.get(goalVarName);
		Enumeration goalClauses = goalVar.clauseRefs.elements();
		while (goalClauses.hasMoreElements()) {
			Clause goalClause = (Clause) goalClauses.nextElement();
			if (goalClause.consequent.booleanValue() == false)
				continue;
			goalClauseStack.push(goalClause);

			Rule goalRule = goalClause.getRule();
			Boolean ruleTruth = goalRule.backChain(); // find rule truth value
			if (ruleTruth == null) {
			} else if (ruleTruth.booleanValue() == true) {
				// rule is OK, assign consequent value to variable

				goalVar.setValue(goalClause.rhs);
				goalVar.setRuleName(goalRule.name);
				goalClauseStack.pop(); // clear item from subgoal stack

				if (goalClauseStack.empty() == true) {
					break; // for now, only find first solution, then stop
				}

			} else {
				goalClauseStack.pop(); // clear item from subgoal stack
			}
		}
		if (goalVar.value == null) {
		}
	}
	
	

}
