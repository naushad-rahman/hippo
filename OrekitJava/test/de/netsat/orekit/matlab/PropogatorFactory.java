package de.netsat.orekit.matlab;

import org.apache.commons.math3.ode.AbstractIntegrator;
import org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator;
import org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator;

public class PropogatorFactory {

	private String type;
	private int maxStep;
	private int minStep;
	private int duration;
	private int stepSize;
	private double positionTolerance;
	private AbstractIntegrator integrator;
	private String integratorType;

	/**
	 * Propagator's Constructor Method
	 * 
	 * @param type
	 * @param integratorType
	 */
	public PropogatorFactory(String type, String integratorType) {
		this.type = type;
		this.integratorType = integratorType;
	}

	/**
	 * Propagator's Constructor Method without integrator.
	 */
	public PropogatorFactory(String type) {
		this(type, null);
	}

	/**
	 * Sets the adaptive integrator for the Propagator
	 * 
	 * @param integrator
	 */
	public void setAdaptiveStepIntegrator(AdaptiveStepsizeIntegrator integrator) {
		this.integrator = integrator;
	}

	/**
	 * Sets the RungeKutta integrator for the Propagator
	 * 
	 * @param integrator
	 */
	public void setRungeKuttaIntegrator(RungeKuttaIntegrator integrator) {
		this.integrator = integrator;
	}

	/**
	 * Sets the duration of the propagation in seconds.
	 * 
	 * @param duration
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * Sets the maximum step size in seconds
	 * 
	 * @param maxSteps
	 */
	public void setMaxStep(int maxStep) {
		this.maxStep = maxStep;
	}

	/**
	 * Sets the minimum step size in seconds
	 * 
	 * @param minStep
	 */
	public void setMinStep(int minStep) {
		this.minStep = minStep;
	}

	/**
	 * Sets the step size of the integrator.
	 */
	public void setStepSize(int stepSize) {
		this.stepSize = stepSize;
	}

	/**
	 * Sets the position tolerance for the propagator integrator.
	 * 
	 * @param positionTolerance
	 */
	public void setPositionTolerance(double positionTolerance) {
		this.positionTolerance = positionTolerance;
	}

	/**
	 * Sets the type of the propagator can be numerical or analytical
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Returns the position tolerance of the propagator.
	 * 
	 * @return {@link Double}
	 */
	public double getPositionTolerance() {
		return this.positionTolerance;
	}

	/**
	 * Returns the maximum step size.
	 * 
	 * @return {@link Integer}
	 */
	public int getMaxStep() {
		return this.maxStep;
	}

	/**
	 * Returns the minimum step size.
	 * 
	 * @return {@link Integer}
	 */
	public int getMinStep() {
		return this.minStep;
	}

	/**
	 * Returns the StepSize of the propagator integrator
	 * 
	 * @return {@link Integer}
	 */
	public int getStepSize() {
		return this.stepSize;
	}

	/**
	 * Returns the duration of the whole simulation in seconds
	 * 
	 * @return {@link Integer}
	 */
	public int getDuration() {
		return this.duration;
	}

	/**
	 * Returns the type of propagator.
	 * 
	 * @return
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Returns the integrator
	 */
	public AbstractIntegrator getIntegrator() {
		return this.integrator;
	}

	/**
	 * Sets the integrator Type of the propagator (used only with numerical
	 * intergrator)
	 * 
	 * @param integratorType
	 */
	public void SetIntegratorType(String integratorType) {
		this.integratorType = integratorType;
	}

	/**
	 * Returns the integrator type of the propagator
	 */
	public String getIntegratorType() {
		return this.integratorType;
	}

	/**
	 * Sets the integrator with the given parameter.
	 */
	public void SetIntegrator() {
	}
}
