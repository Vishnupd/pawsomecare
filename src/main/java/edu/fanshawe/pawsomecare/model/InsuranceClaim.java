package edu.fanshawe.pawsomecare.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


/**
 * The persistent class for the insurance_claim database table.
 * 
 */
@Entity
@Table(name="insurance_claim")
@NamedQuery(name="InsuranceClaim.findAll", query="SELECT i FROM InsuranceClaim i")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class InsuranceClaim implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="claim_id")
	private Integer claimId;

	@Column(name="claim_amount")
	private double claimAmount;

	private Boolean description;

	@Column(name="is_canceled")
	private Boolean isCanceled;

	@Column(name="is_settled")
	private Boolean isSettled;

	//bi-directional many-to-one association to Appointment
	@ManyToOne
	@JoinColumn(name="appt_id")
	private Appointment appointment;

	//bi-directional many-to-one association to Feedback
	@ManyToOne
	@JoinColumn(name="feedback")
	private Feedback feedback;

	//bi-directional many-to-one association to Insurance
	@ManyToOne
	private Insurance insurance;

}