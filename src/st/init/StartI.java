package st.init;

/**
 * Interfejs startowy dla programu. Klasa startowa programu musi implementować
 * ten interfejs oraz być wpisana do pliku init.cfg. Wtedy po zainstalowaniu
 * aktualizacji zostaje ona uruchomiona.
 * @author ShookTea
 */
public interface StartI {
    /**
     * Uruchamia program.
     * @param args argumenty uruchamiania programu
     */
    public void start(String[] args);
}
