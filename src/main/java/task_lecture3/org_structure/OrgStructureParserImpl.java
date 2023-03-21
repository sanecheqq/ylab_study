package task_lecture3.org_structure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Random;

public class OrgStructureParserImpl implements OrgStructureParser {
    private HashMap<String, String> fieldToMethod = new HashMap<>();

    private void initMap() {
        fieldToMethod.put("id", "setId");
        fieldToMethod.put("boss_id", "setBossId");
        fieldToMethod.put("name", "setName");
        fieldToMethod.put("position", "setPosition");
    }

    @Override
    public Employee parseStructure(File csvFile) throws IOException {
        initMap();
        Employee mainBoss = new Employee();
        try (BufferedReader reader = Files.newBufferedReader(csvFile.toPath())) {
            mainBoss = parseFileAndAddEmployees(reader);
        } catch (FileNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            System.out.println(e.getMessage());
        }
        return mainBoss;
    }
    public Employee parseFileAndAddEmployees(BufferedReader reader) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, IOException {
        Employee mainBoss = new Employee();
        String[] fillingFieldsOrder = splitFieldsOrder(reader);
        checkFillingFieldsOrder(fillingFieldsOrder);
        String line;
        boolean addingBoss = true;
        while ((line = reader.readLine()) != null) {
            String[] employeeValues = splitValues(line);
            Employee employee = initEmployee(fillingFieldsOrder, employeeValues);
            if (addingBoss) {
                mainBoss = employee;
                addingBoss = false;
            } else {
                addEmployee(mainBoss, employee);
            }
        }
        return mainBoss;
    }

    private String[] splitFieldsOrder(BufferedReader reader) throws IOException {
        return reader.readLine().split("\\Q;\\E");
    }

    private void checkFillingFieldsOrder(String[] fillingFieldsOrder) {
        if (fillingFieldsOrder == null) {
            throw new NullPointerException("file can not be empty");
        }
    }

    private String[] splitValues(String vaulesLine) throws IOException {
        return vaulesLine.split("\\Q;\\E");
    }

    private Employee initEmployee(String[] fillingFieldsOrder, String[] employeeValues) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Employee employee = new Employee();
        for (int i = 0; i < fillingFieldsOrder.length; i++) {
            String fieldToSet = fillingFieldsOrder[i];
            String methodName = fieldToMethod.get(fieldToSet);
            String addingValue = employeeValues[i];
            Object value = null;
            if (fieldToSet.equals("id") || fieldToSet.equals("boss_id")) {
                if (addingValue.equals("")) {
                    continue;
                }
                value = Long.parseLong(employeeValues[i]);
            } else {
                value = employeeValues[i];
            }

            Method methodSetter = employee.getClass().getDeclaredMethod(methodName, value.getClass());
            methodSetter.invoke(employee, value);
        }
        return employee;
    }

    public boolean addEmployee(Employee boss, Employee employee) {
        if (boss.getId().equals(employee.getBossId())) {
            boss.getSubordinate().add(employee);
            employee.setBoss(boss);
            return true;
        }
        for (Employee nextBoss : boss.getSubordinate()) {
            if (addEmployee(nextBoss, employee)) {
                return true;
            }
        }
        return false;
    }

}
