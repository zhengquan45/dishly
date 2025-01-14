package com.zhengquan.dishly.demos.web.admin;

import cn.hutool.core.bean.BeanUtil;
import com.zhengquan.dishly.entity.ro.CardTemplateRequest;
import com.zhengquan.dishly.entity.CardTemplate;
import com.zhengquan.dishly.mapper.CardTemplateMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/card_template")
public class CardTemplateController {

    private final CardTemplateMapper cardTemplateMapper;

    public CardTemplateController(CardTemplateMapper cardTemplateMapper) {
        this.cardTemplateMapper = cardTemplateMapper;
    }

    @PostMapping
    public ResponseEntity<?> cardTemplate(@RequestBody CardTemplateRequest cardTemplateRequest) {
        CardTemplate cardTemplate = BeanUtil.toBean(cardTemplateRequest, CardTemplate.class);
        cardTemplateMapper.insert(cardTemplate);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    public ResponseEntity<?> cardTemplate() {
        List<CardTemplate> cardTemplates = cardTemplateMapper.selectList(null);
        return ResponseEntity.ok(cardTemplates);
    }

}
