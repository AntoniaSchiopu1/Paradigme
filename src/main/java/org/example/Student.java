package org.example;

public class Student {
    private int numarMatricol;
    private String prenume;
    private String nume;
    private int formatieDeStudiu;

    public Student(int numarMatricol, String prenume, String nume, int formatieDeStudiu) {
        this.numarMatricol = numarMatricol;
        this.prenume = prenume;
        this.nume = nume;
        this.formatieDeStudiu = formatieDeStudiu;
    }
    public int getNumarMatricol() {
        return numarMatricol;
    }
    public String getPrenume() {
        return prenume;
    }
    public String getNume() {
        return nume;
    }
    public int getFormatieDeStudiu() {
        return formatieDeStudiu;
    }
    @Override
    public String toString() {
        return "Student{" + "numarMatrical=" + numarMatricol + ", prenume='" + prenume  + ", nume='" + nume + '\'' + ", formatieDeStudiu=" + formatieDeStudiu + '}';}
}
