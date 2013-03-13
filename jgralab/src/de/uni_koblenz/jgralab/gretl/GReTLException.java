/*
 * JGraLab - The Java graph laboratory
 * (c) 2006-2008 Institute for Software Technology
 *               University of Koblenz-Landau, Germany
 *
 *               ist@uni-koblenz.de
 *
 * Please report bugs to http://serres.uni-koblenz.de/bugzilla
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package de.uni_koblenz.jgralab.gretl;

/**
 * @author Tassilo Horn <horn@uni-koblenz.de>
 * 
 */
public class GReTLException extends RuntimeException {

	private static final long serialVersionUID = 7303598682115721124L;

	public GReTLException(String msg) {
		super(msg);
	}

	public GReTLException(String msg, Exception cause) {
		super(msg, cause);
	}

	public GReTLException(Context c, String msg) {
		this("In phase " + c.getPhase() + ": " + msg);
	}

	public GReTLException(Context c, String msg, Exception cause) {
		this("In phase " + c.getPhase() + ": " + msg, cause);
	}

}
