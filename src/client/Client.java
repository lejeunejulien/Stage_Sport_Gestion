package client;

import activites.Activites;

public class Client {
	private Ref_client ref_client;
	private Activites activites;
	private Boolean presence;
	private Boolean activation_remise=false;
	private int remise;
	private boolean assurance_annulation=false;
	double prix_assurance_annulation=20;
	

	public Client(Ref_client ref_client, Activites activites,Boolean presence) {
		this.ref_client=ref_client;
		this.activites=activites;
		this.presence=presence;
	}

	public Ref_client getRef_client() {
		return ref_client;
	}

	public void setRef_client(Ref_client ref_client) {
		this.ref_client = ref_client;
	}

	public Activites getActivites() {
		return activites;
	}

	public void setActivites(Activites activites) {
		this.activites = activites;
	}

	public Boolean getPresence() {
		return presence;
	}

	public void setPresence(Boolean presence) {
		this.presence = presence;
	}

	public Boolean getActivation_remise() {
		return activation_remise;
	}

	public void setActivation_remise(Boolean activation_remise) {
		this.activation_remise = activation_remise;
	}

	public int getRemise() {
		return remise;
	}

	public void setRemise(int remise) {
		this.remise = remise;
	}


	public double getPrix_assurance_annulation() {
		return prix_assurance_annulation;
	}

	public void setPrix_assurance_annulation(double prix_assurance_annulation) {
		this.prix_assurance_annulation = prix_assurance_annulation;
	}

	public boolean isAssurance_annulation() {
		return assurance_annulation;
	}

	public void setAssurance_annulation(boolean assurance_annulation) {
		this.assurance_annulation = assurance_annulation;
	}

	@Override
	public String toString() {
		return "Client [ref_client=" + ref_client + ", activites=" + activites + "]";
	}
	
	
	

}
