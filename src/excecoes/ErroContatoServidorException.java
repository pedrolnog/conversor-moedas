package excecoes;

public class ErroContatoServidorException extends RuntimeException{
    private String mensagem;

    public ErroContatoServidorException(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String getMessage() {
        return this.mensagem;
    }
}
