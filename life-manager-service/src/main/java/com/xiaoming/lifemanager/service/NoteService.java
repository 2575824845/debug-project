package com.xiaoming.lifemanager.service;

import com.xiaoming.lifemanager.entity.Note;
import java.util.List;

public interface NoteService {
    // 新增备忘录
    boolean addNote(Note note);

    // 获取用户备忘录（支持按标签筛选）
    List<Note> getUserNotes(Integer userId, Integer tagId);

    // 搜索备忘录
    List<Note> searchNotes(Integer userId, String keyword);

    // 置顶/取消置顶
    boolean updateTopStatus(Integer id, Integer isTop);

    // 编辑备忘录
    boolean updateNote(Note note);

    // 删除备忘录
    boolean deleteNote(Integer id);
}