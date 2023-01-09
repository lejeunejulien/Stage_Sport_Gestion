package activites;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Exception.SaisieErroneeException;
import activites.horaire_activites.Ref_activite;
import client.Repository_client;


public class Main_activites{
	Repository_activite access_repository;
	Repository_client access_repository_client;
	Recherche recherche;
	Menus menu;
	
	public Main_activites(Repository_activite access_repository, Repository_client access_repository_client) throws SaisieErroneeException{
		this.access_repository=access_repository;
		this.access_repository_client=access_repository_client;
				
		System.out.println("Main activites");
		System.out.println();
		
		int menu_principal_activite=0;		
		
		do{
			System.out.println();
			System.out.println("Menu paramètre activité");
			
			System.out.println("\n1. Afficher\n2. Ajouter\n3. Modifier\n4. Supprimer\n5. Quitter");
			System.out.println();
			System.out.print("Votre choix : ");
			menu_principal_activite =(new Scanner(System.in)).nextInt();			
			
			switch(menu_principal_activite){
			
			
			case 1:
				//Afficher
				new Afficher(access_repository, access_repository_client)
				.affiche_recherche(new Menus(access_repository, access_repository_client,recherche)
						.menu_afficher_activite(),null);
				break;
			
			///////////////////////////////////////////////////////
				
			case 2:
				//Ajouter
			
				int choix_ajouter=0;
				interface_parametre_activite ajouter = new Ajouter(access_repository, access_repository_client,
						recherche);

				System.out.println(
						"\n1. Ajouter activité\n2. Ajouter horaire\n3. Ajouter tarif\n4. Ajouter présence\n5. Quitter");
				System.out.print("Votre choix : ");
				System.out.println();
				
				try {
				choix_ajouter = (new Scanner(System.in)).nextInt();
				if(choix_ajouter!=1 && choix_ajouter!=2 && choix_ajouter!=3 && choix_ajouter!=4 && choix_ajouter!=5) {
					throw new SaisieErroneeException("Saisie non valide !");}
				}
				catch(SaisieErroneeException e) {
					System.out.println();
					System.out.println(e.getMessage());
				}
				catch(InputMismatchException e) {
					System.out.println();
					System.out.println("Entrer un nombre !");
				}
				

				if (choix_ajouter == 1) {
					//Ajouter activite
					menu_parametre_activite(ajouter, 0, 1);
				}

				if (choix_ajouter == 2 || choix_ajouter == 3) {
					//Ajouter horaire ou tarif
					//On cree une liste qui contient ref(s) activite(s)
					ArrayList<Integer>list_activite_ajouter=new Menus(access_repository, access_repository_client, recherche)
							.menu_afficher_activite();
					System.out.println(list_activite_ajouter);
					
					if(list_activite_ajouter.size()!=0 && list_activite_ajouter!=null){
						//Afficher les activités suivant "Afficher tout" ou "Recherche" + sélection d'une activité + transmission des données
						//au menu parametre activite
						menu_parametre_activite(ajouter,Integer.parseInt(new Afficher(access_repository, access_repository_client)
								.affiche_recherche(list_activite_ajouter,"Ajouter")),choix_ajouter);
					}
					
				}
				if(choix_ajouter==4) {
					//Ajouter presence
					menu_parametre_activite(ajouter,0,choix_ajouter);
				}

				if(choix_ajouter==5) {
					//Quitter
					break;
				}
				break;		
				
			///////////////////////////////////////////////	
			
			case 3:
				//Modifier
				int menu_activite_modifier=0;
				System.out.println();
				System.out.println("1. Nom\n2. Horaire\n3. Tarif\n4. Présence\n5. Quitter");
				System.out.println();
				System.out.print("Que souhaitez-vous modifier ? : ");
				
				try {
					 menu_activite_modifier= (new Scanner(System.in)).nextInt();
					if(menu_activite_modifier!=1 && menu_activite_modifier!=2 && menu_activite_modifier!=3 && menu_activite_modifier!=4 && menu_activite_modifier!=5) {
						throw new SaisieErroneeException();
					}
				}
				catch(SaisieErroneeException | InputMismatchException e) {
					System.out.println();
					System.out.println("Saisie non valide !");
				}
				
				if(menu_activite_modifier==1||menu_activite_modifier==2||menu_activite_modifier==3 || menu_activite_modifier==4) {
					//Menus et création d'une liste qui contient ref(s) activite(s)
					ArrayList<Integer>list_activite_modifier=new Menus(access_repository, access_repository_client, recherche)
							.menu_afficher_activite();
					
					if(list_activite_modifier.size()!=0 && list_activite_modifier!=null){
						//Affichage et sélection des activités + transmission des données au menu paramètre activité
						int ref_modif=Integer.parseInt(new Afficher(access_repository, access_repository_client).affiche_recherche(list_activite_modifier,"Modifier"));
						
						interface_parametre_activite modifier = new Modifier(access_repository, access_repository_client, recherche,ref_modif);
						
						menu_parametre_activite(modifier,ref_modif,menu_activite_modifier);
					}
				}
				
				break;
			
			
			case 4:
				//Supprimer
				interface_parametre_activite supprimer = new Supprimer(access_repository);
				int menu_activite_supprimer=0;
				
				System.out.println();
				System.out.println("\n1. Supprimer toutes les activités de même nom\n2. Supprimer une activité spécifique\n3. Quitter");
				System.out.println();
				System.out.print("Que souhaitez-vous supprimer ? : ");
				
				try {
					menu_activite_supprimer = (new Scanner(System.in)).nextInt();
					System.out.println();
					if(menu_activite_supprimer!=1 && menu_activite_supprimer!=2) {
						throw new SaisieErroneeException();
					}
				}catch(SaisieErroneeException | InputMismatchException e) {
					System.out.println();
					System.out.println("Saisie non valide !");
				}
				
				if(menu_activite_supprimer==1 || menu_activite_supprimer==2) {
					ArrayList<Integer>list_supprimer_activite=new Menus(access_repository, access_repository_client, recherche)
							.menu_afficher_activite();
					
					if(list_supprimer_activite.size()!=0 && list_supprimer_activite!=null){
						int ref_delete=Integer.parseInt(new Afficher(access_repository, access_repository_client).affiche_recherche(list_supprimer_activite,"Supprimer"));
						menu_parametre_activite(supprimer,ref_delete,menu_activite_supprimer);
					}
				}
				break;
					
			
			case 5:
				break;	
			}
		}

		while((menu_principal_activite==1) || (menu_principal_activite==2)||
				(menu_principal_activite==3) || (menu_principal_activite==4)||
				 !(menu_principal_activite==5));	
	}
	
	///////////////////////////////////////////////////////////////////////////////
	
	public void menu_parametre_activite(interface_parametre_activite choix,int ref,int menu_parametre_activite2) throws SaisieErroneeException {
	
		int menu_parametre_activite=menu_parametre_activite2;
		String nom = null;
		
		//Donnees actuelles
		Ref_activite ref_activite=null;
		
		// Capture de l'objet "Ref_activite_horaire" lié à la ref obtenue via la recherche
		if (ref != 0) {
			for (Activites el : access_repository.getListe_general_activites()) {
				if (el != null) {
					if (el.getHoraire_activite().getRef_activite().getRef() == ref) {
						ref_activite = el.getHoraire_activite().getRef_activite();
					}
				}
			}
		}

		do{
			
			//////////////////////////////

			switch(menu_parametre_activite){

			case 1:
				//Nom
				if(ref!=0) {
					//Cas supprimer
					if(choix instanceof Supprimer) {
						String confirmation_delete_all;
						do {
							System.out.print("Confirmez-vous le choix de supprimer la totalité des activités portant ce nom ? O/N : ");
							System.out.println();
							confirmation_delete_all = (new Scanner(System.in)).nextLine();
						} while (!(confirmation_delete_all.equals("O")) && !(confirmation_delete_all.equals("o"))
								&& !(confirmation_delete_all.equals("N")) && !(confirmation_delete_all.equals("n")));
						
						if((confirmation_delete_all.equals("o")) ||(confirmation_delete_all.equals("O"))) {
							String ref_to_string=String.valueOf(ref);
							choix.nom(ref_to_string);
							break;
						}
						else {
							break;
						}
					}
					//Cas modifier
					if(choix instanceof Modifier) {
						System.out.print("Entrez un nom d'activité : ");
						System.out.println();
						nom = (new Scanner(System.in)).nextLine();
						choix.nom(nom);
					}
				}
				else {
					//Cas ajouter
					System.out.print("Entrez un nom d'activité : ");
					System.out.println();
					nom = (new Scanner(System.in)).nextLine();
					choix.nom(nom);
					break;
				}	
				
				break;
				
			case 2:
				 choix.horaire(ref_activite);
				break;	
			case 3:
				try {
					choix.tarif(ref_activite);
				}
				catch(InputMismatchException e) {
					System.out.println("Entrez un prix !");
				}
				break;
			case 4:
				choix.presence(ref);
				break;
				
			case 5:
				break;
			}
		}
		
		while(!(menu_parametre_activite==1) && !(menu_parametre_activite==2)&&
				!(menu_parametre_activite==3) && !(menu_parametre_activite==4)&&
				!(menu_parametre_activite==5));
	}	
	
}

