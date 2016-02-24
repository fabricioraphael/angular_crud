package model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "alunos")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlunoBean {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "age")
	private Integer age;

	@Column(name = "approved")
	private boolean approved;
	
	@Override
	public String toString() {
		return "AlunoBean [id=" + id + ", name=" + name + ", age=" + age + ", approved=" + approved + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
}
