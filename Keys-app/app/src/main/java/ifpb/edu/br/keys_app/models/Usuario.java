package ifpb.edu.br.keys_app.models;

/**
 * Created by Mateus on 01/04/2017.
 */

public class Usuario {

 //   private Integer id;
    private String nome;
    private String matricula;

    public Usuario(String nome, String matricula) {
   //     this.id = id;
        this.nome = nome;
        this.matricula = matricula;
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
        return "" + nome +  ", matricula='" + matricula ;
    }
}
