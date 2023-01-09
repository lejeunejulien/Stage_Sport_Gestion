package activites.horaire_activites;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Scanner;

import Exception.SaisieErroneeException;

public class Date2 {
	LocalDateTime date_recherche;
	LocalDateTime date_debut;
	LocalDateTime date_fin;
	Double duree_activite;

	public Date2(Boolean menu, LocalDateTime date_debut, LocalDateTime date_fin) throws SaisieErroneeException,DateTimeException {
		
		if(menu) {
			
			// date_debut
			int db_year;
			int db_month;
			int db_day;
			int db_hours;
			int db_min;
			
			System.out.print("Date de début");
			System.out.println();
			System.out.print("Entrez une année: ");
			System.out.println();
			db_year = (new Scanner(System.in)).nextInt();

			System.out.print("Entrez un mois : ");
			System.out.println();
			db_month = (new Scanner(System.in)).nextInt();

			System.out.print("Entrez un jour : ");
			System.out.println();
			db_day = (new Scanner(System.in)).nextInt();

			System.out.print("Entrez une heure : ");
			System.out.println();
			db_hours = (new Scanner(System.in)).nextInt();

			System.out.print("Entrez des minutes : ");
			System.out.println();
			db_min = (new Scanner(System.in)).nextInt();
			/////////////////////////////

			// date_fin
			int df_year;
			int df_month;
			int df_day;
			int df_hours;
			int df_min;

			System.out.print("Date de fin");
			System.out.println();
			System.out.print("Entrez une année: ");
			System.out.println();
			df_year = (new Scanner(System.in)).nextInt();

			System.out.print("Entrez un mois : ");
			System.out.println();
			df_month = (new Scanner(System.in)).nextInt();

			System.out.print("Entrez un jour : ");
			System.out.println();
			df_day = (new Scanner(System.in)).nextInt();

			System.out.print("Entrez une heure : ");
			System.out.println();
			df_hours = (new Scanner(System.in)).nextInt();

			System.out.print("Entrez des minutes : ");
			System.out.println();
			df_min = (new Scanner(System.in)).nextInt();
			
			this.date_debut = LocalDateTime.of(db_year, db_month, db_day, db_hours,db_min);
			
			this.date_fin = LocalDateTime.of(df_year, df_month, df_day, df_hours,df_min);
		}
		
		else {
			this.date_debut=date_debut;
			this.date_fin=date_fin;
		}
		
	
		
		Duration duration = Duration.between (this.date_debut, this.date_fin);
		this.duree_activite=(double)duration.toMinutes()/60;
		
	}
	
	public Date2() throws SaisieErroneeException{
		
		System.out.println();
		System.out.println("Entrez une année : ");
		int year =(new Scanner(System.in)).nextInt();
		
		
		System.out.println();
		System.out.println("Entrez un mois : ");
		int month =(new Scanner(System.in)).nextInt();
		

		System.out.println();
		System.out.println("Entrez un jour : ");
		int day =(new Scanner(System.in)).nextInt();
		
		System.out.println();
		System.out.println("Entrez une heure : ");
		int hours =(new Scanner(System.in)).nextInt();
		
		System.out.println();
		System.out.println("Entrez un temps en minutes : ");
		int min =(new Scanner(System.in)).nextInt();
	
	    this.date_recherche = LocalDateTime.of(year,month,day,hours,min);
	}

	public LocalDateTime getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(LocalDateTime date_debut) {
		this.date_debut = date_debut;
	}

	public LocalDateTime getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(LocalDateTime date_fin) {
		this.date_fin = date_fin;
	}

	public Double getDuree_activite() {
		return duree_activite;
	}

	public LocalDateTime getDate_recherche() {
		return date_recherche;
	}

	@Override
	public String toString() {
		return "Date2 [date_recherche=" + date_recherche + ", date_debut=" + date_debut + ", date_fin=" + date_fin
				+ ", duree_activite=" + duree_activite + "]";
	}
	
	
	
	
	
	
	
	
}


