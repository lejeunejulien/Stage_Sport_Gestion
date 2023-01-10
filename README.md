# Stage_Sport_Gestion
UTF8

Main Principal
Main activité et Main client (personnes à inscrire)

Repository activité et Repository client pour stocker listes des activités et des clients

Exceptions (try, catch, throw, throws) dans un package avec une classe Personnalisée

On ne peut pas créer des activités de même nom qui ont lieu en même temps

On ne peut pas affecter des activités (peut importe le nom) qui ont lieu en même temps pour un client

----------------------------------------------------------------------------------------

Dossier Activité
----------------

Class Activité qui contient : Class ref_activité, Class horaire activité, Class tarif activité

Liste Activité qui contient les objets des différentes activités 

------------

-> class Afficher_activité (utilise "Afficher tout" ou "Recherche" via Class Menu et Class Recherche)

-> class Ajouter_activité (Activité, Horaire, Tarif, Présence)

-> class Modifier_activité (Nom, Horaire, tarif, Présence)

-> class Supprimer_activité (Supprimer activités de même nom, Supprimer activité spécifique)

On utilise la fonctionnalité d'afficher_activité ("afficher tout" et "recherche" via class Menu et class recherche) pour sélectionner la réf activité et pour pouvoir Ajouter, Modifier et Supprimer

Class Afficher_activité, Ajouter_activité, Modifier_activité, Supprimer_activité implémentent l'interface parametre activité

Interface parametre activite qui contient :
- Nom
- Horaire
- Tarif
- Présence

Les méthodes de cette interface reçoivent en paramètre la réf activité pour pouvoir effectuer le traitement (Ajouter, Modifier ou Supprimer)

-----------

Afficher activité affiche
-> id activité - nom activité

-> horaire

-> tarif

-> clients

-----------------------------------------------------------------------------

Dossier Client
--------------


Class Client qui contient : Class ref_client, Class Activité, Boolean présence
Liste Client qui contient les objets des différentes clients

------------

-> class Afficher_client (afficher tout, Recherche)

-> class Ajouter_client (Client, Activité, Tarif spécial, Présence)

-> class Modifier_client (Nom/Club, Tarif spécial, Présence)

-> class Supprimer_client (Nom/Club, Activité, Tarif spécial)

On utilise la fonctionnalité d'afficher_activité (afficher tout, recherche) et afficher_client (afficher tout, recherche) 
pour sélectionner la réf client, sélectionner la réf activité et pour pouvoir Ajouter, Modifier et Supprimer

Class Afficher_client, Ajouter_client, Modifier_client, Supprimer_client implémentent l'interface parametre client

Interface parametre activite qui contient :
- Nom
- Activité
- Tarif spécial
- Présence

Ces méthodes de cette interface reçoivent en paramètre la réf activité et la réf client

------------------

Afficher client (Recherche) affiche : 

Pour chaque activité à laquelle le client est inscrit :

-> id - nom client et nom club

-> id activité et nom activité

-> horaire

-> tarif activité

-> tarif client activité

A la fin de l'affichage des activités :

Paiement client (remise de prix, assurance annulation, prix total)

---------------------------------------------------------

Présence (Ajouter, Modifier) pour Client et Activité 
Sélectionne la ref client et la ref activité et effectue le traitement souhaité

--------------------------------------------------------------

Le projet contient une Class Menu qui interagit avec une Class Recherche

Class Menu contient :

-> Menu afficher activité
-> Menu afficher client
-> Menu recherche activité
-> Menu recherche client

Class Recherche contient : 

-> Recherche activité suivant nom
-> Recherche activité suivant date
-> Recherche nom client

Class Menu et Class Rechercge return une Arraylist qui est envoyée à la class Affichage activité ou à la class Affichage client

-----------------------------------------------------

Possible amélioration ->  Main activité et Main client a subdiviser pour rendre le code un peu agréable à lire

