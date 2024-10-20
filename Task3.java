import java.util.ArrayList;
import java.util.OptionalDouble;

public class Task3 {
    public static void main(String[] args) {
        var employees = new ArrayList<Employee>();
        employees.add(new Employee("Иван Иванов", 35, "IT", 120000.0));
        employees.add(new Employee("Петр Петров", 28, "HR", 80000.0));
        employees.add(new Employee("Сергей Сергеев", 40, "IT", 110000.0));
        employees.add(new Employee("Анна Аннова", 30, "Finance", 90000.0));
        employees.add(new Employee("Мария Мариева", 45, "IT", 130000.0));

        var targetDepartment = "IT";
        OptionalDouble averageSalary = employees.stream()
                .filter(e -> e.getDepartment().equals(targetDepartment))
                .mapToDouble(Employee::getSalary)
                .average();

        if (averageSalary.isPresent()) {
            System.out.println("Средняя зарплата в департаменте " + targetDepartment + ": " + averageSalary.getAsDouble());
        } else {
            System.out.println("В департаменте " + targetDepartment + " нет сотрудников.");
        }
    }
}

class Employee {
    private String fullName;
    private Integer age;
    private String department;
    private Double salary;

    public Employee(String fullName, Integer age, String department, Double salary) {
        this.fullName = fullName;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "FullName: " + fullName +
                ", Age: " + age +
                ", Department: '" + department + '\'' +
                ", Salary: " + salary;
    }
}
