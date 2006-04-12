/*
 * Copyright 2006 Okinawa Institute of Science and Technology
 * 
 * This file is part of MZmine.
 * 
 * MZmine is free software; you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * 
 * MZmine is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * MZmine; if not, write to the Free Software Foundation, Inc., 51 Franklin St,
 * Fifth Floor, Boston, MA 02110-1301 USA
 */

/**
 * 
 */
package net.sf.mzmine.io.mzxml;

import net.sf.mzmine.io.RawDataFile;
import net.sf.mzmine.io.Scan;

/**
 * 
 */
class MZXMLScan implements Scan {

    private double MZValues[], intensityValues[];

    /**
     * 
     */
    public MZXMLScan(MZXMLFile rawDataFile, int scanNumber) {
        
    }

    /**
     * @return Returns the intensityValues.
     */
    public double[] getIntensityValues() {
        return intensityValues;
    }

    /**
     * @return Returns the mZValues.
     */
    public double[] getMZValues() {
        return MZValues;
    }

    /**
     * @see net.sf.mzmine.io.Scan#getNumberOfDataPoints()
     */
    public int getNumberOfDataPoints() {
        return MZValues.length;
    }

    /**
     * @see net.sf.mzmine.io.Scan#getRawData()
     */
    public RawDataFile getRawData() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see net.sf.mzmine.io.Scan#getScanNumber()
     */
    public int getScanNumber() {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * @see net.sf.mzmine.io.Scan#getMSLevel()
     */
    public int getMSLevel() {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * @see net.sf.mzmine.io.Scan#getPrecursorMZ()
     */
    public double getPrecursorMZ() {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * @see net.sf.mzmine.io.Scan#getPrecursorRT()
     */
    public double getPrecursorRT() {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * @see net.sf.mzmine.io.Scan#getPrecursorScanNumber()
     */
    public int getPrecursorScanNumber() {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * @see net.sf.mzmine.io.Scan#getScanAcquisitionTime()
     */
    public double getScanAcquisitionTime() {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * @see net.sf.mzmine.io.Scan#getScanDuration()
     */
    public double getScanDuration() {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * @see net.sf.mzmine.io.Scan#getMZRangeMin()
     */
    public double getMZRangeMin() {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * @see net.sf.mzmine.io.Scan#getMZRangeMax()
     */
    public double getMZRangeMax() {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * @see net.sf.mzmine.io.Scan#getBasePeakMZ()
     */
    public double getBasePeakMZ() {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * @see net.sf.mzmine.io.Scan#getBasePeakIntensity()
     */
    public double getBasePeakIntensity() {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * @see net.sf.mzmine.io.Scan#getTotalIonCurrent()
     */
    public double getTotalIonCurrent() {
        // TODO Auto-generated method stub
        return 0;
    }

}
