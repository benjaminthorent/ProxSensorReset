package com.abfactory.proxsensorreset.datamodel;

import android.os.Parcel;
import android.os.Parcelable;

public class CalibrationProcedureData implements Parcelable {

	// Tag to pass the object via intent
	public static final String CALIBRATION_PROCEDURE_DATA = "CALIBRATION_PROCEDURE_DATA";

	// Calibration data
	private double hiddenSensorValue = 0;
	private double freeSensorValue = 0;

	/**
	 * Constructors
	 */
	
	public CalibrationProcedureData() {}
	
	public CalibrationProcedureData(Parcel in) {
	    this.hiddenSensorValue = in.readDouble();
	    this.freeSensorValue = in.readDouble();
	}
	
	/**
	 * Parcelable methods
	 */
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeDouble(hiddenSensorValue);
		dest.writeDouble(freeSensorValue);
	}

	public void readFromParcel(Parcel in) {
		hiddenSensorValue = in.readDouble();
		freeSensorValue = in.readDouble();

	}
	
	@SuppressWarnings("rawtypes")
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
	    public CalibrationProcedureData createFromParcel(Parcel in) {
	        return new CalibrationProcedureData(in);
	    }

	    public CalibrationProcedureData[] newArray(int size) {
	        return new CalibrationProcedureData[size];
	    }
	};
	
	/**
	 * Getter and setter for calibration data when proximity sensor is hidden
	 */

	public double getHiddenSensorValue() {
		return hiddenSensorValue;
	}

	public void setHiddenSensorValue(double hiddenSensorValue) {
		this.hiddenSensorValue = hiddenSensorValue;
	}

	/**
	 * Getter and setter for calibration data when proximity sensor is *NOT* hidden
	 */

	public double getFreeSensorValue() {
		return freeSensorValue;
	}

	public void setFreeSensorValue(double freeSensorValue) {
		this.freeSensorValue = freeSensorValue;
	}

}
