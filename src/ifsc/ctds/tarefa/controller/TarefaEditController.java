package ifsc.ctds.tarefa.controller;

import java.net.URL;
import java.util.ResourceBundle;

import ifsc.ctds.tarefa.entity.Tarefa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TarefaEditController implements Initializable {

	@FXML
	private AnchorPane pnlPrincipal;

	@FXML
	private Label lblTarefa;

	@FXML
	private Label lblDescricao;

	@FXML
	private Label lblDia;

	@FXML
	private TextField txtTarefa;

	@FXML
	private TextField txtDescricao;

	@FXML
	private TextField txtDia;

	@FXML
	private HBox pnlBotoes;

	@FXML
	private Button btnConfirmar;

	@FXML
	private Button btnCancelar;

	// Objeto tarefa
	private Tarefa tarefa;
	// Flag da operação onclick
	private boolean okClick = false;
	// Referência a janela
	private Stage janelaTarefaEdit;

	public Stage getJanelaTarefaEdit() {
		return janelaTarefaEdit;
	}

	public void setJanelaTarefaEdit(Stage janelaTarefaEdit) {
		this.janelaTarefaEdit = janelaTarefaEdit;
	}

	// Método para preencher na tela os valores de uma nova tarefa
	public void populaTela(Tarefa tarefa) {
		//recebe o novo valor para a caixa
		this.tarefa = tarefa;
		//atualizar a UI com novos campos
		this.txtTarefa.setText(tarefa.getTarefa());
		this.txtDescricao.setText(tarefa.getDescricao());
		this.txtDia.setText(tarefa.getDia());
	}

	private boolean validaCampos() {
		StringBuilder mensagemErros = new StringBuilder();
		// Validar o campo tarefa - não pode ser branco
		if (this.txtTarefa.getText() == null || this.txtTarefa.getText().trim().isEmpty()) {
			mensagemErros.append("Informe a tarefa!\n");
		}
		// Validar o campo descrição - não pode ser branco
		if (this.txtDescricao.getText() == null || this.txtDescricao.getText().trim().isEmpty()) {
			mensagemErros.append("Informe a descrição!\n");
		}
		// Validar o campo data - não pode ser nulo
		if (this.txtDia.getText() == null || this.txtDia.getText().trim().isEmpty()) {
			mensagemErros.append("Informe o dia!\n");
		}
		// Verifica se há mensagens de erro
		if (mensagemErros.length() == 0) {
			return true;
		} else {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setTitle("Dados Inválidos");
			alerta.setHeaderText("Favor corrigir as seguintes informações");
			alerta.setContentText(mensagemErros.toString());
			alerta.showAndWait();
			return false;
		}
	}

	public boolean isOkClick() {
		return okClick;
	}

	@FXML
	void onClickBtnCancelar(ActionEvent event) {
		this.getJanelaTarefaEdit().close();
	}

	@FXML
	void onClickBtnConfirmar(ActionEvent event) {
		if (validaCampos()) {
			this.tarefa.setTarefa(this.txtTarefa.getText());
			this.tarefa.setDescricao(this.txtDescricao.getText());
			this.tarefa.setDia(this.txtDia.getText());

			this.okClick = true;
			this.getJanelaTarefaEdit().close();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Implementar se necessário
	}
}
