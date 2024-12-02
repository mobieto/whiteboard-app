package com.whiteboard.whiteboardapp2.Controller;

import com.whiteboard.whiteboardapp2.Model.WhiteboardAction;
import com.whiteboard.whiteboardapp2.Repo.CacheRepository;
import com.whiteboard.whiteboardapp2.Repo.WhiteboardActionRepository;
import com.whiteboard.whiteboardapp2.WhiteboardApp2Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

import static com.whiteboard.whiteboardapp2.Constants.WB_ACTION_PREFIX;
import static com.whiteboard.whiteboardapp2.Constants.WB_STATE_PREFIX;

@Controller
public class DrawController {
    @Autowired
    private CacheRepository cacheRepository;

    @Autowired
    private WhiteboardActionRepository whiteboardActionRepository;

    @MessageMapping("/draw-stroke")
    @SendTo("/topic/board-state")
    public String drawStroke(@Payload WhiteboardAction action) {
        //cacheRepository.put(WB_ACTION_PREFIX + "test1", "This is a stroke !");

        return "Drew stroke!";
    }

    @MessageMapping("/draw-shape")
    @SendTo("/topic/board-state")
    public String drawShape(@Payload WhiteboardAction action) {
        return "Drew shape!";
    }

    @MessageMapping("/draw-text")
    @SendTo("/topic/board-state")
    public String drawText(@Payload WhiteboardAction action) {
        //cacheRepository.put(WB_ACTION_PREFIX + "test1", "This is a text !");

        return "Drew text!";
    }

    @MessageMapping("/get-board-state")
    @SendTo("/topic/board-state")
    public List<WhiteboardAction> getBoardState() {
        // TODO: Send state from SQL database along with cached state from Redis

        return List.of(new WhiteboardAction());
    }

    @MessageMapping("/get-num-users")
    @SendTo("/topic/connected-users")
    public Long getNumUsers() {
        return Long.parseLong(cacheRepository.get(WB_STATE_PREFIX + "num-users").orElse("0"));
    }
}
