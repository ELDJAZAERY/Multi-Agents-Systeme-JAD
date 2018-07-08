package communication;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import JAD.Agents;

public class BDD {

/*
	public static void main(String[] args) throws Exception {
		initialisation();

		for(Agents agent:Agents.values())
			System.out.println(chargerLogs(agent));
	}
*/
	
	
	public static ArrayList<ARequette> chargerLogs(Agents agent) throws Exception {
		ObjectInputStream in = null;
		ArrayList<ARequette> Logs = new ArrayList<>();
		ARequette log;
		final String src = "BDDLogs/" + agent.name() + ".txt";
		try{in = new ObjectInputStream(new FileInputStream(src));}catch(Exception e){}


		while(true) {
			try {
				log = (ARequette) in.readObject();
			} catch (Exception e) {
				break;
			}
			Logs.add(log);
		}
		
		if(in!=null) in.close();
		return Logs;
	}

	
	public static ArrayList<RequAutre> chargerLogsAutre(Agents agent) throws Exception {
		ObjectInputStream in = null;
		ArrayList<RequAutre> Logs = new ArrayList<>();
		RequAutre log;
		final String src = "BDDLogs/" + agent.name() + ".txt";
		try{in = new ObjectInputStream(new FileInputStream(src));}catch(Exception e){}


		while(true) {
			try {
				log = (RequAutre) in.readObject();
			} catch (Exception e) {
				break;
			}
			Logs.add(log);
		}
		
		if(in!=null) in.close();
		return Logs;
	}
	
	
	public static void maj_Logs(ArrayList<ARequette> Logs, Agents agent) throws Exception {
		ObjectOutputStream out = null;
		String src = "BDDLogs/" + agent.name() + ".txt";

		try {
			out = new ObjectOutputStream(new FileOutputStream(src));
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (ARequette log : Logs)
			out.writeObject(log);
	}

	
	public static void maj_LogsAutre(ArrayList<RequAutre> Logs, Agents agent) throws Exception {
		ObjectOutputStream out = null;
		String src = "BDDLogs/" + agent.name() + ".txt";

		try {
			out = new ObjectOutputStream(new FileOutputStream(src));
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (ARequette log : Logs)
			out.writeObject(log);
		
	}

	
	public static void initialisation() {
		ArrayList<ARequette> Logs = new ArrayList<>();
		String log;

		log = "[ Appartement Studio 1 1 No No No 10000 22 ALGER Bodour 053399 ]";
		Logs.add(ARequette.getARequette(log));
		
		log = "[ Appartement Studio 1 1 No No No 10500 25 ALGER Ahmed 0556699 ]";
		Logs.add(ARequette.getARequette(log));

		log = "[ Appartement Studio 1 1 No No No 9000 20 BEJAIA Boulam 06695666 ]";
		Logs.add(ARequette.getARequette(log));
		
		log = "[ Appartement Studio 1 1 No No No 11000 30 ALGER Naser 0556699 ]";
		Logs.add(ARequette.getARequette(log));
		try{
			maj_Logs(Logs,Agents.Agent_Studio);
		}catch(Exception e){System.out.println(e);}
		Logs.clear();
		
		
		
		log = "[ Appartement T1 1 1 Yes No No 13000 30 ALGER Bodour 053399 ]";
		Logs.add(ARequette.getARequette(log));
		
		log = "[ Appartement T1 1 1 Yes No No 11000 35 ORAN ADNAN 053399 ]";
		Logs.add(ARequette.getARequette(log));

		log = "[ Appartement T2 2 1 Yes No No 15000 45 ALGER Bodour 053399 ]";
		Logs.add(ARequette.getARequette(log));
		
		log = "[ Appartement T2 2 1 No No No 13000 45 ORAN ADNAN 053399 ]";
		Logs.add(ARequette.getARequette(log));

		log = "[ Appartement T3 3 1 Yes No No 15000 75 ALGER Bodour 053399 ]";
		Logs.add(ARequette.getARequette(log));
		
		log = "[ Appartement T4 4 1 No No No 15000 100 ORAN ADNAN 053399 ]";
		Logs.add(ARequette.getARequette(log));

		log = "[ Appartement T4 4 1 Yes No No 16000 85 ALGER Bodour 053399 ]";
		Logs.add(ARequette.getARequette(log));
		
		log = "[ Appartement T5 5 1 Yes No No 16500 125 ORAN ADNAN 053399 ]";
		Logs.add(ARequette.getARequette(log));

		log = "[ Appartement T5 5 1 Yes No No 18000 150 ALGER Bodour 053399 ]";
		Logs.add(ARequette.getARequette(log));
		
		log = "[ Appartement T6 6 1 Yes No No 20000 180 ORAN ADNAN 053399 ]";
		Logs.add(ARequette.getARequette(log));
		try{
			maj_Logs(Logs,Agents.Agent_Fi);
		}catch(Exception e){System.out.println(e);}
		Logs.clear();

		
		log = "[ Appartement Duplex 4 2 Yes No No 13000 22 ALGER Bodour 053399 ]";
		Logs.add(ARequette.getARequette(log));
		
		log = "[ Appartement Duplex 4 2 No No No 13000 22 ALGER Bodour 053399 ]";
		Logs.add(ARequette.getARequette(log));

		log = "[ Appartement Duplex 4 2 Yes No No 13000 22 ALGER Bodour 053399 ]";
		Logs.add(ARequette.getARequette(log));
		try{
			maj_Logs(Logs,Agents.Agent_Duplex);
		}catch(Exception e){System.out.println(e);}
		Logs.clear();

		
		
		log = "[ Maison Maison_classique 13 3 Yes No Yes 35000 250 ALGER Bodour 053399 ]";
		Logs.add(ARequette.getARequette(log));

		log = "[ Maison Maison_classique 10 2 Yes No Yes 30000 220 ALGER Bodour 053399 ]";
		Logs.add(ARequette.getARequette(log));

		log = "[ Maison Maison_classique 15 3 Yes No Yes 35000 350 ORAN BENSOMA 053399 ]";
		Logs.add(ARequette.getARequette(log));

		log = "[ Maison Maison_classique 8 2 Yes No Yes 25000 180 ALGER ARABI 053399 ]";
		Logs.add(ARequette.getARequette(log));
		try {
			maj_Logs(Logs, Agents.Agent_MaisonClass);
		} catch (Exception e) {
			System.out.println(e);
		}
		Logs.clear();

		
		log = "[ Maison Maison_campagne 13 3 Yes Yes Yes 38000 250 ALGER Bodour 053399 ]";
		Logs.add(ARequette.getARequette(log));

		log = "[ Maison Maison_campagne 10 2 Yes Yes Yes 32000 220 ALGER Bodour 053399 ]";
		Logs.add(ARequette.getARequette(log));

		log = "[ Maison Maison_campagne 15 3 Yes Yes Yes 37000 350 ORAN BENSOMA 053399 ]";
		Logs.add(ARequette.getARequette(log));

		log = "[ Maison Maison_campagne 8 2 No Yes Yes 28000 180 ALGER ARABI 053399 ]";
		Logs.add(ARequette.getARequette(log));

		log = "[ Maison Maison_campagne 15 3 Yes Yes No 37000 350 ORAN BENSOMA 053399 ]";
		Logs.add(ARequette.getARequette(log));

		log = "[ Maison Maison_campagne 8 2 No Yes No 28000 180 ALGER ARABI 053399 ]";
		Logs.add(ARequette.getARequette(log));
		try {
			maj_Logs(Logs, Agents.Agent_MaisonCampagne);
		} catch (Exception e) {
			System.out.println(e);
		}
		Logs.clear();

		
		log = "[ Maison Bungalow 13 3 Yes No No 38000 250 ALGER Bodour 053399 ]";
		Logs.add(ARequette.getARequette(log));
		
		log = "[ Maison Bungalow 10 2 Yes No No 32000 220 ALGER Bodour 053399 ]";
		Logs.add(ARequette.getARequette(log));

		log = "[ Maison Bungalow 15 3 Yes No No 37000 350 ORAN BENSOMA 053399 ]";
		Logs.add(ARequette.getARequette(log));
		
		log = "[ Maison Bungalow 8 2 No No No 28000 180 ALGER ARABI 053399 ]";
		Logs.add(ARequette.getARequette(log));
		try{
			maj_Logs(Logs,Agents.Agent_Bungalow);
		}catch(Exception e){System.out.println(e);}
		Logs.clear();

		System.out.println("Logs Sauvgarder !!!!");
	}

}
