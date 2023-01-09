package activites;

import Exception.SaisieErroneeException;
import activites.horaire_activites.Ref_activite;


public interface interface_parametre_activite {
	
	void horaire(Ref_activite ref_activite_temp) throws SaisieErroneeException;
	
	void presence(int ref) throws SaisieErroneeException;

	void tarif(Ref_activite ref_activite_temp) throws SaisieErroneeException;

	void nom(String nom);
	
}
