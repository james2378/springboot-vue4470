package com.project.demo.controller;

import com.project.demo.entity.PaperInformation;
import com.project.demo.service.PaperInformationService;
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
 * 论文信息：(PaperInformation)表控制层
 *
 */
@RestController
@RequestMapping("/paper_information")
public class PaperInformationController extends BaseController<PaperInformation, PaperInformationService> {

    /**
     * 论文信息对象
     */
    @Autowired
    public PaperInformationController(PaperInformationService service) {
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
