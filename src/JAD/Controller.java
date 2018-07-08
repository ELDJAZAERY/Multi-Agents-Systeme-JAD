package JAD;

import communication.ARequette;
import communication.LogType;
import communication.RequAutre;


public class Controller extends sysExpert.Controller{

	public void executeRequ(ARequette req) {
		if(!req.getLogType().equals(LogType.Autre)) req = analyseRequette(req);
		System.out.println("Requette : --> "+req);
		ACentral.ExecutRequette(req);
	}

	
	public void executeRequ(RequAutre req) {
		ACentral.ajouteAutre(req);
	}
	
	public void ajouteRegle(RequAutre req) {
		//ACentral.ajouteAutre(req);
	}

}
