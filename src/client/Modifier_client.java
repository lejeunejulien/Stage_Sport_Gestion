package client;

import java.util.Scanner;

import Exception.SaisieErroneeException;
import activites.Ajouter_Modifier_Presence;
import activites.Recherche;
import activites.Repository_activite;

public class Modifier_client implements interface_parametre_client {
	
	Repository_activite access_repository;
	Repository_client access_repository_client;
	Recherche recherche;
	int ref;
	
	public Modifier_client(Repository_activite access_repository, Repository_client access_repository_client,int ref, Recherche recherche) {
		this.access_repository=access_repository;
		this.access_repository_client =access_repository_client;
		this.recherche=recherche;
		this.ref=ref;
	}
	

	@Override
	public void nom(String nom, String club) {
		
		if (access_repository_client.getListe_client().size() != 0) {
			Ref_client objet_ref = null;
			for (Client client : access_repository_client.getListe_client()) {
				if (client.getRef_client().getRef() == ref) {
					objet_ref = client.getRef_client();
				}
			}

			for (Client el : access_repository_client.getListe_client()) {
				if (el.getRef_client().getNom().equals(objet_ref.getNom())
						&& el.getRef_client().getClub().equals(objet_ref.getClub())) {
					el.getRef_client().setNom(nom);
					el.getRef_client().setClub(club);
				}
			}
		}
		else {
			System.out.println("Il n'y a pas de personne inscrite !");
		}
		
		
	}

	@Override
	public void activite(Ref_client ref_client, int ref_activite) {
	}


	@Override
	public void presence() throws SaisieErroneeException {
		Ajouter_Modifier_Presence var = new Ajouter_Modifier_Presence(access_repository, access_repository_client, recherche);
		var.Modifier(ref);
	}


	@Override
	public void tarif_special(Ref_client objet_ref_client) throws SaisieErroneeException{
		
		if(access_repository_client.getListe_client().size()!=0) {
			int choix = 0;

			do {
				System.out.print("\n1. Assurance annulation\n2. Remise de prix");
				System.out.println();
				System.out.println();
				System.out.println("Que souhaitez-vous modifier ?");
				choix = (new Scanner(System.in)).nextInt();

				switch (choix) {
				case 1:
					for (Client el : access_repository_client.getListe_client()) {
						if (el.getRef_client().getNom().equals(objet_ref_client.getNom())
								&& el.getRef_client().getClub().equals(objet_ref_client.getClub())) {

							String decision_assurance_annulation;
							System.out.print("\n1. Activer\n2. Désactiver");
							System.out.println();
							decision_assurance_annulation = (new Scanner(System.in)).nextLine();

							if (decision_assurance_annulation.equals("1")) {
								el.setAssurance_annulation(true);
							}
							if (decision_assurance_annulation.equals("2")) {
								el.setAssurance_annulation(false);
							}
						}
					}
					break;

				case 2:
					for (Client el : access_repository_client.getListe_client()) {
						if (el.getRef_client().getNom().equals(objet_ref_client.getNom())
								&& el.getRef_client().getClub().equals(objet_ref_client.getClub())) {
							if (el.getActivation_remise() == true) {
								String decision_modif_remise_prix;
								System.out.print("\n1. Desactiver la remise de prix\n2. Modifier la remise (%) ");
								System.out.println();
								decision_modif_remise_prix = (new Scanner(System.in)).nextLine();
								if (decision_modif_remise_prix.equals("1")) {
									el.setActivation_remise(false);
								}
								if (decision_modif_remise_prix.equals("2")) {
									el.setActivation_remise(true);
									int new_remise_prix;
									System.out.print("\nQuelle est votre nouvelle remise de prix (%) ");
									System.out.println();
									new_remise_prix = (new Scanner(System.in)).nextInt();
									el.setRemise(new_remise_prix);
								}
							}
						}
					}
					break;

				case 3:
					break;
				}

			} while (!(choix == 1) && !(choix == 2) && !(choix == 3));
		}
		else {
			System.out.println("Il n'y a pas de personne inscrite !");
		}
		
	}

}
