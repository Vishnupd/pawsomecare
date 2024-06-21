package edu.fanshawe.pawsomecare.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the ins_pack_name database table.
 * 
 */
@Entity
@Table(name="ins_pack_name")
@NamedQuery(name="InsPackName.findAll", query="SELECT i FROM InsPackName i")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class InsPackName implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private double amount;

	private double coverage;

	@Column(name="created_at")
	private Timestamp createdAt;

	private String description;

	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="pack_name")
	private String packName;

	private String type;

	@Column(name="updated_on")
	private Timestamp updatedOn;

	@Column(name="validity_in_months")
	private Integer validityInMonths;

	//bi-directional many-to-one association to Insurance
	@OneToMany(mappedBy="insPackName")
	@ToString.Exclude
	private List<Insurance> insurances;

	public Insurance addInsurance(Insurance insurance) {
		getInsurances().add(insurance);
		insurance.setInsPackName(this);

		return insurance;
	}

	public Insurance removeInsurance(Insurance insurance) {
		getInsurances().remove(insurance);
		insurance.setInsPackName(null);

		return insurance;
	}

}