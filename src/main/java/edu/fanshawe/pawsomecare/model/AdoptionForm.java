package edu.fanshawe.pawsomecare.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * The persistent class for the adoption_form database table.
 * 
 */
@Entity
@Table(name="adoption_form")
@NamedQuery(name="AdoptionForm.findAll", query="SELECT a FROM AdoptionForm a")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class AdoptionForm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="created_at")
	private Timestamp createdAt;

	private String description;

	@Column(name="updated_on")
	private Timestamp updatedOn;

	//bi-directional many-to-one association to Pet
	@ManyToOne
	private Pet pet;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="approved_by")
	private User approvedBy;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="owner_id")
	private User ownerId;

}