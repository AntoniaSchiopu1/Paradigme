package org.example;

import java.util.Objects;

public class Student
{
    protected int numarMatricol;
    protected String prenume;
    protected String nume;
    protected int formatieDeStudiu;

    public Student(int numarMatricol, String prenume, String nume, int formatieDeStudiu)
    {
        this.numarMatricol = numarMatricol;
        this.prenume = prenume;
        this.nume = nume;
        this.formatieDeStudiu = formatieDeStudiu;
    }

    public int getNumarMatricol() { return numarMatricol; }
    public String getPrenume() { return prenume; }
    public String getNume() { return nume; }
    public int getFormatieDeStudiu() { return formatieDeStudiu; }

    @Override
    public String toString()
    {
        return "Student{ " + "numarMatricol= " + numarMatricol + ", prenume= " + prenume + ", nume= " + nume  + ", formatieDeStudiu= " + formatieDeStudiu + "}";
    }

    @Override
    public boolean equals(Object obiectPrimit)
    {
        if (this == obiectPrimit) return true;
        if (obiectPrimit == null || this.getClass() != obiectPrimit.getClass()) return false;
        Student celalalt = (Student) obiectPrimit;
        return this.numarMatricol == celalalt.numarMatricol;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(numarMatricol);
    }
}