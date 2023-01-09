package activites;

import java.util.ArrayList;
import java.util.Scanner;
import Exception.SaisieErroneeException;
import client.Afficher_client;
import client.Client;
import client.Repository_client;

public class Ajouter_Modifier_Presence{
	
	Repository_activite access_repository;
	Repository_client access_repository_client;
	Recherche recherche;
	
	public Ajouter_Modifier_Presence(Repository_activite access_repository, Repository_client access_repository_client, Recherche recherche) {
		this.access_repository=access_repository;
		this.access_repository_client=access_repository_client;
		this.recherche=recherche;
	}
	
	public void Ajouter() throws SaisieErroneeException {
		Boolean Verif_activite=false;
		Boolean Verif_client=false;
		
		//Sélectionner l'activité où l'on souhaite mettre une présence
		ArrayList<Integer> list = new Menus(access_repository, access_repository_client, recherche)
				.menu_afficher_client();

		if (list.size() != 0) {
			int ref_activite = Integer.parseInt(new Afficher(access_repository, access_repository_client).affiche_recherche(list,"Ajouter"));
			
			// On regarde quel client(s) sont affectes à l'activité sélectionnée ci-dessus
			ArrayList<Integer> liste_ref_client = new ArrayList<>();
			for (Client el : access_repository_client.getListe_client()) {
				if (el.getActivites().getHoraire_activite().getRef_activite().getRef() == ref_activite) {
					if (el.getPresence() == null) {
						liste_ref_client.add(el.getRef_client().getRef());
						System.out.println();
						System.out.println(el.getRef_client().getRef() + " Nom client : " + el.getRef_client().getNom()
								+ " - Club : " + el.getRef_client().getClub());

						System.out.println();
					}
				}
			}

			// On choisit le client
			int choix_client;
			System.out.print("Entrez une référence client :");
			System.out.println();
			choix_client = (new Scanner(System.in)).nextInt();

			// Vérifie que la ref client entrée par l'user est valide
			for (Integer i : liste_ref_client) {
				if (i == choix_client) {
					Verif_client = true;
				}
			}

			// Mettre présent pour ce client
			if (Verif_client == true) {
				for (Client client : access_repository_client.getListe_client()) {
					if (client.getRef_client().getRef() == choix_client
							&& client.getActivites().getHoraire_activite().getRef_activite().getRef() == ref_activite) {
						client.setPresence(true);
					}
				}
			}

			else {
				throw new SaisieErroneeException("Entrer une référence client valide !");
			}
		}
	}

	public void Modifier(int ref_activite) throws SaisieErroneeException {
		
			Boolean Verif_client =false;
			ArrayList<Integer>liste_ref_client=new ArrayList<>();

			//On a déjà la ref activité en paramètre ici on recherche une ref client
			for (Client el : access_repository_client.getListe_client()) {
				if (el.getActivites().getHoraire_activite().getRef_activite().getRef() == ref_activite) {
						liste_ref_client.add(el.getRef_client().getRef());
						System.out.println();
						System.out.println(el.getRef_client().getRef() + " Nom client : " + el.getRef_client().getNom()
								+ " - Club : " + el.getRef_client().getClub());

						System.out.println();
				}
			}
			
			//L'user entre une ref client
			int choix_client;
			System.out.print("Entrez une référence client : ");
			choix_client = (new Scanner(System.in)).nextInt();
			System.out.println();
			
			
			//Vérifie que la ref client de l'user est valide
			if(liste_ref_client.get(0)==choix_client) {
				Verif_client=true;
			}
			
			if(Verif_client==true) {
				//On définit présent ou pas 
				String modif_presence_client;
				System.out.print("Présent ou pas ? (O/N): ");
				modif_presence_client = (new Scanner(System.in)).nextLine();
				System.out.println();
				
				for (Client client : access_repository_client.getListe_client()) {
					if(client.getActivites().getHoraire_activite().getRef_activite().getRef()==ref_activite && client.getRef_client().getRef()==choix_client) {
						if (modif_presence_client.equals("O") || modif_presence_client.equals("o")) {
							client.setPresence(true);
						}

						else if (modif_presence_client.equals("N") || modif_presence_client.equals("n")) {
							client.setPresence(false);
						}
					}
				}
				
			}
			else {
				throw new SaisieErroneeException("Entrer une référence client valide !");
			}

			
		
	}

}
