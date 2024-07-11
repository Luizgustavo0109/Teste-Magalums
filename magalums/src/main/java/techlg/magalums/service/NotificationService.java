package techlg.magalums.service;

import org.springframework.stereotype.Service;
import techlg.magalums.dto.ScheduleNotificationDto;
import techlg.magalums.entity.Notification;
import techlg.magalums.entity.Status;
import techlg.magalums.repository.NotificationRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service

public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void scheduleNotification (ScheduleNotificationDto dto) {
        notificationRepository.save(dto.toNotification());
    }

    public Optional <Notification> findById(Long notificationId) {
        return   notificationRepository.findById(notificationId);
    }

    public void cancelNotification(Long notificationId){
        var notification = findById(notificationId);

        if (notification.isPresent()){
            notification.get().setStatus(Status.Values.CANCELED.toStatus());
            notificationRepository.save(notification.get());
        }
    }

    public void checkAndSend(LocalDateTime dateTime) {
        var notifications = notificationRepository.findByStatusInAndDateTimeBefore(List.of(
                Status.Values.PENDING.toStatus(),
                Status.Values.ERROR.toStatus()
        ), dateTime);

        notifications.forEach(sendNotification());
    }

    private Consumer<? super Notification> sendNotification() {
        return n -> {
            //TODO - realizar o envio

            n.setStatus(Status.Values.SUCCESS.toStatus());
            notificationRepository.save(n);
        };
    }

}
