package wang.jinggo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wang.jinggo.config.DruidConfiguration;
import wang.jinggo.pojo.WorkInfoForm;

import java.util.List;

@Controller
public class WorkInfoController {

    private final static Logger LOG =  LoggerFactory.getLogger(WorkInfoController.class);

    @ResponseBody
    @RequestMapping("/addworkinfo.html")
    public void addWorkInfo(@Validated({WorkInfoForm.Add.class}) WorkInfoForm workInfo,
                            BindingResult result){
        if(result.hasErrors()){
            List<ObjectError> list = result.getAllErrors();
            FieldError error = (FieldError) list.get(0);
            LOG.info(error.getObjectName() + ", " + error.getField() + "," + error.getDefaultMessage());
        }
        return;
    }
}
