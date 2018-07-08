package JAD;

import communication.ARequette;
import communication.LogType;
import communication.Type;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class AgentJAD extends Agent{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8651578667328361099L;


	@Override
	protected void setup() {
		recevoirMessages();
	}
	
	
	@SuppressWarnings("serial")
	void sendMessage(ARequette requette , Agents agentDist){
		addBehaviour( new OneShotBehaviour() {
			@Override
			public void action() {
				ACLMessage message = new ACLMessage(ACLMessage.INFORM);
				message.setContent(requette.toString());
				message.addReceiver(new AID(agentDist.name(),AID.ISLOCALNAME));
				send(message);
			}});
	}
	
	
	@SuppressWarnings("serial")
	void recevoirMessages(){
		addBehaviour(new CyclicBehaviour() {
			@Override
			public void action() {
				ACLMessage message = receive();
				if(message!=null){
				   traitementRequette(message.getContent());
				}
				else block();
			}
		});		
	}

	
	void traitementRequette(String message){
		ARequette req = ARequette.getARequette(message);
		if(req==null) return;
		
		Execut(req);
	}

	
	void Execut(ARequette req){
		Agents distinationAgent=Agents.Agent_Studio;
		
		// send to all Agents
		if(sendAll(req)) return ;
		
		switch (req.getType().name()) {
			case  "Studio": distinationAgent=Agents.Agent_Studio; break;
			case  "T1": case "T2" : case "T3" : case "T4" : case "T5" : case "T6" :
				distinationAgent=Agents.Agent_Fi; break;
			case "Duplex" : distinationAgent = Agents.Agent_Duplex; break;
			case "Maison_classique" : distinationAgent = Agents.Agent_MaisonClass; break;
			case "Maison_campagne" : distinationAgent = Agents.Agent_MaisonCampagne; break;
			case "Bungalow" : distinationAgent = Agents.Agent_Bungalow;	break;
		}
		
		System.out.println(" 2) "+getLocalName()+": --> "+distinationAgent);
		sendMessage(req,distinationAgent);
	}

		
	boolean sendAll(ARequette req){
		if(!req.getType().equals(Type.Null)) return false;
		
		if(req.getLogType().equals(LogType.Maison)){
			sendMessage(req,Agents.Agent_MaisonClass);
			sendMessage(req,Agents.Agent_MaisonCampagne);
			sendMessage(req,Agents.Agent_Bungalow);
		}else if(req.getLogType().equals(LogType.Appartement)){
			sendMessage(req,Agents.Agent_Studio);
			sendMessage(req,Agents.Agent_Fi);
			sendMessage(req,Agents.Agent_Duplex);			
		}else{
			sendMessage(req,Agents.Autre);
		}
		
		return true;
	}
		
	
}
