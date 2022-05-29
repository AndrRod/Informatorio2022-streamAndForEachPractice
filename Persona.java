import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data @AllArgsConstructor
public class Persona {
    private int edad;
    private String nombre;
    private Genero genero;

//    public Persona(int edad, String nombre, Genero genero) {
//        this.edad = edad;
//        this.nombre = nombre;
//        this.genero = genero;
//    }
//    public int getEdad(){
//        return edad;
//    }
//    public void setEdad(int edad){
//        this.edad = edad;
//    }
//    public String getNombre(){
//        return nombre;
//    }
//    public void setNombre(String nombre){
//        this.nombre = nombre;
//    }
//    public Genero getGenero(){
//        return genero;
//    }
//    public void setGenero(Genero genero){
//        this.genero = genero;
//    }
//    @Override
//    public String toString() {
//        return "Persona{" +
//                "nombre='" + nombre + '\'' +
//                ", Edad=" + edad +
//                ", genero=" + genero +
//                '}';
//    }
}
