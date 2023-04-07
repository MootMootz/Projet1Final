package fr.isika.cda24.TPAnnuaire.entitees;

import java.io.IOException;
import java.io.RandomAccessFile;

public class ArbreBinaireDeRechercheAnnuaire {

private Noeud racine;
	

	public ArbreBinaireDeRechercheAnnuaire() {
		this.racine = new Noeud();
	}
	public ArbreBinaireDeRechercheAnnuaire(Noeud racine) {
		this.racine = racine;
	}
	
	public void ajouterDansArbre(Stagiaire nomAjout, RandomAccessFile raf) throws IOException{
		//si arbre vide
		
		if(raf.length() == 0 ) {
			
			raf.seek(0);
			
			racine.ecrireStagiaire(nomAjout,raf);
			
		} else {
			
			raf.seek(0);
			
			Noeud noeudCourant = racine.lireStagiaire(raf);
			
			noeudCourant.ajouterNoeud(nomAjout, raf);
		}
	}
	
	public void affichageInfixe(RandomAccessFile raf) throws IOException{    // doit être public arraylist
		if (raf.length() == 0) {

			System.out.println("arbre vide");

		} else {
			raf.seek(0);

			Noeud noeudCourant = racine.lireStagiaire(raf);

			noeudCourant.affichageInfixeNoeud(raf);
		
		}

	}
}