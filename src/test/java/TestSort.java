import org.example.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestSort {
    @Test
    public void TestSortNume() {
        List<Student> lista = new ArrayList<>();

        lista.add(new Student(10234, "Antonia", "Schiopu", 312));
        lista.add(new Student(10555, "Alexandru", "Salaghe", 312));
        lista.add(new Student(20678, "Flavius", "Stefan", 315));
        lista.add(new Student(90026, "Anne", "Predoi", 316));

        lista.sort(Comparator.comparing(Student::getNume));

        Assertions.assertEquals("Predoi", lista.get(0).getNume());
        Assertions.assertEquals("Salaghe", lista.get(1).getNume());
        Assertions.assertEquals("Schiopu", lista.get(2).getNume());
        Assertions.assertEquals("Stefan", lista.get(3).getNume());
    }
}
