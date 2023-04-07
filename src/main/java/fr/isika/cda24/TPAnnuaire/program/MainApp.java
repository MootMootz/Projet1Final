package fr.isika.cda24.TPAnnuaire.program;

import fr.isika.cda24.TPAnnuaire.program.PremiereFenetre;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

	PremiereFenetre root = new PremiereFenetre();
	
//	Scene scene = new Scene(gridPane1); // gripPane1 pas reconnu ?
	
//	stage.setScene(scene);
//	
	stage.setTitle("Bienvenue à l'annuaire - Isika");
	
	stage.sizeToScene();
	
	stage.show();
	
	}
}
