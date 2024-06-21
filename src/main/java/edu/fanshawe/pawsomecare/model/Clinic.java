package edu.fanshawe.pawsomecare.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the clinic database table.
 * 
 */
@Entity
@NamedQuery(name="Clinic.findAll", query="SELECT c FROM Clinic c")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Clinic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String address;

	private String description;

	private String name;

	private String specialities;

	//bi-directional many-to-one association to Appointment
	@OneToMany(mappedBy="clinicBean")
	@ToString.Exclude
	private List<Appointment> appointments;

	//bi-directional many-to-many association to Veterinarian
	@ManyToMany(mappedBy="clinics")
	@ToString.Exclude
	private List<Veterinarian> veterinarians;

	public Appointment addAppointment(Appointment appointment) {
		getAppointments().add(appointment);
		appointment.setClinicBean(this);

		return appointment;
	}

	public Appointment removeAppointment(Appointment appointment) {
		getAppointments().remove(appointment);
		appointment.setClinicBean(null);

		return appointment;
	}

}