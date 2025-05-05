package excopen.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Contact {
    private String vk;
    private String telegram;
    @Column(nullable = false)
    private String phone;
}

