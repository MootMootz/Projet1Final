package fr.isika.cda24.TPAnnuaire.entitees;

import java.io.IOException;
import java.io.RandomAccessFile;

public class FichierBinaire extends Noeud {

	public String sourceFichierBin = "src/main/java/fr/isika/cda24/TPAnnuaire/doc/test.bin";

	public FichierBinaire(Stagiaire cle, int filsGauche, int filsDroit) {
		super(cle, filsGauche, filsDroit);
	}

	public void ecrireStagiaire(Stagiaire stagiaire, RandomAccessFile raf) {

		try {
			raf.seek(raf.length());

			raf.writeChars(stagiaire.getNomLong());
			raf.writeChars(stagiaire.getPrenomLong());
			raf.writeChars(stagiaire.getDptLong());
			raf.writeChars(stagiaire.getPromoLong());
			raf.writeChars(stagiaire.getAnnee());
			raf.writeInt(Noeud.FILS_NUL);
			raf.writeInt(Noeud.FILS_NUL);

			raf.close(); // ferme Le fichier.

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Noeud lireStagiaire(RandomAccessFile raf) {

		String nom = "";
		String prenom = "";
		String dpt = "";
		String promo = "";
		String annee = "";
		int filsGauche = -1;
		int filsDroit = -1;

		try {

			// Lit bes variables de l'objet stagiaire

			for (int i = 0; i < Stagiaire.TAILLE_NOM_Caracteres; i++) {
				nom += raf.readChar();
			}
			for (int i = 0; i < Stagiaire.TAILLE_Prenom_Caracteres; i++) {
				prenom += raf.readChar();
			}
			for (int i = 0; i < Stagiaire.TAILLE_Dpt_Caracteres; i++) {
				dpt += raf.readChar();
			}
			for (int i = 0; i < Stagiaire.TAILLE_promo_Caracteres; i++) {
				promo += raf.readChar();
			}
			for (int i = 0; i < Stagiaire.TAILLE_annee_Caracteres; i++) {
				annee += raf.readChar();
			}

			filsGauche = raf.readInt();
			filsDroit = raf.readInt();

			Stagiaire stagiaire = new Stagiaire(nom, prenom, dpt, promo, annee);

			return new Noeud(stagiaire, filsGauche, filsDroit);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void ajouterStagiaire(Stagiaire stagiaireNouveau, RandomAccessFile raf) throws IOException {

		int comparaison = this.getCle().getNom().compareTo(stagiaireNouveau.getNom()); // comparer le statgiaire à ajouter avec les stagiaires déjà présents

		if (comparaison > 0) {
			if (this.getFilsGauche() == FILS_NUL) {
				raf.seek(raf.getFilePointer() - 12); // déplace le curseur de 12 octets et se positionne sur la case du fils gauche
				raf.writeInt((int) (raf.length() / (Stagiaire.TAILLE_Stagiaires_Octets + 8))); // calcul de l'index du nouveau noeud et l'écrit
				ecrireStagiaire(stagiaireNouveau, raf); // écriture du nouveau noeud dans le fichier
			} else {
				raf.seek(this.getFilsGauche() * (Stagiaire.TAILLE_Stagiaires_Octets + 8));// déplace le curseur au niveau du fils gauche
				Noeud noeudFilsGauche = lireStagiaire(raf);// lit le fils gauche
				((FichierBinaire) noeudFilsGauche).ajouterStagiaire(stagiaireNouveau, raf); // appel récursif de la méthode afin de trouver un moyen d'insérer le noeud
			}
		} else if (comparaison < 0) {
			if (this.getFilsDroit() == FILS_NUL) {
				raf.seek(raf.getFilePointer() - 8); // se déplace de 8 octets pour aller au niveau du fils droit
				raf.writeInt((int) (raf.length() / (Stagiaire.TAILLE_Stagiaires_Octets + 8)));
				ecrireStagiaire(stagiaireNouveau, raf);
				Noeud noeudFilsDroit = lireStagiaire(raf);
				((FichierBinaire) noeudFilsDroit).ajouterStagiaire(stagiaireNouveau, raf);
			} else if (comparaison == 0) {
				if (this.getDoublon() == FILS_NUL);
			}
		}
	}

	public void supprimerStagiaire(Stagiaire stagiaireSuppr, RandomAccessFile raf, int index) {
	}

}
