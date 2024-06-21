package edu.fanshawe.pawsomecare.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the vaccine database table.
 * 
 */
@Entity
@NamedQuery(name="Vaccine.findAll", query="SELECT v FROM Vaccine v")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Vaccine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private double amount;

	@Column(name="created_on")
	private Timestamp createdOn;

	private String description;

	@Column(name="is_ins_allowed")
	private Boolean isInsAllowed;

	private String name;

	//bi-directional many-to-one association to Appointment
	@OneToMany(mappedBy="vaccineBean")
	@ToString.Exclude
	private List<Appointment> appointments;

	public Appointment addAppointment(Appointment appointment) {
		getAppointments().add(appointment);
		appointment.setVaccineBean(this);

		return appointment;
	}

	public Appointment removeAppointment(Appointment appointment) {
		getAppointments().remove(appointment);
		appointment.setVaccineBean(null);

		return appointment;
	}

}