package fr.isika.cda24.TPAnnuaire.entitees;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class ArbreBinaireDeRechercheAnnuaire {

	private Noeud racine;

	public ArbreBinaireDeRechercheAnnuaire() {
		this.racine = new Noeud();
	}

	public ArbreBinaireDeRechercheAnnuaire(Noeud racine) {
		this.racine = racine;
	}

	public void ajouterDansArbre(Stagiaire nomAjout, RandomAccessFile raf) throws IOException {
		// si arbre vide
		if (raf.length() == 0) {
			raf.seek(0);
			racine.ecrireStagiaire(nomAjout, raf);
		} else {
			raf.seek(0);
			Noeud noeudCourant = racine.lireStagiaire(raf);
			noeudCourant.ajouterNoeud(nomAjout, raf);
		}
	}

	public void affichageInfixe(RandomAccessFile raf) throws IOException { // doit être public arraylist
		if (raf.length() == 0) {
			System.out.println("arbre vide");
		} else {
			raf.seek(0);
			Noeud noeudCourant = racine.lireStagiaire(raf);
			noeudCourant.affichageInfixeNoeud(raf);
		}
	}
	
//	public void rechercheMulticriteres 
//	(String nomStagiaireRecherche,
//		String prenomStagiaireRecherche,
//		String departementStagiaireRecherche,
//		String promoStagiaireRecherche,
//		RandomAccessFile raf) throws IOException{
//	
//	Stagiaire stagiaireRecherche = new Stagiaire();
//	Scanner sc = new Scanner(System.in);
//	
//	String searchFieldNom = null ;
//	String searchFieldPrenom = null ;
//	String searchFieldDepartement = null ;
//	String searchFieldPromo = null ;
//	String searchFieldAnnee = null ;
//			
//	// si rien de rentré >> affiche toute la liste
//	// si le scanner = nom OU prenom OU promo OU dptmt OU annee >> premier filtre
//	
//	//		valueOf(List<Stagiaire> stagiaire);
//
//	
//	// Lire les caractères rentrés dans le bloc recherche
//	sc.search next().isBlank();
//	
//	if (searchFieldNom!=null) {
//	
//
//	
//	if (sc.next().chars().getClass().getField(Stagiaire) = Stagiaire.get)
//		System.out.println();
//	
//	//TABLEAU
////	for (Stagiaire stag : laListeDeStagiaire) {
////		System.out.println(stag);
//	
//	if (sc.next().chars() = Sagiaire.getPrenom)
//		System.out.println(Annuaire);
//	if (sc.next().chars().raf = Sagiaire.getDepartement)
//		System.out.println(Annuaire);
//	if (sc.next().chars().raf = Sagiaire.getPromo)
//		System.out.println(Annuaire);
//	if (sc.next().chars().raf = Sagiaire.getA)
//		System.out.println(Annuaire);
//	}
//}
}
