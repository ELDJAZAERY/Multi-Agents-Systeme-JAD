package Application;


import communication.ARequette;
import javafx.beans.property.SimpleStringProperty;
public class Result {


    private final SimpleStringProperty logType;
    private final SimpleStringProperty type;
    private final SimpleStringProperty nbChambre;
    private final SimpleStringProperty nbEtage;
    private final SimpleStringProperty cuisinSepar;
    private final SimpleStringProperty jardin;
    private final SimpleStringProperty toit;
    private final SimpleStringProperty surface;
    private final SimpleStringProperty prix;
    private final SimpleStringProperty adresse;
    private final SimpleStringProperty nomProp;
    
    
	public Result(SimpleStringProperty logType, SimpleStringProperty type, SimpleStringProperty nbChambre,
			SimpleStringProperty nbEtage, SimpleStringProperty cuisinSepar, SimpleStringProperty jardin,
			SimpleStringProperty toit, SimpleStringProperty surface, SimpleStringProperty prix,
			SimpleStringProperty adresse, SimpleStringProperty nomProp) {
		this.logType = logType;
		this.type = type;
		this.nbChambre = nbChambre;
		this.nbEtage = nbEtage;
		this.cuisinSepar = cuisinSepar;
		this.jardin = jardin;
		this.toit = toit;
		this.surface = surface;
		this.prix = prix;
		this.adresse = adresse;
		this.nomProp = nomProp;
	}
	
	
	//Convert ARequette to Resultat !!
	public void setvalues(ARequette req){
		this.logType.set(req.getLogType().toString());
		this.type.set(req.getType().toString());
		this.nbChambre.set(""+ req.getNbChambre());
		this.nbEtage.set(""+req.getNbEtage());
		this.cuisinSepar.set(req.getCuisinSepar().toString());
		this.jardin.set(req.getJardin().toString());
		this.toit.set(req.getToit().toString());
		this.surface.set(""+req.getSurface());
		this.prix.set(""+req.getPrix());
		this.adresse.set(req.getAdress().toString());
		this.nomProp.set(req.getNomProp().toString());;		
	}
	
	
	public SimpleStringProperty getLogType() {
		return logType;
	}
	public SimpleStringProperty getType() {
		return type;
	}
	public SimpleStringProperty getNbChambre() {
		return nbChambre;
	}
	public SimpleStringProperty getNbEtage() {
		return nbEtage;
	}
	public SimpleStringProperty getCuisinSepar() {
		return cuisinSepar;
	}
	public SimpleStringProperty getJardin() {
		return jardin;
	}
	public SimpleStringProperty getToit() {
		return toit;
	}
	public SimpleStringProperty getSurface() {
		return surface;
	}
	public SimpleStringProperty getPrix() {
		return prix;
	}
	public SimpleStringProperty getAdresse() {
		return adresse;
	}
	public SimpleStringProperty getNomProp() {
		return nomProp;
	}
    
   
}