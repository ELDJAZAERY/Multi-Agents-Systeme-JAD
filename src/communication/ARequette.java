package communication;

import jade.util.leap.Serializable;

public class ARequette implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	// System Expert
	private LogType logType = LogType.Null;
	private Type type = Type.Null;
	protected int nbChambre = 0 ;


	protected int nbEtage = 0;
	protected Boolean cuisinSepar = Boolean.Null , jardin = Boolean.Null , toit = Boolean.Null ;

	// Recherche Local
	protected int prix = 0 , surface = 0 ;
	protected String adresse = "" , nomProp ="" , tel="";
	
	
	// send from Agent Central to Agent Annex
	public ARequette(LogType logType ,Type type, int nbChambre, int nbEtage, Boolean cuisinSepar, Boolean jardin, Boolean toit) {
		this.logType = logType;
		this.type = type;
		this.nbChambre = nbChambre;
		this.nbEtage = nbEtage;
		this.cuisinSepar = cuisinSepar;
		this.jardin = jardin;
		this.toit = toit;
	}
	
	
	// send from Agent Annex to Agent Central
	public ARequette(Type type, int nbChambre, int nbEtage, Boolean cuisinSepar, Boolean jardin, Boolean toit,
			 int prix, int surface,String adresse,String nomProp , String tel) {
		this.type = type;
		this.nbChambre = nbChambre;
		this.nbEtage = nbEtage;
		this.cuisinSepar = cuisinSepar;
		this.jardin = jardin;
		this.toit = toit;
		this.adresse = adresse;
		this.prix = prix;
		this.surface = surface;
		this.nomProp = nomProp;
		this.tel = tel;
	}


	public Type getType() {
		return type;
	}

	public int getNbChambre() {
		return nbChambre;
	}
	public int getPrix() {
		return prix;
	}
	public int getSurface() {
		return surface;
	}

	public String getAdress() {
		return adresse;
	}

	public void setAdress(String adresse) {
		this.adresse = adresse;
	}

	public int getNbEtage() {
		return nbEtage;
	}

	public Boolean getCuisinSepar() {
		return cuisinSepar;
	}

	public Boolean getJardin() {
		return jardin;
	}

	public Boolean getToit() {
		return toit;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public void setSurface(int surface) {
		this.surface = surface;
	}
	
	public String getNomProp() {
		return nomProp;
	}


	public void setNomProp(String nomProp) {
		this.nomProp = nomProp;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setNbChambre(int nbChambre) {
		this.nbChambre = nbChambre;
	}

	public LogType getLogType() {
		return logType;
	}


	public static ARequette getARequette(String requet){
		if(requet.isEmpty() || requet==null) return null;
		
		ARequette aReq;
		String[] criteres;
		
		try{
		    criteres = requet.split(" ");				
		
			aReq = new ARequette(LogType.valueOf(criteres[1]),Type.valueOf(criteres[2]),Integer.parseInt(criteres[3]),Integer.parseInt(criteres[4]),
					Boolean.valueOf(criteres[5]),Boolean.valueOf(criteres[6]),Boolean.valueOf(criteres[7]));
			
		}catch(Exception e){
			return null;
		}
		
		try{
			if(criteres[8].equals("]")) return aReq;
			aReq.setPrix(Integer.parseInt(criteres[8]));
			if(criteres[9].equals("]")) return aReq;
			aReq.setSurface(Integer.parseInt(criteres[9]));
			if(criteres[10].equals("]")) return aReq;
			aReq.setAdress(criteres[10]);
			if(criteres[11].equals("]")) return aReq;
			aReq.setNomProp(criteres[11]);
			if(criteres[12].equals("]")) return aReq;
			aReq.setTel(criteres[12]);
		}catch(Exception e){ return aReq; }
		
		return aReq;
	}


	@Override
	public String toString() {
		return "[ " + logType + " " + type + " " + nbChambre + " " + nbEtage + " "
				+ cuisinSepar + " " + jardin + " " + toit + " " + prix + " " + surface
				+ " " + adresse + " " + nomProp + " " + tel + " ]";
	}
	
	public String AfficheReq(){
		return "Requette [ Type du Logement:" +logType+ " , Type: " + type + " , Nombre de Chambre: " + nbChambre + " , Nombre d'etage: " + nbEtage + " , cuisine sérapere: "
				+ cuisinSepar + " , Jardin: " + jardin + " , Toit: " + toit + " , Prix: " + prix + " , Surface: " + surface
				+ " , Adresse: " + adresse + " , Nom de Proprietaire: " + nomProp + " , Tél: " + tel + " ]";		
	}	
	
	@Override
	public boolean equals(Object obj) {
		ARequette other = (ARequette) obj ;
		return toString().equals(other.toString());
	}
	
}
