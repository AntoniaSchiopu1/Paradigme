package org.example;

import java.util.Objects;

public class Student {
    protected int numarMatricol;
    protected String prenume;
    protected String nume;
    protected int formatieDeStudiu;
   // private Double nota;

    public Student(int numarMatricol, String prenume, String nume, int formatieDeStudiu) {
        this.numarMatricol = numarMatricol;
        this.prenume = prenume;
        this.nume = nume;
        this.formatieDeStudiu = formatieDeStudiu;
       // this.nota = 0.0;
    }
   // public void setNota(double nota) {this.nota = nota;}

    public int getNumarMatricol() {return numarMatricol;}
    public String getPrenume() {
        return prenume;
    }
    public String getNume() {return nume;}
    public int getFormatieDeStudiu() {
        return formatieDeStudiu;
    }
   // public double getNota() {return nota;}

/*    @Override
    public String toString() {
        return "Student{" + "numarMatrical=" + numarMatricol + ", prenume='" + prenume  + ", nume='" + nume + '\'' + ", formatieDeStudiu=" + formatieDeStudiu +
        ", nota="+nota +";"}
 */
@Override
public String toString() {
    return "Student{" + "numarMatrical=" + numarMatricol + ", prenume='" + prenume + ", nume='" + nume + '\'' + ", formatieDeStudiu=" + formatieDeStudiu + ";}";
}
    @Override
    public boolean equals(Object obiectPrimit) {
        if (this == obiectPrimit)
            return true;
        if (obiectPrimit == null || this.getClass() != obiectPrimit.getClass())
            return false;
        Student celalalt = (Student) obiectPrimit;

        return this.numarMatricol == celalalt.numarMatricol;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(numarMatricol);
    }
}
