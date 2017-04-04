package ifpb.edu.br.keys_app.models;

/**
 * Created by Mateus on 04/04/2017.
 */

public class Reserva {

    private Integer id;
    private String horaReserva;
    private String horaDevolucao;
    private Usuario usuario;
    private Chave chave;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHoraReserva() {
        return horaReserva;
    }

    public void setHoraReserva(String horaReserva) {
        this.horaReserva = horaReserva;
    }

    public String getHoraDevolucao() {
        return horaDevolucao;
    }

    public void setHoraDevolucao(String horaDevolucao) {
        this.horaDevolucao = horaDevolucao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Chave getChave() {
        return chave;
    }

    public void setChave(Chave chave) {
        this.chave = chave;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", horaReserva='" + horaReserva + '\'' +
                ", horaDevolucao='" + horaDevolucao + '\'' +
                ", usuario=" + usuario +
                ", chave=" + chave +
                '}';
    }
}
