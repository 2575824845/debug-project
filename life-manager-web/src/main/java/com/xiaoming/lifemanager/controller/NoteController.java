package com.xiaoming.lifemanager.controller;

import com.xiaoming.lifemanager.common.Result;
import com.xiaoming.lifemanager.entity.Note;
import com.xiaoming.lifemanager.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
@CrossOrigin
public class NoteController {
    @Autowired
    private NoteService noteService;

    // 新增备忘录
    @PostMapping("/add")
    public Result<Void> addNote(@RequestBody Note note) {
        boolean success = noteService.addNote(note);
        return success ? Result.success() : Result.error("新增失败");
    }

    // 获取用户备忘录（支持按标签筛选）
    @GetMapping("/list")
    public Result<List<Note>> getUserNotes(
            @RequestParam Integer userId,
            @RequestParam(required = false) Integer tagId
    ) {
        List<Note> list = noteService.getUserNotes(userId, tagId);
        return Result.success(list);
    }

    // 搜索备忘录
    @GetMapping("/search")
    public Result<List<Note>> searchNotes(
            @RequestParam Integer userId,
            @RequestParam String keyword
    ) {
        List<Note> list = noteService.searchNotes(userId, keyword);
        return Result.success(list);
    }

    // 置顶/取消置顶
    @GetMapping("/updateTop")
    public Result<Void> updateTopStatus(
            @RequestParam Integer id,
            @RequestParam Integer isTop
    ) {
        boolean success = noteService.updateTopStatus(id, isTop);
        return success ? Result.success() : Result.error("操作失败");
    }

    // 编辑备忘录
    @PostMapping("/update")
    public Result<Void> updateNote(@RequestBody Note note) {
        boolean success = noteService.updateNote(note);
        return success ? Result.success() : Result.error("编辑失败");
    }

    // 删除备忘录
    @GetMapping("/delete")
    public Result<Void> deleteNote(@RequestParam Integer id) {
        boolean success = noteService.deleteNote(id);
        return success ? Result.success() : Result.error("删除失败");
    }
}