import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import model.*;

public class GerarBin {
    public static void main(String[] args) {
        Usuario bibliotecario1 = new Bibliotecario("Cleide", "12345678901", "001", "01/01/1970", "admin", "senha123");
        Usuario bibliotecario2 = new Bibliotecario("Fulano", "98765432109", "002", "02/02/1980", "fulano", "senha456");

        ArrayList<Usuario> usuarios = new ArrayList<>();
        usuarios.add(bibliotecario1);
        usuarios.add(bibliotecario2);

        BancoDAO bd = BancoDAO.getInstance();

        try {
            bd.salvarArquivo(usuarios);
            System.out.println("Arquivo binário gerado com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao gerar arquivo binário: " + e.getMessage());
        }
    }
}
