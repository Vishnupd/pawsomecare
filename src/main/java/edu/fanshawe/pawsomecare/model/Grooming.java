package edu.fanshawe.pawsomecare.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the grooming database table.
 * 
 */
@Entity
@NamedQuery(name="Grooming.findAll", query="SELECT g FROM Grooming g")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Grooming implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private double cost;

	private Integer description;

	@Column(name="is_ins_allowed")
	private Boolean isInsAllowed;

	private Integer name;

	@Column(name="time_require")
	private Timestamp timeRequire;

	//bi-directional many-to-many association to Appointment
	@ManyToMany
	@JoinTable(
		name="grooming_appointment"
		, joinColumns={
			@JoinColumn(name="grooming_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="appointment_id")
			}
		)
	@ToString.Exclude
	private List<Appointment> appointments;

}