import java.util.Comparator;

public class SortByAttendancePercent implements Comparator<Student> {
    @Override
    public int compare(Student st1, Student st2) {
        if(st1.getAttend() < st2.getAttend())
            return 1;
        else if(st1.getAttend() > st2.getAttend())
            return -1;
        else
            return 0;
    }
}
