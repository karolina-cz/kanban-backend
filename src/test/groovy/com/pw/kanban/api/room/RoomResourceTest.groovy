package com.pw.kanban.api.room

import com.fasterxml.jackson.databind.ObjectMapper
import com.pw.kanban.domain.room.RoomDto
import com.pw.kanban.domain.room.RoomRepository
import com.pw.kanban.domain.room.RoomRepresentation
import com.pw.kanban.domain.room.RoomType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@Transactional
@ComponentScan
@EnableAutoConfiguration
@AutoConfigureMockMvc
@SpringBootTest
class RoomResourceTest extends Specification {
    private static final String BASE_URL = '/api/room'

    @Autowired
    MockMvc mvc

    @Autowired
    RoomRepository repository

    def "CreateRoom"() {
        given:
        def dto = new RoomDto(roomType: kanbanRoomType)

        when:
        def response = mvc.perform(createRoom(dto)).andReturn().response

        then:
        response.status == HttpStatus.CREATED.value()

        and:
        def id = new ObjectMapper().readValue(response.contentAsString, UUID)

        and:
        repository.getOne(id).type == kanbanRoomType
        repository.getOne(id).blockersProbability == 25
        repository.getOne(id).currentDay == 1

        where:
        kanbanRoomType << [RoomType.KANBAN_SYSTEM, RoomType.KANBAN_BOARD]

    }

    def "GetRoom"() {
    }

    def "DeleteRoom"() {
    }

    def "PatchRoom"() {
    }

    def createRoom(RoomDto roomDto) {
        post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(roomDto))
    }
}
