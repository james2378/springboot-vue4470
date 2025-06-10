package com.project.demo.controller;

import com.project.demo.entity.TopicInformation;
import com.project.demo.service.TopicInformationService;
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
 * 选题信息：(TopicInformation)表控制层
 *
 */
@RestController
@RequestMapping("/topic_information")
public class TopicInformationController extends BaseController<TopicInformation, TopicInformationService> {

    /**
     * 选题信息对象
     */
    @Autowired
    public TopicInformationController(TopicInformationService service) {
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
