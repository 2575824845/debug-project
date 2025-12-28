package com.xiaoming.lifemanager.mapper;

import com.xiaoming.lifemanager.entity.Note;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface NoteMapper {
    // 新增备忘录
    int addNote(Note note);
    // 查询用户所有备忘录（支持按标签筛选）
    List<Note> selectByUserId(@Param("userId") Integer userId, @Param("tagId") Integer tagId);
    // 按标题搜索备忘录
    List<Note> searchByTitle(@Param("userId") Integer userId, @Param("keyword") String keyword);
    // 置顶/取消置顶
    int updateTopStatus(@Param("id") Integer id, @Param("isTop") Integer isTop);
    // 编辑备忘录
    int updateNote(Note note);
    // 删除备忘录
    int deleteNote(@Param("id") Integer id);
}