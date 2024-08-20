package application;

import ifsc.ctds.tarefa.controller.TarefaListaController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// carregando arquivo de layout
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/ifsc/ctds/tarefa/view/TarefaLista.fxml"));
			Parent listaXML = loader.load();
			// carregando o controller
			TarefaListaController tarefaListaController = loader.getController();
			// cria cena e a janela principal
			Scene listaLayout = new Scene(listaXML);
			Stage listaJanela = new Stage();
			listaJanela.setTitle("Menu do Sistema");
			listaJanela.initModality(Modality.APPLICATION_MODAL);
			listaJanela.resizableProperty().setValue(Boolean.FALSE);
			listaJanela.setScene(listaLayout);
			listaJanela.show();
			// posicionar a janela no meio da tela do computador
			Rectangle2D posicaoJanela = Screen.getPrimary().getVisualBounds();
			listaJanela.setX((posicaoJanela.getWidth() - listaJanela.getWidth()) / 2);
			listaJanela.setY((posicaoJanela.getHeight() - listaJanela.getHeight()) / 2);
			// configurar o evento de fechar a janela
			listaJanela.setOnCloseRequest(event -> {
				if (tarefaListaController.onCloseQuery())
					System.exit(0);
				else
					event.consume();
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
