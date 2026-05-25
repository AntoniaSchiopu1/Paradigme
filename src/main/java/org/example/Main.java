package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public Main() throws FileNotFoundException {
    }

    public static boolean verificaPrezenta(List<Student> lista, int matricolCautat)
    {

        for (Student s : lista) {

            if (s.getNumarMatricol() == matricolCautat) {
                return true;
            }
        }

        return false;
    }

    public static boolean verificaPrezentaHash(HashSet<Integer> setMatricole, int matricolCautat)
    {
        return setMatricole.contains(matricolCautat);
    }

    public static void salveazaInFisier(List<Student> lista, String numeFisier)
    {
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

    public static Double returnarenota(Map<Integer, Double> note, int matricolCautat)
    {

        return note.get(matricolCautat);
    }

    public static Double gasesteNota(Map<Integer, Double> note, int matricol)
    {

        return note.get(matricol);
    }

    public static float gasesteNotaDupaNumeComplet(String prenume, String nume, Map<String, Double> hartaNote)
    {
        String cheie = prenume + "-" + nume;
        Double gasit = hartaNote.get(cheie);
        if (gasit != null) {
            return gasit.floatValue();
        }
        return 0.0f;
    }

    public static void main(String[] args)
    {
        laborator1();
        laborator2();
        laborator3();
        laborator4();
        laborator5();
        laborator7();
        laborator8();
        laborator9();
        laborator10();
        laborator11();
        colocviu();
    }


    public static void laborator1()
    {
        System.out.println("Laborator 1");
        Student student1 = new Student(10234, "Antonia", "Schiopu", 312);
        System.out.println(student1);
        Student student2 = new Student(10555, "Alexandru", "Salaghe", 314);
        System.out.println(student2);
        Student student3 = new Student(20678, "Flavius", "Stefan", 315);
        System.out.println(student3);
        Student student4 = new Student(90026, "Anne", "Predoi", 316);
        System.out.println(student4);
    }


    public static void laborator2()
    {
        System.out.println("Laborator 2");
        List<Student> listaStudenti = new ArrayList<>();
        listaStudenti.add(new Student(10000, "Ana", "Bacila", 317));
        listaStudenti.add(new Student(70899, "David", "Stancu", 318));
        listaStudenti.add(new Student(80911, "Ioana", "Munteanu", 319));
        listaStudenti.add(new Student(30455, "Gabriel", "Halmaghe", 320));

        System.out.println("Lista de studenti:");
        for (Student s : listaStudenti)
        {
            System.out.println(s);
        }

        if (verificaPrezenta(listaStudenti, 22222))
        {
            System.out.println("Studentul este prezent!");
        } else
        {
            System.out.println("Studentul nu este prezent!");
        }

        HashSet<Integer> setMatricole = new HashSet<>();
        for (Student s : listaStudenti) {
            setMatricole.add(s.getNumarMatricol());
        }
        int deCautat = 80911;
        if (verificaPrezentaHash(setMatricole, deCautat))
        {
            System.out.println("Studentul este prezent ");
        } else
        {
            System.out.println("Studentul nu este prezent.");
        }
        System.out.println("Lista completa:");
        for (Student s : listaStudenti)
        {
            System.out.println(s);
        }
    }


    public static void laborator3()
    {
        System.out.println("Laborator 3");
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
        listaDinFisier.sort(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.getNume().compareTo(s2.getNume());
            }
        });
        salveazaInFisier(listaDinFisier, "StudentiSortati.csv");

        //sortare format de studiu+nume
        listaDinFisier.sort(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                int compGrupa = Integer.compare(s1.getFormatieDeStudiu(), s2.getFormatieDeStudiu());
                if (compGrupa != 0) {
                    return compGrupa;
                }
                return s1.getNume().compareTo(s2.getNume());
            }
        });
        salveazaInFisier(listaDinFisier, "Studiu+Nume.csv");
    }


    public static void laborator4() {
        System.out.println("Laborator 4");
        List<Student> listaDinFisier = citesteDinFisier("FStudent.csv");
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
            System.out.println(s.getNumarMatricol() + ", " + s.getPrenume() + ", " + s.getNume() + ", " + s.getFormatieDeStudiu() + ", Nota: " + (notaStudent != null ? notaStudent : "Fara nota"));
        }

        System.out.println();
        int idCautat = 10234;
        Double notaGasita = returnarenota(note, idCautat);

        if (notaGasita != null) {
            System.out.println("Studentul " + idCautat + " are nota: " + notaGasita);
        } else {
            System.out.println("Studentul nu a fost gasit în baza de date cu note.");
        }

        System.out.println();
        Map<String, Double> hartaNoteDupaNume = new HashMap<>();
        for (Student s : listaDinFisier) {
            Double n = note.get(s.getNumarMatricol());
            if (n != null) {
                String cheieNume = s.getPrenume() + "-" + s.getNume();
                hartaNoteDupaNume.put(cheieNume, n);
            }
        }

        float notaM = gasesteNotaDupaNumeComplet("Antonia", "Schiopu", hartaNoteDupaNume);
        float notaN = gasesteNotaDupaNumeComplet("Alexandru", "Salaghe", hartaNoteDupaNume);

        System.out.println("Nota pentru Antonia este: " + notaM);
        System.out.println("Nota pentru Alexandru este: " + notaN);
    }


    public static void laborator5()
    {
        System.out.println("Laborator 5");
        List<Student> listaDinFisier = citesteDinFisier("FStudent.csv");
        Map<Integer, Double> note = incarcaNoteDinFisier("NrmNote.csv");
        ArrayList<StudentCuNota> listaNouaL5 = new ArrayList<>();

        for (Student s : listaDinFisier)
        {
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


    public static void laborator7() {
        System.out.println("Laborator 7");
        List<Student> listaDinFisier = citesteDinFisier("FStudent.csv");
        Map<Integer, Double> note = incarcaNoteDinFisier("NrmNote.csv");
        ArrayList<StudentCuNota> listaNouaL5 = new ArrayList<>();

        for (Student s : listaDinFisier) {
            Double n = note.get(s.getNumarMatricol());
            double nota = (n != null) ? n : 0.0;
            listaNouaL5.add(new StudentCuNota(s.getNumarMatricol(), s.getPrenume(), s.getNume(), s.getFormatieDeStudiu(), nota));
        }

        List<List<StudentCuNota>> formatii = StudentCuNota.imparteInFormatii(listaNouaL5);
    }


    public static void laborator8() {
        System.out.println("Laborator 8");
        lab8();
        lab8_2();
        lab8_3();

        List<Student> listaDinFisier = citesteDinFisier("FStudent.csv");
        exportaStudentiExcel(listaDinFisier);

        List<Student> listaDinExcel = importaStudentiExcel();
        System.out.println(" Studenti importati din Excel ");
        for (Student s : listaDinExcel) {
            System.out.println(s);
        }
        System.out.println(" ");
    }


    public static void laborator9() {
        System.out.println("Laborator 9");
        List<StudentCuNota> studentiCuNote = Arrays.asList(
                new StudentCuNota(1025, "Andrei", "Popa", 412, 8.70),
                new StudentCuNota(1024, "Ioan", "Mihalcea", 411, 10),
                new StudentCuNota(1026, "Anamaria", "Prodan", 311, 8.90),
                new StudentCuNota(1029, "Bianca", "Popescu", 311, 10),
                new StudentCuNota(1029, "Maria", "Pana", 312, 4.10),
                new StudentCuNota(1029, "Gabriela", "Mohanu", 312, 7.33),
                new StudentCuNota(1029, "Marius", "Nasta", 312, 3.20),
                new StudentCuNota(1029, "Marius", "Nasta", 312, 5.12),
                new StudentCuNota(1029, "Andrei", "Dobrescu", 312, 2.22)
        );

        System.out.println("Studentii cu nota 10 sunt: ");
        studentiCuNote.stream().filter(s -> s.getNota() == 10).forEach(s -> System.out.println(s));

        System.out.println("Studentii cu nota <5 sunt : ");
        studentiCuNote.stream().filter(s -> s.getNota() < 5).forEach(s -> System.out.println(s));

        System.out.println("Studentii modificati: ");
        List<StudentCuNota> studentiModificati = studentiCuNote.stream()
                .map(s -> {
                    if (s.getNota() < 4.0) {
                        return new StudentCuNota(s.getNumarMatricol(), s.getPrenume(), s.getNume(), s.getFormatieDeStudiu(), 4.0);
                    }
                    return s;
                })
                .collect(Collectors.toList());
        studentiModificati.forEach(s -> System.out.println(s));

        System.out.println("Suma notelor este: ");
        double sumaNote = studentiModificati.stream().mapToDouble(s -> s.getNota()).sum();
        System.out.println(sumaNote);

        System.out.println("Media notelor este : ");
        double media = sumaNote / studentiModificati.size();
        System.out.println(media);
    }


    public static void laborator10() {
        System.out.println("Laborator 10");
        System.out.println("Notificare schimbare nota : ");
        StudentCuNota s = new StudentCuNota(1, "Ion", "Popescu", 101, 7.0);
        s.adaugaObservator((nume, nota) -> System.out.println("Update: " + nume + " are nota " + nota));
        s.setNota(9.5);
        s.setNota(10.0);
        s.setNota(5.56);
    }


    public static void laborator11() {
        System.out.println("Laborator 11");
        List<Student> fStudent = new ArrayList<>();

        Thread tCitire = new Thread(() -> {
            fStudent.addAll(citesteDinFisier("FStudent.csv"));
        });

        Thread tSortare = new Thread(() -> {
            try {
                tCitire.join();
            } catch (InterruptedException ignored) {}


            fStudent.sort(new Comparator<Student>() {
                @Override
                public int compare(Student stud1, Student stud2) {
                    return stud1.getNume().compareTo(stud2.getNume());
                }
            });

            System.out.println(" Rezultatul sortarii:");
            fStudent.forEach(stud -> System.out.println("  -> " + stud.getNume() + " " + stud.getPrenume()));
        });

        tCitire.start();
        tSortare.start();

        try {
            tSortare.join();
        } catch (InterruptedException ignored) {}
    }

    public static void colocviu()
    {   System.out.println("Ex1");
        System.out.println("Ex4");
        List<StudentCuNota> listaColocviu = new ArrayList<>();
        List<StudentCuNota>Listnoua = new ArrayList<>(); List<StudentCuNota>Listnou2 = new ArrayList<>();
        Thread tCitire = new Thread(() -> {
            listaColocviu.addAll(citesteDinFisiercuNota("AfisStudentiL5.csv"));
        });
        Thread tfiltrsub = new Thread(() -> {
            try {
                tCitire.join();
            } catch (InterruptedException ignored) {}
            listaColocviu.stream().filter(s -> s.getNota() < 5).forEach(s ->Listnoua.add(s));
            salveazaInFisiercuNota(Listnoua, "NotaSub5.csv");
        });

        Thread tfiltrpeste = new Thread(() -> {
            try {
                tCitire.join();
            } catch (InterruptedException ignored) {}
            listaColocviu.stream().filter(s -> s.getNota() >= 5).forEach(s ->Listnou2.add(s));
            salveazaInFisiercuNota(Listnou2, "NotaPeste5.csv");
        });


    }
    public static List<StudentCuNota> citesteDinFisiercuNota(String numeFisier) {
        List<StudentCuNota> lista = new ArrayList<>();
        try {
            File myObj = new File(numeFisier);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.trim().isEmpty()) continue;

                String[] parti = data.split(",");
                int numarMatricoll = Integer.parseInt(parti[0].trim());
                String prenume = parti[1].trim();
                String nume = parti[2].trim();
                int formatieDeStudiu  = Integer.parseInt(parti[3].trim());
                double nota= Integer.parseInt(parti[4].trim());

                lista.add(new StudentCuNota(numarMatricoll, prenume, nume, formatieDeStudiu,nota));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fișierul nu a fost găsit.");
        }
        return lista;
    }
    public static void salveazaInFisiercuNota(List<StudentCuNota> lista, String numeFisier)
    {
        try {
            PrintWriter writer = new PrintWriter(numeFisier);
            for (StudentCuNota s : lista) {
                writer.println(s.getNumarMatricol() + ", " + s.getPrenume() + ", " +
                        s.getNume() + ", " + s.getFormatieDeStudiu()+","+s.getNota());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Eroare la scriere.");
        }
    }

    public static List<Student> citesteDinFisier(String numeFisier) {
        List<Student> lista = new ArrayList<>();
        try {
            File myObj = new File(numeFisier);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.trim().isEmpty()) continue;

                String[] parti = data.split(",");
                int matricol = Integer.parseInt(parti[0].trim());
                String prenume = parti[1].trim();
                String nume = parti[2].trim();
                int grupa = Integer.parseInt(parti[3].trim());

                lista.add(new Student(matricol, prenume, nume, grupa));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fișierul nu a fost găsit.");
        }
        return lista;
    }

    public static Map<Integer, Double> incarcaNoteDinFisier(String numeFisier) {
        Map<Integer, Double> noteHarta = new HashMap<>();
        try (Scanner cititorNote = new Scanner(new File(numeFisier))) {
            while (cititorNote.hasNextInt()) {
                int matricol = cititorNote.nextInt();
                if (cititorNote.hasNextDouble()) {
                    double valoareNota = cititorNote.nextDouble();
                    noteHarta.put(matricol, valoareNota);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fișierul de note nu a fost găsit.");
        }
        return noteHarta;
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

    public static void lab8() {
        try (FileInputStream file = new FileInputStream(new File("laborator8_input.xlsx"));
             XSSFWorkbook workbook = new XSSFWorkbook(file)) {

            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    if (cell.getCellType() == CellType.NUMERIC) {
                        System.out.print(cell.getNumericCellValue() + "\t");
                    } else if (cell.getCellType() == CellType.STRING) {
                        System.out.print(cell.getStringCellValue() + "\t");
                    }
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("An error occurred." + e.getMessage());
        }
    }

    public static void lab8_2() {
        try (FileInputStream fis = new FileInputStream(new File("laborator8_input.xlsx"));
             XSSFWorkbook workbookIn = new XSSFWorkbook(fis);
             XSSFWorkbook workbookOut = new XSSFWorkbook()) {

            XSSFSheet sheetIn = workbookIn.getSheetAt(0);
            XSSFSheet sheetOut = workbookOut.createSheet("Media");

            int rowIdx = 0;
            for (Row rowIn : sheetIn) {
                Row rowOut = sheetOut.createRow(rowIdx++);
                int colIdx = 0;
                double suma = 0;
                int countNum = 0;

                for (Cell cellIn : rowIn) {
                    Cell cellOut = rowOut.createCell(colIdx++);
                    if (cellIn.getCellType() == CellType.NUMERIC) {
                        double val = cellIn.getNumericCellValue();
                        cellOut.setCellValue(val);

                        if (colIdx > 3) {
                            suma += val;
                            countNum++;
                        }
                    } else if (cellIn.getCellType() == CellType.STRING) {
                        cellOut.setCellValue(cellIn.getStringCellValue());
                    }
                }

                if (countNum > 0) {
                    rowOut.createCell(colIdx).setCellValue(suma / countNum);
                }
            }

            try (FileOutputStream fos = new FileOutputStream("laborator8_output2.xlsx")) {
                workbookOut.write(fos);
            }
        } catch (IOException e) {
            System.out.println("An error occurred. " + e.getMessage());
        }
    }

    public static void lab8_3() {
        try (FileInputStream fis = new FileInputStream(new File("laborator8_input.xlsx"));
             XSSFWorkbook workbookIn = new XSSFWorkbook(fis);
             XSSFWorkbook workbookOut = new XSSFWorkbook()) {

            XSSFSheet sheetIn = workbookIn.getSheetAt(0);
            XSSFSheet sheetOut = workbookOut.createSheet("Rezultat ");

            int rowIdx = 0;
            for (Row rowIn : sheetIn) {
                Row rowOut = sheetOut.createRow(rowIdx++);
                int colIdx = 0;

                for (Cell cellIn : rowIn) {
                    Cell cellOut = rowOut.createCell(colIdx++);
                    if (cellIn.getCellType() == CellType.NUMERIC) {
                        cellOut.setCellValue(cellIn.getNumericCellValue());
                    } else if (cellIn.getCellType() == CellType.STRING) {
                        cellOut.setCellValue(cellIn.getStringCellValue());
                    }
                }

                String formula = "AVERAGE(D" + rowIdx + ":F" + rowIdx + ")";
                rowOut.createCell(colIdx).setCellFormula(formula);
            }

            try (FileOutputStream fos = new FileOutputStream("laborator8_output3.xlsx")) {
                workbookOut.write(fos);
            }
        } catch (IOException e) {
            System.err.println("An error occurred." + e.getMessage());
        }
    }

    public static void exportaStudentiExcel(List<Student> lista) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Studenti");

        int rowIdx = 0;
        for (Student s : lista) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(s.getNumarMatricol());
            row.createCell(1).setCellValue(s.getPrenume());
            row.createCell(2).setCellValue(s.getNume());
            row.createCell(3).setCellValue(s.getFormatieDeStudiu());
        }

        try (FileOutputStream fos = new FileOutputStream("laborator8_students.xlsx")) {
            workbook.write(fos);
            workbook.close();
            System.out.println("Studenti au fost adaugati in Excel");
        } catch (IOException e) {
            System.out.println("An error occurred." + e.getMessage());
        }
    }

    public static List<Student> importaStudentiExcel() {
        List<Student> studentiImportati = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(new File("laborator8_students.xlsx"));
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                int matricol = (int) row.getCell(0).getNumericCellValue();
                String prenume = row.getCell(1).getStringCellValue();
                String nume = row.getCell(2).getStringCellValue();
                int grupa = (int) row.getCell(3).getNumericCellValue();

                studentiImportati.add(new Student(matricol, prenume, nume, grupa));
            }
        } catch (IOException e) {
            System.out.println("An error occurred." + e.getMessage());
        }
        return studentiImportati;
    }
}