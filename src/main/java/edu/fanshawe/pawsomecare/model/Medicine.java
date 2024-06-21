package edu.fanshawe.pawsomecare.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the medicine database table.
 * 
 */
@Entity
@NamedQuery(name="Medicine.findAll", query="SELECT m FROM Medicine m")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Medicine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="created_on")
	private Timestamp createdOn;

	private String description;

	@Column(name="expires_at")
	private Timestamp expiresAt;

	@Column(name="is_ins_allowed")
	private Boolean isInsAllowed;

	private String name;

	@Column(name="per_cost")
	private double perCost;

	//bi-directional many-to-many association to Appointment
	@ManyToMany(mappedBy="medicines")
	@ToString.Exclude
	private List<Appointment> appointments;

}