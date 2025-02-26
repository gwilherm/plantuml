/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2023, Arnaud Roques
 *
 * Project Info:  http://plantuml.com
 * 
 * If you like this project or if you find it useful, you can support us at:
 * 
 * http://plantuml.com/patreon (only 1$ per month!)
 * http://plantuml.com/paypal
 * 
 * This file is part of PlantUML.
 *
 * PlantUML is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PlantUML distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 *
 * Original Author:  Arnaud Roques
 *
 *
 */
package net.sourceforge.plantuml.style;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.cucadiagram.Stereostyles;
import net.sourceforge.plantuml.cucadiagram.Stereotype;

public class StyleSignatures implements StyleSignature {

	private final List<StyleSignature> all = new ArrayList<StyleSignature>();

	public void add(StyleSignature signature) {
		all.add(signature);

	}

	@Override
	public String toString() {
		return all.toString();
	}

	@Override
	public Style getMergedStyle(StyleBuilder currentStyleBuilder) {
		if (all.size() == 0)
			throw new UnsupportedOperationException();
		Style result = null;
		for (StyleSignature basic : all) {
			final Style tmp = basic.getMergedStyle(currentStyleBuilder);
			if (result == null)
				result = tmp;
			else
				result = result.mergeWith(tmp, MergeStrategy.KEEP_EXISTING_VALUE_OF_STEREOTYPE);
		}
		return result;

	}

	@Override
	public StyleSignature withTOBECHANGED(Stereotype stereotype) {
		if (all.size() == 0)
			throw new UnsupportedOperationException();
		throw new UnsupportedOperationException();
	}

	@Override
	public StyleSignature with(Stereostyles stereostyles) {
		if (all.size() == 0)
			throw new UnsupportedOperationException();
		final StyleSignatures result = new StyleSignatures();
		for (StyleSignature basic : all)
			result.add(basic.with(stereostyles));

		return result;
	}

}