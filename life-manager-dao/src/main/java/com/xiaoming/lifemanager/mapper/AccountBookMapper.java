package com.xiaoming.lifemanager.mapper;

import com.xiaoming.lifemanager.entity.AccountBook;
import com.xiaoming.lifemanager.entity.AccountBookStatistics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface AccountBookMapper {
    // 新增收支记录
    int addAccount(AccountBook accountBook);
    // 查询用户所有收支
    List<AccountBook> selectByUserId(@Param("userId") Integer userId);
    // 按日期范围查询（用于月度统计）
    List<AccountBook> selectByDateRange(
            @Param("userId") Integer userId,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate
    );
    // 编辑收支记录
    int updateAccount(AccountBook accountBook);
    // 删除收支记录
    int deleteAccount(@Param("id") Integer id);
    
    // 统计月收入和支出
    AccountBookStatistics selectMonthlyStatistics(
            @Param("userId") Integer userId,
            @Param("month") String month
    );
    
    // 按分类统计收支
    List<AccountBookStatistics.CategoryStatistics> selectCategoryStatistics(
            @Param("userId") Integer userId,
            @Param("month") String month
    );
}