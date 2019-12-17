package ua.in.sz.spring.export;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class ScheduleEntity {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
}
