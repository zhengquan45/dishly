package com.zhengquan.dishly.demos.web.admin;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhengquan.dishly.entity.ro.UserCardRequest;
import com.zhengquan.dishly.entity.ro.UserCardUpdateRequest;
import com.zhengquan.dishly.entity.CardTemplate;
import com.zhengquan.dishly.entity.TransactionLog;
import com.zhengquan.dishly.entity.UserCard;
import com.zhengquan.dishly.mapper.CardTemplateMapper;
import com.zhengquan.dishly.mapper.TransactionLogMapper;
import com.zhengquan.dishly.mapper.UserCardMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/admin/user_card")
public class UserCardController {

    private final CardTemplateMapper cardTemplateMapper;

    private final UserCardMapper userCardMapper;

    private final TransactionLogMapper transactionLogMapper;

    public UserCardController(CardTemplateMapper cardTemplateMapper, UserCardMapper userCardMapper, TransactionLogMapper transactionLogMapper) {
        this.cardTemplateMapper = cardTemplateMapper;
        this.userCardMapper = userCardMapper;
        this.transactionLogMapper = transactionLogMapper;
    }


    @PostMapping
    public ResponseEntity<?> userCard(@RequestBody UserCardRequest userCardRequest) {
        String cardTemplateId = userCardRequest.getCardTemplateId();
        CardTemplate cardTemplate = cardTemplateMapper.selectById(cardTemplateId);
        UserCard userCard = new UserCard();
        userCard.setUserId(userCardRequest.getUserId());
        userCard.setStatus("ACTIVE");
        userCard.setTemplateId(cardTemplate.getId());
        userCard.setStartDate(LocalDate.now());
        userCard.setEndDate(LocalDate.now().plusDays(cardTemplate.getValidDays()));
        userCard.setRemainingUses(cardTemplate.getTotalUses());
        userCardMapper.insert(userCard);
        //TODO 调用微信支付
        TransactionLog transactionLog = new TransactionLog();
        transactionLog.setOrderId(userCard.getId());
        transactionLog.setAmount(userCard.getPrice().doubleValue());
        transactionLog.setRemark(cardTemplate.getCardName());
        transactionLog.setTransactionType("CARD");
        transactionLog.setStatus("SUCCESS");
        transactionLog.setUserId(userCardRequest.getUserId());
        transactionLogMapper.insert(transactionLog);
        return ResponseEntity.ok().build();
    }


    @PutMapping
    public ResponseEntity<?> userCard(@RequestBody UserCardUpdateRequest userCardUpdateRequest) {
        UserCard userCard = new UserCard();
        userCard.setId(userCardUpdateRequest.getUserCardId());
        userCard.setEndDate(userCardUpdateRequest.getEndDate());
        userCard.setRemainingUses(userCardUpdateRequest.getRemainingUses());
        userCard.setStatus(userCardUpdateRequest.getStatus());
        userCardMapper.updateById(userCard);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    public ResponseEntity<?> userCard(@RequestParam("userId") Long userId) {
        List<UserCard> userCards = userCardMapper.selectList(Wrappers.lambdaQuery(UserCard.class)
                .eq(UserCard::getUserId, userId));
        return ResponseEntity.ok(userCards);
    }
}
