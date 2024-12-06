package italo.siserp.core.exception;

public class BusinessException extends ErrorException {
    
    public final static String USER_NOT_FOUND = "Usuário não encontrado.";
    public final static String USERNAME_JA_EXISTE = "Já existe um usuário cadastrado com o username informado.";
    public final static String EMAIL_JA_EXISTE = "Já existe um usuário cadastrado com o email informado.";

    public final static String USER_NOT_CREATED = "Não foi possível criar o usuário.";
    public final static String USER_NOT_UPDATED = "Não foi possível atualizar o usuário.";
    public final static String USER_NOT_REMOVED = "Não foi possível remover o usuário.";

    public BusinessException( String message ) {
        super( message );
    }

}
