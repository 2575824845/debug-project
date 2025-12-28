package com.xiaoming.lifemanager.controller;

import com.xiaoming.lifemanager.common.Result;
import com.xiaoming.lifemanager.entity.AccountBook;
import com.xiaoming.lifemanager.service.AccountBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@CrossOrigin
public class AccountBookController {
    @Autowired
    private AccountBookService accountBookService;

    // 新增收支
    @PostMapping("/add")
    public Result<Void> addAccount(@RequestBody AccountBook accountBook) {
        boolean success = accountBookService.addAccount(accountBook);
        return success ? Result.success() : Result.error("新增失败");
    }

    // 获取用户所有收支
    @GetMapping("/list")
    public Result<List<AccountBook>> getUserAccounts(@RequestParam Integer userId) {
        List<AccountBook> list = accountBookService.getUserAccounts(userId);
        return Result.success(list);
    }

    // 按日期范围查询（前端月度统计用）
    @GetMapping("/listByDate")
    public Result<List<AccountBook>> getAccountsByDate(
            @RequestParam Integer userId,
            @RequestParam String startDate,
            @RequestParam String endDate
    ) {
        List<AccountBook> list = accountBookService.getAccountsByDateRange(userId, startDate, endDate);
        return Result.success(list);
    }

    // 编辑收支
    @PostMapping("/update")
    public Result<Void> updateAccount(@RequestBody AccountBook accountBook) {
        boolean success = accountBookService.updateAccount(accountBook);
        return success ? Result.success() : Result.error("编辑失败");
    }

    // 删除收支
    @GetMapping("/delete")
    public Result<Void> deleteAccount(@RequestParam Integer id) {
        boolean success = accountBookService.deleteAccount(id);
        return success ? Result.success() : Result.error("删除失败");
    }
}