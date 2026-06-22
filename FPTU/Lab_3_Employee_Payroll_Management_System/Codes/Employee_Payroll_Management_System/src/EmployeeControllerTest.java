import controllers.EmployeeController;
import enums.EmployeeRole;
import enums.EmployeeStatus;
import models.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EmployeeControllerTest {
    private EmployeeController controller;
    
    @Before
    public void setUp() {
        controller = new EmployeeController();
    }
    
    @Test
    public void testAddEmployees() {
        Employee employee = new Employee(
                "E001",
                "John",
                EmployeeRole.Developer,
                1000,
                22,
                500,
                EmployeeStatus.Active
        );
        
        Employee result = controller.Add(employee);

        Assert.assertNotNull(result);
        Assert.assertEquals("E001", result.getId());
    }
}
