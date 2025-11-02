import Controller.Controller;
import Model.MemberHandler;
import Model.PersistentStorage.PersistentStorageUsingTxt;
import View.Menu.ConsoleMenu;
import View.Menu.MenuHandler;


public class Main {
    /**
     * The entry point of application.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        MemberHandler memberHandler = new MemberHandler(new PersistentStorageUsingTxt());
        MenuHandler menuHandler = new MenuHandler(new ConsoleMenu());
        Controller c = new Controller(menuHandler, memberHandler);
        c.startApplication();

    }
}
