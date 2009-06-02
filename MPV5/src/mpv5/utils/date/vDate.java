/*
 * 
This file is part of MP.

MP is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

MP is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with MP.  If not, see <http://www.gnu.org/licenses/>.

 * 
 */
package mpv5.utils.date;


import java.util.Date;

/**
 *
 *  anti43
 */
public class vDate {

    public boolean isVerified = false;
    public String defDate = null;
    public String sqlDate = null;
    public Date date = null;
    public String ovalue = "";

    public vDate(String date) {

        ovalue = date;

        try {
            this.date = DateConverter.getDate(date);
            defDate = DateConverter.getDefDateString(this.date);
            sqlDate = DateConverter.getSQLDateString(this.date);

            if (this.date != null && defDate != null && sqlDate != null) {
                this.isVerified = true;
            }
        } catch (Exception e) {
            this.isVerified = false;
        }

    }
}
