package edu.fanshawe.pawsomecare.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="is_active")
	private Boolean isActive;

	private String name;

	//bi-directional many-to-one association to Pet
	@OneToMany(mappedBy="category")
	@ToString.Exclude
	private List<Pet> pets;

	public Pet addPet(Pet pet) {
		getPets().add(pet);
		pet.setCategory(this);

		return pet;
	}

	public Pet removePet(Pet pet) {
		getPets().remove(pet);
		pet.setCategory(null);

		return pet;
	}

}