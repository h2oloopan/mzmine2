/*
 * Copyright 2006-2007 The MZmine Development Team
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

package net.sf.mzmine.modules.identification.qbixlipiddb;

import java.util.logging.Logger;

import net.sf.mzmine.data.CompoundIdentity;
import net.sf.mzmine.data.PeakList;
import net.sf.mzmine.data.PeakListRow;
import net.sf.mzmine.taskcontrol.Task;

/**
 * 
 */
class QBIXLipidDBSearchTask implements Task {

	private Logger logger = Logger.getLogger(this.getClass().getName());

	private PeakList peakList;
	private QBIXLipidDBSearchParameters parameters;

	private TaskStatus status;
	private String errorMessage;
	private String[][] databaseValues;
	private int processedRows = 0;
	private int totalRows = 0;

	QBIXLipidDBSearchTask(PeakList peakList,
			QBIXLipidDBSearchParameters parameters) {
		status = TaskStatus.WAITING;
		this.peakList = peakList;
		this.parameters = parameters;
	}

	/**
	 * @see net.sf.mzmine.taskcontrol.Task#cancel()
	 */
	public void cancel() {
		status = TaskStatus.CANCELED;
	}

	/**
	 * @see net.sf.mzmine.taskcontrol.Task#getErrorMessage()
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @see net.sf.mzmine.taskcontrol.Task#getFinishedPercentage()
	 */
	public float getFinishedPercentage() {
		if (totalRows == 0)
			return 0;
		return ((float) processedRows) / (float) totalRows;
	}

	/**
	 * @see net.sf.mzmine.taskcontrol.Task#getStatus()
	 */
	public TaskStatus getStatus() {
		return status;
	}

	/**
	 * @see net.sf.mzmine.taskcontrol.Task#getTaskDescription()
	 */
	public String getTaskDescription() {
		return "Peak identification of " + peakList
				+ " using QBIX internal lipid database.";
	}

	/**
	 * @see java.lang.Runnable#run()
	 */
	public void run() {

		status = TaskStatus.PROCESSING;

		logger.info("Running " + getTaskDescription());

		QBIXLipidDBUtils queryUtil = new QBIXLipidDBUtils(parameters);

		QBIXLipidDBConnection dbConnection = new QBIXLipidDBConnection(
				parameters);

		if (!dbConnection.openConnection()) {
			logger.severe("Could not open database connection");
			errorMessage = "Could not open database connection";
			status = TaskStatus.ERROR;
		}

		totalRows = peakList.getRows().length;
		processedRows = 0;
		for (PeakListRow peakRow : peakList.getRows()) {

			QBIXLipidDBQuery[] queries = queryUtil.createQueries(peakRow);

			for (QBIXLipidDBQuery query : queries) {
				CompoundIdentity[] foundCommonLipidIdentities = dbConnection
						.runQueryOnCommonLipids(query);

				int validCommonLipidsCount = 0;
				for (CompoundIdentity identity : foundCommonLipidIdentities)
					if (queryUtil.validateCommonLipidIdentity(query, identity)) {
						peakRow.addCompoundIdentity(identity);
						validCommonLipidsCount++;
					}

				CompoundIdentity[] foundTheoreticalLipidIdentities = dbConnection
						.runQueryOnTheoreticalLipids(query);

				int validTheoreticalLipidsCount = 0;
				for (CompoundIdentity identity : foundTheoreticalLipidIdentities)
					if (queryUtil.validateTheoreticalLipidIdentity(query,
							identity)) {
						peakRow.addCompoundIdentity(identity);
						validTheoreticalLipidsCount++;
					}

				logger.finest("Common lipids, found "
						+ foundCommonLipidIdentities.length + " validated "
						+ validCommonLipidsCount
						+ ", Theoretical lipids, found "
						+ foundTheoreticalLipidIdentities.length
						+ ", validated " + validTheoreticalLipidsCount);

			}

			logger.finest("Created " + queries.length + " and added "
					+ peakRow.getCompoundIdentities().length
					+ " identities for row " + peakRow.getAverageMZ() + ", "
					+ peakRow.getAverageRT());

			processedRows++;

		}

		dbConnection.closeConnection();

		logger.info("Finished " + getTaskDescription());

		status = TaskStatus.FINISHED;

	}

}
