package activites;

import java.time.LocalDateTime;
import java.util.ArrayList;

import activites.horaire_activites.Horaire_activite;
import client.Ref_client;
import client.Repository_client;

public class Recherche {
	Repository_activite access_repository;
	Repository_client access_repository_client;
	
	public Recherche(Repository_activite access_repository,Repository_client access_repository_client) {
		this.access_repository=access_repository;
		this.access_repository_client=access_repository_client;
		
	}
	
	//Recherche activité par nom activité

	public ArrayList<Integer> recherche_activite_nom(String nom_recherche) {

		String nom = nom_recherche;
		String nom_lower_case=nom.toLowerCase();
		String nom_liste_lower_case=null;
		
		ArrayList<Integer> liste_nom_recherche = new ArrayList<>();

		for (Horaire_activite el : access_repository.getListe_horaire_activite()) {
			nom_liste_lower_case=el.getRef_activite().getActivite().toLowerCase();
			if (nom_liste_lower_case.contains(nom_lower_case)) {
				liste_nom_recherche.add(el.getRef_activite().getRef());
			}
		}
		return liste_nom_recherche;
	}
	
	//////////////////////////////////////////////////////////////////////
	
	//Recherche par date activité
	public ArrayList<Integer> recherche_activite_date(LocalDateTime date_recherche) {

		LocalDateTime date = date_recherche;
		ArrayList<Integer> liste_date_recherche = new ArrayList<>();
		

		for (Horaire_activite el : access_repository.getListe_horaire_activite()) {
			if (date.compareTo(el.getDate().getDate_debut()) == 0) {
				liste_date_recherche.add(el.getRef_activite().getRef());
			} else {
				if (date.compareTo(el.getDate().getDate_fin()) == 0) {
					liste_date_recherche.add(el.getRef_activite().getRef());
				} else {
					if ((date.compareTo(el.getDate().getDate_debut()) == 1)
							&& (date.compareTo(el.getDate().getDate_fin())) == -1) {
						liste_date_recherche.add(el.getRef_activite().getRef());
					}
				}
			}
		}

		return liste_date_recherche;
	}
	
	/////////////////////////////////////////////////////////////////////
	
	//Recherche par nom client
	public ArrayList<Integer> recherche_nom_client(String nom_recherche) {
		
		String nom=nom_recherche;
		String nom_lower_case=nom.toLowerCase();
		String nom_liste_lower_case=null;
		
		ArrayList<Integer> liste_nom_recherche = new ArrayList<>();
		
		for (Ref_client el: access_repository_client.getListe_ref_client()) {
			nom_liste_lower_case=el.getNom().toLowerCase();
			
			if(nom_liste_lower_case.contains(nom_lower_case)) {
				liste_nom_recherche.add(el.getRef());
			}
		}
		liste_nom_recherche.add(-1);
		
		return liste_nom_recherche;
		
	}		
}
