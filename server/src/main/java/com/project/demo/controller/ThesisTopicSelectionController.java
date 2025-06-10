package com.project.demo.controller;

import com.project.demo.entity.ThesisTopicSelection;
import com.project.demo.service.ThesisTopicSelectionService;
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
 * 论文选题：(ThesisTopicSelection)表控制层
 *
 */
@RestController
@RequestMapping("/thesis_topic_selection")
public class ThesisTopicSelectionController extends BaseController<ThesisTopicSelection, ThesisTopicSelectionService> {

    /**
     * 论文选题对象
     */
    @Autowired
    public ThesisTopicSelectionController(ThesisTopicSelectionService service) {
        setService(service);
    }


    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        Map<String, String> mapthesis_title = new HashMap<>();
        mapthesis_title.put("thesis_title",String.valueOf(paramMap.get("thesis_title")));
        List listthesis_title = service.select(mapthesis_title, new HashMap<>()).getResultList();
        if (listthesis_title.size()>0){
            return error(30000, "字段论文题目内容不能重复");
        }
        this.addMap(paramMap);
        return success(1);
    }

}
