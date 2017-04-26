package mvc.view;

import java.util.Optional;

import javafx.scene.control.TextInputDialog;

public class InputDialogs {

	public static double saisieLambda() {
		double lambda = 0;
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setHeaderText(null);
		dialog.setTitle("Saisie de lambda");
		dialog.setContentText("Veuillez entrer lambda : ");
		Optional<String> res = dialog.showAndWait();

		try {
			lambda = Double.parseDouble(res.get());
			return lambda;
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}

	public static int saisieOrdre() {
		int ordre = 0;
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setHeaderText(null);
		dialog.setTitle("Saisie de l'ordre pour la moyenne mobile");
		dialog.setContentText("Veuillez entrer l'ordre : ");
		Optional<String> res = dialog.showAndWait();

		try {
			ordre = Integer.parseInt(res.get());
			return ordre;
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}
}
