/*******************************************************************************
 * Copyright (c) 2010, Petr Kalafatic (gemini@kalafatic.eu).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU GPL Version 3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.txt
 *
 * Contributors:
 *     Petr Kalafatic - initial API and implementation
 ******************************************************************************/
package eu.kalafatic.protocols.interfaces;

import java.util.Map;

/**
 * The Interface interface ICoder.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public interface ICoder {

	/**
	 * To map.
	 * @param parameters the parameters
	 * @return the map
	 */
	@SuppressWarnings("rawtypes")
	Map toMap(Object... parameters);
}
