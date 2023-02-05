import java.util.*;

public class Main {
    public static void main(String[] args) {
        /*
        TreeSet<Student> studentsTree1 = new TreeSet<>(new SortByAge());
        TreeSet<Student> studentsTree2 = new TreeSet<>(new SortByScholarship());
        TreeSet<Student> studentsTree3 = new TreeSet<>(new SortByMark());
        */
        LinkedList<Student> students = new LinkedList<>();
        Student student = new Student("Petya","Ivanov","Petrovich",
                2700,100,20,20,"male",4.5);
        students.add(student);
        Student student1 = new Student("Нестор","Махно"," ",
                2200,10,200,19,"male",4.5);
        students.add(student1);
        Student student2 = new Student("Николя","Шидман","Васильевна",
                3200,12,20,34,"female",3.2);
        students.add(student2);

        //double attendancePercent = student.getAttend()/(student.getAttend() + student.getMiss());
        //System.out.printf("%.1f%n",attendancePercent);
        int flag = -1;
        while(flag != 0) {
            Scanner sc = new Scanner(System.in);
            menu(students);
            //printInfo(students);
            System.out.println("Желаете продолжить? (Любая клавиша) - Да, 0- Нет");
            flag = sc.nextInt();
        }
        System.out.println("Всего доброго!");
    }
    public static void menu(LinkedList<Student> students)
    {
        int action;
        Scanner sc = new Scanner(System.in);
        System.out.println("Добро пожаловать в систему учёта студентов СУС!");
        System.out.println("Выберите действие: ");
        System.out.println("1 - просмотреть текущую информацию о студентах, \n" +
                            "2 - добавить студента, \n" +
                            "3 - удалить студента, \n" +
                            "4 - изменить параметры студента, \n" +
                            "5 - отсортировать студентов, \n" +
                            "0 - выйти из системы"
        );
        action = sc.nextInt();
            switch (action) {
                case 1:
                    printInfo(students);
                    break;
                case 2:
                    addStudent(students);
                    break;
                case 3:
                    delStudent(students);
                    break;
                case 4:
                    changeStudent(students);
                    break;
                case 5:
                    printSortedInfo(students);
                    break;
                case 0:
                    break;
                default:
                    System.err.println("Invalid option!");

            }
    }
    public static void addStudent(LinkedList<Student> students){
        Scanner sc = new Scanner(System.in);
        Student st = new Student();
        int errFlag = -1;
        try{
            System.out.println("Введите имя нового студента:");
            st.setName(sc.nextLine());
            System.out.println("Введите фамилию нового студента:");
            st.setSurname(sc.nextLine());
            System.out.println("Введите отчество нового студента (при наличии):");
            st.setPatronymic(sc.nextLine());
            System.out.println("Введите возраст нового студента (целое число лет):");
            st.setAge(sc.nextInt());
            String name = st.getName();
            if (name.charAt(name.length() - 1) == 'а' || name.charAt(name.length() - 1) == 'я'||
                    name.charAt(name.length() - 1) == 'ь')
                st.setGender("female");
            else
                st.setGender("male");
            System.out.println("Введите количество пропущенных занятий:");
            st.setMiss(sc.nextDouble());
            System.out.println("Введите количество посещённых занятий:");
            st.setAttend(sc.nextDouble());
            System.out.println("Введите стипендию нового студента:");
            st.setScholarship(sc.nextDouble());
            System.out.println("Введите средний балл нового студента:");
            st.setMark(sc.nextDouble());
            if(st.getSurname().equals("Каганович") && st.getName().equals("Лазарь") // || st.getName().equals("Lazar"))
                    && st.getPatronymic().equals("Моисеевич")){
                throw new Exception("It's not a place for politicians!");
            }

            else if(st.getAge() > 60 || st.getAge() < 16){
                throw new Exception("It's not a right time for this person to study!");
            }
            else if(st.getMark() < 1 || st.getMark() > 5){
                throw new Exception("Unreal mark!");
            }
            else
                students.add(st);
         } catch(Exception e){
            System.err.println(e.getMessage());
            System.out.println();
            }
        }
        //printInfo(students);

    public static void printInfo(LinkedList<Student> students){
        for(int i = 0; i < students.size(); i ++){
            double attendancePercent = students.peekFirst().getAttend()/(students.peekFirst().getAttend()
                    + students.peekFirst().getMiss());
            //System.out.printf("%.1f%n",attendancePercent);
            System.out.print(i+1 + ". " + "Name: " + students.peekFirst().getName() + " " +
                            students.peekFirst().getPatronymic() + " " + students.peekFirst().getSurname() +
                            "; age: " + students.peekFirst().getAge() + "; gender: "
                            + students.peekFirst().getGender() + "; scholarship: "  +
                            students.peekFirst().getScholarship() +
                             "; average mark: " + students.peekFirst().getMark() +
                              "; attendance percent: ");
            System.out.printf("%.2f%n",attendancePercent);
            System.out.println();
            students.addLast(students.pollFirst());
        }
    }

    public static void printSortedInfo(LinkedList<Student> students){
        int action;
        List<Student> studentsSorted = new ArrayList<>(students);
        System.out.println("Как отсортировать студентов? \n" +
                "1 - по возрасту, \n" +
                "2 - по размеру стипендии, \n" +
                "3 - по среднему баллу, \n" +
                "4 - по посещаемости, \n" +
                "0 - выйти"
        );
        Scanner sc = new Scanner(System.in);
        action = sc.nextInt();
        List<Student> studentsCopy = new ArrayList<>(students);

        switch (action) {
            case 1:
                studentsCopy.sort(new SortByAge());
                break;
            case 2:
                studentsCopy.sort(new SortByScholarship());
                break;
            case 3:
                studentsCopy.sort(new SortByMark());
                break;
            case 4:
                studentsCopy.sort(new SortByAttendancePercent());
                break;
            default:
                System.err.println("Invalid option!");
        }
        for(int i = 0; i < studentsCopy.size(); i++) {
            double attendancePercent = studentsCopy.get(i).getAttend() / (studentsCopy.get(i).getAttend()
                    + studentsCopy.get(i).getMiss());
            System.out.print(i + 1 + ". " + "Name: " + studentsCopy.get(i).getName() + " " +
                    studentsCopy.get(i).getPatronymic() + " " + studentsCopy.get(i).getSurname() +
                    "; age: " + studentsCopy.get(i).getAge() + "; gender: "
                    + studentsCopy.get(i).getGender() + "; scholarship: " +
                    studentsCopy.get(i).getScholarship() +
                    "; average mark: " + studentsCopy.get(i).getMark() +
                    "; attendance percent: ");
            System.out.printf("%.2f%n", attendancePercent);
            System.out.println();
        }
    }
    public static void delStudent(LinkedList<Student> students){
        Scanner sc = new Scanner(System.in);
        //Student st = new Student();
        System.out.println("Введите фамилию студента, которого хотите удалить:");
        //st.setName(sc.nextLine());
        String delname = sc.nextLine();
        for(int i = 0; i < students.size(); i ++){
            if(students.peekFirst().getSurname().equals(delname)){
                students.pollFirst();
                break;
            }
            students.addLast(students.pollFirst());
        }

        //System.out.println();
    }

    public static void changeStudent(LinkedList<Student> students){
        Scanner sc = new Scanner(System.in);
        //Student st = new Student();
        System.out.println("Введите фамилию студента, для которого Вы хотите внести изменения:");
        //st.setName(sc.nextLine());
        String chngname = sc.nextLine();
        System.out.println("Какой параметр изменить?");
        System.out.println("1 - оценку (среднее значение), \n" +
                "2 - размер стипендии, \n" +
                "0 - выйти из системы"
        );
        int action = sc.nextInt();
        for(int i = 0; i < students.size(); i ++){
            if(students.peekFirst().getSurname().equals(chngname)) {
                switch (action){
                    case (1):
                        System.out.println("Введите новое значение: ");
                        double newMark = sc.nextDouble();;
                        if(newMark <= 5 && newMark >= 1)
                            students.peekFirst().setMark(newMark);
                        else System.err.println("Invalid mark!");
                        break;
                    case (2):
                        System.out.println("Введите новое значение: ");
                        double newScholarship = sc.nextDouble();;
                        if(newScholarship <= 60000 && newScholarship >= 1750)
                            students.peekFirst().setScholarship(newScholarship);
                        else System.err.println("Invalid scholarship!");
                        break;
                    case (0): break;
                }

            }
            students.addLast(students.pollFirst());
        }
    }
}