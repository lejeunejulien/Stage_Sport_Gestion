package activites;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import Exception.SaisieErroneeException;
import activites.horaire_activites.Date2;
import client.Repository_client;

public class Menus {
	Repository_activite access_repository;
	Repository_client access_repository_client;
	Recherche recherche;
	
	public Menus(Repository_activite access_repository,Repository_client access_repository_client, Recherche recherche) {
		this.access_repository=access_repository;
		this.access_repository_client=access_repository_client;
		this.recherche=recherche;

	}
	
	// Menu affichage activité
	public ArrayList<Integer> menu_afficher_activite() throws SaisieErroneeException{

		int menu_afficher;

		ArrayList<Integer> liste_affichage = new ArrayList<Integer>();
		do {
			System.out.println();
			System.out.println("Menu afficher ACTIVITE");
			System.out.println("\n1. Afficher tout\n2. Recherche\n3. Quitter");
			System.out.println();
			System.out.print("Votre choix : ");
			System.out.println();
			menu_afficher = (new Scanner(System.in)).nextInt();

			switch (menu_afficher) {
			case 1:

				int total_activite = access_repository.getListe_general_activites().size();
				for (int i = 1; i <= total_activite; i++) {
					liste_affichage.add(i);
				}
				break;

			case 2:
				
				liste_affichage = menu_recherche_activite();
				break;

			case 3:
				break;
			}
		}

		while (!(menu_afficher == 1) && !(menu_afficher == 2) && !(menu_afficher == 3));

		return liste_affichage;
	}
	
	//////////////////////////////////////////////////////////////////////
	
	// Menu affichage activite
		public ArrayList<Integer> menu_afficher_client() throws SaisieErroneeException {

			int menu_afficher;

			ArrayList<Integer> liste_affichage = new ArrayList<Integer>();
			do {
				System.out.println();
				System.out.println("Menu afficher CLIENT");
				System.out.println("\n1. Afficher tout\n2. Recherche(Paiement Client)\n3. Quitter");
				System.out.println();
				System.out.print("Votre choix : ");
				System.out.println();
				menu_afficher = (new Scanner(System.in)).nextInt();

				switch (menu_afficher) {
				case 1:

					int total_client = access_repository_client.getListe_client().size();
					for (int i = 1; i <= total_client; i++) {
						liste_affichage.add(i);
					}
					break;

				case 2:

					liste_affichage = menu_recherche_client();
					break;

				case 3:
					break;
				}
			}

			while (!(menu_afficher == 1) && !(menu_afficher == 2) && !(menu_afficher == 3));
			
			
			return liste_affichage;
		}
	
	////////////////////////////////////////////////////////////////////:
	
	//Menu recherche activite par nom activite et date
	public  ArrayList<Integer> menu_recherche_activite() throws SaisieErroneeException{
		System.out.println("Menu Recherche activite");
		System.out.println();
		
		int menu_recherche_activite;
		ArrayList<Integer> liste_recherche = null;
		
		
		
		do{
			System.out.println();
			System.out.println("Menu Recherche");
			System.out.println("\n1. Par nom\n2. Par horaire\n3. Quitter");
			System.out.println();
			System.out.print("Votre choix : ");
			System.out.println();
			
			menu_recherche_activite =(new Scanner(System.in)).nextInt();
			
			switch(menu_recherche_activite){
			case 1:
				String nom_recherche;
				
				System.out.println();
				System.out.print("Entrez un nom : ");
				System.out.println();
				nom_recherche =(new Scanner(System.in)).nextLine();
				
				liste_recherche= new Recherche(access_repository, access_repository_client).recherche_activite_nom(nom_recherche);
				
				break;
			
			case 2:
			    LocalDateTime date_recherche = new Date2().getDate_recherche();
			    liste_recherche = new Recherche(access_repository, access_repository_client).recherche_activite_date(date_recherche);
				break;		
			
			case 3:
				break;
			
			}
		}

		while(!(menu_recherche_activite == 1) && !(menu_recherche_activite == 2) && !(menu_recherche_activite == 3));
		
		
		return liste_recherche;
	}
	
	///////////////////////////////////////
	
	// Menu recherche client par nom client et nom activité
	public  ArrayList<Integer> menu_recherche_client() throws SaisieErroneeException {
		System.out.println("Menu Recherche client");
		System.out.println();
		
		int menu_recherche_client;
		ArrayList<Integer> liste_recherche = null;
		do{
			System.out.println();
			System.out.println("Menu Recherche client");
			System.out.println("\n1. Par nom client (Paiement)\n2. Par nom Activité\n3. Quitter");
			System.out.println();
			System.out.print("Votre choix : ");
			System.out.println();
			
			menu_recherche_client =(new Scanner(System.in)).nextInt();
			
			switch(menu_recherche_client){
			case 1:
				String nom_recherche_client=null;
				
				System.out.println();
				System.out.print("Entrez un nom de client: ");
				System.out.println();
				nom_recherche_client =(new Scanner(System.in)).nextLine();
				
				liste_recherche= new Recherche(access_repository, access_repository_client).recherche_nom_client(nom_recherche_client);
				
				break;
			
			case 2:
				String nom_recherche_activite;
				
				System.out.println();
				System.out.print("Entrez un nom d'activité : ");
				System.out.println();
				nom_recherche_activite =(new Scanner(System.in)).nextLine();
				
			    liste_recherche = new Recherche(access_repository, access_repository_client).recherche_activite_nom(nom_recherche_activite);
				break;
			
			case 3:
				break;
			}
		}

		while(!(menu_recherche_client == 1) && !(menu_recherche_client == 2) && !(menu_recherche_client == 3));
		
				
		return liste_recherche;
		
	}
}
