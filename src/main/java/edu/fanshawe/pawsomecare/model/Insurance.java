package edu.fanshawe.pawsomecare.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the insurance database table.
 * 
 */
@Entity
@NamedQuery(name="Insurance.findAll", query="SELECT i FROM Insurance i")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Insurance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="expired_at")
	private Timestamp expiredAt;

	private Integer feedback;

	@Column(name="updated_on")
	private Timestamp updatedOn;

	//bi-directional many-to-one association to InsPackName
	@ManyToOne
	@JoinColumn(name="pack_id")
	private InsPackName insPackName;

	//bi-directional many-to-one association to Pet
	@ManyToOne
	private Pet pet;

	//bi-directional many-to-one association to InsuranceClaim
	@OneToMany(mappedBy="insurance")
	@ToString.Exclude
	private List<InsuranceClaim> insuranceClaims;

	//bi-directional many-to-many association to Feedback
	@ManyToMany
	@JoinTable(
		name="insurance_feedback"
		, joinColumns={
			@JoinColumn(name="insurance_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="feedback_id")
			}
		)
	@ToString.Exclude
	private List<Feedback> feedbacks;

	public InsuranceClaim addInsuranceClaim(InsuranceClaim insuranceClaim) {
		getInsuranceClaims().add(insuranceClaim);
		insuranceClaim.setInsurance(this);

		return insuranceClaim;
	}

	public InsuranceClaim removeInsuranceClaim(InsuranceClaim insuranceClaim) {
		getInsuranceClaims().remove(insuranceClaim);
		insuranceClaim.setInsurance(null);

		return insuranceClaim;
	}

}