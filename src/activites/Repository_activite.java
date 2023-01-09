package activites;

import java.time.LocalDateTime;
import java.util.ArrayList;

import Exception.SaisieErroneeException;
import activites.horaire_activites.Date2;
import activites.horaire_activites.Horaire_activite;
import activites.horaire_activites.Ref_activite;
import activites.tarif_activites.Tarif_activite;


public class Repository_activite {
	

	// Liste references activites
	private ArrayList<Ref_activite> liste_ref_activite = new ArrayList<>();
	
	// Tarif activite
	
	private ArrayList<Tarif_activite> liste_tarif_activite = new ArrayList<>();
	
	// Liste activites generale
	private ArrayList<Activites> liste_general_activites = new ArrayList<>();
	
	//Liste horaire activite
	
    private ArrayList<Horaire_activite> liste_horaire_activite = new ArrayList<>();
        
    
    {
    	{
    		try {
				insert_data();
			} catch (SaisieErroneeException e) {
				System.out.println("Donnees de depart non valides");
			}
    	}
    }
    
    private void insert_data() throws SaisieErroneeException{
//    	// Activites
//		Ref_activite ref_activite_temp = new Ref_activite(1, "repas_dejeuner");
//		Ref_activite ref_activite_temp2 = new Ref_activite(2, "repas_diner");
//		liste_ref_activite.add(ref_activite_temp);
//		liste_ref_activite.add(ref_activite_temp2);
//
//		Tarif_activite tarif_activite_temp = new Tarif_activite(ref_activite_temp, "fft", 10);
//		Tarif_activite tarif_activite_temp2 = new Tarif_activite(ref_activite_temp, "fft", 30);
//		liste_tarif_activite.add(tarif_activite_temp);
//		liste_tarif_activite.add(tarif_activite_temp2);
//
//		Date2 date_test = new Date2(false, LocalDateTime.of(2022, 6, 4, 8, 0), LocalDateTime.of(2022, 6, 4, 12, 0));
//		Date2 date_test2 = new Date2(false, LocalDateTime.of(2022, 6, 5, 8, 0), LocalDateTime.of(2022, 6, 5, 12, 0));
//
//		Horaire_activite horaire_activite_temp = new Horaire_activite(ref_activite_temp, date_test);
//		liste_horaire_activite.add(horaire_activite_temp);
//		Horaire_activite horaire_activite_temp2 = new Horaire_activite(ref_activite_temp2, date_test2);
//		liste_horaire_activite.add(horaire_activite_temp2);
//
//		Activites activite_temp = new Activites(horaire_activite_temp, tarif_activite_temp);
//		liste_general_activites.add(activite_temp);
//		Activites activite_temp2 = new Activites(horaire_activite_temp2, tarif_activite_temp);
//		liste_general_activites.add(activite_temp2);
    }
    
    ///////////////////////////////////////////////////////////////////////

	public ArrayList<Ref_activite> getListe_ref_activite() {
		return liste_ref_activite;
	}

	public void setListe_ref_activite(Ref_activite liste_ref_activite) {
		this.liste_ref_activite.add(liste_ref_activite);

	}
	
	/////////////////

	public ArrayList<Tarif_activite> getListe_tarif_activite() {
		return liste_tarif_activite;
	}

	public void setListe_tarif_activite(Tarif_activite liste_tarif_activite) {
		this.liste_tarif_activite.add(liste_tarif_activite);
	}
	
	////////////////

	public ArrayList<Activites> getListe_general_activites() {
		return liste_general_activites;
	}

	public void setListe_general_activites(Activites liste_general_activites) {
		this.liste_general_activites.add(liste_general_activites);
	}

	
	///////////////

	public ArrayList<Horaire_activite> getListe_horaire_activite() {
		return liste_horaire_activite;
	}

	public void setListe_horaire_activite(Horaire_activite liste_horaire_activite) {
		this.liste_horaire_activite.add(liste_horaire_activite);
	}
    

	
	
}
