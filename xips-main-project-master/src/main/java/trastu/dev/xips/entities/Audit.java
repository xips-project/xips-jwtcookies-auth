package trastu.dev.xips.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

import java.time.LocalDateTime;

@Embeddable
@Data
public class Audit {

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    @PrePersist
    public void prePersist(){
        this.createdAt=LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedAt=LocalDateTime.now();
    }

    @PostRemove
    public void postRemove(){
        this.deletedAt=LocalDateTime.now();
    }
}
