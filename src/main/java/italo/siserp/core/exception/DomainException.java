package italo.siserp.core.exception;

public class DomainException extends ErrorException {        

    public final static String FIELD_REQUIRED = "O campo '${1}' é de preenchimento obrigatório.";
    public final static String INVALID_EMAIL = "O email informado está em formato inválido.";

    public DomainException( String message ) {
        super( message );
    }

}
