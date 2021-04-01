package com.pw.kanban.domain.room_member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoomMemberRepository extends JpaRepository<RoomMember, UUID> {
}
