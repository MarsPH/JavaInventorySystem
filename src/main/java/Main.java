import DAO.itemDAOimpl;
import ui.MainMenu;
import service.*;

public class Main {

    //private static final String sql = "SELECT name, price FROM items;\n";

    public static void main(String[] args) {
        itemDAOimpl itemDAO = new itemDAOimpl();
        itemService itemService = new itemService(itemDAO);
        MainMenu menu = new MainMenu(itemService);
        menu.show();
    }
}