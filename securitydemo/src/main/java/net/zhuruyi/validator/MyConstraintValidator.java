package net.zhuruyi.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 13:05 2018/3/10
 * @Modified By:
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {



   /* @Autowired 可是使用注入 service*/

    @Override
    public void initialize(MyConstraint myConstraint) {
        System.out.println("my validator init");
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println(o);
        return false;
    }
}
