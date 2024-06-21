package edu.fanshawe.pawsomecare.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the veterinarian database table.
 * 
 */
@Entity
@NamedQuery(name="Veterinarian.findAll", query="SELECT v FROM Veterinarian v")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Veterinarian implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="consult_fee")
	private double consultFee;

	@Column(name="contact_no")
	private String contactNo;

	private String email;

	@Column(name="is_active")
	private Boolean isActive;

	private String name;

	//bi-directional many-to-one association to Appointment
	@OneToMany(mappedBy="veterinarian")
	@ToString.Exclude
	private List<Appointment> appointments;

	//bi-directional many-to-many association to Clinic
	@ManyToMany
	@JoinTable(
		name="veterinarian_clinic"
		, joinColumns={
			@JoinColumn(name="veterinarian_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="clinic_id")
			}
		)
	@ToString.Exclude
	private List<Clinic> clinics;

	public Appointment addAppointment(Appointment appointment) {
		getAppointments().add(appointment);
		appointment.setVeterinarian(this);

		return appointment;
	}

	public Appointment removeAppointment(Appointment appointment) {
		getAppointments().remove(appointment);
		appointment.setVeterinarian(null);

		return appointment;
	}

}