package activites;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import Exception.SaisieErroneeException;
import client.Client;
import client.Repository_client;

public class Afficher {

	Repository_activite access_repository;
	Repository_client access_repository_client;

	public Afficher(Repository_activite access_repository, Repository_client access_repository_client) {
		this.access_repository = access_repository;
		this.access_repository_client = access_repository_client;
	}

	public String affiche_recherche(ArrayList<Integer> list_affichage, String traitement) throws SaisieErroneeException{
		
		Boolean Verif=false;
		String valeur_return = null;

		if (traitement == null) {
			affiche_tout(list_affichage);
		}

		if (traitement != null) {
			affiche_tout(list_affichage);

			String ref;

			System.out.println();
			System.out.println("Sélectionnez une référence activité pour " + traitement + " ?");
			
			ref = (new Scanner(System.in)).nextLine();
			
			for (Integer i : list_affichage) {
				if(Verif==false) {
					if(Integer.parseInt(ref)==i) {
						Verif=true;
					}
				}
			}
			
			if(Verif==true) {
				valeur_return = ref;
			}
			else {
				throw new SaisieErroneeException("Entrer une référence activité valide !");
			}	
		}
		return valeur_return;

	}

	public void affiche_tout(ArrayList<Integer> list_affichage) {

		if (list_affichage != null) {
			for (Integer i : list_affichage) {

				for (Activites el : access_repository.getListe_general_activites()) {
					if (el != null) {

						if (el.getHoraire_activite() != null) {
							if (el.getHoraire_activite().getRef_activite().getRef() == i) {
								
								//Reference
								System.out.println();
								System.out.println("id activité : "+el.getHoraire_activite().getRef_activite().getRef());
								System.out.println("Nom activité : "+el.getHoraire_activite().getRef_activite().getActivite());
								System.out.println();

								//Horaire
								if (el.getHoraire_activite().getDate() != null) {
									LocalDateTime date_debut =el.getHoraire_activite().getDate().getDate_debut(); 
									LocalDateTime date_fin =el.getHoraire_activite().getDate().getDate_fin();

									System.out.println("Date début : "+date_debut.getHour()+":"+date_debut.getMinute()+" | "+ date_debut.getDayOfMonth()+"/"+date_debut.getMonthValue()+"/"+date_debut.getYear());
									System.out.println("Date fin : "+date_fin.getHour()+":"+date_fin.getMinute()+" | "+ date_fin.getDayOfMonth()+"/"+date_fin.getMonthValue()+"/"+date_fin.getYear());
									System.out.println("Durée : "+el.getHoraire_activite().getDate().getDuree_activite()+" H");

									System.out.println();
								}

								//Tarif
								if (el.getTarif_activite().getPrix() != 0) {
									System.out.println("Type de prix : "+el.getTarif_activite().getType_prix());
									System.out.println("Prix : "+el.getTarif_activite().getPrix()+" €");
								}

								
								//Client(s)
								for (Client client : access_repository_client.getListe_client()) {
									if(client!=null && client.getActivites()!=null) {
										if (client.getActivites().getHoraire_activite().getRef_activite().getRef() == i) {
										String presence = null;
										
										System.out.println();
										System.out.println("id client : "+client.getRef_client().getRef());
										System.out.println( "Nom client : "+ client.getRef_client().getNom() + " -  Nom du club : "+ client.getRef_client().getClub());
										if(client.getPresence()==null) {
											presence = "Non";
										}
										if(client.getPresence()!=null) {
											if(client.getPresence()==true) {
												presence = "Oui";
											}
											else {
												presence="Non";
											}	
										}

										System.out.println("Présence : " + presence);
										System.out.println();

									}
								}

							}
							System.out.println("----------------------------");
						}
					}
				}
			}
		}
	}
}
}
