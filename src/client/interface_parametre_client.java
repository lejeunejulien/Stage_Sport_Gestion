package client;

import Exception.SaisieErroneeException;

public interface interface_parametre_client {

	void nom(String nom,String club);

	void activite(Ref_client ref_client, int ref_activite);
	
	void presence()throws SaisieErroneeException;
	
	void tarif_special(Ref_client ref_client) throws SaisieErroneeException;
}
