package activites;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Scanner;

import Exception.SaisieErroneeException;
import activites.horaire_activites.Date2;
import activites.horaire_activites.Horaire_activite;
import activites.horaire_activites.Ref_activite;
import activites.tarif_activites.Tarif_activite;
import client.Repository_client;


public class Ajouter implements interface_parametre_activite{
	
	Repository_activite access_repository;
	Repository_client access_repository_client;
	Recherche recherche;
	
	public Ajouter(Repository_activite access_repository, Repository_client access_repository_client, Recherche recherche) {
		this.access_repository=access_repository;
		this.access_repository_client=access_repository_client;
		this.recherche=recherche;
	}

	//Nom
	@Override
	public void nom(String nom) {
		
		Ref_activite ref_activite_temp=null;
		
		int last_ref = 0;
		
		//Si la liste des activités est pas vide
		if(access_repository.getListe_ref_activite() != null) {
			last_ref=access_repository.getListe_ref_activite().size()+1;
			ref_activite_temp =new Ref_activite(last_ref,nom);
			access_repository.setListe_ref_activite(ref_activite_temp);
		}
		else {
			//Si la liste des activités est vide
			ref_activite_temp =new Ref_activite(1,nom);
			access_repository.setListe_ref_activite(ref_activite_temp);
		}
		
		/////////////////
		
		//Creation d'une activité
		
		Tarif_activite tarif_activite = new Tarif_activite(ref_activite_temp,null,0);
		access_repository.setListe_tarif_activite(tarif_activite);
		
		Horaire_activite horaire_activite = new Horaire_activite(ref_activite_temp,null);
		access_repository.setListe_horaire_activite(horaire_activite);

		Activites activite_temp = new Activites(horaire_activite, tarif_activite);
		access_repository.setListe_general_activites(activite_temp);
		
	}
	
	///////////////////////////////////////////////////////
	
	//Horaire
	@Override
	public void horaire(Ref_activite ref_activite_temp) throws SaisieErroneeException,DateTimeException {
		Date2 date = new Date2(true,null,null);

		int last_ref = 0;
		
		Ref_activite ref_activite_temp2=null;
		boolean New = true;
		boolean Interfere=false;

		// Ajouter horaire à une activité n'ayant pas encore définit d'horaire
		for (Activites el : access_repository.getListe_general_activites()) {
			if (el != null) {
				if (el.getHoraire_activite().getRef_activite().equals(ref_activite_temp)) {
					if (el.getHoraire_activite().getDate() == null) {
						el.getHoraire_activite().setDate(date);
						New = false;
					}
				}
			}
		}

		//Activite a déjà un horaire défini -> on souhaite ajouter un nouvel horaire à cette activité
		if (New == true) {

			//Vérifie qu'il n'y pas d'interférence entre les activités
			for (Horaire_activite el : access_repository.getListe_horaire_activite()) {
				if(el.getRef_activite().getActivite().equalsIgnoreCase(ref_activite_temp.getActivite())){
					LocalDateTime new_date_debut = date.getDate_debut();
					LocalDateTime new_date_fin = date.getDate_fin();
					if((new_date_debut.compareTo(el.getDate().getDate_debut())==0)||
							(new_date_debut.compareTo(el.getDate().getDate_fin())==0)||((new_date_debut.compareTo(el.getDate().getDate_debut())==1)&&
							(new_date_debut.compareTo(el.getDate().getDate_fin())==-1))){
						Interfere=true;
						System.out.println("Cet horaire interfère avec une autre horaire pour cette même activite !");}

					if((new_date_fin.compareTo(el.getDate().getDate_debut())==0)||
								(new_date_fin.compareTo(el.getDate().getDate_fin())==0)||((new_date_fin.compareTo(el.getDate().getDate_debut())==1)&&
								(new_date_fin.compareTo(el.getDate().getDate_fin())==-1))){
						Interfere=true;
						System.out.println("Cet horaire interfère avec une autre horaire pour cette même activite !");
						}
				}
			}

			//Si pas d'interférence on peut créer cette nouvelle activité
			if(Interfere==false){
				last_ref = access_repository.getListe_ref_activite().size() + 1;
				ref_activite_temp2 = new Ref_activite(last_ref, ref_activite_temp.getActivite());
				access_repository.setListe_ref_activite(ref_activite_temp2);

				Tarif_activite tarif_activite = new Tarif_activite(ref_activite_temp2, null, 0);
				access_repository.setListe_tarif_activite(tarif_activite);

				Horaire_activite horaire_activite = new Horaire_activite(ref_activite_temp2, date);
				access_repository.setListe_horaire_activite(horaire_activite);

				Activites activite_temp = new Activites(horaire_activite, tarif_activite);
				access_repository.setListe_general_activites(activite_temp);
			}

		}

	}
	
	/////////////////////////////////////////////////////////////////////////////////////
	
	//Tarif
	@Override
	public void tarif(Ref_activite ref_activite_temp) throws SaisieErroneeException{
		
		int choix_type_prix;
		String type_prix;
		Boolean EmptyOrNot = false;
	
		//On regarde si cette activite a déjà des données sur le tarif
		for (int i = 0; i < access_repository.getListe_general_activites().size(); i++) {
			if(access_repository.getListe_general_activites().get(i).getHoraire_activite().getRef_activite().getActivite()==ref_activite_temp.getActivite()) {
				if(access_repository.getListe_general_activites().get(i).getTarif_activite().getPrix()==0) {
					EmptyOrNot=true;
				}
			}
		}
		
		//Si l'activité n'a pas encore de tarif affecté
		if(EmptyOrNot==true) {
			do {
				System.out.println("Type prix");
				System.out.println("1. fft\n2. €/h");
				System.out.println("Votre choix : ");
				System.out.println();
				
				choix_type_prix = (new Scanner(System.in)).nextInt();
			} while (!(choix_type_prix == 1) && !(choix_type_prix == 2));

			if (choix_type_prix == 1) {
				type_prix = "fft";
			} else {
				type_prix = "€/h";
			}

			int prix;
			System.out.print("Entrez le prix :");
			prix = (new Scanner(System.in)).nextInt();

			Tarif_activite tarif_activite_temp = new Tarif_activite(ref_activite_temp, type_prix, prix);

			for (Activites el : access_repository.getListe_general_activites()) {
				if (el.getHoraire_activite().getRef_activite().getActivite().equals(ref_activite_temp.getActivite())) {
					el.setTarif_activite(tarif_activite_temp);
				}
			}
		}
		else {
			System.out.println("Cette activité contient déjà un tarif ! ");
		}
		
		
	}
	
	/////////////////////////////////////////////////////////////////////////////////////
	
	//Presence
	@Override
	public void presence(int ref) throws SaisieErroneeException {
		Ajouter_Modifier_Presence var = new Ajouter_Modifier_Presence(access_repository, access_repository_client, recherche);
		var.Ajouter();
	}

}
