package client;


public class Ref_client {
	private String nom;
    private String club;
    private int ref;

    public Ref_client(int ref,String nom, String club) {
        this.nom = nom;
        this.club = club;
        this.ref=ref;

    }

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getClub() {
		return club;
	}

	public void setClub(String club) {
		this.club = club;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	@Override
	public String toString() {
		return "Ref_client " + ref;
	}


	
	
	
    
    
}