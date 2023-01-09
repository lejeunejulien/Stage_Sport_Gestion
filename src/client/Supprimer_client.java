package client;

import java.util.Scanner;
import activites.Repository_activite;


public class Supprimer_client implements interface_parametre_client{
	
	Repository_activite access_repository;
	Repository_client access_repository_client;
	
	
	public Supprimer_client(Repository_activite access_repository,Repository_client access_repository_client) {
		this.access_repository=access_repository;
		this.access_repository_client=access_repository_client;
		}

	@Override
	public void nom(String ref_client,String empty) {
		if(access_repository_client.getListe_client().size()!=0) {
			int ref = Integer.parseInt(ref_client);
			Ref_client ref_client_objet = null;

			for (Client el : access_repository_client.getListe_client()) {
				if (el != null) {
					if (el.getRef_client().getRef() == ref) {
						ref_client_objet = el.getRef_client();
					}
				}
			}

			for (int i = 0; i < access_repository_client.getListe_client().size(); i++) {
				if (access_repository_client.getListe_client().get(i).getRef_client().getNom()
						.equals(ref_client_objet.getNom())
						&& access_repository_client.getListe_client().get(i).getRef_client().getClub()
								.equals(ref_client_objet.getClub())) {
					access_repository_client.getListe_client().set(i, null);
				}
			}
		}
		else {
			System.out.println("Il n'y a pas de personne inscrite !");
		}
		
	}

	@Override
	public void activite(Ref_client ref_client, int ref_activite) {

		if (access_repository_client.getListe_client().size() != 0) {
			for (int i = 0; i < access_repository_client.getListe_client().size(); i++) {
				if (access_repository_client.getListe_client().get(i) != null) {
					if (access_repository_client.getListe_client().get(i).getRef_client().equals(ref_client)
							&& access_repository_client.getListe_client().get(i).getActivites().getHoraire_activite()
									.getRef_activite().getRef() == ref_activite) {
						access_repository_client.getListe_client().set(i, null);
					}
				}
			}
		}
		else {
			System.out.println("Il n'y pas de personne inscrite !");
		}

	}

	@Override
	public void presence() {		
	}

	@Override
	public void tarif_special(Ref_client objet_ref_client) {
		
		if(access_repository_client.getListe_client().size()!=0) {
			int choix;
			System.out.print("\n1. Assurance annulation\n2. Remise de prix");
			System.out.println();
			System.out.println("Que souhaitez-vous supprimer ?");
			choix = (new Scanner(System.in)).nextInt();

			switch (choix) {
			case 1:
				for (Client el : access_repository_client.getListe_client()) {
					if (el.getRef_client().getNom().equals(objet_ref_client.getNom())
							&& el.getRef_client().getClub().equals(objet_ref_client.getClub())) {
						if (el.isAssurance_annulation()) {
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
							el.setActivation_remise(false);
						}
					}
				}
				break;
			}
		}
		else {
			System.out.println("Il n'y a pas de personne inscrite !");
		}
		
		
		
	}
}
