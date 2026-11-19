package com.TestingApp.repository;

import com.TestingApp.entities.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.context.SpringBootTest;

//@Import(TestContainerConfiguration.class)
@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ALL)
//@SpringBootTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = Employee.builder()
                .name("vikas")
                .email("vikas@gmail.com")
                .salary(100L)
                .build();
    }

    @Test
    void testFindByEmail_whenEmailIsPresent_thenReturnEmployee() {
//        Arrange, Given
        employeeRepository.save(employee);

//        Act, When
        List<Employee> employeeList = employeeRepository.findByEmail(employee.getEmail());

//        Assert, Then
        assertThat(employeeList).isNotNull();
        assertThat(employeeList).isNotEmpty();
        assertThat(employeeList.get(0).getEmail()).isEqualTo(employee.getEmail());
    }

    @Test
    void testFindByEmail_whenEmailIsNotFound_thenReturnEmptyEmployeeList() {
//        Given
        String email = "notPresent.123@gmail.com";
//        When
        List<Employee> employeeList = employeeRepository.findByEmail(email);
//        Then
        assertThat(employeeList).isNotNull();
        assertThat(employeeList).isEmpty();
    }
}