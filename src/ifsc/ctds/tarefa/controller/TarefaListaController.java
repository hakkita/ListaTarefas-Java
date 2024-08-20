package ifsc.ctds.tarefa.controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import ifsc.ctds.tarefa.dao.TarefaDAO;
import ifsc.ctds.tarefa.entity.Tarefa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TarefaListaController implements Initializable {

	@FXML
	private AnchorPane pnlPrincipal;

	@FXML
	private SplitPane pnlDivisao;

	@FXML
	private AnchorPane pnlEsquerda;

	@FXML
	private TableView<Tarefa> tbvTarefas;

	@FXML
	private TableColumn<Tarefa, Integer> tbcId;

	@FXML
	private TableColumn<Tarefa, String> tbcTarefa;

	@FXML
	private AnchorPane pnlDireita;

	@FXML
	private GridPane pnlDetalhes;

	@FXML
	private Label lblTarefa;

	@FXML
	private Label lblDescricao;

	@FXML
	private Label lblDia;

	@FXML
	private Label lblTarefaValor;

	@FXML
	private Label lblDiaValor;

	@FXML
	private Label lblDescricaoValor;

	@FXML
	private ButtonBar barBotoes;

	@FXML
	private Button btnIncluir;

	@FXML
	private Tooltip tipIncluir;

	@FXML
	private Button btnEditar;

	@FXML
	private Tooltip tipEditar;

	@FXML
	private Button btnExcluir;

	@FXML
	private Tooltip tipExcluir;

	private Stage stage;

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void configurarStage() {
		this.setStage(new Stage());
		this.getStage().initModality(Modality.APPLICATION_MODAL);
		this.getStage().resizableProperty().setValue(Boolean.FALSE);
	}

	// Lista de objetos tarefa
	private List<Tarefa> listaTarefas;
	// Lista observável - atualiza a UI
	private ObservableList<Tarefa> observableListaTarefa = FXCollections.observableArrayList();
	// Objeto para acessar e realizar as operações no banco sobre a tarefa
	private TarefaDAO tarefaDAO;

	public static final String TAREFA_EDITAR = " - Editar";
	public static final String TAREFA_INCLUIR = " - Incluir";

	/**********************************************************************/
	/********************* GETTERS E SETTERS ********************************/
	/**********************************************************************/

	public TarefaDAO getTarefaDAO() {
		return tarefaDAO;
	}

	public void setTarefaDAO(TarefaDAO tarefaDAO) {
		this.tarefaDAO = tarefaDAO;
	}

	public List<Tarefa> getListaTarefas() {
		return listaTarefas;
	}

	public void setListaTarefas(List<Tarefa> listaTarefas) {
		this.listaTarefas = listaTarefas;
	}

	public ObservableList<Tarefa> getObservableListaTarefa() {
		return observableListaTarefa;
	}

	public void setObservableListaTarefa(ObservableList<Tarefa> observableListaTarefa) {
		this.observableListaTarefa = observableListaTarefa;
	}

	/**********************************************************************/
	/*********************** INICIALIZAÇÃO **********************************/
	/**********************************************************************/
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.setTarefaDAO(new TarefaDAO());
		this.carregarTableViewTarefas();
		this.selecionarItemTableViewTarefas(null);
		this.tbvTarefas.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> selecionarItemTableViewTarefas(newValue));
		this.tbvTarefas.setItems(getObservableListaTarefa());
	}

	public boolean onCloseQuery() {
		Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
		alerta.setTitle("Confirmar saída");
		alerta.setHeaderText("Deseja sair do sistema?");
		ButtonType botaoNao = ButtonType.NO;
		ButtonType botaoSim = ButtonType.YES;
		alerta.getButtonTypes().setAll(botaoSim, botaoNao);
		// pegar a resposta do usuário
		Optional<ButtonType> resultado = alerta.showAndWait();
		return resultado.get() == botaoSim ? true : false;
	}

	public void carregarTableViewTarefas() {
		this.tbcId.setCellValueFactory(new PropertyValueFactory<>("idTarefa"));
		this.tbcTarefa.setCellValueFactory(new PropertyValueFactory<>("tarefa"));
		this.setListaTarefas(this.getTarefaDAO().getAll());
		this.setObservableListaTarefa(FXCollections.observableArrayList(this.getListaTarefas()));
		this.tbvTarefas.setItems(this.getObservableListaTarefa());
	}

	public void selecionarItemTableViewTarefas(Tarefa tarefa) {
		if (tarefa != null) {
			this.lblTarefaValor.setText(tarefa.getTarefa());
			this.lblDescricaoValor.setText(tarefa.getDescricao());
			this.lblDiaValor.setText(tarefa.getDia());
		} else {
			this.lblTarefaValor.setText("");
			this.lblDescricaoValor.setText("");
			this.lblDiaValor.setText("");
		}
	}

	public boolean onShowTelaTarefaEditar(Tarefa tarefa, String operacao) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/ifsc/ctds/tarefa/view/TarefaEdit.fxml"));
			Parent tarefaEditXML = loader.load();
			Stage janelaTarefaEditar = new Stage();
			janelaTarefaEditar.setTitle("Cadastro de Tarefa" + operacao);
			janelaTarefaEditar.initModality(Modality.APPLICATION_MODAL);
			janelaTarefaEditar.resizableProperty().setValue(Boolean.FALSE);
			Scene tarefaEditLayout = new Scene(tarefaEditXML);
			janelaTarefaEditar.setScene(tarefaEditLayout);
			TarefaEditController tarefaEditController = loader.getController();
			tarefaEditController.setJanelaTarefaEdit(janelaTarefaEditar);
			tarefaEditController.populaTela(tarefa);
			janelaTarefaEditar.showAndWait();
			return tarefaEditController.isOkClick();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**********************************************************************/
	/*********************** CONFIGURAÇÃO DOS BOTÕES ************************/
	/**********************************************************************/
	@FXML
	void onClickBtnIncluir(ActionEvent event) {
		Tarefa tarefa = new Tarefa();
		boolean btnConfirmarClick = this.onShowTelaTarefaEditar(tarefa, TAREFA_INCLUIR);

		if (btnConfirmarClick) {
			this.getTarefaDAO().create(tarefa);
			this.carregarTableViewTarefas();
		}
	}

	@FXML
	void onClickBtnEditar(ActionEvent event) {
		Tarefa tarefa = this.tbvTarefas.getSelectionModel().getSelectedItem();
		if (tarefa != null) {
			boolean btnConfirmarClick = this.onShowTelaTarefaEditar(tarefa, TAREFA_EDITAR);
			if (btnConfirmarClick) {
				this.getTarefaDAO().update(tarefa, null);
				this.carregarTableViewTarefas();
			}
		} else {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setContentText("Por favor, escolha uma tarefa na tabela!");
			alerta.show();
		}
	}

	@FXML
	void onClickBtnExcluir(ActionEvent event) {
		Tarefa tarefa = this.tbvTarefas.getSelectionModel().getSelectedItem();
		if (tarefa != null) {
			Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
			alerta.setTitle("Confirmar exclusão");
			alerta.setHeaderText("Deseja realmente excluir a tarefa " + tarefa.getTarefa() + "?");
			ButtonType botaoNao = ButtonType.NO;
			ButtonType botaoSim = ButtonType.YES;
			alerta.getButtonTypes().setAll(botaoSim, botaoNao);
			Optional<ButtonType> resultado = alerta.showAndWait();
			if (resultado.get() == botaoSim) {
				this.getTarefaDAO().delete(tarefa);
				this.carregarTableViewTarefas();
			}
		} else {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setContentText("Por favor, escolha uma tarefa na tabela!");
			alerta.show();
		}
	}
}
