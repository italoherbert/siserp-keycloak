package italo.siserp.core.validation;

import java.util.List;

public class ValidatorComposite implements Validator {

    private final List<Validator> validators;

    public ValidatorComposite( List<Validator> validators ) {
        this.validators = validators;
    }


    @Override
    public void validate() {
        validators.forEach( v -> v.validate() );
    }
    
}
