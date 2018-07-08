package sysExpert;
import java.awt.*;
import java.util.*;

public class RuleApplet {

	static Frame frame;
	static RuleVarDialog dlg;
	static RuleBase logements , Appartements , Maisons;
	
	public static void initSysExp(){
		initAppartementsRuleBase();
		initMaisonsRuleBase();
	}

	// ....
	// Rule base definitions
	// display dialog to get user value for a variable
	static public String waitForAnswer(String prompt, String labels) {
		// position dialog over parent dialog
		dlg = new RuleVarDialog(frame, true);
		dlg.label1.setText(" " + prompt + "\n (" + labels + ")");
		dlg.setLocation(400, 250);
		dlg.show();
		String ans = dlg.getText();
		return ans;
	}

	
	@SuppressWarnings({ "unchecked", "unused" })
	public static void initlogementsRuleBase(RuleBase rb) {
		
		rb.goalClauseStack = new Stack<Object>(); // goals and subgoals
		rb.variableList = new Hashtable<String, RuleVariable>();
		
		RuleVariable logement = new RuleVariable("Logement");
		logement.setLabels("Studio T1 T2 T3 T4 T5 Duplex Maison_classique Maison_campagne Bungalow");
		logement.setPromptText("What kind of Logement is it?");
		
		
		RuleVariable logementType = new RuleVariable("Type du Logement");
		logementType.setLabels("Appartement Maison");
		logementType.setPromptText("quel est le type du logement?");
		
		
		RuleVariable nbChambre = new RuleVariable("Nombre de chambre");
		nbChambre.setLabels("1 2 3 4 5 6 +6");
		nbChambre.setPromptText("nombre de chambres ?");
		
		
		RuleVariable nbEtage = new RuleVariable("Nombre detage");
		nbEtage.setLabels("1 2 3 4");
		nbEtage.setPromptText("Nombre d'etage?");
		
		
		RuleVariable cuisineSep = new RuleVariable("cuisine séparéer");
		cuisineSep.setLabels("Yes No");
		cuisineSep.setPromptText("cuisine séparéer?");
		
		
		RuleVariable jardin = new RuleVariable("Jardin");
		jardin.setLabels("Yes No");
		jardin.setPromptText("Jardin?");
		
		
		RuleVariable toit = new RuleVariable("Toit");
		toit.setLabels("Yes No");
		toit.setPromptText("Toit?");


		rb.variableList.put(logementType.name, logementType);		
		rb.variableList.put(toit.name, toit);
		rb.variableList.put(jardin.name, jardin);
		rb.variableList.put(nbEtage.name, nbEtage);
		rb.variableList.put(nbChambre.name, nbChambre);
		rb.variableList.put(logement.name, logement);
		rb.variableList.put(cuisineSep.name, cuisineSep);

		
		// Note: at this point all variables values are NULL
		Condition cEquals = new Condition("=");
		Condition csuppThan = new Condition(">");
		
		// define rules
		rb.ruleList = new Vector<Object>();

		
		Rule studio = new Rule(rb, "studio",new Clause(nbChambre, cEquals, "1"),
				new Clause(cuisineSep, cEquals, "No"),new Clause(logement,cEquals,"Studio"));
		
		Rule t1 = new Rule(rb, "T1",new Clause(nbChambre, cEquals, "1"),
				new Clause(cuisineSep, cEquals, "Yes"),new Clause(logement,cEquals,"T1"));

		Rule t2 = new Rule(rb, "T2", new Clause(logementType, cEquals, "Appartement"),
				new Clause(nbChambre, cEquals, "2"),new Clause(logement,cEquals,"T2"));

		Rule t3 = new Rule(rb, "T3", new Clause(logementType, cEquals, "Appartement"),
				new Clause(nbChambre, cEquals, "3"), new Clause(nbEtage,cEquals, "1"),
				new Clause(logement,cEquals,"T3"));

		Rule t4 = new Rule(rb, "T4", new Clause(logementType, cEquals, "Appartement"),
				new Clause(nbChambre, cEquals, "4"), new Clause(nbEtage,cEquals, "1"),
				new Clause(logement,cEquals,"T4"));

		Rule t5 = new Rule(rb, "T5", new Clause(logementType, cEquals, "Appartement"),
				new Clause(nbChambre, cEquals, "5"), new Clause(nbEtage,cEquals, "1"),
				new Clause(logement,cEquals,"T5"));

		Rule t6 = new Rule(rb, "T6", new Clause(logementType, cEquals, "Appartement"),
				new Clause(nbChambre, cEquals, "6"), new Clause(nbEtage,cEquals, "1"),
				new Clause(logement,cEquals,"T6"));
		
		Rule Duplex = new Rule(rb, "Duplex", new Clause(logementType, cEquals, "Appartement"),
				new Clause(nbChambre, csuppThan, "2"), new Clause(nbEtage,cEquals, "2"),
				new Clause(logement,cEquals,"Duplex"));

		Rule maison_class = new Rule(rb, "Maison classique", new Clause(logementType, cEquals, "Maison"),
				new Clause(nbChambre, csuppThan, "2"),new Clause(jardin,cEquals,"No"),
				new Clause(logement,cEquals,"Maison_classique"));
		
		Rule maison_campagne = new Rule(rb, "Maison campagne", new Clause(logementType, cEquals, "Maison"),
				new Clause(nbChambre, csuppThan, "2"),new Clause(jardin,cEquals,"Yes"),
				new Clause(logement,cEquals,"Maison_campagne"));

		Rule bungalow = new Rule(rb, "Bungalow", new Clause(logementType, cEquals, "Maison"),
				new Clause(nbChambre, csuppThan, "2"), new Clause(nbEtage,cEquals, "2"),
				new Clause(toit,cEquals,"Non"),new Clause(logement,cEquals,"Bungalow"));	
	}
		
	
	
	@SuppressWarnings({ "unchecked", "unused" })
	public static void initAppartementsRuleBase() {

		Appartements = new RuleBase("Logement Rule Base");
		RuleBase rb = Appartements;
		
		
		rb.goalClauseStack = new Stack<Object>(); // goals and subgoals
		rb.variableList = new Hashtable<String, RuleVariable>();
		
		RuleVariable logement = new RuleVariable("Logement");
		logement.setLabels("Studio T1 T2 T3 T4 T5 Duplex ");
		logement.setPromptText("What kind of Logement is it?");
		
		
		RuleVariable nbChambre = new RuleVariable("Nombre de chambre");
		nbChambre.setLabels("1 2 3 4 5 6 +6");
		nbChambre.setPromptText("nombre de chambres ?");
		
		
		RuleVariable nbEtage = new RuleVariable("Nombre detage");
		nbEtage.setLabels("1 2 3 4");
		nbEtage.setPromptText("Nombre d'etage?");
		
		
		RuleVariable cuisineSep = new RuleVariable("cuisine séparéer");
		cuisineSep.setLabels("Yes No");
		cuisineSep.setPromptText("cuisine séparéer?");
		
		
		rb.variableList.put(nbEtage.name, nbEtage);
		rb.variableList.put(nbChambre.name, nbChambre);
		rb.variableList.put(logement.name, logement);
		rb.variableList.put(cuisineSep.name, cuisineSep);

		
		// Note: at this point all variables values are NULL
		Condition cEquals = new Condition("=");
		Condition csuppThan = new Condition(">");
		Condition cLessThan = new Condition("<");
		
		// define rules
		rb.ruleList = new Vector<Object>();

		
		Rule studio = new Rule(rb, "studio",new Clause(nbChambre, cEquals, "1"),new Clause(nbEtage,cLessThan, "2"),
				new Clause(cuisineSep, cEquals, "No"),new Clause(logement,cEquals,"Studio"));
		
		Rule t1 = new Rule(rb, "T1",new Clause(nbChambre, cEquals, "1"),new Clause(nbEtage,cLessThan, "2"),
				new Clause(cuisineSep, cEquals, "Yes"),new Clause(logement,cEquals,"T1"));

		Rule t2 = new Rule(rb, "T2", new Clause(nbChambre, cEquals, "2"),new Clause(nbEtage,cLessThan, "2"),
				new Clause(logement,cEquals,"T2"));

		Rule t3 = new Rule(rb, "T3", new Clause(nbChambre, cEquals, "3"),new Clause(nbEtage,cLessThan, "2"),
				new Clause(logement,cEquals,"T3"));

		Rule t4 = new Rule(rb, "T4", new Clause(nbChambre, cEquals, "4"),new Clause(nbEtage,cLessThan,"2"),
				new Clause(logement,cEquals,"T4"));

		Rule t5 = new Rule(rb, "T5",new Clause(nbChambre, cEquals, "5"), new Clause(nbEtage,cLessThan, "2"),
				new Clause(logement,cEquals,"T5"));

		Rule t6 = new Rule(rb, "T6",new Clause(nbChambre, cEquals, "6"), new Clause(nbEtage,cLessThan, "2"),
				new Clause(logement,cEquals,"T6"));
		
		Rule Duplex = new Rule(rb, "Duplex",new Clause(nbChambre, csuppThan, "2"), new Clause(nbEtage,cEquals, "2"),
				new Clause(logement,cEquals,"Duplex"));

	}
	
	
	
	@SuppressWarnings({ "unchecked", "unused" })
	public static void initMaisonsRuleBase() {

		Maisons = new RuleBase("Logement Rule Base");
		RuleBase rb = Maisons;
		
		
		rb.goalClauseStack = new Stack<Object>(); // goals and subgoals
		rb.variableList = new Hashtable<String, RuleVariable>();
		
		RuleVariable logement = new RuleVariable("Logement");
		logement.setLabels("Maison_classique Maison_campagne Bungalow");
		logement.setPromptText("What kind of Logement is it?");
						
		
		RuleVariable jardin = new RuleVariable("Jardin");
		jardin.setLabels("Yes No");
		jardin.setPromptText("Jardin?");
		
		
		RuleVariable toit = new RuleVariable("Toit");
		toit.setLabels("Yes No");
		toit.setPromptText("Toit?");


		rb.variableList.put(toit.name, toit);
		rb.variableList.put(jardin.name, jardin);
		rb.variableList.put(logement.name, logement);

		
		// Note: at this point all variables values are NULL
		Condition cEquals = new Condition("=");
		
		// define rules
		rb.ruleList = new Vector<Object>();

		Rule maison_class = new Rule(rb, "Maison classique",new Clause(jardin,cEquals,"No"),
				new Clause(toit,cEquals,"Yes") , new Clause(logement,cEquals,"Maison_classique"));
		
		Rule maison_campagne = new Rule(rb, "Maison campagne",new Clause(jardin,cEquals,"Yes"),
				new Clause(logement,cEquals,"Maison_campagne"));

		Rule bungalow = new Rule(rb, "Bungalow" , new Clause(toit,cEquals,"No"),new Clause(jardin,cEquals,"No")
				,new Clause(logement,cEquals,"Bungalow"));
	}


	
	public void demoLogementsFC(RuleBase rb) {
		// should be a Mini-Van
		((RuleVariable) rb.variableList.get("Logement")).setValue(null);
		((RuleVariable) rb.variableList.get("Type du Logement")).setValue(null);
		((RuleVariable) rb.variableList.get("Nombre de chambre")).setValue("1");
		((RuleVariable) rb.variableList.get("Nombre detage")).setValue("1");
		((RuleVariable) rb.variableList.get("cuisine séparéer")).setValue("No");
		((RuleVariable) rb.variableList.get("Jardin")).setValue("No");
		rb.forwardChain(); // chain until quiescence...
	}

	
	public void demoLogementsBC(RuleBase rb) {
		((RuleVariable) rb.variableList.get("vehicle")).setValue(null);
		((RuleVariable) rb.variableList.get("Type du Logement")).setValue(null);
		((RuleVariable) rb.variableList.get("Nombre de chambre")).setValue("1");
		((RuleVariable) rb.variableList.get("Nombre detage")).setValue("1");
		((RuleVariable) rb.variableList.get("cuisine séparéer")).setValue("No");
		((RuleVariable) rb.variableList.get("Jardin")).setValue("No");
		rb.backwardChain("Logement"); // chain until quiescence...
	}

}