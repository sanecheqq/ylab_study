package test_lecture3.org_structure;

import org.junit.Test;
import task_lecture3.org_structure.Employee;
import task_lecture3.org_structure.OrgStructureParser;
import task_lecture3.org_structure.OrgStructureParserImpl;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class OrgStructureParserTest {
    @Test
    public void testParser() {
        OrgStructureParser parser = new OrgStructureParserImpl();
        Employee boss;
        try {
            boss = parser.parseStructure(new File("src/test/java/test_lecture3/org_structure/data.csv"));
        } catch (IOException e) {
            throw new Error(e);
        }
        String name1 = "Саня";
        String position1 = "Помощник программиста";

        String name2 = "Михалыч";
        String position2 = "Секретарь";

        Employee employeeSanya = findEmployee(boss, name1, position1);
        Employee employeeMikhalich = findEmployee(boss, name2, position2);

        assertThat(employeeSanya.getName()).isEqualTo(name1);
        assertThat(employeeSanya.getPosition()).isEqualTo(position1);

        assertThat(employeeMikhalich.getName()).isEqualTo(name2);
        assertThat(employeeMikhalich.getPosition()).isEqualTo(position2);

        printEmployeeInfo(employeeSanya);
        printEmployeeInfo(employeeMikhalich);
    }

    public void printEmployeeInfo(Employee employee) {
        System.out.format("%d;%d;%s;%s\n", employee.getId(), employee.getBossId(),
                employee.getName(), employee.getPosition());
    }

    public Employee findEmployee(Employee boss, String name, String position) {
        if (boss.getName().equals(name) && boss.getPosition().equals(position)) {
            return boss;
        }
        for (Employee employee : boss.getSubordinate()) {
            Employee goalEmployee = findEmployee(employee, name, position);
            if (goalEmployee != null) {
                return goalEmployee;
            }
        }
        return null;
    }
}
