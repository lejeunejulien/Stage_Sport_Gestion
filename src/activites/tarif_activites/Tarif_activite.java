package activites.tarif_activites;

import activites.horaire_activites.Ref_activite;


public class Tarif_activite {
	Ref_activite ref_activite;
    private String type_prix;
    private int prix;
    
    
	public Tarif_activite(Ref_activite ref_activite_temp, String type_prix, int prix) {
		this.ref_activite = ref_activite_temp;
		this.type_prix = type_prix;
		this.prix = prix;
	}


	public String getType_prix() {
		return type_prix;
	}


	public void setType_prix(String type_prix) {
		this.type_prix = type_prix;
	}


	public int getPrix() {
		return prix;
	}


	public void setPrix(int prix) {
		this.prix = prix;
	}


	public Ref_activite getRef_activite() {
		return ref_activite;
	}


	public void setRef_activite(Ref_activite ref_activite) {
		this.ref_activite = ref_activite;
	}

	

	
	

	

}
