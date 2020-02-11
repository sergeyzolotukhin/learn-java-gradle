package ua.in.sz.swing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
	public static final int HELLO = 0;
	public static final int GOODBYE = 1;

	private String message;
	private int status;
}
