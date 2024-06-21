package edu.fanshawe.pawsomecare.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the appointment database table.
 * 
 */
@Entity
@NamedQuery(name="Appointment.findAll", query="SELECT a FROM Appointment a")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Appointment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private double amount;

	@Column(name="appt_time")
	private Timestamp apptTime;

	@Column(name="consult_detail")
	private String consultDetail;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="is_consult")
	private Boolean isConsult;

	@Column(name="is_grooming")
	private Boolean isGrooming;

	@Column(name="is_vaccine")
	private Boolean isVaccine;

	@Column(name="next_visit_suggest")
	private Timestamp nextVisitSuggest;

	private String reason;

	@Column(name="tablet_prescribed")
	private String tabletPrescribed;

	@Column(name="updated_on")
	private Timestamp updatedOn;

	@Column(name="vaccine_dose")
	private double vaccineDose;

	@Column(name="vaccine_other")
	private String vaccineOther;

	//bi-directional many-to-one association to Clinic
	@ManyToOne
	@JoinColumn(name="clinic")
	private Clinic clinicBean;

	//bi-directional many-to-one association to Feedback
	@ManyToOne
	@JoinColumn(name="feedback")
	private Feedback feedbackBean;

	//bi-directional many-to-one association to Pet
	@ManyToOne
	private Pet pet;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="appt_by")
	private User user;

	//bi-directional many-to-one association to Vaccine
	@ManyToOne
	@JoinColumn(name="vaccine")
	private Vaccine vaccineBean;

	//bi-directional many-to-one association to Veterinarian
	@ManyToOne
	@JoinColumn(name="vet_id")
	private Veterinarian veterinarian;

	//bi-directional many-to-many association to Medicine
	@ManyToMany
	@JoinTable(
		name="appointment_medicine"
		, joinColumns={
			@JoinColumn(name="appointment_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="medicine_id")
			}
		)
	@ToString.Exclude
	private List<Medicine> medicines;

	//bi-directional many-to-many association to Grooming
	@ManyToMany(mappedBy="appointments")
	@ToString.Exclude
	private List<Grooming> groomings;

	//bi-directional many-to-one association to InsuranceClaim
	@OneToMany(mappedBy="appointment")
	@ToString.Exclude
	private List<InsuranceClaim> insuranceClaims;

	public InsuranceClaim addInsuranceClaim(InsuranceClaim insuranceClaim) {
		getInsuranceClaims().add(insuranceClaim);
		insuranceClaim.setAppointment(this);

		return insuranceClaim;
	}

	public InsuranceClaim removeInsuranceClaim(InsuranceClaim insuranceClaim) {
		getInsuranceClaims().remove(insuranceClaim);
		insuranceClaim.setAppointment(null);

		return insuranceClaim;
	}

}