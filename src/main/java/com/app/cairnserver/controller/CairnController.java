package com.app.cairnserver.controller;

import com.app.cairnserver.cairn.board.Board;
import com.app.cairnserver.model.actions.MovePiece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
public class CairnController {

    private final Board board = new Board();

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/movePiece")
    @SendTo("/topic/movePiece")
    @CrossOrigin(origins = "http://localhost:3000")
    public Board movePiece(final MovePiece movePiece) throws Exception {

        //chessBoard.movePiece(movePiece);
        this.template.convertAndSend("/topic/movePiece", board);

        return board;
    }

    @MessageMapping("/board")
    @SendTo("/topic/board")
    @CrossOrigin(origins = "http://localhost:3000")
    public Board getBoard() {
        return board;
    }
}
