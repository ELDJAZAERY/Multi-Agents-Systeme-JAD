package JAD;

import java.util.ArrayList;
import communication.BDD;
import communication.Boolean;
import communication.LogType;
import communication.RequAutre;


public class AgentJADExpert extends AgentJAD{

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ArrayList<RequAutre> Logs = new ArrayList<>();
	
	@Override
	protected void setup() {
		try{
			Logs = BDD.chargerLogsAutre(Agents.valueOf(getLocalName()));
		}catch(Exception e){e.printStackTrace();}
		recevoirMessages();
	}
	
	
	@Override
	void traitementRequette(String message){
		RequAutre req = RequAutre.getARequette(message);
		if(req==null) return;
		
		// si Requette d'ajout
		if(!req.getNomProp().isEmpty() && !req.getLogtype().equals("Autre")){
			if(!Logs.contains(req)){
				Logs.add(req);
				try{BDD.maj_LogsAutre(Logs,Agents.valueOf(getLocalName()));System.out.println();}catch(Exception e){}
			}
			return;
		}
		
		for(RequAutre log:getLogsCorresp(req))
			sendMessage(log,Agents.Agent_Central);
		
	}

	
	private ArrayList<RequAutre> getLogsCorresp(RequAutre req){
		ArrayList<RequAutre> LogsCorresp = new ArrayList<>();
		boolean maison = false;
		if(req.getLogtype().equals(LogType.Maison))
			maison = true;
		
		for(RequAutre log:Logs){
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
