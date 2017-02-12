package mx.razorblade.test1firebase;

/**
 * Created by multimedia13 on 03/02/17.
 */

public class Usuario {
    public String Nombre;
    public String Apellido;
    public String ID;

    Usuario(){
        Nombre="";
        Apellido="";
        ID="";
    }

    public Usuario(String Nombre,String Apellido,String ID) {
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.ID = ID;
    }

}
