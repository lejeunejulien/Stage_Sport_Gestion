package client;


import java.time.LocalDateTime;
import java.util.Scanner;

import Exception.SaisieErroneeException;
import activites.*;

public class Ajouter_client implements interface_parametre_client{
	
	Repository_activite access_repository;
	Repository_client access_repository_client;
	Recherche recherche;
	
	public Ajouter_client(Repository_activite access_repository, Repository_client access_repository_client, Recherche recherche) {
		this.access_repository=access_repository;
		this.access_repository_client =access_repository_client;
		this.recherche=recherche;
	}
	
	//Nom
	@Override
	public void nom(String nom,String club) {
		
		int last_ref = 0;
		Ref_client ref_client_temp;
		
		//Si la liste n'est pas vide
		if(access_repository_client.getListe_ref_client() != null) {
			last_ref=access_repository_client.getListe_ref_client().size()+1;
			ref_client_temp =new Ref_client(last_ref,nom,club);
			access_repository_client.setListe_ref_client(ref_client_temp);
			
		}
		//Si la liste de personnes est vide
		else {
			ref_client_temp =new Ref_client(1,nom,club);
			access_repository_client.setListe_ref_client(ref_client_temp);
		}

		////////////////////////////////////////////////
		
		//Création d'un client
		
		Client client = new Client(ref_client_temp, null,null);
		access_repository_client.setListe_client(client);
	}
	
	///////////////////////////////////////////////////////
	
	//Activite
	@Override
	public void activite(Ref_client objet_ref_client,int ref_activite) {
		Boolean New =true;
		Boolean Interfere=false;

		//Trouver l'objet de l'activité
		Activites activite=null;
		for (Activites j:access_repository.getListe_general_activites()) {
			if(j.getHoraire_activite().getRef_activite().getRef()==ref_activite){
				activite=j;
			}
		}

		// Regarde si il y a le client n'a pas une activité affectée
		for (Client el : access_repository_client.getListe_client()) {
			if (el != null) {
				if (el.getRef_client().equals(objet_ref_client)) {
					if (el.getActivites() == null) {
						el.setActivites(activite); // représente affectation de l'activité lors du new Activite
						New = false;
					}
				}
			}

		}

		//Si le client a une activité affectée
		if(New==true) {

			//Trouver le nom de l'activité
			String nom_activite=null;
			for (Client client:access_repository_client.getListe_client()) {
					if(client.getActivites().getHoraire_activite().getRef_activite().getRef()==ref_activite){
						nom_activite=client.getActivites().getHoraire_activite().getRef_activite().getActivite();
				}
			}

			for (Client el:access_repository_client.getListe_client()) {
				//Nom du client equals
				if((el.getRef_client().getNom().equalsIgnoreCase(objet_ref_client.getNom()))&&
						(el.getRef_client().getClub().equalsIgnoreCase(objet_ref_client.getClub()))){
					//Nom activite equals
					if(el.getActivites().getHoraire_activite().getRef_activite().getActivite().equalsIgnoreCase(nom_activite)){
						//Si il s'agit d'exactement la même activité
						if(ref_activite==el.getActivites().getHoraire_activite().getRef_activite().getRef()){
							System.out.println("Ce client a déjà cette activité !");
							Interfere=true;
						}
						else{
							LocalDateTime new_date_debut = activite.getHoraire_activite().getDate().getDate_debut();
							LocalDateTime new_date_fin = activite.getHoraire_activite().getDate().getDate_fin();
							if((new_date_debut.compareTo(el.getActivites().getHoraire_activite().getDate().getDate_debut())==0)||
									(new_date_debut.compareTo(el.getActivites().getHoraire_activite().getDate().getDate_fin())==0)||((new_date_debut.compareTo(el.getActivites().getHoraire_activite().getDate().getDate_debut())==1)&&
									(new_date_debut.compareTo(el.getActivites().getHoraire_activite().getDate().getDate_fin())==-1))){
								Interfere=true;
								System.out.println("Cet activité interfère avec une autre activite pour ce même client !");}

							if((new_date_fin.compareTo(el.getActivites().getHoraire_activite().getDate().getDate_debut())==1)||
									(new_date_fin.compareTo(el.getActivites().getHoraire_activite().getDate().getDate_fin())==0)||((new_date_fin.compareTo(el.getActivites().getHoraire_activite().getDate().getDate_debut())==1)&&
									(new_date_fin.compareTo(el.getActivites().getHoraire_activite().getDate().getDate_fin())==-1))){
								Interfere=true;
								System.out.println("Cet activité interfère avec une autre activite pour ce même client !");
							}
						}
					}
				}
			}
			//Si il n'y a pas d'inférerence au niveau activité ou client
			if(Interfere==false){
				
				Client client = new Client(objet_ref_client, activite, null);
				access_repository_client.setListe_client(client);
			}
		}
	}

	@Override
	public void presence() throws SaisieErroneeException {
		Ajouter_Modifier_Presence var = new Ajouter_Modifier_Presence(access_repository, access_repository_client, recherche);
		var.Ajouter();
	}

	@Override
	public void tarif_special(Ref_client objet_ref_client) throws SaisieErroneeException{
		
		int choix=0;
		do {
			
		System.out.print("\n1. Assurance annulation\n2. Remise de prix\n3. Quitter");
		System.out.println();
		System.out.println("Que souhaitez-vous ajouter ?");
		choix = (new Scanner(System.in)).nextInt();
		
		
		switch (choix) {
		case 1:
			String decision_assurance_annulation;
			System.out.print("Souhaitez-vous appliquez une assurance annulation ? O/N ");
			System.out.println();
			decision_assurance_annulation = (new Scanner(System.in)).nextLine();
			if(decision_assurance_annulation.equals("O")||decision_assurance_annulation.equals("o")){
				for (Client el : access_repository_client.getListe_client()) {
					if(el.getRef_client().equals(objet_ref_client)) {
						el.setAssurance_annulation(true);
					}
				}
			}
			break;

		case 2:
			String decision_remise_prix;
			System.out.print("Souhaitez-vous appliquez une remise de prix ? O/N ");
			System.out.println();
			decision_remise_prix = (new Scanner(System.in)).nextLine();
			if (decision_remise_prix.equals("O") || decision_remise_prix.equals("o")) {
				int remise;
				System.out.print("Combien en % ");
				System.out.println();
				remise = (new Scanner(System.in)).nextInt();

				for (Client el : access_repository_client.getListe_client()) {
					if (el.getRef_client().equals(objet_ref_client)) {
						el.setActivation_remise(true);
						el.setRemise(remise);
					}
				}
			}
			break;
		
		case 3:
			break;
		}

	} while (!(choix == 1) && !(choix == 2) && !(choix == 3));

}
}

