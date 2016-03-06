package de.netsat.orekit.matlab;

import org.orekit.bodies.OneAxisEllipsoid;
import org.orekit.errors.OrekitException;
import org.orekit.forces.ForceModel;
import org.orekit.forces.SphericalSpacecraft;
import org.orekit.forces.drag.DragForce;
import org.orekit.forces.drag.HarrisPriester;
import org.orekit.forces.gravity.HolmesFeatherstoneAttractionModel;

public class NetSatForceModelFactory {

	private final ConstantValues constants;
	private final ForceModel holmesFeatherstone;
	private final ForceModel dragForce;

	/**
	 * Initiates the needed force models
	 * 
	 * @throws OrekitException
	 * 
	 */
	public NetSatForceModelFactory(final ConstantValues constants) throws OrekitException {
		this.constants = constants;
		this.dragForce = new DragForce(
				new HarrisPriester(this.constants.getSun(), new OneAxisEllipsoid(this.constants.getEarthRadius(),
						this.constants.getEarthFlattening(), this.constants.getEci())),
				new SphericalSpacecraft(0.01, 2.2, 0, 0));

		this.holmesFeatherstone = new HolmesFeatherstoneAttractionModel(this.constants.getEci(),
				this.constants.getGravityProvider());

	}

	/**
	 * Returns the drag ForceModel
	 * 
	 * @return
	 */
	public final ForceModel getDrag() {
		return this.dragForce;
	}

	/**
	 * Returns the Holmes Featherstone Gravity Model.
	 * 
	 * @return
	 */
	public ForceModel getHolmesFeatherstone() {
		return this.holmesFeatherstone;
	}

}
