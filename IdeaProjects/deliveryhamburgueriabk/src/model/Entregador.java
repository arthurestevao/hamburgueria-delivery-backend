package model;

public class Entregador {

    private String nome;
    private String telefone;
    private String veiculo;

    public Entregador(String nome, String telefone, String veiculo) {
        this.nome = nome;
        this.telefone = telefone;
        this.veiculo = veiculo;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getVeiculo() {
        return veiculo;
    }
}