package activites;

import activites.horaire_activites.Ref_activite;


public class Supprimer implements interface_parametre_activite{
	
	Repository_activite access_repository;
	
	public Supprimer(Repository_activite access_repository) {
		this.access_repository=access_repository;
		}

	@Override
	public void nom(String ref_string) {
		int ref = Integer.parseInt(ref_string);
		Ref_activite ref_activite_objet = null;
		
		//On recherche l'objet
		for (Activites el : access_repository.getListe_general_activites()) {
			if (el != null) {
				if (el.getHoraire_activite().getRef_activite().getRef() == ref) {
					ref_activite_objet = el.getHoraire_activite().getRef_activite();
				}
			}
		}

		for (int i = 0; i < access_repository.getListe_general_activites().size(); i++) {
			if (access_repository.getListe_general_activites().get(i) != null) {
				if (access_repository.getListe_general_activites().get(i).getHoraire_activite().getRef_activite()
						.getActivite().equals(ref_activite_objet.getActivite())) {
					access_repository.getListe_general_activites().set(i, null);
				}
			}
		}
	}

	@Override
	public void horaire(Ref_activite ref_activite_temp) {
		
		for (int i = 0; i < access_repository.getListe_general_activites().size(); i++) {
			if (access_repository.getListe_general_activites().get(i) != null) {
				if (access_repository.getListe_general_activites().get(i).getHoraire_activite().getRef_activite()
						.getRef() == ref_activite_temp.getRef()) {
					access_repository.getListe_general_activites().set(i, null);
				}
			}

		}
	}
		
	@Override
	public void tarif(Ref_activite ref_activite_temp) {
	}

	@Override
	public void presence(int ref) {
	}

}
