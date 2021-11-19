package com.pw.kanban.application.room

import com.pw.kanban.api.room.RoomResource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class CreateRoomHandlerTest extends Specification {

    @Autowired (required = false)
    private RoomResource

    def "should create room with tasks and column limits"() {
        expect: "the WebController is created"
    }
}
