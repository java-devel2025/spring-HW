package org.skypro.exam.service;


import org.skypro.exam.model.Question;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}

