package org.example;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public final class StudentCuNota extends Student {
    private double nota;
    private final List<StudentObserver> observatori = new ArrayList<>();

    public StudentCuNota(int numarMatricol, String prenume, String nume, int formatieDeStudiu, double nota) {

        super(numarMatricol, prenume, nume, formatieDeStudiu);
        this.nota = nota;
    }

    public void adaugaObservator(StudentObserver obs) {
        this.observatori.add(obs);
    }
    private void notifica() {
        for (StudentObserver obs : observatori) {
            obs.actualizeaza(this.prenume + " " + this.nume, this.nota);
        }
    }

    public double getNota() { return nota; }

    public void setNota(double nota)
    { this.nota = nota;
    notifica();
    }
    public void setId(double nota)
    { this.nota = nota;
        notifica();
    }

    @Override
    public String toString() {

        return super.toString() + ", nota=" + nota;
    }

    public StudentCuNota muta(int formatienoua)
    {
        return new StudentCuNota(numarMatricol,prenume,nume,formatienoua,nota);
    }

    public static List<List<StudentCuNota>> imparteInFormatii(ArrayList<StudentCuNota> studenti) {
        int total = studenti.size();
        int dimFormatie1 = (total + 1) / 2;

        List<StudentCuNota> formatie1 = new ArrayList<>(studenti.subList(0, dimFormatie1));
        List<StudentCuNota> formatie2 = new ArrayList<>(studenti.subList(dimFormatie1, total));

        return Arrays.asList(formatie1, formatie2);
    }

}