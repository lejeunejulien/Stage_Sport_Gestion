package client;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import Exception.SaisieErroneeException;
import activites.Repository_activite;

public class Afficher_client {

	Repository_activite access_repository;
	Repository_client access_repository_client;

	public Afficher_client(Repository_activite access_repository, Repository_client access_repository_client) {
		this.access_repository = access_repository;
		this.access_repository_client = access_repository_client;
	}

	public String affiche_recherche(ArrayList<Integer> list_affichage, String traitement) throws SaisieErroneeException {
		
		Boolean Verif=false;
		String valeur_return = null;

		if (traitement == null) {
			affiche_tout(list_affichage);
		}

		if (traitement != null) {
			affiche_tout(list_affichage);

			String ref;

			System.out.println();
			System.out.println("Sélectionnez une référence client pour " + traitement + " ?");
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
				throw new SaisieErroneeException("Entrer une référence client valide !");
			}
			
		}
		return valeur_return;
	}

	public void affiche_tout(ArrayList<Integer> list_affichage) {
		Boolean Activation_Paiement=false;

		if (list_affichage != null) {
			double paiement_activites = 0;
			
			//Si indice -1 dans la liste -> affichage paiement client
			for (Integer ref : list_affichage) {
				if(ref==-1) {
					Activation_Paiement=true;
				}
			}
			if(Activation_Paiement==true) {
				list_affichage.remove(list_affichage.size()-1);
			}
			////////////////////
			
			for (Integer i : list_affichage) {

				for (Client el : access_repository_client.getListe_client()) {
					if (el != null) {
						if (el.getRef_client()!= null) {
							if (el.getRef_client().getRef() == i) {

								//Ref client
								System.out.println();
								System.out.println("id client : "+el.getRef_client().getRef());
								System.out.println("Nom : "+ el.getRef_client().getNom() + " - Club : " + el.getRef_client().getClub());
								
								//Présence
								String presence=null;
								if(el.getPresence()==null) {
									presence = "Non";
								}
								if(el.getPresence()!=null) {
									if(el.getPresence()==true) {
										presence = "Oui";
									}
									else {
										presence="Non";
									}	
								}
								
								System.out.println("Présence : " + presence);
								System.out.println();

								//Ref activite
								if (el.getActivites()!= null) {
									if (el.getActivites().getHoraire_activite().getRef_activite().getRef() != 0) {
										System.out.println("id activité : "+el.getActivites().getHoraire_activite().getRef_activite().getRef()
														+ " | Nom activité : " + el.getActivites().getHoraire_activite()
																.getRef_activite().getActivite());
										System.out.println();
									}
									

									//Horaire
									if (el.getActivites().getHoraire_activite()!= null) {
										LocalDateTime date_debut = el.getActivites().getHoraire_activite().getDate().getDate_debut();
										LocalDateTime date_fin = el.getActivites().getHoraire_activite().getDate().getDate_fin();
										System.out.println("Date début : "+date_debut.getHour()+":"+date_debut.getMinute()+" | "+ date_debut.getDayOfMonth()+"/"+date_debut.getMonthValue()+"/"+date_debut.getYear());
										System.out.println("Date fin : "+date_fin.getHour()+":"+date_fin.getMinute()+" | "+ date_fin.getDayOfMonth()+"/"+date_fin.getMonthValue()+"/"+date_fin.getYear());
										System.out.println("Durée : "+el.getActivites().getHoraire_activite().getDate().getDuree_activite()+" H");
										System.out.println();
									}

									//Tarif activite
									if (el.getActivites().getTarif_activite().getType_prix()!= null) {
										System.out.println("Type de prix : "+el.getActivites().getTarif_activite().getType_prix());
										System.out.println("Prix : "+el.getActivites().getTarif_activite().getPrix()+" €");
										System.out.println();

										double tarif_total_activite;

										if (el.getActivites().getTarif_activite().getType_prix().equals("fft")) {
											tarif_total_activite = el.getActivites().getTarif_activite().getPrix();

										} else {
											tarif_total_activite = el.getActivites().getHoraire_activite().getDate()
													.getDuree_activite()
													* el.getActivites().getTarif_activite().getPrix();
										}
										paiement_activites += tarif_total_activite;
										System.out.println("Tarif de base activité : " + tarif_total_activite+" €");
									}
									
									System.out.println();
									System.out.println("---------");

								}
							}
						}
					}
				}
			}
			
			//Paiement client
			if(Activation_Paiement) {
				System.out.println("Paiement total activité(s) client " +list_affichage.get(0)+ " : " + paiement_activites+" €");
				System.out.println();
				
				Boolean affichage=false;
				double total=paiement_activites;
				
				for (Client client : access_repository_client.getListe_client()) {
					if(client.getRef_client().getRef()==list_affichage.get(0)) {
						if(affichage==false) {
							affichage=true;
							if (client.getActivation_remise() == true) {
								double remise = client.getRemise();
								double reduction = (1 - (remise / 100));
								System.out.println("Remise de prix : " + client.getRemise() + " %");
								total = paiement_activites * (reduction);
							}
							if (client.getActivation_remise() == false) {
								System.out.println("Aucune remise de prix");
							}
							if (client.isAssurance_annulation()) {
								System.out.println(
										"Prix assurance annulation : " + client.getPrix_assurance_annulation() + " €");
								total += client.getPrix_assurance_annulation();
							}
							if (client.isAssurance_annulation() == false) {
								System.out.println("Aucune assurance annulation");
							}
						}		
					}
				}
				System.out.println();
				System.out.println("Total : "+total+" €");
			}
		}

	}
}
