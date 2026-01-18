package org.example.repository;

import jakarta.enterprise.context.ApplicationScoped;
import org.example.model.Feedback;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class FeedbackRepository {

    private List<Feedback> feedbacks = new ArrayList<>();

    public FeedbackRepository() {
        // Відгук на заявку №100 (яка є в RequestRepository)
        feedbacks.add(new Feedback(1L, 100L, "Андрій", 5, "Дуже швидко полагодили, дякую!"));

        // Відгук на заявку №101
        feedbacks.add(new Feedback(2L, 101L, "Олена", 4, "Все працює, але дорого."));
    }

    // Знайти всі відгуки для конкретної заявки
    public List<Feedback> findByRequestId(Long requestId) {
        return feedbacks.stream()
                .filter(f -> f.requestId.equals(requestId))
                .collect(Collectors.toList());
    }

    public void addFeedback(Feedback feedback) {
        long newId = feedbacks.stream().mapToLong(f -> f.id).max().orElse(0) + 1;
        feedback.id = newId;
        feedbacks.add(feedback);
    }

    public List<Feedback> getAll() {
        return feedbacks;
    }
}