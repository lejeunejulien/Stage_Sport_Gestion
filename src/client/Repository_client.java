package client;


import java.util.ArrayList;

import activites.Repository_activite;

public class Repository_client {

	Repository_activite access_repository;

	public Repository_client(Repository_activite access_repository) {
		this.access_repository = access_repository;
		insertion_data_client();
	}
	
	
	public void insertion_data_client() {
//		// Client
//		Ref_client ref_client = new Ref_client(1, "Julien", "Club triathlon");
//		liste_ref_client.add(ref_client);
//		Ref_client ref_client2 = new Ref_client(2, "Roger", "Club triathlon2");
//		liste_ref_client.add(ref_client2);
//
//		Client client_general = new Client(ref_client, access_repository.getListe_general_activites().get(0),null);
//		liste_client.add(client_general);
//		Client client_general2 = new Client(ref_client2, access_repository.getListe_general_activites().get(1),null);
//		liste_client.add(client_general2);		
	}
	

	// Liste références activités
	private ArrayList<Ref_client> liste_ref_client = new ArrayList<>();
	
	// Liste client activité
	private ArrayList<Client> liste_client = new ArrayList<>();
	

	//////////////////////////////////////////////////////////////
	
	
	public ArrayList<Ref_client> getListe_ref_client() {
		return liste_ref_client;
	}

	public void setListe_ref_client(Ref_client ref_client) {
		this.liste_ref_client.add(ref_client);
	}


	public ArrayList<Client> getListe_client() {
		return liste_client;
	}

	public void setListe_client(Client client) {
		this.liste_client.add(client);
	}



}
