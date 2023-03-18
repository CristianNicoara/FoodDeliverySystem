import presentationLayer.controller.Controller;
import presentationLayer.view.*;

public class MainClass {
    public static void main(String[] args) {
        Controller controller = new Controller(new AdministratorView(), new ClientView(), new EmployeeView(), new LogInView(), new AddItemView(), new ModifyItemView());
    }
}
