import com.sun.source.tree.LiteralTree;

import javax.annotation.processing.SupportedSourceVersion;
import java.net.Proxy;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PrincipalClass {
    public static void main(String[] args){
        List<Persona> personaList = getPersonas();

        System.out.println("-------------------------------------------------de una lista trear todas las personas de genero femenino");
        List<Persona> listMujeres = personaList.stream()
                .filter(p-> p.getGenero().equals(Genero.FEMENINO))
                .collect(Collectors.toList());
        listMujeres.forEach(m-> System.out.println(m));

        System.out.println("-------------------------------------------------de una lista traer los generos por los cuales estan dividias las categorias");
        Set<Genero> generoList = personaList.stream().map(g-> g.getGenero()).collect(Collectors.toSet());
        generoList.forEach(System.out::println);


        System.out.println("-------------------------------------------------de una lista traer el nombre del hombre con mayor edad");
        String nombreHombreMayorEdad = personaList.stream().filter(g-> g.getGenero().equals(Genero.MASCULINO))
                .max(Comparator.comparing(Persona::getEdad))
                .map(p-> p.getNombre()).get();
        System.out.println(nombreHombreMayor(personaList));


        System.out.println("-------------------------------------------------de una lista de mujeres traer los nombres de las mujeres que empicen con la letra M y se ordenen por edad");
        List<String> mujeresMas60ConM = personaList.stream()
                .filter(g-> g.getGenero().equals(Genero.FEMENINO))
                .filter(g-> g.getNombre().startsWith("M"))
                .sorted(Comparator.comparing(Persona::getEdad))
                .map(n-> n.getNombre()).collect(Collectors.toList());
        mujeresMas60ConM.forEach(System.out::println);



        System.out.println("-------------------------------------------------de una lista filtrar los valores nullos y los vacios");
        List<String> palabras = new ArrayList<>(){{add("hola"); add(null); add(""); add("informatorio");}};
//        palabras.removeAll(Collections.singleton(null));
        List<String> palabrasConFiltro = palabras.stream().filter(Objects::nonNull).filter(p-> !p.isEmpty()).collect(Collectors.toList());
        palabrasConFiltro.forEach(System.out::println);
        System.out.println("-------------------------------------------------concatenar palabras de una lista ");
        System.out.println(palabras.stream().filter(Objects::nonNull).map(p-> p+" ").collect(Collectors.joining()));
        System.out.println("-------------------------------------------------contar cuantas letras tiene cada una de las palabras de la lista");
        palabras.stream().filter(Objects::nonNull).map(p-> p.length()).collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("-------------------------------------------------Ejercicio de entrevista - Hallar el primer carácter no repetido en un string");
        String palabraLetrasRep = "ndnadz";
        System.out.println(palabraLetrasRep);
        Map<String, Integer> ocurrencias = new HashMap();

        palabraLetrasRep.chars().forEach((l)-> {
            ocurrencias.put(Character.toString(l), (int) palabraLetrasRep.chars().mapToObj(
                p -> p).filter((q)-> q == l).count());});

            for (int i= 0; i < palabraLetrasRep.length(); i++) {
            if(ocurrencias.get(String.valueOf(palabraLetrasRep.charAt(i))).equals(1)){
                System.out.println(palabraLetrasRep.charAt(i) + " = " + ocurrencias.get(String.valueOf(palabraLetrasRep.charAt(i))));
                break;
            }
            else {System.out.println("_");}
        }
        System.out.println("-----------------------------------------------");

        String cadena = "4hola3holz1G44";

        System.out.println("--------------------------------------------eliminar vocales y numeros de un String y devolver cadena string");
        System.out.println(
        cadena.chars().mapToObj(p-> p).filter(p-> !List.of("a", "e", "i", "o", "u").contains(Character.toString(p))).filter(p-> Character.toString(p).matches( "[a-zA-Z]"))
                .map(p-> Character.toString(p) + " ")
                .collect(Collectors.joining()));

        System.out.println("--------------------------------------------eliminar letras de un String y devolver solo numeros");
        System.out.println(cadena.chars().mapToObj(p-> p).filter(p-> Character.toString(p).matches("[0-9]"))
                .map(p-> Character.toString(p) + " ").collect(Collectors.joining()));

        System.out.println("-------------------------------------------- eliminar letras y numero 4 de un String y devolver cadena ");
        System.out.println(cadena.chars().mapToObj(p-> p).filter(p-> Character.toString(p).matches("[0-35-9]"))
                .map(p-> Character.toString(p) + " ").collect(Collectors.joining()));


        System.out.println("-------------------------------------------- EJERCICIO CON NUMEROS - ordenar inverso");
        List<Integer> integerList= List.of(4, 6, 3, 5, 2, 1);
//        integerList.forEach(System.out::println);
        integerList.stream().sorted(Comparator.comparing(Integer::intValue).reversed()).forEach(System.out::println);


        List<Integer> listNum = List.of(0, 1, 2, 3, 4, 5);
        Integer potencia = 2;
        System.out.println("-------------------------------------------------calcular potencia");
        listNum.stream().map(x -> calcularPotencia(x, potencia)).collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("-------------------------------------------------");
        listNum.stream().map(x-> sumarNumeroDescendienteHastaCero(x)).collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("-------------------------------------------------");
//        listNum.stream().map(x-> sumarMismoNumeroHastaCero(x)).collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("-------------------------------------------- ordenar de mayor a menor lista de numeros");
        List<Integer> listNumb = List.of(3,5,1,22, 6, 88, 43);

        System.out.println(listNumb.stream().sorted(Comparator.comparing(Integer::intValue).reversed()).collect(Collectors.toList()));
        System.out.println("-------------------------------------------- informar si la lista esta ordenada dec, asc o desordenada");
        List<Integer> listaOrdenada = List.of(1, 3, 2);
        if(listaOrdenada.equals(listaOrdenada.stream().sorted(Comparator.comparing(Integer::intValue)).collect(Collectors.toList()))){ System.out.println("esta ordenada ascendiente");}
        else if(listaOrdenada.equals(listaOrdenada.stream().sorted(Comparator.comparing(Integer::intValue).reversed()).collect(Collectors.toList()))){ System.out.println("ordenada descendiente");}
        else {System.out.println("lista desordenada");}

        System.out.println("-------------------------------------------- si la cadena es palindromo o no (palabra que se lee igual de atras para adelante)");
        String pali = "anana";
        System.out.println(pali);
        String cadenaInvert = Optional.of(pali).map(w-> new StringBuilder(w).reverse()).get().toString();
        System.out.println(cadenaInvert.equals(pali));
        if ((cadenaInvert.equals(pali))) {
            System.out.println("es PALINDROMO");
        } else {
            System.out.println("NO ES PALINDROMO");
        }
        System.out.println("-------------------------------------------- metodo que indique que caracteres conteiene la cadena y cuantas veces se encuentra y en que posiciones");
        String caracteresADetermCantYPos = "andndresres";
//        HashMap entrega de manera desordenada no como LinkedHasMap<>();
        Map<String, String> caractRepetyPosición = new LinkedHashMap<>();
        caracteresADetermCantYPos.chars().forEach(p-> caractRepetyPosición.put(
                "\n Letra: "+ Character.toString(p) + " ",
                " Repeticiones: "+ caracteresADetermCantYPos.chars().mapToObj(c-> c).filter(c-> c.equals(p)).count() +
                " posición: " + caracteresADetermCantYPos.indexOf(p)));
        for (Map.Entry<String, String> m: caractRepetyPosición.entrySet()) {System.out.print(m);}


        System.out.println("\n-------------------------------------------- informar si un numero es perfecto - si la suma de sus divisores da el numero (6-> 3, 2, 1)");
        List<Integer> listaNumeros = List.of(2, 3, 4, 5, 6);
        listaNumeros.stream().forEach((n)-> {
            if (n.equals(calculadarPerfecto(n))) {
                System.out.println(n + ": Es perfecto");
            } else {
                System.out.println(n + ": N0 es perfecto");
            }
        });

        System.out.println("\n-------------------------------------------- sumar todos los numeros que no sean 4 y 6");
        System.out.println(listaNumeros + "\n la suma sin 4 y 6 es: "+ listaNumeros.stream().filter(n-> n != 4 && n!= 6).mapToInt(n-> n.intValue()).sum());
        System.out.println("\n-------------------------------------------- sacar la media de todos los numeros ");
        System.out.println(listaNumeros+ "\n la media de la lista de numero es: " + listaNumeros.stream().mapToInt(n-> n.intValue()).average().getAsDouble());

        System.out.println("----------------------------------------------- ");
    }
    public static int calculadarPerfecto(int num){
        int numPerf= 0;
        for(int i = 1; i< num; i++){
            if(num%i== 0) numPerf+=i;
        }
        return numPerf;
    }
    public static Integer calcularPotencia(Integer numero, Integer potencia) {
        return (potencia == 0) ? 1 : numero * calcularPotencia(numero, potencia - 1);
    }
    public static Integer sumarNumeroDescendienteHastaCero(int numero){
        return (numero == 0) ? 0 : numero + sumarNumeroDescendienteHastaCero(numero - 1);
    }
    public static String nombreHombreMayor(List<Persona> personaList){
        return personaList.stream()
                .filter(g-> g.getGenero().equals(Genero.MASCULINO))
                .max(Comparator.comparing(Persona::getEdad)).map(n-> n.getNombre()).get();
    }
    private static List<Persona> getPersonas() {
        return List.of(
                new Persona(19, "ana", Genero.FEMENINO),
                new Persona(22, "andres", Genero.MASCULINO),
                new Persona(26, "Roberto", Genero.MASCULINO),
                new Persona(67, "María", Genero.FEMENINO),
                new Persona(55, "Natalia", Genero.FEMENINO),
                new Persona(69, "Moría", Genero.FEMENINO)

        );
    }
}
