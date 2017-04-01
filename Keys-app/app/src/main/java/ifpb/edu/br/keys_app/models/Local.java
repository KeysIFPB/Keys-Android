package ifpb.edu.br.keys_app.models;

import java.sql.Time;

/**
 * Created by Mateus on 31/03/2017.
 */

public class Local {

    private String nome;
    private Time horaReserva;
    private Time horaDevolucao;
    private String nomeResponsavel;
    private boolean situacao;

    private Local(String nome,Time horaReserva,Time horaDevolucao,String nomeResponsavel) {
        this.nome = nome;
        this.horaReserva = horaReserva;
        this.horaDevolucao = horaDevolucao;
        this.nomeResponsavel = nomeResponsavel;
    }


//-------------------------------------gets and sets----------------------------------------//
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Time getHoraReserva() {
        return horaReserva;
    }
    public void setHoraReserva(Time horaReserva) {
        this.horaReserva = horaReserva;
    }
    public Time getHoraDevolucao() {
        return horaDevolucao;
    }
    public void setHoraDevolucao(Time horaDevolucao) {
        this.horaDevolucao = horaDevolucao;
    }
    public String getNomeResponsavel() {
        return nomeResponsavel;
    }
    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }
    public boolean isSituacao() {
        return situacao;
    }
    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }
//--------------------------------------------------------------------------------------------//


    @Override
    public String toString() {
        return "Local{" +
                "nome='" + nome + '\'' +
                ", horaReserva=" + horaReserva +
                ", horaDevolucao=" + horaDevolucao +
                ", nomeResponsavel='" + nomeResponsavel + '\'' +
                ", situacao=" + situacao +
                '}';
    }
}
