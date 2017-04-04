package ifpb.edu.br.keys_app.models;

/**
 * Created by Mateus on 01/04/2017.
 */

public class Usuario {

    private Integer id;
    private String nome;
    private String matricula;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }




    @Override
    public String toString() {
        return "Usuario{" +
                ", nome='" + nome + '\'' +
                ", matricula='" + matricula + '\'' +
                '}';
    }
}
