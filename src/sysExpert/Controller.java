package sysExpert;

import communication.ARequette;
import communication.LogType;

public class Controller {

	
	public static ARequette analyseRequette(ARequette req){
		
		RuleBase currentRuleBase;
		if(req.getLogType().equals(LogType.Appartement)){
			RuleApplet.initAppartementsRuleBase();
			currentRuleBase = RuleApplet.Appartements;
		}else{
			RuleApplet.initMaisonsRuleBase();
			currentRuleBase = RuleApplet.Maisons;
		}
		
		
		String[] r = req.toString().split(" ");
		String[] name ={"Type du Logement","Logement","Nombre de chambre","Nombre detage","cuisine séparéer","Jardin","Toit"};
				
		for (int i = 0; i < name.length; i++) {
			String varName = name[i];
			// Null pointure Exception 
			RuleVariable rvar = (RuleVariable) currentRuleBase.getVariableList().get(varName);
			if(rvar!=null) rvar.setValue(r[i+1]);
		}
		
		currentRuleBase.forwardChain();
		
		String s ="";
		for (int i = 0; i < name.length; i++) {
			String varName = name[i];
			RuleVariable rvar = (RuleVariable) currentRuleBase.getVariableList().get(varName);
			if(rvar != null) s+=" "+rvar.getValue().toString();
			else s+=" "+r[i+1];
		}
		s="["+s+" ]";
		
		return  ARequette.getARequette(s);		
	}
		

}
