package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.List;


public class Main {
    public static boolean verificaPrezenta(List<Student> lista, int matricolCautat) {

        for (Student s : lista) {

            if (s.getNumarMatricol() == matricolCautat) {
                return true;
            }
        }

        return false;
    }

    public static boolean verificaPrezentaHash(HashSet<Integer> setMatricole, int matricolCautat) {
        return setMatricole.contains(matricolCautat);
    }
    public  static void main() {

/*
        Student student1 = new Student(10234, "Antonia", "Schiopu", 312);
        System.out.println(student1);
        Student student2 = new Student(10555, "Alexandru", "Salaghe", 314);
        System.out.println(student2);
        Student student3 = new Student(20678, "Flavius", "Stefan", 315);
        System.out.println(student3);
        Student student4 = new Student(90026,"Anne","Predoi",316);
*/
        /*List<Student> listaStudenti = new ArrayList<>();
        listaStudenti.add(new Student(10000, "Ana", "Bacila", 317));
        listaStudenti.add(new Student(70899, "David", "Stancu", 318));
        listaStudenti.add(new Student(80911, "Ioana", "Munteanu", 319));
        listaStudenti.add(new Student(30455, "Gabriel", "Halmaghe", 320));




        System.out.println("Lista de studenți:");
        for (Student s : listaStudenti)
        {
            System.out.println(s);
        }

        if (verificaPrezenta(listaStudenti, 22222))
        {
            System.out.println("Studentul este prezent!");

        }
        else System.out.println("Studentul nu este prezent!");

        HashSet<Integer> setMatricole = new HashSet<>();
        for (Student s : listaStudenti) {
            setMatricole.add(s.getNumarMatricol());
        }
        int deCautat = 80911;
        if (verificaPrezentaHash(setMatricole, deCautat)) {
            System.out.println("Studentul este prezent ");
        } else {
            System.out.println("Studentul nu este prezent.");
        }
        System.out.println("Lista completă:");
        for (Student s : listaStudenti) {
            System.out.println(s);
        }
*/
        //fisier
        List<Student> listaDinFisier = new ArrayList<>();
        try {
            File myObj = new File("FStudent.csv");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.trim().isEmpty()) continue;


                String[] parti = data.split(",");
                int matricol = Integer.parseInt(parti[0].trim());
                String prenume = parti[1].trim();
                String nume = parti[2].trim();
                int grupa = Integer.parseInt(parti[3].trim());

                listaDinFisier.add(new Student(matricol, prenume, nume, grupa));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //sortare
        listaDinFisier.sort(Comparator.comparingInt(Student::getNumarMatricol));
        salveazaInFisier(listaDinFisier, "StudentiSortati.csv");
        //sortare format de studiu+nume
        listaDinFisier.sort(
                Comparator.comparingInt(Student::getFormatieDeStudiu)
                        .thenComparing(Student::getNume)
        );
        salveazaInFisier(listaDinFisier, "Studiu+Nume.csv");




    }
    public static void salveazaInFisier(List<Student> lista, String numeFisier) {
        try {
            PrintWriter writer = new PrintWriter(numeFisier);
            for (Student s : lista) {
                writer.println(s.getNumarMatricol() + ", " + s.getPrenume() + ", " +
                        s.getNume() + ", " + s.getFormatieDeStudiu());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Eroare la scriere.");
        }
    }
}
