
import java.time.DateTimeException;
import java.util.InputMismatchException;
import java.util.Scanner;
import Exception.SaisieErroneeException;
import activites.Repository_activite;
import client.Repository_client;

public class MajorMain {
	
	public static Repository_activite access_repository = new Repository_activite();
	public static Repository_client access_repository_client = new Repository_client(access_repository);
	
	
	public static void main(String[] args) {
	
		int menu_principal = 0;
	
		do {
			System.out.println();
			System.out.println("Bienvenue au stage de sport");
			System.out.println("\n1. Activités\n2. Client\n3. Quitter");
			System.out.println();
			System.out.print("Votre choix : ");
			
			
			try {
			menu_principal = (new Scanner(System.in)).nextInt();
			}
			catch(InputMismatchException e){
				System.out.println();
				System.out.println("Entrer un nombre !");
				System.out.println();
			}
			

			switch (menu_principal) {
			case 1:
				try {
					new activites.Main_activites(access_repository,access_repository_client);	
				}
				catch(SaisieErroneeException e) {
					System.out.println();
					System.out.println(e.getMessage());
					System.out.println();
				}
				catch(InputMismatchException e) {
					System.out.println();
					System.out.println("Entrer un nombre !");
					System.out.println();
				}
				catch(DateTimeException e) {
					System.out.println();
					System.out.println("Date non valide !");
					System.out.println();
				}
				break;
				
			case 2:
				try {
					new client.Main_client(access_repository, access_repository_client);	
				}
				catch(SaisieErroneeException e) {
					System.out.println();
					System.out.println(e.getMessage());
					System.out.println();
				}
				catch(InputMismatchException e) {
					System.out.println();
					System.out.println("Entrer un nombre !");
					System.out.println();
				}
				catch(DateTimeException e) {
					System.out.println();
					System.out.println("Date non valide !");
					System.out.println();
				}
				break;
				
			case 3:
				break;
			}
		}

		while ((menu_principal == 1) || (menu_principal == 2) || !(menu_principal == 3));
	}

}
