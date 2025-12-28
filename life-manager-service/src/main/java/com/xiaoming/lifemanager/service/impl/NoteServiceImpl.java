package com.xiaoming.lifemanager.service.impl;

import com.xiaoming.lifemanager.entity.Note;
import com.xiaoming.lifemanager.mapper.NoteMapper;
import com.xiaoming.lifemanager.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteMapper noteMapper;

    @Override
    public boolean addNote(Note note) {
        return noteMapper.addNote(note) > 0;
    }

    @Override
    public List<Note> getUserNotes(Integer userId, Integer tagId) {
        return noteMapper.selectByUserId(userId, tagId);
    }

    @Override
    public List<Note> searchNotes(Integer userId, String keyword) {
        return noteMapper.searchByTitle(userId, keyword);
    }

    @Override
    public boolean updateTopStatus(Integer id, Integer isTop) {
        return noteMapper.updateTopStatus(id, isTop) > 0;
    }

    @Override
    public boolean updateNote(Note note) {
        return noteMapper.updateNote(note) > 0;
    }

    @Override
    public boolean deleteNote(Integer id) {
        return noteMapper.deleteNote(id) > 0;
    }
}