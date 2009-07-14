/*
 *  This file is part of MP.
 *
 *  MP is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  MP is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with MP.  If not, see <http://www.gnu.org/licenses/>.
 */
package mpv5.db.common;

import mpv5.globals.Messages;

/**
 * Thrown if no matching data was found, the resultset is empty
 *  
 */
public class NodataFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    public NodataFoundException() {
        super(Messages.NO_DATA_FOUND.toString());
    }

    /**
     * 
     * @param context
     */
    public NodataFoundException(Context context) {
        super(Messages.NO_DATA_FOUND.toString() + " in Context: " +  context);
    }
}
