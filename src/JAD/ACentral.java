package JAD;

import communication.ARequette;
import communication.RequAutre;


public class ACentral extends AgentJAD {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static ACentral agenceCentral ;
	
	
	@Override
	protected void setup() {
		recevoirMessages();
		System.out.println("System ready !!!");
		agenceCentral = this;
	};
	
	
	@Override
	protected void traitementRequette(String message){
		System.out.println("Logement Affichage !! --> "+message);
	}
	
	
	public static void ExecutRequette(ARequette req){
		Agents distinationAgent = Agents.Agent_Appartement;
		
		
		switch (req.getLogType().name()){
			case "Appartement": distinationAgent=Agents.Agent_Appartement; break;
			case "Maison" : distinationAgent = Agents.Agent_Maison; break;
			case "Autre" : distinationAgent = Agents.Autre; break;
		}

		agenceCentral.sendMessage(req,distinationAgent);
		System.out.println(" 1) Acentral: --> "+distinationAgent);
	}
	
	
	public static void ajouteAutre(RequAutre req){
		agenceCentral.sendMessage(req,Agents.Autre);
	}
	
	public static void LunchSys(){
			String [] jadeArg = new String [2];
			StringBuffer agents = new StringBuffer();

			agents.append(Agents.Agent_Central+":JAD.ACentral;");
			agents.append(Agents.Agent_Appartement+":JAD.AgentJAD;");
			agents.append(Agents.Agent_Maison+":JAD.AgentJAD;");
			agents.append(Agents.Agent_Studio+":JAD.AgentJADAnnex;");
			agents.append(Agents.Agent_Fi+":JAD.AgentJADAnnex;");
			agents.append(Agents.Agent_Duplex+":JAD.AgentJADAnnex;");
			agents.append(Agents.Agent_MaisonClass+":JAD.AgentJADAnnex;");
			agents.append(Agents.Agent_MaisonCampagne+":JAD.AgentJADAnnex;");
			agents.append(Agents.Agent_Bungalow+":JAD.AgentJADAnnex;");
			agents.append(Agents.Autre+":JAD.AgentJADExpert");
			
			jadeArg[0]="";
			jadeArg[0]="-gui";
			jadeArg[1]=agents.toString();
			jade.Boot.main(jadeArg);
	}

}
