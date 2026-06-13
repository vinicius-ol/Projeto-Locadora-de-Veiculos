public class Main {
    public static void main(String[] args) {
        Locadora locadora = new Locadora("Safe Rent", "Rua das Flores", 100);
        Menu menu = new Menu(locadora);
        menu.iniciar();
    }
}
