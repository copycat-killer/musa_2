package layer.database.entity;

import static javax.persistence.GenerationType.IDENTITY;

// Generated 22-nov-2016 14.40.56 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * Variable generated by hbm2java.
 */
@Entity
@Table(name = "variable", catalog = "musa_db")
public class Variable implements java.io.Serializable {

	private static final long serialVersionUID = 863012542133378528L;

	/** The id variable. */
	private Integer idVariable;

	/** The name. */
	private String name;

	/** The value. */
	private String value;

	/** The id case. */
	private Integer idCase;

	/**
	 * Instantiates a new variable.
	 */
	public Variable() {
	}

	/**
	 * Instantiates a new variable.
	 *
	 * @param name the name
	 * @param value the value
	 * @param idCase the id case
	 */
	public Variable(String name, String value, Integer idCase) {
		this.name = name;
		this.value = value;
		this.idCase = idCase;
	}

	/**
	 * Gets the id variable.
	 *
	 * @return the id variable
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idVariable", unique = true, nullable = false)
	public Integer getIdVariable() {
		return this.idVariable;
	}

	/**
	 * Sets the id variable.
	 *
	 * @param idVariable the new id variable
	 */
	public void setIdVariable(Integer idVariable) {
		this.idVariable = idVariable;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	@Column(name = "name", length = 45)
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	@Column(name = "value", length = 45)
	public String getValue() {
		return this.value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Gets the id case.
	 *
	 * @return the id case
	 */
	@Column(name = "idCase")
	public Integer getIdCase() {
		return this.idCase;
	}

	/**
	 * Sets the id case.
	 *
	 * @param idCase the new id case
	 */
	public void setIdCase(Integer idCase) {
		this.idCase = idCase;
	}

}
