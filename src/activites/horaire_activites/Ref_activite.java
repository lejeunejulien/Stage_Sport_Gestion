package activites.horaire_activites;


public class Ref_activite {	
	private String activite;
	private int ref;
	
	
	public Ref_activite(int ref,String activite) {
		this.activite=activite;
		this.ref=ref;
	}

	
	///////////////////////////////////////////////////////////
	
	@Override
	public String toString() {
		return "Ref_activite_horaire [activite=" + activite + ", ref=" + ref + "]";
	}
	
	
	///////////////////////////////////////////////////////////


	public String getActivite() {
		return activite;
	}


	public void setActivite(String activite) {
		this.activite = activite;
	}


	public int getRef() {
		return ref;
	}


	public void setRef(int ref) {
		this.ref = ref;
	}



	

	//////////////////////////////////////////////
	
	
}
