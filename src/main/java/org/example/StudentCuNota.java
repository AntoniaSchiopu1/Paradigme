package org.example;


public class StudentCuNota extends Student {
    private double nota;

    public StudentCuNota(int numarMatricol, String prenume, String nume, int formatieDeStudiu, double nota) {

        super(numarMatricol, prenume, nume, formatieDeStudiu);
        this.nota = nota;
    }


    public double getNota() { return nota; }
    public void setNota(double nota) { this.nota = nota; }

    @Override
    public String toString() {

        return super.toString() + ", nota=" + nota;
    }
}