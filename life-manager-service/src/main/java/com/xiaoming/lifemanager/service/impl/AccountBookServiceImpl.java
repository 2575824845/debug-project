package com.xiaoming.lifemanager.service.impl;

import com.xiaoming.lifemanager.entity.AccountBook;
import com.xiaoming.lifemanager.entity.AccountBookStatistics;
import com.xiaoming.lifemanager.mapper.AccountBookMapper;
import com.xiaoming.lifemanager.service.AccountBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccountBookServiceImpl implements AccountBookService {
    @Autowired
    private AccountBookMapper accountBookMapper;

    @Override
    public boolean addAccount(AccountBook accountBook) {
        return accountBookMapper.addAccount(accountBook) > 0;
    }

    @Override
    public List<AccountBook> getUserAccounts(Integer userId) {
        return accountBookMapper.selectByUserId(userId);
    }

    @Override
    public List<AccountBook> getAccountsByDateRange(Integer userId, String startDate, String endDate) {
        return accountBookMapper.selectByDateRange(userId, startDate, endDate);
    }

    @Override
    public boolean updateAccount(AccountBook accountBook) {
        return accountBookMapper.updateAccount(accountBook) > 0;
    }

    @Override
    public boolean deleteAccount(Integer id) {
        return accountBookMapper.deleteAccount(id) > 0;
    }

    @Override
    public AccountBookStatistics getMonthlyStatistics(Integer userId, String month) {
        // 获取月度统计数据
        AccountBookStatistics statistics = accountBookMapper.selectMonthlyStatistics(userId, month);
        if (statistics != null) {
            // 获取分类统计详情
            List<AccountBookStatistics.CategoryStatistics> categoryDetails = accountBookMapper.selectCategoryStatistics(userId, month);
            statistics.setCategoryDetails(categoryDetails);
        }
        return statistics;
    }
}