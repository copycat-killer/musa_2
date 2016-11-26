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
 * FunctionalReq generated by hbm2java
 */
@Entity
@Table(name = "functional_req", catalog = "musa_db")
public class FunctionalReq implements java.io.Serializable {

	private Integer idFunctionalReq;
	private Process process;
	private Specification specification;
	private String name;
	private String type;
	private String description;
	private String currentState;
	private String triggerCondition;
	private String finalState;

	public FunctionalReq() {
	}

	public FunctionalReq(Process process, Specification specification,
			String name, String type, String description, String currentState,
			String triggerCondition, String finalState) {
		this.process = process;
		this.specification = specification;
		this.name = name;
		this.type = type;
		this.description = description;
		this.currentState = currentState;
		this.triggerCondition = triggerCondition;
		this.finalState = finalState;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idFunctional_Req", unique = true, nullable = false)
	public Integer getIdFunctionalReq() {
		return this.idFunctionalReq;
	}

	public void setIdFunctionalReq(Integer idFunctionalReq) {
		this.idFunctionalReq = idFunctionalReq;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idWF")
	public Process getProcess() {
		return this.process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idSpecification")
	public Specification getSpecification() {
		return this.specification;
	}

	public void setSpecification(Specification specification) {
		this.specification = specification;
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

	@Column(name = "current_state", length = 45)
	public String getCurrentState() {
		return this.currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	@Column(name = "trigger_condition", length = 45)
	public String getTriggerCondition() {
		return this.triggerCondition;
	}

	public void setTriggerCondition(String triggerCondition) {
		this.triggerCondition = triggerCondition;
	}

	@Column(name = "final_state", length = 45)
	public String getFinalState() {
		return this.finalState;
	}

	public void setFinalState(String finalState) {
		this.finalState = finalState;
	}

}
