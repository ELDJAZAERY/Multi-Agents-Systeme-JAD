package JAD;

import java.util.ArrayList;
import communication.ARequette;
import communication.BDD;
import communication.Boolean;
import communication.LogType;


public class AgentJADAnnex extends AgentJAD{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ArrayList<ARequette> Logs = new ArrayList<>();

	@Override
	protected void setup(){
		try{
			Logs = BDD.chargerLogs(Agents.valueOf(getLocalName()));
		}catch(Exception e){e.printStackTrace();}
		recevoirMessages();
	}
	
	
	@Override
	void traitementRequette(String message){
		ARequette req = ARequette.getARequette(message);
		if(req==null) return;
		
		// si Requette d'ajout
		if(!req.getNomProp().isEmpty()){
			if(!Logs.contains(req)){
				Logs.add(req);
				try{BDD.maj_Logs(Logs,Agents.valueOf(getLocalName()));}catch(Exception e){}
			}
			return;
		}
		
		for(ARequette log:getLogsCorresp(req))
			sendMessage(log,Agents.Agent_Central);
		
	}

	
	
	private ArrayList<ARequette> getLogsCorresp(ARequette req){
		ArrayList<ARequette> LogsCorresp = new ArrayList<>();
		boolean maison = false;
		if(req.getLogType().equals(LogType.Maison))
			maison = true;
		
		for(ARequette log:Logs){
			boolean cores = true;
			
			if(log==null) continue;
			if(req.getNbChambre()!=0 && req.getNbChambre() > log.getNbChambre())
				cores=false;
			
			if(cores && req.getNbEtage()!=0 && req.getNbEtage() > log.getNbEtage())
				cores = false;
				
			if(cores && !maison && !req.getCuisinSepar().equals(Boolean.Null) && !req.getCuisinSepar().equals(log.getCuisinSepar()))
				cores = false;
				
			if(cores && maison && !req.getJardin().equals(Boolean.Null) && !req.getJardin().equals(log.getJardin()))
				cores = false;
				
			if(cores && maison && !req.getToit().equals(Boolean.Null) && !req.getToit().equals(log.getToit()))
				cores = false;
				
			if(cores && req.getPrix()!=0 && req.getPrix() < log.getPrix())
				cores = false;

			if(cores && req.getSurface()!=0 && req.getSurface() > log.getSurface())
				cores = false;

			if(cores && !req.getAdress().equals("") && !req.getAdress().equals(log.getAdress()))
				cores = false;
			
			if(cores)
				LogsCorresp.add(log);
			
		}
		
		return LogsCorresp;
	}


}
