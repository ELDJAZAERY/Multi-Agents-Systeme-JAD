package Application;

import JAD.ACentral;
import communication.ARequette;
import communication.BDD;
import communication.RequAutre;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import sysExpert.RuleApplet;;


/***
 * 
 * PS: 
 * 	--- ADMIN Interface ---	
 * 	   1) option d'ajout Logement ( Appartement ou Maison Seulement ) Exemple [ Appartement Studio 1 1 No No No 10000 22 ALGER Bodour 053399 ]
 * 	   2) ajouter une regle Exemple: [ CarAvan CarVanT3 3 1 Null No Yes ]
 * 			+ il peut ajouter des Logs de cette categorie Exemple:[ CarAvan CarVanT3 3 1 Yes No Yes 3500 30 Mobile AHMED 0552223654 ]
 *
 *  --- USER INTERFACE ---
 * 	   3) remplir le tablau d'affichage 
 * 	   4) ajouter une autre type de logement "AUTRE" et quand je selection Autre il affiche Cuissin jardain et Toit
 * 
 **/
	


public class Controller extends JAD.Controller{
	
	public String log ="Appartement";
    public ToggleGroup group1;
	public Pane App;
	public Pane maison;
	public ComboBox nbChambre;
	public ComboBox etage;
	public ToggleGroup Toit;
	public ToggleGroup jardin;
	public ToggleGroup cuis;
	public TextField surface;
	public TextField Prix;
	public TableView tableView;

	
	@FXML
	private void initialize() {
		group1.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
			public void changed(ObservableValue<? extends Toggle> ov,
								Toggle old_toggle, Toggle new_toggle) {

				if(group1.getSelectedToggle().getUserData().equals("Appartement")){
					maison.setVisible(false);
					App.setVisible(true);
					log="Appartement";
				}
				else if(group1.getSelectedToggle().getUserData().equals("Maison")){
					log="Maison";
					App.setVisible(false);
					maison.setVisible(true);
				}else{
					log="Autre";
					// set maison et appartemet partie visible
				}
			}
		});
		TableColumn lit1 = new TableColumn("Type");
		lit1.setMinWidth(100);
		lit1.setCellValueFactory(
				new PropertyValueFactory<>("Type"));

		TableColumn lit2 = new TableColumn("Nombre de Chambre");
		lit2.setMinWidth(100);
		lit2.setCellValueFactory(
				new PropertyValueFactory<>("NombreDeChambre"));


		TableColumn lit3 = new TableColumn("Addresse");
		lit3.setMinWidth(100);
		lit3.setCellValueFactory(
				new PropertyValueFactory<>("Addresse;"));

		TableColumn lit4 = new TableColumn("Prix");
		lit3.setMinWidth(100);
		lit3.setCellValueFactory(
				new PropertyValueFactory<>("Prix"));
		tableView.getColumns().clear();
		tableView.getColumns().addAll(lit1, lit2, lit3,lit4);

//		BDD.initialisation();
		RuleApplet.initSysExp();
    	ACentral.LunchSys();
	}

	
	public void lunch(){
		String s="";
		
		// Type de Logement
		if(group1.getSelectedToggle().getUserData().equals("Appartement"))
			s="[ Appartement Null";
		else if(group1.getSelectedToggle().getUserData().equals("Maison"))
			s="[ Maison Null";
		else
			s="[ Autre Null";
			
	
		// nb Chambres
		if(nbChambre.getValue()!=null)
			s=s+" "+nbChambre.getValue();
		else
			s+=" "+'1';
		
		// nb Etages
		if(etage.getValue() !=null)
		s=s+" "+etage.getValue();
		else
			s=s+" "+'0';
		
		// Type
		if(log.equals("Appartement")){			
			if(cuis.getSelectedToggle() !=null)
				s=s+" "+cuis.getSelectedToggle().getUserData().toString()+" "+"No"+" "+"No";
			else
				s=s+" "+"Null"+" "+"Null"+" "+"Null";
		}
		else{
			if(jardin.getSelectedToggle() !=null )
				s=s+" "+"Null"+" "+jardin.getSelectedToggle().getUserData().toString();
			else
				s=s+" "+"Null"+" "+"Null";
				
			if(Toit.getSelectedToggle() !=null )
				s=s+" "+Toit.getSelectedToggle().getUserData().toString();
			else
				s=s+" "+"Null"+" "+"Null";
		}

		//prix
		if(!Prix.getText().isEmpty())
			s=s+" "+Prix.getText();
		else 
			s+=" "+'0';
		
		
		//surface
		if(!surface.getText().isEmpty())
			s=s+" "+surface.getText();
		else 
			s+=" "+'0';

		s=s+" ]";
		
		/***c'est la requette **/
		ARequette Q =ARequette.getARequette(s);
		executeRequ(Q);
	}
	
	
	
	public void ajouteLogAutre(){
		// interface Admin requiperi meno hado ga3 [ CarAvan(n'importe quelle) CarVanT3(n'importe quelle) 3 1 NULL NO Yes 3500 30 Mobile AHMED 0552223654 ]
		String s="[ CarAvan CarVanT3 3 1 Yes No Yes 3500 30 Mobile AHMED 0552223654 ]";
		
		RequAutre req = RequAutre.getARequette(s);
		
		System.out.println(req);
		executeRequ(req);
	}
	
	public void ajouteRegle(){
		// ajouter une regle Exemple: [ CarAvan CarVanT3 3 1 Null No Yes ]
		String s="[ CarAvan CarVanT3 3 1 Null No Yes ]";
		RequAutre req = RequAutre.getARequette(s);
		ajouteRegle(req);
	}
}
