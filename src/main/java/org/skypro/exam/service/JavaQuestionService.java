package org.skypro.exam.service;

import org.skypro.exam.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final List<Question> questions = new ArrayList<>();
    private final Random random = new Random();

    @Override
    public Question add(String question, String answer) {
        Question q = new Question(question, answer);
        questions.add(q);
        return q;
    }

    @Override
    public Question remove(String question, String answer) {
        Question q = new Question(question, answer);
        if (!questions.remove(q)) {
            throw new IllegalArgumentException("Вопрос не найден");
        }
        return q;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableList(questions);
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            throw new IllegalStateException("Список вопросов пуст");
        }
        return questions.get(random.nextInt(questions.size()));
    }
}
