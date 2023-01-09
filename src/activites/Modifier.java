package activites;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Scanner;

import Exception.SaisieErroneeException;
import activites.horaire_activites.Date2;
import activites.horaire_activites.Horaire_activite;
import activites.horaire_activites.Ref_activite;
import client.Repository_client;

public class Modifier implements interface_parametre_activite{
	
	Repository_activite access_repository;
	Repository_client access_repository_client;
	Recherche recherche;
	int ref;
	
	public Modifier(Repository_activite access_repository, Repository_client access_repository_client, Recherche recherche, int ref) {
		this.access_repository=access_repository;
		this.access_repository_client=access_repository_client;
		this.recherche=recherche;
		this.ref=ref;
	}

	@Override
	public void nom(String nom) {
		
		if (access_repository.getListe_general_activites().size() != 0) {
			for (Activites el : access_repository.getListe_general_activites()) {
				if (el.getHoraire_activite().getRef_activite().getRef() == ref) {
					el.getHoraire_activite().getRef_activite().setActivite(nom);
				}
			}
		}
		else {
			System.out.println("Il n'y pas d'activité enregistrée !");
		}
		
		
	}

	@Override
	public void horaire(Ref_activite ref_activite_temp) throws SaisieErroneeException,DateTimeException {
		
		if(access_repository.getListe_general_activites().size()!=0) {
			Date2 date = new Date2(true, null, null);
			Boolean Interfere = false;

			for (Horaire_activite el : access_repository.getListe_horaire_activite()) {
				if (el.getRef_activite().getRef() != ref_activite_temp.getRef()
						&& el.getRef_activite().getActivite().equals(ref_activite_temp.getActivite())) {
					LocalDateTime new_date_debut = date.getDate_debut();
					LocalDateTime new_date_fin = date.getDate_fin();
					if ((new_date_debut.compareTo(el.getDate().getDate_debut()) == 0)
							|| (new_date_debut.compareTo(el.getDate().getDate_fin()) == 0)
							|| ((new_date_debut.compareTo(el.getDate().getDate_debut()) == 1)
									&& (new_date_debut.compareTo(el.getDate().getDate_fin()) == -1))) {
						Interfere = true;
						System.out
								.println("Cet horaire interfère avec un autre horaire dans le cadre cette activité !");
					}

					if ((new_date_fin.compareTo(el.getDate().getDate_debut()) == 0)
							|| (new_date_fin.compareTo(el.getDate().getDate_fin()) == 0)
							|| ((new_date_fin.compareTo(el.getDate().getDate_debut()) == 1)
									&& (new_date_fin.compareTo(el.getDate().getDate_fin()) == -1))) {
						Interfere = true;
						System.out
								.println("Cet horaire interfère avec un autre horaire dans le cadre cette activité !");
					}
				}
			}

			for (int i = 0; i < access_repository_client.getListe_client().size(); i++) {
				LocalDateTime new_date_debut = date.getDate_debut();
				LocalDateTime new_date_fin = date.getDate_fin();

				LocalDateTime date_debut = access_repository_client.getListe_client().get(i).getActivites()
						.getHoraire_activite().getDate().getDate_debut();
				LocalDateTime date_fin = access_repository_client.getListe_client().get(i).getActivites()
						.getHoraire_activite().getDate().getDate_fin();

				if ((new_date_debut.compareTo(date_debut) == 0) || (new_date_debut.compareTo(date_fin) == 0)
						|| ((new_date_debut.compareTo(date_debut) == 1)
								&& (new_date_debut.compareTo(date_fin) == -1))) {
					Interfere = true;
					System.out.println("Cet horaire interfère avec un autre horaire dans le cadre d'un client !");
				}

				if ((new_date_fin.compareTo(date_debut) == 0) || (new_date_fin.compareTo(date_fin) == 0)
						|| ((new_date_fin.compareTo(date_debut) == 1) && (new_date_fin.compareTo(date_fin) == -1))) {
					Interfere = true;
					System.out.println("Cet horaire interfère avec un autre horaire dans le cadre d'un client !");
				}
			}

			// Si pas d'interference au niveau activité et client
			if (Interfere == false) {
				for (Activites el : access_repository.getListe_general_activites()) {
					if (el.getHoraire_activite().getRef_activite().getRef() == ref_activite_temp.getRef()) {
						el.getHoraire_activite().setDate(date);
					}
				}
			}
		}
		else {
			System.out.println("Il n'y pas d'activité enregistrée !");
		}
		
	}

	@Override
	public void tarif(Ref_activite ref_activite_temp) throws SaisieErroneeException {
		
		int choix_type_prix;
		String type_prix;
				
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

		for (Activites el : access_repository.getListe_general_activites()) {
			if (el.getHoraire_activite().getRef_activite().getActivite().equals(ref_activite_temp.getActivite())) {
				
				el.getTarif_activite().setType_prix(type_prix);
				el.getTarif_activite().setPrix(prix);
			}
		}
	}

	@Override
	public void presence(int ref) throws SaisieErroneeException {
		Ajouter_Modifier_Presence var = new Ajouter_Modifier_Presence(access_repository, access_repository_client, recherche);
		var.Modifier(ref);
	}


}
