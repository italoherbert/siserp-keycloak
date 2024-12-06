package italo.siserp.core.exception;

public class ErrorBuilder {
    
    public static String build( String message, String... params ) {
        String error = message;
        for( int i = 0; i < params.length; i++ )
            error = error.replace( "${"+(i+1)+"}", params[ i ] );
        return error;
    }

}
