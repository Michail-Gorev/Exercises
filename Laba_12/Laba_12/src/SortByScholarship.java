import java.util.Comparator;

public class SortByScholarship implements Comparator<Student> {
    @Override
    public int compare(Student student1, Student student2){
        if(student1.getScholarship() > student2.getScholarship())
            return 1;
        else if(student1.getScholarship() < student2.getScholarship())
            return -1;
        else
            return 0;
    }
}