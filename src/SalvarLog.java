import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SalvarLog {
    void salvarArquivo(String historico) {
        try {
            FileWriter escritor = new FileWriter("logs/log-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss")) + ".txt");
            escritor.write(historico);
            escritor.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo de log." + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro desconhecido ao salvar arquivo de log." + e.getMessage());
        }
    }
}