package com.pw.kanban;

import com.pw.kanban.domain.room.RoomRepository;
import com.pw.kanban.domain.room_member.Color;
import com.pw.kanban.domain.task.TaskRepository;
import com.pw.kanban.domain.room.Room;
import com.pw.kanban.domain.task.Task;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class KanbanApplication {

	public static void main(String[] args) {
		SpringApplication.run(KanbanApplication.class, args);
//		Task task = new Task(null, false, "abc", 1, 2, 2.0);
//		createTaskHandler.handle(task);
	}

	@Bean
	public CommandLineRunner demo(RoomRepository roomRepository, TaskRepository taskRepository) {
		return (args) -> {
////			Room room = new Room("abc", 1, 2, 3);
////			roomRepository.save(room);
////
////			Optional<Room> optionalRoom = roomRepository.findById(room.getRoomId());
////			Room room1 = optionalRoom.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
////			Task task = new Task(room1, false, "abc", 1, 2, 1);
////			taskRepository.save(task);
//			UUID uuid = UUID.fromString("42f40b50-d2b3-4a8c-9d36-3e5dc8999e60");
//			Optional<Room> optionalRoom2 = roomRepository.findById(uuid);
//			Room room2 = optionalRoom2.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//			System.out.println("");
//			List<Task> tasks = room2.getTasks();
////			System.out.println(tasks);
		};
	}

}
