package com.ubs.opsit.interviews.configuration;

import com.ubs.opsit.interviews.constant.ClockConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import com.ubs.opsit.interviews.domain.BerlinClock;

import java.sql.Timestamp;
import java.util.Date;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.context.event.EventListener;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

/**
 * Class to publish event of clock status and time in specified interval.
 * Clock status is scheduled for every 2 seconds.
 * Clock timing is scheduled for every 1 minutes.
 */

@Component
public class BerlinClockEventPublisher implements ApplicationListener<BrokerAvailabilityEvent>{

    private final MessageSendingOperations<String> messagingTemplate;
    private static final Logger LOG = LoggerFactory.getLogger(BerlinClockEventPublisher.class);

    @Autowired
    public BerlinClock berlinClock;

    @Autowired
    public  BerlinClockEventPublisher(final MessageSendingOperations<String> messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void onApplicationEvent(final BrokerAvailabilityEvent event) {

    }

    /**
     * Publish Clock Status
     */
    @Scheduled(fixedDelay = 2000)
    public void sendClockStatus() {
            berlinClock.setClockOnOffStatus(Integer.parseInt(ClockConstants.SECOND_FORMAT.format(new Date())));
            this.messagingTemplate.convertAndSend(
                    ClockConstants.STATUS_QUEUE, berlinClock.getClockOnOffStatus());
    }

    /**
     * Publish clock timings.
     */
    @Scheduled(fixedDelay = 60000)
    public void sendClockTime() {
        publishBerlinClockTime();
    }

    /**
     * Method to set the initial time after Web socket connection established
     * @param sessionConnectedEvent
     * @throws InterruptedException
     */

    @EventListener
    public void sessionConnectedEvent(SessionConnectedEvent sessionConnectedEvent) throws InterruptedException {
        LOG.debug("Web socket connection established...");
        publishBerlinClockTime();
        LOG.debug("Published the initial time for this session...");
    }

    private void publishBerlinClockTime(){
        berlinClock.setTime(ClockConstants.TIME_FORMAT.format(new Date()));
        this.messagingTemplate.convertAndSend(ClockConstants.TIME_QUEUE, berlinClock.getClockArr());
    }

}
