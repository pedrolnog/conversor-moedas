package excecoes;

public class ErroConversaoMoedaException extends RuntimeException {
    private String mensagem;
    public ErroConversaoMoedaException(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String toString() {
        return this.mensagem;
    }
}
