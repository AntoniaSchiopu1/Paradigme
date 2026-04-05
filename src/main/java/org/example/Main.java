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

    public static Double returnarenota(Map<Integer, Double> note, int matricolCautat) {

        return note.get(matricolCautat);
    }
    public static Double gasesteNota(Map<Integer, Double> note, int matricol) {

        return note.get(matricol);
    }
   /* public static float GasesteNota(String prenume, String nume, Map<Integer, Student> studenti) {

        Map<String, Student> hartaDupaNume = new HashMap<>();
        for (Student s : studenti.values()) {
            String cheieNume = s.getPrenume() + "-" + s.getNume();
            hartaDupaNume.put(cheieNume, s);
        }
        String deCautat = prenume + "-" + nume;
        Student gasit = hartaDupaNume.get(deCautat);

        if (gasit != null) {
            return (float) gasit.getNota();
        }

        return 0;
    }
*/
    public static void main() {

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
        listaDinFisier.sort(Comparator.comparing(Student::getNume));
        salveazaInFisier(listaDinFisier, "StudentiSortati.csv");




        //sortare format de studiu+nume
        listaDinFisier.sort(
                Comparator.comparingInt(Student::getFormatieDeStudiu)
                        .thenComparing(Student::getNume)
        );
        salveazaInFisier(listaDinFisier, "Studiu+Nume.csv");
//Laborator4
//ex1
        Map<Integer, Double> note = new HashMap<>();

        try {
            File fisierNote = new File("NrmNote.csv");
            Scanner cititorNote = new Scanner(fisierNote);

            while (cititorNote.hasNextInt()) {
                int matricol = cititorNote.nextInt();
                if (cititorNote.hasNextDouble()) {
                    double valoareNota = cititorNote.nextDouble();
                    note.put(matricol, valoareNota);
                }
            }
            cititorNote.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }


        for (Student s : listaDinFisier) {

            Double notaStudent = note.get(s.getNumarMatricol());

            System.out.println(s.getNumarMatricol() + ", " + s.getPrenume() + ", " + s.getNume() + ", " + s.getFormatieDeStudiu() + ", Nota: " + (notaStudent != null ? notaStudent : "Fără notă"));
        }
//ex2
        System.out.println();
        int idCautat = 10234;
        Double notaGasita = returnarenota(note, idCautat);

        if (notaGasita != null) {
            System.out.println("Studentul " + idCautat + " are nota: " + notaGasita);
        } else {
            System.out.println("Studentul nu a fost găsit în baza de date cu note.");
        }
//ex3
        for (Student s : listaDinFisier) {
            Double notaStudent = gasesteNota(note, s.getNumarMatricol());
            System.out.println(s.getNumarMatricol() + ", " + s.getNume() + ", " + s.getPrenume() + ", " + + s.getFormatieDeStudiu() +  ", Nota: " + (notaStudent != null ? notaStudent : "Lipsă"));
        }
//l4
        /*
        System.out.println();
        Map<Integer, Student> studenti = new HashMap<>();
        for (Student s : listaDinFisier) {
            studenti.put(s.getNumarMatricol(), s);
        }

        try (Scanner cititorNote = new Scanner(new File("NrmNote.csv"))) {
            while (cititorNote.hasNextInt()) {
                int idDinFisier = cititorNote.nextInt();
                if (cititorNote.hasNextDouble()) {
                    double valoareNota = cititorNote.nextDouble();

                    //  Mergem direct la studentul cu acest ID
                    Student s = studenti.get(idDinFisier);

                    if (s != null) {
                        s.setNota(valoareNota);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
        for (Student s : studenti.values()) {
            System.out.println(s);
        }
        float notaM = GasesteNota("Antonia", "Schiopu", studenti);
        float notaN = GasesteNota("Alexandru", "Salaghe", studenti);

        System.out.println("Nota pentru Antonia este: " + notaM);
        System.out.println("Nota pentru Alexandru este: " + notaN);


        System.out.println();
        for (Student s : studenti.values()) {
            System.out.println(s);
        }
        */


        //laborator5
        List<StudentCuNota> listaNouaL5 = new ArrayList<>();

        for (Student s : listaDinFisier) {
            Double n = note.get(s.getNumarMatricol());
            double nota;

            if (n != null) {
                nota = n;
            } else {
                nota = 0.0;
            }

            StudentCuNota studentNou = new StudentCuNota(
                    s.getNumarMatricol(),
                    s.getPrenume(),
                    s.getNume(),
                    s.getFormatieDeStudiu(),
                    nota
            );

            listaNouaL5.add(studentNou);
        }

        salvareListaStudentiCuNota(listaNouaL5, "AfisStudentiL5.csv");






    }
    public static void salvareListaStudentiCuNota(List<StudentCuNota> lista, String numeFisier) {
        try {
            PrintWriter writer = new PrintWriter(new File(numeFisier));
            for (StudentCuNota s : lista) {
                writer.println(s.getNumarMatricol() + ", " + s.getPrenume() + ", " +
                        s.getNume() + ", " + s.getFormatieDeStudiu() + ", " + s.getNota());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
}
