package italo.siserp.core.validation.validator;

import italo.siserp.core.exception.DomainException;
import italo.siserp.core.exception.ErrorBuilder;
import italo.siserp.core.validation.Validator;

public class RequiredValidator implements Validator {

    private final String fieldName;
    private final String fieldValue;

    public RequiredValidator(String fieldName, String fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    @Override
    public void validate() {
        if ( fieldValue == null )
            throw new DomainException( ErrorBuilder.build( DomainException.FIELD_REQUIRED, fieldName ) );
        if ( fieldValue.isBlank() )
            throw new DomainException( ErrorBuilder.build( DomainException.FIELD_REQUIRED, fieldName ) );
    }
    
}
