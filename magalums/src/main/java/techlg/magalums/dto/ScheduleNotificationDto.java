package techlg.magalums.dto;

import techlg.magalums.entity.Channel;
import techlg.magalums.entity.Notification;
import techlg.magalums.entity.Status;

import java.time.LocalDateTime;

public record ScheduleNotificationDto(LocalDateTime dateTime, String destination, String message, Channel.Values channel) {

    public Notification toNotification() {
        return new Notification(
                dateTime,
                destination,
                message,
                channel.toChannel(),
                Status.Values.PENDING.toStatus()


        );
    }
}
