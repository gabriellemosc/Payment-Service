package com.gabriel.payment.repository;

import com.gabriel.payment.domain.WebhookEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying; //  INSERT/UPDATE/DELETE
import org.springframework.data.jpa.repository.Query; // personalize  query
import org.springframework.data.repository.query.Param; // map named parameter nomeados
import java.time.LocalDateTime;

import java.util.UUID;

public interface WebhookEventRepository extends JpaRepository<WebhookEvent, UUID>{          //table and unique ID
    @Modifying
    @Query(value = """
        INSERT INTO webhook_event (id, event_id, transaction_id, processed_at)
        VALUES (:id, :eventId, :transactionId, :processedAt)
        ON CONFLICT (event_id) DO NOTHING
    """, nativeQuery = true)

    int insertIgnoreConflict(
            @Param("id") UUID id,
            @Param("eventId") String eventId,
            @Param("transactionId") String transactionId,
            @Param("processedAt") LocalDateTime processedAt
    );
}
