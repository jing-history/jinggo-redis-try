package wang.jinggo.annation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class WorkOverTimeValidator implements ConstraintValidator<WorkOverTime, Integer> {
    WorkOverTime work;
    int max;
    public void initialize(WorkOverTime work){
        this.work = work;
        max = work.max();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        //检验逻辑
        if(value == null){
            return true;
        }

        return value < max;
    }
}
