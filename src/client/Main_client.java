package client;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Exception.SaisieErroneeException;
import activites.Afficher;
import activites.Menus;
import activites.Recherche;
import activites.Repository_activite;


public class Main_client {
	
	Repository_activite access_repository;
	Repository_client access_repository_client;
	Recherche recherche;
	Menus menus;
	
	public Main_client(Repository_activite access_repository, Repository_client access_repository_client) throws SaisieErroneeException{
		
		this.access_repository=access_repository;
		this.access_repository_client=access_repository_client;
		
		System.out.println();
		System.out.println("Main client");
		
		int menu_principal_client;
		int menu_parametre_client=0;
		
		
		do{
			System.out.println();
			System.out.println("Menu paramètre client");
			System.out.println("\n1. Afficher\n2. Ajouter\n3. Modifier\n4. Supprimer\n5. Quitter");
			System.out.print("Votre choix : ");
			System.out.println();
			menu_principal_client =(new Scanner(System.in)).nextInt();
			
			
			switch(menu_principal_client){
			case 1:
				new Afficher_client(access_repository, access_repository_client)
				.affiche_recherche(new Menus(access_repository, access_repository_client,recherche)
						.menu_afficher_client(),null);
				break;
				
		        //////////////////////////////
				
			case 2:
				//Ajouter
				int choix_ajouter;
				
				interface_parametre_client ajouter = new Ajouter_client(access_repository, access_repository_client, recherche);
				
				do {

					System.out.println("\n1. Client\n2. Activité\n3. Tarif special\n4. Présence\n5. Quitter");
					System.out.print("Votre choix : ");
					System.out.println();
					choix_ajouter =(new Scanner(System.in)).nextInt();
					
					switch (choix_ajouter) {
					case 1: 
						//Client
						menu_parametre_client(ajouter,0,0,1);
						break;
					
					case 2:
						//Activite
						int ref_ajouter_client=0;
						int ref_ajouter_activite=0;
						ArrayList<Integer> list_client_ajouter_activite=new Menus(access_repository, access_repository_client,recherche).menu_afficher_client();
						
						
						if(list_client_ajouter_activite.size()!=0 && list_client_ajouter_activite!=null) {
							ref_ajouter_client =Integer.parseInt(new Afficher_client(access_repository, access_repository_client)
									.affiche_recherche(list_client_ajouter_activite,"Ajouter"));
							
							
							ArrayList<Integer>list_activite=new Menus(access_repository, access_repository_client,recherche)
									.menu_afficher_activite();
							
							if(list_activite.size()!=0 && list_activite!=null) {
								ref_ajouter_activite =Integer.parseInt(new Afficher(access_repository, access_repository_client)
									.affiche_recherche(list_activite,"Ajouter"));
								
								menu_parametre_client(ajouter,ref_ajouter_client,ref_ajouter_activite,2);
							}
						}
						
						break;
					
					
					case 3 :
						//Tarif
						ArrayList<Integer>list_client_ajouter_tarif=new Menus(access_repository, access_repository_client,recherche)
								.menu_afficher_client();
						
						if(list_client_ajouter_tarif.size()!=0 && list_client_ajouter_tarif!=null) {
							ref_ajouter_client =Integer.parseInt(new Afficher_client(access_repository, access_repository_client)
								.affiche_recherche(list_client_ajouter_tarif,"Ajouter"));
							
							menu_parametre_client(ajouter,ref_ajouter_client,0,3);
						}				
						
						break;
						
					case 4 :
						//Présence
						menu_parametre_client(ajouter,0,0,4);
						break;
						
					case 5 :
						break;
									
					}	
				}
				while(!(choix_ajouter==1) && !(choix_ajouter==2) && !(choix_ajouter==3)&& !(choix_ajouter==4)&& !(choix_ajouter==5));
				
				break;
				
				
			   /////////////////////////////////
				
			case 3:
				// Modifier
				int menu_client_modifer=0;
				
				//Menu Modifier
				System.out.println("\n1. Nom/Club\n2. Tarif special\n3. Présence\n4. Quitter");
				System.out.println();
				System.out.print("Que souhaitez-vous modifier ? : ");
				
				///////
				try {
					 menu_client_modifer= (new Scanner(System.in)).nextInt();
					if(menu_client_modifer!=1 && menu_client_modifer!=2 && menu_client_modifer!=3 && menu_client_modifer!=4) {
						throw new SaisieErroneeException();
					}
				}
				catch(SaisieErroneeException | InputMismatchException e) {
					System.out.println();
					System.out.println("Saisie non valide !");
				}
				//////
				
				//Transfert choix menu modifier vers choix menu parametre client
								
				if(menu_client_modifer==2) {
					menu_parametre_client=3;
				}
				
				else if(menu_client_modifer==3) {
					menu_parametre_client=4;
				}
				else {
					menu_parametre_client=menu_client_modifer;
				}
				
				///////////////
				
				ArrayList<Integer>list_modifier=new Menus(access_repository, access_repository_client,recherche)
						.menu_afficher_client();
			
				
				if (list_modifier.size() != 0 && list_modifier!=null) {
					
					int ref_modifier_client = Integer
							.parseInt(new Afficher_client(access_repository, access_repository_client)
									.affiche_recherche(list_modifier, "Modifier"));
					
					interface_parametre_client modifier = new Modifier_client(access_repository,
							access_repository_client, ref_modifier_client, recherche);
					
					if (menu_parametre_client == 1||menu_parametre_client == 3) {
						//Nom/Club et tarif spécial
						menu_parametre_client(modifier, ref_modifier_client, 0, menu_parametre_client);
					}
					
					if(menu_parametre_client ==4) {
						//Présence
						menu_parametre_client(modifier, 0 , 0, menu_parametre_client);
					}

				}	
				
				break;
				
				///////////////////////////////
			
			case 4:
				//Supprimer
							
				int menu_client_supprimer_temp=0;
				//Menu supprimer
				interface_parametre_client supprimer = new Supprimer_client(access_repository, access_repository_client);

				System.out.println("\n1. Nom/Club -> !!La totalité des données du client sont supprimées !!"
							+ "\n2. Activité\n3. Tarif special\n4. Quitter");
				System.out.println();
				System.out.print("Que souhaitez-vous supprimer ? : ");
				
				///////
				try {
					menu_client_supprimer_temp = (new Scanner(System.in)).nextInt();
					System.out.println();
					if(menu_client_supprimer_temp!=1 && menu_client_supprimer_temp!=2 && menu_client_supprimer_temp!=3 && menu_client_supprimer_temp!=4) {
						throw new SaisieErroneeException();
					}
				}catch(SaisieErroneeException | InputMismatchException e) {
					System.out.println();
					System.out.println("Saisie non valide !");
				}
				//////
				
				//Supprimer nom/club ou tarif spécial
				if (menu_client_supprimer_temp == 1||menu_client_supprimer_temp == 3) {
					
					ArrayList<Integer>list_supprimer_nom_tarif=new Menus(access_repository, access_repository_client,recherche)
							.menu_afficher_client();
					if(list_supprimer_nom_tarif.size()!=0 && list_supprimer_nom_tarif!=null) {
						
						menu_parametre_client=menu_client_supprimer_temp;
						
						menu_parametre_client(supprimer,Integer.parseInt(new Afficher_client(access_repository, access_repository_client)
							.affiche_recherche(list_supprimer_nom_tarif,"Supprimer")),0,menu_parametre_client);
					}
				}
				
				//Supprimer activite
				if (menu_client_supprimer_temp == 2) {
					ArrayList<Integer>list_supprimer_activite_client=new Menus(access_repository, access_repository_client,recherche)
							.menu_afficher_client();

					if (list_supprimer_activite_client.size() != 0 && list_supprimer_activite_client!=null) {
						
						int ref_delete_client=Integer.parseInt(new Afficher_client(access_repository, access_repository_client)
								.affiche_recherche(list_supprimer_activite_client, "Supprimer"));

						ArrayList<Integer> list_supprimer_activite_activite = new Menus(access_repository,
								access_repository_client, recherche).menu_afficher_activite();

						if (list_supprimer_activite_activite.size() != 0 && list_supprimer_activite_activite!=null) {

							menu_parametre_client = menu_client_supprimer_temp;

							int ref_delete_activite = Integer
									.parseInt(new Afficher(access_repository, access_repository_client)
											.affiche_recherche(list_supprimer_activite_activite, "Supprimer"));

							menu_parametre_client(supprimer,ref_delete_client,ref_delete_activite, 2);

						}
					}
				}

				break;

			case 5:
				break;
			
			
			}}
		while(!(menu_principal_client==1) && !(menu_principal_client==2)&& 
				!(menu_principal_client==3) && !(menu_principal_client==4)&&
				!(menu_principal_client==5));	
		
	}
	
	///////////////////////////////////////////////////////////////////////////////

	
	public void menu_parametre_client(interface_parametre_client choix,int ref_client2,int ref_activite2,int menu_parametre_client2) throws SaisieErroneeException {
	
		String nom = null;
		String club=null;
	
		int menu_parametre_client=0;
		menu_parametre_client =menu_parametre_client2;
		
		//Donnees actuelles
		Ref_client ref_client_objet=null;
		
		//Capture de l'objet "Ref_activite_horaire" lié à la ref obtenue via la recherche 
		if(ref_client2!=0) {
			for (Client el : access_repository_client.getListe_client()) {
				if (el != null) {
					if (el.getRef_client().getRef() == ref_client2) {
						ref_client_objet = el.getRef_client();
					}
				}
			}
		}
		
		do{
			
			//////////////////////////////
	
			switch(menu_parametre_client){

			case 1:
				    // Supprimer
					if(choix instanceof Supprimer_client) {
						
						String confirmation_delete_all;
						do {
							System.out.print("Confirmez-vous le choix de supprimer la totalité des données du client ? O/N : ");
							System.out.println();
							confirmation_delete_all = (new Scanner(System.in)).nextLine();
						} while (!(confirmation_delete_all.equals("O")) && !(confirmation_delete_all.equals("o"))
								&& !(confirmation_delete_all.equals("N")) && !(confirmation_delete_all.equals("n")));
						
						if((confirmation_delete_all.equals("o")) ||(confirmation_delete_all.equals("O"))) {
							String ref_client_to_string=String.valueOf(ref_client2);
							choix.nom(ref_client_to_string,"");
		
						}
					}
					///////////////////////////////
					
					else {
						//Ajouter et modifier
						if((choix instanceof Ajouter_client && ref_client2==0)||(choix instanceof Modifier_client)) {
							System.out.print("Entrez un nom de client: ");
							System.out.println();
							nom = (new Scanner(System.in)).nextLine();

							System.out.print("Entrez un club : ");
							System.out.println();
							club = (new Scanner(System.in)).nextLine();

							choix.nom(nom,club);		
						}	
					}
					break;
				
			case 2:
				choix.activite(ref_client_objet,ref_activite2);
				
				break;	
				
			case 3:
				try {
					choix.tarif_special(ref_client_objet);
				}
				catch(SaisieErroneeException e) {
					System.out.println("Saisie non valide");
				}
				catch(InputMismatchException e) {
					System.out.println("Entrer un nombre");
				}
				
				break;
				
			case 4:
				choix.presence();
				break;
				
			case 5:
				break;
			}
		
		}
		while(!(menu_parametre_client==1) && !(menu_parametre_client==2) &&!(menu_parametre_client==3)&&!(menu_parametre_client==4));
	}	
}
