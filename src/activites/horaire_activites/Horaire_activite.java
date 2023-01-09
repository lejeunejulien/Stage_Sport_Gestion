package activites.horaire_activites;


public class Horaire_activite {
	
	Ref_activite ref_activite;
	Date2 date;
	
	
	public Horaire_activite(Ref_activite ref_activite_horaire, Date2 date) {
		
		this.ref_activite = ref_activite_horaire;
		this.date = date;
	}

	public Date2 getDate() {
		return date;
	}


	public void setDate(Date2 date) {
		this.date = date;
	}

	public Ref_activite getRef_activite() {
		return ref_activite;
	}

	public void setRef_activite(Ref_activite ref_activite) {
		this.ref_activite = ref_activite;
	}

	@Override
	public String toString() {
		return "Horaire_activite [ref_activite=" + ref_activite + ", date=" + date + "]";
	}


	
	
	
	
	
	
	
	
	
}
