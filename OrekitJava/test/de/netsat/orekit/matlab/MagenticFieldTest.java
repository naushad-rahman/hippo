package de.netsat.orekit.matlab;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.orekit.attitudes.Attitude;
import org.orekit.errors.OrekitException;
import org.orekit.frames.FramesFactory;
import org.orekit.orbits.KeplerianOrbit;

import de.netsat.orekit.NetSatConfiguration;
import de.netsat.orekit.matlab.loadScripts;
import de.netsat.orekit.matlab.MatlabPushHandler;
import de.netsat.orekit.matlab.EventCalculator;

import org.orekit.propagation.SpacecraftState;
import org.orekit.propagation.numerical.NumericalPropagator;
import org.orekit.utils.PVCoordinates;
import org.orekit.utils.TimeStampedAngularCoordinates;

import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;

public class MagenticFieldTest {

	/**
	 * 
	 * @param mi
	 * @return
	 * @throws MatlabInvocationException
	 * @throws OrekitException
	 */

	public static SpacecraftState runNumericalPropagatorlocal(MatlabInterface mi, double mu)
			throws MatlabInvocationException, OrekitException

	{
		int sat_nr = 1;
		Object[] returningObject;
		SensorDataType[] options = { SensorDataType.ORBITAL_ELEMENTS, SensorDataType.TIMESTAMP, };
		MatlabFunctionType[] matlabFunctions = { MatlabFunctionType.Plot };
		PropagatorDataType np = PropagatorDataType.NUMERICAL_KEPLERIAN_RUNGEKUTTA;
		MatlabPushHandler mph = new MatlabPushHandler(mi, options, matlabFunctions, true);
		mph.setVariableInMatlab("mu", mu);
		returningObject = mi.returningEval("setNumericalPropagatorSettings()", 5);
		KeplerianOrbit keplerOrbit = loadScripts.getKeplerOrbit(mi, sat_nr);
		double positionTolerance = ((double[]) returningObject[0])[0];
		double minStep = ((double[]) returningObject[1])[0];
		double maxstep = ((double[]) returningObject[2])[0];
		double duration = ((double[]) returningObject[3])[0];
		double outputStepSize = ((double[]) returningObject[4])[0];
		NetsatPropagatorFactory NumericalPropagatorFactory = new NetsatPropagatorFactory(np, maxstep, minStep, duration,
				outputStepSize, positionTolerance, keplerOrbit);
		NumericalPropagator numericPropagator = NumericalPropagatorFactory.getNumericalPropagator();
		SpacecraftState initialState = new SpacecraftState(keplerOrbit,
				new Attitude(FramesFactory.getEME2000(),
						new TimeStampedAngularCoordinates(keplerOrbit.getDate(),
								new PVCoordinates(new Vector3D(10, 10), new Vector3D(1, 2)),
								new PVCoordinates(new Vector3D(15, 3), new Vector3D(1, 2)))),
				1.0);

		EventCalculator evenetCal = new EventCalculator();
		numericPropagator.addEventDetector(evenetCal.getEclipseEventDetecor());
		numericPropagator.setInitialState(initialState);
		numericPropagator.setMasterMode(outputStepSize, mph);
		SpacecraftState finalState = numericPropagator.propagate(keplerOrbit.getDate().shiftedBy(duration));
		return finalState;

	}

	/**
	 * Set the variable in Matl ab (the variable should be the type double or
	 * could be casted to double.
	 * 
	 * @param mi
	 * @param name
	 * @param value
	 * @throws MatlabInvocationException
	 */
	public static void setVariableInMatlab(MatlabInterface mi, String name, double value)
			throws MatlabInvocationException {

		mi.getProxy().setVariable(name, value);
	}

	public static void main(String[] args)
			throws OrekitException, MatlabConnectionException, MatlabInvocationException {
		// Object[] obj = null;
		NetSatConfiguration.init();
		MatlabInterface mi;
		ConstantValues constants = new ConstantValues();
		mi = new MatlabInterface(MatlabInterface.MATLAB_PATH, null);
		runNumericalPropagatorlocal(mi, constants.getMu());

		// System.out.println(((double[]) obj[1])[0]);
	}

}
