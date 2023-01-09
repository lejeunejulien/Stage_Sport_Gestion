package activites;

import activites.horaire_activites.Horaire_activite;
import activites.tarif_activites.Tarif_activite;

public class Activites {
	
	private Horaire_activite horaire_activite;
	
	private Tarif_activite tarif_activite;
	
	public Activites(Horaire_activite liste_horaire_activite_temp,Tarif_activite tarif_activite_temp) {
		this.horaire_activite=liste_horaire_activite_temp;
		this.tarif_activite =tarif_activite_temp;
		
	}

	public Horaire_activite getHoraire_activite() {
		return horaire_activite;
	}

	public void setHoraire_activite(Horaire_activite horaire_activite) {
		this.horaire_activite = horaire_activite;
	}

	public Tarif_activite getTarif_activite() {
		return tarif_activite;
	}

	public void setTarif_activite(Tarif_activite tarif_activite) {
		this.tarif_activite = tarif_activite;
	}

	@Override
	public String toString() {
		return "Activites [horaire_activite=" + horaire_activite + ", tarif_activite=" + tarif_activite + "]";
	}

	

}
