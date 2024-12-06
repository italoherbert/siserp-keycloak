package italo.siserp.core.validation.validator;

import italo.siserp.core.exception.DomainException;
import italo.siserp.core.validation.Validator;

public class EmailValidator implements Validator {

    private String fieldValue;

    public EmailValidator( String fieldValue ) {
        this.fieldValue = fieldValue;
    }

    @Override
    public void validate() {
        if ( fieldValue == null )
            throw new DomainException( DomainException.INVALID_EMAIL );

        if ( !fieldValue.matches( "\\w+\\.{0,1}\\w+\\@{1}\\w+\\.{1}\\w+" ) )
            throw new DomainException( DomainException.INVALID_EMAIL );                     
    }
    
}
