package ifsc.ctds.tarefa.entity;

public class Tarefa {
	private int idTarefa;
	private String tarefa;
	private String descricao;
	private String dia;

	// Getters e Setters

	public int getIdTarefa() {
		return idTarefa;
	}

	public void setIdTarefa(int idTarefa) {
		this.idTarefa = idTarefa;
	}

	public String getTarefa() {
		return tarefa;
	}

	public void setTarefa(String tarefa) {
		this.tarefa = tarefa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}
}