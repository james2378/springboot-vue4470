package com.project.demo.controller;

import com.project.demo.entity.DefenseInformation;
import com.project.demo.service.DefenseInformationService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 答辩信息：(DefenseInformation)表控制层
 *
 */
@RestController
@RequestMapping("/defense_information")
public class DefenseInformationController extends BaseController<DefenseInformation, DefenseInformationService> {

    /**
     * 答辩信息对象
     */
    @Autowired
    public DefenseInformationController(DefenseInformationService service) {
        setService(service);
    }


    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
