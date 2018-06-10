package com.wedul.wedulpos.message.dto;

import java.awt.TrayIcon.MessageType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * chatting message 
 * 
 * @author jeongcheol
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
	private String chatRoomId;
    private String writer;
    private String message;
    private MessageType type;
}