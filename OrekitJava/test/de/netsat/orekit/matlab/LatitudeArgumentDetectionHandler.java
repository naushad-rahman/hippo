package de.netsat.orekit.matlab;

import org.orekit.errors.OrekitException;
import org.orekit.propagation.SpacecraftState;
import org.orekit.propagation.events.handlers.EventHandler;
import org.orekit.time.AbsoluteDate;

public class LatitudeArgumentDetectionHandler implements EventHandler<NetSatLatitudeArgumentDetector> {

	private AbsoluteDate latitudeArgumentEventDate;
	private final double angle;

	public LatitudeArgumentDetectionHandler(final double angle) {
		this.angle = angle;
	}

	@Override
	public Action eventOccurred(SpacecraftState s, NetSatLatitudeArgumentDetector detector, boolean increasing)
			throws OrekitException {
		this.latitudeArgumentEventDate = new AbsoluteDate();
		System.out.println(this.angle);
		System.out.println(s.getDate());
		System.out.println("Argument of Latitude crosssing detected!");
		this.latitudeArgumentEventDate = s.getDate();
		return Action.CONTINUE;
	}

	@Override
	public SpacecraftState resetState(NetSatLatitudeArgumentDetector detector, SpacecraftState oldState)
			throws OrekitException {
		return oldState;
	}

	/**
	 * Returns the date object when this event happened.
	 * 
	 * @return {@link AbsoluteDate}
	 */
	public AbsoluteDate getLatitudeArgumentEventDate() {
		return this.latitudeArgumentEventDate;
	}

	/**
	 * Reset the value to 0 after the event occurred.
	 * 
	 * @param date
	 */
	public void setLatitudeArgumentEventDate(AbsoluteDate date) {
		this.latitudeArgumentEventDate = date;
	}

}