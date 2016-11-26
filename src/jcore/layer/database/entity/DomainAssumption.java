package layer.database.entity;

// Generated 22-nov-2016 14.40.56 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * DomainAssumption generated by hbm2java
 */
@Entity
@Table(name = "domain_assumption", catalog = "musa_db")
public class DomainAssumption implements java.io.Serializable {

	private Integer idAssumption;
	private Domain domain;
	private String name;
	private String type;
	private String description;

	public DomainAssumption() {
	}

	public DomainAssumption(Domain domain, String name, String type,
			String description) {
		this.domain = domain;
		this.name = name;
		this.type = type;
		this.description = description;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idAssumption", unique = true, nullable = false)
	public Integer getIdAssumption() {
		return this.idAssumption;
	}

	public void setIdAssumption(Integer idAssumption) {
		this.idAssumption = idAssumption;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idDomain")
	public Domain getDomain() {
		return this.domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	@Column(name = "name", length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "type", length = 45)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "description", length = 45)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
