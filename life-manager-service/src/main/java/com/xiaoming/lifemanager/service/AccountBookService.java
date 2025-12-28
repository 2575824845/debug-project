package com.xiaoming.lifemanager.service;

import com.xiaoming.lifemanager.entity.AccountBook;
import com.xiaoming.lifemanager.entity.AccountBookStatistics;
import java.util.List;

public interface AccountBookService {
    // 新增收支
    boolean addAccount(AccountBook accountBook);

    // 获取用户所有收支
    List<AccountBook> getUserAccounts(Integer userId);

    // 按日期范围查询（用于前端月度统计）
    List<AccountBook> getAccountsByDateRange(Integer userId, String startDate, String endDate);

    // 编辑收支
    boolean updateAccount(AccountBook accountBook);

    // 获取月度统计数据
    AccountBookStatistics getMonthlyStatistics(Integer userId, String month);

    // 删除收支
    boolean deleteAccount(Integer id);
}